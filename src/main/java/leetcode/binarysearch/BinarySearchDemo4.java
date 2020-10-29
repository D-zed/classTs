package leetcode.binarysearch;

/**
 * https://juejin.im/post/6884964370716033031
 * 二分查找 查找最后一个小于等于给定值的元素 变种4
 *
 * @author dzd
 */
public class BinarySearchDemo4 {

  static int[] a={0,1,2,2,3,4,5,5,7,8,9};
  static int target=10;
    public static void main(String[] args) {

     int bsearch=   binarySearch(a,target,a.length);
        System.out.println(target+"在数组的index :"+bsearch);
    }

    private static int binarySearch(int[] a, int target, int length) {

        int low=0;
        int high=length-1;
        while (low<=high){

            int mid=(low+high)/2;
            if (a[mid]<=target){
                if (mid==high||a[mid+1]>target){
                    return mid;
                }
                low=mid+1;

            }else {
                high=mid-1;
            }

        }
        return -1;
    }
}
