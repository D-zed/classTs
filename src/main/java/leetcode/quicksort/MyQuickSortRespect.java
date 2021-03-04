package leetcode.quicksort;

import com.alibaba.fastjson.JSON;

/**
 * start 17:38 end 17:42
 * @author dengzidi
 */
public class MyQuickSortRespect {
    static int[] a = {1, 22, 3, 55, 333, 100};


    public static void quickSort(int[]a,int left,int right){
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
             }

             while (i<j&&a[i]<hole){
                i++;
             }
             if (i<j){
                a[j]=a[i];
             }
          }

          a[i]=hole;
          quickSort(a,left,i-1);
          quickSort(a,i+1,right);
       }

    }

   public static void main(String[] args) {
      quickSort(a,0,a.length-1);

      System.out.println(JSON.toJSONString(a));
   }

}