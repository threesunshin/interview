#!usr/bin/env python
# -*- coding: utf8 -*-
"""
this file contains some sort method, including quickSort, binarySort etc.
"""
class Node(object):
    def __init__(self, data=0, next=None):
        self.data = data
        self.next = next

class LinkedList(object):
    def __init__(self):
        self._head = None

    def add(self, item):
        temp = Node(item)
        temp.setNext(self._head)
        self._head = temp
        

    def print_list(self):
        head = self.root
        while head != 0:
            print head.data
            if head.next != 0:
                head = head.next
            else:
                break



    def judge_circle(self, root):
        """
        判断链表有环，设置两个指针，一个走的慢，一个走的快，快的能追上慢的，就是有环
        """
        assert root is not None
        slow, fast = root, root
        while True:
            if slow.next != 0 and fast.next != 0 and fast.next.next != 0:
                slow, fast = slow.next, fast.next.next
            else:
                return False

            if slow == fast:
                return True
        return False


n1 = Node(1)
n2 = Node(2,n1)
n3 = Node(3,n2)
L = LinkedList(n3)
L.print_list()