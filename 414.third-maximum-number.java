
import static java.lang.Integer.MIN_VALUE;

/*
 * @lc app=leetcode id=414 lang=java
 *
 * [414] Third Maximum Number
 *
 * https://leetcode.com/problems/third-maximum-number/description/
 *
 * algorithms
 * Easy (28.61%)
 * Total Accepted:    90.9K
 * Total Submissions: 314.2K
 * Testcase Example:  '[3,2,1]'
 *
 * Given a non-empty array of integers, return the third maximum number in this
 * array. If it does not exist, return the maximum number. The time complexity
 * must be in O(n).
 * 
 * Example 1:
 * 
 * Input: [3, 2, 1]
 * 
 * Output: 1
 * 
 * Explanation: The third maximum is 1.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1, 2]
 * 
 * Output: 2
 * 
 * Explanation: The third maximum does not exist, so the maximum (2) is
 * returned instead.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: [2, 2, 3, 1]
 * 
 * Output: 1
 * 
 * Explanation: Note that the third maximum here means the third maximum
 * distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 * 
 * 解题思路：用三个整数保存第一大、第二大、第三大，因为重复的要剔除，比较的时候要注意不能取等于的情况
 */
class Solution {
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for(int num:nums){
            if( num > first) {
                third = second;
                second = first;
                first = num;
            }
            else if( num < first && num>second){
                third = second;
                second = num;
            }
            else if( num < second && num > third){
                third = num;
            }
        }
        if( third == Long.MIN_VALUE)
            return (int)first;
        else
            return (int)third;
    }


}
