package leetcode.binarysearch;

/**
 * https://juejin.im/post/6884964370716033031
 * 二分查找 查找第一个值等于给定值的元素 变种1
 * @author dzd
 */
public class BinarySearchDemo1 {

  static int[] a={0,1,2,2,3,4,5,5,7,8,9};
  static int target=5;
    public static void main(String[] args) {

     int bsearch=   binarySearch(a,target,a.length);
        System.out.println(target+"在数组的index :"+bsearch);
    }

    private static int binarySearch(int[] a, int target, int length) {
        int low=0;
        int high=length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (a[mid]==target){
                if (a[mid-1]==target){
                    high=mid-1;
                }else {
                    return mid;
                }
            }else if(a[mid]>target){
                 high=mid-1;
            }else {
                low=mid+1;
            }
        }

        return -1;
    }


}
