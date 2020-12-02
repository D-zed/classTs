package leetcode.quicksort;

import com.alibaba.fastjson.JSON;

/**
 * 快排 计时 start 16：43
 * @author dzd
 */
public class MyQuickSort1110 {

    private static int a[]={11,88,158,42,14,2};

    public static void main(String[] args) {
        quickSort(a,0,a.length-1);

        System.out.println(JSON.toJSONString(a));
    }

    private static void quickSort(int[] a, int leftIndex, int rightIndex) {

        if (leftIndex<rightIndex){

            int left=leftIndex;
            int right=rightIndex;
            int hole=a[left];
            while (left<right){
                while (left<right&&a[right]>hole){
                    right--;
                }
                if (a[right]<hole){
                    a[left]=a[right];
                    left++;
                }

                while (left<right&&a[left]<hole){
                    left++;
                }
                if (a[left]>hole){
                    a[right]=a[left];
                    right--;
                }
            }
            a[left]=hole;

            quickSort(a,leftIndex,left-1);
            quickSort(a,left+1,rightIndex);
        }


    }


}