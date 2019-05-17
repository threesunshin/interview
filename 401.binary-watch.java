import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=401 lang=java
 *
 * [401] Binary Watch
 *
 * https://leetcode.com/problems/binary-watch/description/
 *
 * algorithms
 * Easy (45.03%)
 * Total Accepted:    63.1K
 * Total Submissions: 139.3K
 * Testcase Example:  '0'
 *
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and
 * the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the
 * right.
 * 
 * For example, the above binary watch reads "3:25".
 * 
 * Given a non-negative integer n which represents the number of LEDs that are
 * currently on, return all possible times the watch could represent.
 * 
 * Example:
 * Input: n = 1Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04",
 * "0:08", "0:16", "0:32"]
 * 
 * 
 * Note:
 * 
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid,
 * it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for
 * example "10:2" is not valid, it should be "10:02".
 * 
 * 题意：每个led都是2的进制，表示hours的是4个：8 4 2 1，表示minutes的是6个：32 16 8 4 2 1;入参是亮灯的个数
 *
 * 解题思路：
 * 动态规划。有n个led等，其中亮了k个时，可以有f(n,k)种可能性。如何计算f(n,k)呢？当n=k时，n个led灯，n个都亮，很好理解，就只有1中可能。当n>k时，如果第一个灯亮，那么就需要剩下的n-1个灯里，有k-1个灯亮，也就是f(n-1,k-1)；当第一个灯不亮，那么就是剩下的n-1个灯里，有k个灯亮。就这两种可能。因此，计算f(n,k)的公式为：
f(n,k) = 1　　　　　　　　　　　当n=k时
f(n,k) = f(n-1,k-1) + f(n-1,k)　　　当n>k时
 */
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> list= new ArrayList<>();
        int timecode[] = new int[10];
        dfs(timecode, 0, 0, list, num);
        return list;
    }

    //pos表示目前算到哪个led了，count表示已经亮了几个led了
    public void dfs(int[] timecode, int pos, int count, List<String> list,int num){
        if( count == num ){
            String time = decodeTime(timecode);
            if( time != null){
                list.add(time);

            }
           return;
        }

        if( pos == timecode.length)
            return;
        timecode[pos] = 1;
        dfs(timecode, pos+1, count+1,list,num);
        timecode[pos] = 0;
        dfs(timecode, pos+1, count,list,num);
    }

    public String decodeTime(int[] timecode){
        if( timecode == null || timecode.length == 0)
            return null;
        int hours=0, minutes=0;
        for(int i=0; i<4;i++){
            if( timecode[i] == 1){
                hours += (int)Math.pow(2,i);
            }
        }
        for(int i=4;i<10;i++){
            if( timecode[i] == 1){
                minutes += (int)Math.pow(2,i-4);
            }
        }
        String min = "" + minutes;
        if(minutes < 10)
            min = "0" + min;
        //判断时间的可行性
        if(hours  >= 12  ||  minutes  >=  60)
            return null;
        return hours + ":" + min;
    }

}
