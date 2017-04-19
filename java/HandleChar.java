/*
some functions for handle string
 */
import java.util.regex.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class HandleChar{
    
    public char getAppearOnce(String str){
        /*
        找出字符串中第一个不重复的字符，如‘aabbcdc’ 要返回d
         */
        int[] arr = new int[256];
        char result = '\0';
        int min = Integer.MAX_VALUE;
        for(int i=0;i<256;i++){
            arr[i] = -1;
        }
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if( arr[ch] == -1){
                arr[ch] = i;
            }
            else if( arr[ch] > -1){
                arr[ch] = -2;
            }
        }

        for(int i=0;i<256;i++){
            if( arr[i]>-1 && min > arr[i]){
                min = arr[i];
                result = (char)i;
            }
        }

        return result;
    }

    public double str2int(String str){
        /*
        字符串转化为数字，要考虑负数，小数的情况
    test case: "", "1we2",".","-","12","-12","12.5","12.3","00.12","+","-","1","1."
         */
        boolean m = Pattern.matches("^[+-]{0,1}\\d+(\\.[\\d]+)?$", str);
        if( !m ){
            System.out.println("the input has wrong format.");
            return 0;
        }
        char[] ch = str.toCharArray();
        double result = 0;
        boolean flag = true;
        int i = 0;
        int j = 0;
        if(ch[0] == '-'){
            flag = false;
            i++;
        }else if( ch[0] == '+'){
            i++;
        }
        for(;i< ch.length;i++){
            if( ch[i] == '.'){
                j = ch.length-i-1;
                i++;
            }

            if( result > Integer.MAX_VALUE){
                System.out.println("big value.");
                break;
            }
            result *= 10;
            result += ch[i]-'0';
        }
        for (i=0;i<j ;i++ ) {
            result = result * 0.1;
        }
        if( !flag)
            result = 0-result;
            
        return result;
    }

    public String reverse_order( String str){
        /*
    一个字符串，如"This is a test" ,输出"test a is This",要考虑空间
       */
      String[] array = str.split("\\s+");
      StringBuilder result = new StringBuilder();
      for( int j = array.length-1; j>=0; j--){
        result.append(array[j]);
        result.append(" ");
      }
      return result.substring(0,result.length()-1);
      
    }

    public boolean judgeNum(int number){
        /*
        1^3 + 5^3 + 3^3 = 153 , 所以153被称为水仙花数，现在输入为 100 < N < 999 , 请实现一个函数，判断N是否是水仙花数
         */
        if( number <= 100 || number >= 999){
            System.out.println("the number must less than 999 and more than 100.");
            return false;
        }
        int[] array = apartNum(number);
        int result = 0;
        for(int i:array)
            result += i*i*i;
        if( result == number)
            return true;
        else
            return false;
    }

    public int[] apartNum(int number){
        int[] result = new int[3];
     
        int i = 0;
        while( number > 0){
        
            result[i] = number%10;
            number = number/10;
            i++;
        }
        return result;
    }

    public String removeChar(String str){
        /*
        去除字符串中所有 XYX 模式的子串， 比如 ABCBCAE, 最后的输出为 E ， 都有什么实现方法，各有什么优缺点，有没有O(n)的方法
         */
        if( str == null || str.length() <=2)
            return str;
        for(int i=0; i+2<str.length();i++){
            if( str.length() <= 2)
                break;
            if( str.charAt(i) == str.charAt(i+2)){
                str = str.replaceAll(str.substring(i,i+3),"");
                i = -1;//删除xyx子串后，一定要从头再开始查找
            }
        }
        return str;
    }

    public int getAppearNumOnce(int[] array){
        /*
         找出给定数组中第一个不重复的整数，比如 { 1, 2, 3, 1, 3, 3, 4, 5, 4 }, 输出为 2.
         思路：用map的key保存数组中的数字，value是数组中的index，如果是第一次出现value就是在数组中的位置，如果是第二次出现就标记为-1，
         test cases: {},{1},{1,2},{1,1},{1,2,3},{1,2,2},{2,2,1}{2,1,2},{1,1,2,2},{1,2,1,1,2,3}
         */
        if( array == null || array.length == 0)
            return -1;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int minIndex = Integer.MAX_VALUE;
        int result = -1;
        for(int i=0; i< array.length; i++){
            if( map.containsKey(array[i]) ){
                map.put(array[i],-1);
            }
            else
                map.put( array[i], i);
        }
        for( Map.Entry<Integer,Integer> entry:map.entrySet() ){
            int index = entry.getValue();
            if( index > -1 && index < minIndex){
                minIndex = index;
                result = entry.getKey();
            }
        }
        if( minIndex == Integer.MAX_VALUE){
            System.out.println("not exist expected number.");
            return -1;
        }
        return result;
    }

    public int getAppearHalf(int[] array){
        /*
        给定数组中，某一个数的出现次数超过了元素个数的一半，找出这个数。 比如 { 1, 3, 3, 1, 3, 3, 4, 3, 4 }, 输出为 3
        方法1：排序，输出中间数
        方法2：用key记录数组中的一个数，count表示他出现的次数，遇到相等的数，count+1，不等的数，count-1.当count=0时，把当前的数赋值给key。
        方法3：两两比较数组中的数，如果不等，两个都删除，如果相等，就删除一个。最后剩下的就是要求的数。
        test case: {},[1],{1,2},{1,1},{1,1,2},{1,2,3},{1,2,1},{2,1,1},{1,2,2,1,1},
        {1,2,2,1,1,2},{1,2,2,1,1,2,1},
         */
        
        if( array == null && array.length == 0){
            return -1;
        }
        int key = array[0];
        int count = 1;
        for(int i=1;i<array.length;i++){
            
            if( array[i] == key ){
                count++;
            }
            else{
                count--;
            }
            if(count == 0){
                key = array[i];
                count = 1;
            }
            
        }
        count = 0;
        for(int i=0;i<array.length;i++){
            if( array[i] == key)
                count++;
        }
        if( count*2 <= array.length){
            System.out.println("don't exist expected number.");
            return -1;
        }
        return key;
    }

    public int getOneCount(int num){
        /*
        数字的二进制中数字1的个数
         */
        int count = 0;
        while( num != 0){
            if( (num & 1) == 1)
                count++;
            num =( num>>1);
        }
        return count;
    }

    public int getOneCount2(int num){
        int count = 0;
        for(; num != 0; count++)
            num  &= num-1;
        return count;
    }

    public int maxChar(char[] a, char[] b){
        /*
        求最大前缀，字符串A：accdefcdfg,字符串B：cdfgab，在B中，c，cd，cdf，cdfg...都可以称为是B的前缀，现在在A中寻找B的最大前缀，打印最大前缀并返回最大前缀的位置。比如，B在A中的前缀有，c，cd，cdfg，最大前缀是cdfg，打印出cdfg返回7
         */
        if( a == null || b == null )
            return -1;
        int i = 0;
        int j = 0;
        int index = 0;
        int maxIndex = 0;
        int maxLength = 0;
        while( i<a.length ){
            index = i;
            while( i < a.length && j < b.length && a[i] == b[j]){
                i++;
                j++;
            }
            
            if( maxLength < j ){
                maxLength = j;
                maxIndex = i - j +1;
                //System.out.printf("the i=%d,maxIndex=%d",i, maxIndex);
            }
            j = 0;
            i = index +1;
        }
        StringBuilder result = new StringBuilder();
        for(i=0;i<maxLength;i++)
            result.append(b[i]);
        System.out.println(result);
        System.out.println(maxIndex);
        return maxIndex;
    }

    public int minNum(int[] a){
        /*
        把一个数组最开始的若干个元素搬到数组的末尾，称之为数组的旋转。输入一个递增排序的数组的一个排序，输出旋转数组的最小元素。例如：[3, 4, 5, 1, 2]为[1, 2, 3, 4, 5]的一个旋转，输出最小的数字1.
        二分查找
        test case:[],[1],[1,2],[2,1],[1,2,3],[2,3,1],[3,1,2]
         */
        if( a == null || a.length == 0){
            return -1;
        }
        int low = 0;
        int high = a.length-1;
        int min = a[low];
        int mid = 0;
        while( low < high ){
            mid = (low + high)/2;
            if( a[mid] > min){
                low = mid+1;
            }
            else{
                high = mid;
                min = a[mid];
            }
        }
        return min;
    }

    public static void main(String[] args){
        HandleChar h = new HandleChar();
        //{},{1},{1,2},{1,1},{1,2,3},{1,2,2},{2,2,1}{2,1,2},{1,1,2,2},{1,2,1,1,2,3}
        int[] a = new int[]{3,4,5,1,2};
        int[] b = new int[]{1,2,3,4,5,6};
        int[] c = new int[]{5,1,2,3,4};
        System.out.println(h.minNum(a));
        System.out.println(h.minNum(b));
        System.out.println(h.minNum(c));
    }
}