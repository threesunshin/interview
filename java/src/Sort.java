/*
some sort methods.
 */
public class Sort{
    //选择排序:每一趟从待排序的数据元素中选出最小（或最大）的一个元素
    //顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完
    public void selectSort(int[] a){
        if( a == null || a.length == 0){
            return;
        }
        int minIndex = 0;
        int temp = 0;
        for(int i=0; i< a.length;i++){
            minIndex = i;
            for( int j=i; j<a.length;j++){
                if(a[j] < a[minIndex]){
                    minIndex = j;
                }
            }
            if( minIndex != i){
                temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }

        }
    }
    //冒泡排序
    public void buddleSort(int[] a){
        if( a == null || a.length == 0){
            return;
        }
        int temp = 0;
        for(int i=a.length-1;i>0; i--){
            for(int j=0; j<i;j++){
                if( a[j+1] < a[j]){
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }

            }

        }
    }
    //归并排序

    //快速排序 时间复杂度O(nlogn)
    public void quickSort(int[] a){
        if( a == null || a.length <= 1){
            return;
        }
        quick(a, 0, a.length-1);

    }
    public void quick(int[] a, int left, int right){
        if( left >= right )
            return;
        int flag = a[left];
        int l = left;
        int r = right;
        while( l<r ){
            
            while( l < r && a[r] >= flag)
                r--;
            a[l] = a[r];
            while( l < r && a[l] <= flag)
                l++;
            a[r] = a[l];
        }
        a[l] = flag;

        quick(a, left, l-1);
        quick(a, l+1, right);
    }

    //快速选择，利用快排实现，解决 kth element问题，top k element问题
    /*
    215. Kth Largest Element in an Array (Medium)

    题目描述：找到第 k 大的元素。
    解题思路：1.用快排，然后取第k个，时间复杂度 O(nlogn)
    2.
     */


    //堆排序
    public void heapSort(int[] a){

    }

    //桶排序


    public static void main(String[] args) {
        int[] a = new int[]{6,9,5,8,1,4,2};

        Sort s = new Sort();
        s.quickSort(a);
        for(int i:a){
            System.out.println(i);
        }
        
    }
}