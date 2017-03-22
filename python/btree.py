#!usr/bin/env python
# -*- coding: utf-8 -*-
"""
二叉树，先序、中序，后序遍历
"""
class Node(object):
    def __init__(self, data=0, left=0, right=0):
        self.data = data
        self.left = left
        self.right = right

class BTree(object):
    def __init__(self, root=0):
        self.root = root

    def preOrder(self, node):
        if node is 0:            
            return
        print node.data        
        self.preOrder(node.left)
        self.preOrder(node.right)

    def inOrder(self, node):
        if node is 0:
            return
        self.inOrder(node.left)
        print node.data
        self.inOrder(node.right)

    def postOrder(self, node):
        if node is 0:
            return
        self.postOrder(node.left)
        self.postOrder(node.right)
        print node.data

n1 = Node(0)
n2 = Node(1)
n3 = Node(2,n1,0)
n4 = Node(3,n2,n3)

bt = BTree(n4)
print "in order:"
print bt.inOrder(bt.root)
print "post order"
print bt.postOrder(bt.root)