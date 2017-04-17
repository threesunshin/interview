#!usr/bin/env python
# -*- coding: utf-8 -*-
"""
this file contains some sort method, including quickSort, binarySort etc.
"""

def quicksort( list, left, right):
    if left >= right:
        return list
    flag = list[left]
    i, j = left, right

    while i<j:
        while i < j and list[j] >= flag:
            j = j-1
        list[i] = list[j]
        while i < j and list[i] <= flag:
            i = i+1
        list[j] = list[i]
        
    list[i] = flag

    quicksort(list, left, i-1)
    quicksort(list, i+1, right)


def mergesort(lists):
    """
    归并排序
    """
    if(len(lists) <= 1):
        return lists
    middle = len(lists)/2
    left = mergesort( lists[:middle])
    right = mergesort( lists[middle:])
    return merge(left, right)

def merge(left, right):
    r, l = 0, 0
    result = []
    while l < len(left) and r < len(right):
        if left[l] < right[r]:
            result.append(left[l])
            l = l+1
        else:
            result.append(right[r])
            r = r+1
    result += right[r:]
    result += left[l:]
    
    return result


def binarysearch(lists, value):
    """
    二分查找
    """
    if len(list1) <= 0:
        return -1

    low, high = 0, len(lists)-1
    while low <= high:
        mid = (low + high)/2
        if lists[mid] < value:
            low = mid +1
        elif lists[mid] > value:
            high = mid -1
        else:
            return mid
    return -1



list1 = [1,2,3,4,5,6,7]
#quicksort(list1, 0, len(list1)-1)
#print mergesort(list1)
print binarysearch(list1,1)
print binarysearch(list1, 10)


