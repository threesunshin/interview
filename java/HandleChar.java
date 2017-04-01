/*
some functions for handle string
 */
import java.util.regex.*;

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

    public static void main(String[] args){
        HandleChar h = new HandleChar();
        
        System.out.println(h.getAppearOnce("this is a test"));
    }
}