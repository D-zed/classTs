package leetcode.binarysearch;

/**
 * https://juejin.im/post/6884964370716033031
 * 二分查找 查找最后一个值等于给定值的元素 变种2
 *如果 a[mid] 这个元素已经是数组中的最后一个元素了，那它肯定是我们要找的；如果 a[mid]
 * 的后一个元素 a[mid+1] 不等于 value，那也说明 a[mid] 就是我们要找的最后一个值等于给定值的元素。
 * 如果我们经过检查之后，发现 a[mid] 后面的一个元素 a[mid+1] 也等于 value，那说明当前的这个 a[mid]
 * 并不是最后一个值等于给定值的元素。我们就更新 low=mid + 1，因为要找的元素肯定出现在 [mid+1, high] 之间。

 * @author dzd
 */
public class BinarySearchDemo2 {

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
            int mid= (high+low)/2;
            if (a[mid]==target){
                if (a[mid+1]==target){
                    low=mid+1;
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
