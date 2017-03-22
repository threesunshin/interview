#!usr/bin/env python
# -*- coding: utf8 -*-
"""
this file contains some sort method, including quickSort, binarySort etc.
"""
def judgecircle():
    """
    判断链表有环，设置两个指针，一个走的慢，一个走的快，快的能追上慢的，就是有环
    """
    pass



arr = [[1,4,7,10,15], [2,5,8,12,19], [3,6,9,16,22], [10,13,14,17,24], [18,21,23,26,30]] 
def getNum(num, data=None):
    """
    在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数;
    思路：先跟每行的最后一位元素比较，如果比num小，就删除整行；如果比num大，就删除这列
    """
    while data:
        if num > data[0][-1]:
            del data[0] 
            getNum(num, data) 
        elif num < data[0][-1]: 
            data = list(zip(*data)) 
            del data[-1] 
            data = list(zip(*data)) 
            getNum(num, data) 
        else: 
            print 'go there'
            return True 
            
    return False

def maxCommon(a, b):
    """
    最大公约数
    """
    while b: a,b = b, a%b ##辗转相除法
    return a

def minCommon(a, b): 
    """
    最小公倍数，先求出最大公约数，再用两者积除以最大公约数
    """
    c = a*b 
    while b: 
        a,b = b, a%b 
        return c//a 

def median(data): 
    """
    求中位数
    """
    data.sort() 
    half = len(data) // 2 
    return (data[half] + data[~half])/2 



def Fibonacci(num):
    n, a, b = 0, 0, 1
    while n < num:
        yield b
        a, b = b, a+b
        n += 1

def getOneCount(num):
    """
    输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示
    """
    count  = 0
    while num != 0:
        num = num & (num-1)
        count += 1
    
    return count
    
print getOneCount(2)
print getOneCount(6)
print getOneCount(8)



def switch(list1, list2):
    """
    有两个序列a,b，大小都为n,序列元素的值任意整形数，无序；
    要求：通过交换a,b中的元素，使[序列a元素的和]与[序列b元素的和]之间的差最小。
    思路：

1. 分别计算a,b序列的和；
2. 求a序列和与b序列和的差值的一半，记为half；
3. 在和值大的序列中找出一个与和值小的序列中的元素max的差值最接近half的元素，记为min；
4. 将max与min互换即可。
    """
    pass

def balancenum(list):
    """
    平衡点问题 
    平衡点：比如int[] numbers = {1,3,5,7,8,25,4,20}; 25前面的总和为24，25后面的总和也是24，25这个点就是平衡点；假如一个数组中的元素，其前面的部分等于后面的部分，那么这个点的位序就是平衡点 
    要求：返回任何一个平衡点
    test cases: [],[1],[1,1],[1,2,1],[1,3,3],[1,2,3,4,1,5]
    """
    totalsum = reduce((lambda x, y: x+y), list)
    i, subsum = 1, 0
    while i < len(list)-1:
        subsum += list[i-1]
        needsum = (totalsum - list[i])/2
        if subsum == needsum:
            return (i, list[i])
        i += 1
    return None



def control_point(list):
    """
    支配点问题： 
    支配数：数组中某个元素出现的次数大于数组总数的一半时就成为支配数，其所在位序成为支配点；比如int[] a = {3,3,1,2,3};3为支配数，0，1，4分别为支配点； 
    要求：返回任何一个支配点
    test cases: [],[1],[1,2],[2,2,3],[1,2,3,3,3]
    """
    assert list is not None
    mid = len(list)/2
    for value in list:
        count, mark, i = 0, 0, 0
        
        while i < len(list):
            if list[i] == value:
                count += 1
                mark = i
            i += 1
            if count > mid:
                return (mark, list[mark])
    
    return -1


