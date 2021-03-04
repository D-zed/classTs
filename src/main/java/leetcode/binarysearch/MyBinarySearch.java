package leetcode.binarysearch;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 二分查找 easy
 * 二分查找的前提是对有序数组进行查找
 * @author dzd
 */
public class MyBinarySearch {

  static int[] a={1,2,3,4,5,6,7,8,9,0};
  static int target=9;
    public static void main(String[] args) {
       int index= binarySearch(a);
        System.out.println(index);
    }

    private static int binarySearch(int[] a) {
        int low=0;
        int high=a.length-1;
        while (low<high){
            int mid=(low+high)/2;
            if (a[mid]==target){
                return mid;
            }
            if (a[mid]>target){
                high=mid;
            }
            if (a[mid]<target){
                low=mid;
            }
        }
        return -1;
    }


}
