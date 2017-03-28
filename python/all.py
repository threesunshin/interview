#!usr/bin/env python
# -*- coding: utf8 -*-


arr = [[1,4,7,10,15], [2,5,8,12,19], [3,6,9,16,22], [10,13,14,17,24], [18,21,23,26,30]] 
def get_num(num, data=None):
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

def max_common(a, b):
    """
    最大公约数
    """
    while b: a,b = b, a%b ##辗转相除法
    return a

def min_common(a, b): 
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

def get_one_count(num):
    """
    输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示
    """
    count  = 0
    while num != 0:
        num = num & (num-1)
        count += 1
    
    return count
 

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

def char2int(s):
    return {'0':0, '1':1, '2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9}[s]

def mulit(x, y):
    return 10*x + y

def str_to_int(str):
    """
    字符串转化为数字，要考虑负数，小数的情况
    test case: "", "1we2",".","-","12","-12","12.5","12.3e","01",None,"999999999999"
    """
    import re
    import sys
    if re.match("^\-{0,1}\d+\.{0,1}\d+$", str) is  None:
        print "the input string is not number."
        return None

    flag = True
    result = 0
    before = 0
    
    i = 0
    if str[0] == '-':
        flag = False
        i = 1
    while i < len(str) and result < sys.maxsize:
        
        if str[i] == ".":
            i += 1
            before = result
            result = 0
            break
        result *= 10
        result += char2int(str[i])
        i += 1
    j = len(str)-1
    while j >= i:
        result +=  char2int(str[j])
        result *= 0.1
        j -= 1

    if flag is False:
        return 0-(before + result)
    else:
        return before + result

def str2int(str):
    """
    simple version
    """
    import re
    import sys
    if re.match("^\-{0,1}\d+\.{0,1}\d+$", str) is  None:
        print "the input string is not number."
        return None

    flag = True
    result = 0
   
    if str[0] == '-':
        flag = False
        str = str[1:]
    if str.find("."):
        sub = str.split(".")
        part1 = reduce(mulit, map(char2int, sub[0]))
        part2 = reduce(mulit, map(char2int, sub[1]))*0.1**len(sub[1])
        result = part1 + part2
    else:
        result = reduce(mulit, map(char2int, str))
    
    return result if flag else 0-result

def reverse_order1(str):
    """
    一个字符串，如"This is a test" ,输出"test a is This",要考虑空间
    """
    import re
    strs = re.split("\s+",str)
    return " ".join(strs[::-1])

def reverse_order2(str):
    """
    一个字符串，如"This is a test" ,输出"tset a si siht",要考虑空间
    """
    return str[::-1]

def get_appear_once(str):
    """
    找出字符串中第一个不重复的字符，如‘aabbcdc’ 要返回d
    思路：用256位长的数组保存字符状态，如果首次出现就保存index，如果有重复字符就标记为-2。然后再扫描这个数组，找到最小的值。
    test case: "","a","aabb","a b a"
    """
    import sys
    a = [-1 for x in range(0,256)]
    result = '\0'
    min = sys.maxsize
    for index, char in enumerate(str):
        k = ord(char)
        if a[k] == -1:
            a[k] = index
        elif a[k] > -1:
            a[k] = -2

    for index in range(0,256):
        if a[index] > -1 and min > a[index]:
            min = a[index]
            result = chr(index)

    return result

print get_appear_once("a b a b")


