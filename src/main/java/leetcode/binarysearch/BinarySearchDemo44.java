package leetcode.binarysearch;

/**
 * https://juejin.im/post/6884964370716033031
 * 二分查找 查找最后一个小于等于给定值的元素 变种4
 *
 * @author dzd
 */
public class BinarySearchDemo44 {

  static int[] a={0,1,2,2,3,4,5,5,7,8,9};
  static int target=5;
    public static void main(String[] args) {

     int bsearch=   binarySearch(a,target);
        System.out.println(target+"在数组的index :"+bsearch);
    }

    /**
     * 查找最后一个小于等于给定值的元素
     * 找到第一个大于等于给定值的元素即可
     * @param a
     * @param target
     * @return
     */
    private static int binarySearch(int[] a, int target) {

        int low=0;
        int high=a.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (a[mid]>=target){
                if (a[mid-1]>target){
                    high=mid-1;
                }else {
                    return mid;
                }
            }else {
                low=mid+1;
            }
        }
        if (target>a[a.length-1]){
            return a.length-1;
        }
        return -1;
    }


}
