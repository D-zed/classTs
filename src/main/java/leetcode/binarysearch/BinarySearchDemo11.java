package leetcode.binarysearch;

/**
 * https://juejin.im/post/6884964370716033031
 * 二分查找 查找第一个值等于给定值的元素 变种1
 * @author dzd
 */
public class BinarySearchDemo11 {

  static int[] a={0,1,2,2,3,4,5,5,7,8,9};
  static int target=5;
    public static void main(String[] args) {
      int i = binarySearch(a, target);
      System.out.println(i);
    }

  private static int binarySearch(int[] a, int target) {
    int low=0;
    int high=a.length-1;
    while (low<high){
      int mid=(low+high)/2;

      if (target>a[mid]){
         if (a[++mid]==target){
           return mid-1;
         }
        low=mid;
      }
      if (target==a[mid]){
        while (a[--mid]==target){

        }
        return mid;
      }
      if (target<a[mid]){

        high=mid;
      }
    }
    return -1;
  }


}
