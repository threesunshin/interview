/*
 * @lc app=leetcode id=530 lang=java
 *
 * [530] Minimum Absolute Difference in BST
 *
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
 *
 * algorithms
 * Easy (49.70%)
 * Total Accepted:    58.2K
 * Total Submissions: 115.6K
 * Testcase Example:  '[1,null,3,2]'
 *
 * Given a binary search tree with non-negative values, find the minimum
 * absolute difference between values of any two nodes.
 * 
 * Example:
 * 
 * 
 * Input:
 * 
 * ⁠  1
 * ⁠   \
 * ⁠    3
 * ⁠   /
 * ⁠  2
 * 
 * Output:
 * 1
 * 
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and
 * 1 (or between 2 and 3).
 * 
 * 
 * 
 * 
 * Note: There are at least two nodes in this BST.
 * 解题思路：BST的左<根<右的特性，中序遍历
 */




class Solution {
    int res = Integer.MAX_VALUE, pre=-1;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return res;

    }
    public void inOrder(TreeNode root){
        if( root == null)
            return;
        inOrder(root.left);

        if( pre != -1)
            res = (res>(root.val-pre))?(root.val-pre):res;

        pre = root.val;
        inOrder(root.right);
    }
}
