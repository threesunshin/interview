/*
some sort methods.
 */
public class Sort{
    //选择排序
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
    //快速排序
    //二分查找
    public static void main(String[] args) {
        int[] a = new int[]{6,9,5,8,1,4,2};

        Sort s = new Sort();
        s.buddleSort(a);
        for(int i:a){
            System.out.println(i);
        }
        
    }
}