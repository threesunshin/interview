/*
数组相关的算法题
 */
public class MyArray{
    public int getKmax(int[] array, int k){
    /*
    返回数组中第k大的数
    思路：利用快排的思想，数组中随机找个元素x，把数组分成两部分，sa的元素比x小，sb的元素比x大。如果sa的个数小于k，那要找的数在sb，是sb中第 k-len(sa)；如果sa的个数大于k，那要找的数在sa。
     */
    if( array == null || k <= 0 || array.length < k){
        return -1;
    }
    return getK(array,k,0, array.length-1);
    }

    public int getK(int[] array, int k, int left, int right){
        System.out.println("k="+k+";left="+left+";right="+right);
        if( array == null || left > right)
            return -1;
        if( left == right && k == 1)
            return array[left];
        int flag = array[left];
        int le = left;
        int rt = right;
        while( le<rt ){
            while( le<rt && array[rt] >= flag)
                rt--;
            array[le] = array[rt];
            System.out.println("line 29 :rt:"+rt);
            while(le<rt && array[le] <= flag)
                le++;
            System.out.println("le:"+le);
            array[rt] = array[le];
        }
        array[le] = flag;

        if( le - left == k-1)
            return flag;
        else if( le -left < (k-1))
            return getK(array, k-(le-left)-1, le+1,right);
        else
            return getK( array, k, left, le-1);

    }

    public static void main(String[] args){
        MyArray a = new MyArray();
       // int[] array = new int[]{5,7,2,3,4};
        //System.out.println(a.getKmax(array,2));
        int[] a2 = new int[]{5,6,7};
        System.out.println(a.getKmax(a2,3));
    }
}