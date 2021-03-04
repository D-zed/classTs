package leetcode.quicksort;

import com.alibaba.fastjson.JSON;

/**
 * 复习快排并且计时
 * @author dzd
 */
public class MyQuickSort {

    private static int a[]={11,88,158,42,14,2};

    public void quickSort(){
        quickSort(a,0,a.length-1);
        System.out.println(JSON.toJSONString(a));
    }

    private void quickSort(int[] a, int left, int right) {
         if (left<right){
             int i=left;
             int j=right;
             int hole=a[left];
             while (i<j){
                 while (i<j&&a[j]>hole){
                     j--;
                 }
                 if (i<j){
                     a[i]=a[j];
                     i++;
                 }
                 while (i<j&&a[i]<hole){
                     i++;
                 }
                 if (i<j){
                     a[j]=a[i];
                     j--;
                 }
             }
             a[j]=hole;
             quickSort(a,left,j-1);
             quickSort(a,j+1,right);
         }
    }

    public static void main(String[] args) {
        MyQuickSort myQuickSort=new MyQuickSort();
        myQuickSort.quickSort();
    }
}