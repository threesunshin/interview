package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by liyuhua on 13/02/2019.
 * 算法思想：双指针
 */
public class TwoPoint {

    /*
    Leetcode ：167. Two Sum II - Input array is sorted (Easy)
    Input: numbers={2, 7, 11, 15}, target=9
    Output: index1=1, index2=2
    题目描述：在有序数组中找出两个数，使它们的和为 target。
     */
    public void sum_167(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0)
            return;
        if (numbers.length == 1 && numbers[0] == target)
            System.out.println("find:");
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                System.out.println("find:" + numbers[i] + ", " + numbers[j]);
                return;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else
                j--;

        }
        System.out.println("not find:");
    }

    /*
    633. Sum of Square Numbers (Easy)
    题目描述：判断一个数是否为两个数的平方和。
    Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
     */
    public boolean judge_633(int number) {
        int i = 0, j = (int) Math.sqrt(number);
        while (i <= j) {
            int sum = i * i + j * j;
            if (sum == number)
                return true;
            else if (sum < number)
                i++;
            else
                j--;
        }
        return false;
    }

    /*
    反转字符串中的元音字符
    345. Reverse Vowels of a String (Easy)
    Given s = "leetcode", return "leotcede".
     */
    //我的第一版解法，错误
    public String reverse_345(String str) {
        if (str == null || str.length() == 0)
            return str;
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 0, j = str.length() - 1;

        while (i < j) {

            while (!vowels.contains(str.charAt(i)))
                i++;
            while (!vowels.contains(str.charAt(j)))
                j--;
            if (i < j) {
                //交换, 替换string的字符？？

            }
        }
        return null;
    }

    //正解
    public String reverse1(String str) {
        if (str == null || str.length() == 0)
            return str;
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 0, j = str.length() - 1;
        char[] result = new char[str.length()];
        while ( i<j ){
            char first = str.charAt(i);
            char last = str.charAt(j);
            if ( !vowels.contains(first) ) {
                result[i++] = first;
            }
            else if ( !vowels.contains(last) ) {
                result[j--] = last;
            }
            else{
                result[i++] = last;
                result[j--] = first;
            }
        }
        return  new String(result);
    }

    /*
    回文字符串
    680. Valid Palindrome II (Easy)
    Input: "abca"
    Output: True
    Explanation: You could delete the character 'c'.
    题目描述：可以删除一个字符，判断是否能构成回文字符串。
     */
    public boolean palindrome_680(String str){
        if( str == null || str.length() <= 2)
            return true;
        int i=0,j=str.length()-1;
        boolean deleted = false;
        while (i<j){
            char ci = str.charAt(i);
            char cj = str.charAt(j);
            if( ci==cj ){
                i++;
                j--;
            }
            else if( ci != cj && deleted == false){
                if( str.charAt(i+1) == str.charAt(j)){
                    i++;
                    deleted = true;
                }
                else if(str.charAt(i) == str.charAt(j-1)){
                    j--;
                    deleted = true;
                }
                else
                    return false;
            }
            else{
                return false;
            }
        }
        return true;
    }

    //另一个解法, 代码更清晰
    public boolean palindrome1(String str){
        if( str == null || str.length() <= 2)
            return true;
        int i=0,j=str.length()-1;
        while( i++ < j-- ){
            if( str.charAt(i) != str.charAt(j)){
                return isPalindrome(str, i+1, j) || isPalindrome(str, i, j-1);
            }
        }
        return true;
    }

    public boolean isPalindrome(String str, int i, int j){
        while( i<j ){
            if( str.charAt(i++) != str.charAt(j--))
                return false;
        }
        return true;
    }

    /*
    88. Merge Sorted Array (Easy)
    给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

说明:

初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

    Input:
    nums1 = [1,2,3,0,0,0], m = 3
    nums2 = [2,5,6],       n = 3

    Output: [1,2,2,3,5,6]

     */
    public int[] mergeTwoArray(int[] arr1, int m, int[] arr2, int n){
        if( arr2 == null || arr2.length <=0 )
            return arr1;
        int flag = m+n-1;
        int i=m-1, j=n-1;
        while( i>=0 || j >=0){
            if( i<0 )
                arr1[flag--] = arr2[j--];
            else if( j<0 )
                arr1[flag--] = arr1[i--];
            else if( arr1[i] <= arr2[j])
                arr1[flag--] = arr2[j--];
            else
                arr1[flag--] = arr1[i--];
        }
        return arr1;
    }

    /*
    判断链表是否存在环
    141. Linked List Cycle (Easy)
     */

    /*
    最长子序列

    524. Longest Word in Dictionary through Deleting (Medium)
    Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

    Example 1:

Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"
给了我们一个字符串，和一个字典，让我们找到字典中最长的一个单词，这个单词可以通过给定单词通过删除某些字符得到。由于只能删除某些字符，并不能重新排序
解题思路：

     */
    public String findLongestWord(String str, List<String> list){
        if( list == null || list.size() == 0 || str == null || str.length() == 0)
            return null;
//        list.sort(Comparator<String> c {
//
//        });
        int i=0, j=0, k=0;
        while( j < list.size() ){
            String b = list.get(j);
            k=0;i=0;
            for(; i < str.length() && k < b.length() ;){
                if( str.charAt(i) == b.charAt(k)){
                    i++;
                    k++;
                }else{
                    i++;
                }

            }
            if( k == b.length())
                return b;


        }
        return null;
    }
}
