package leetcode.binarysearch;

/**
 * https://juejin.im/post/6884964370716033031
 * 二分查找 查找第一个大于等于给定值的元素 变种3
 *
 如果 a[mid] 小于要查找的值 value，那要查找的值肯定在 [mid+1, high] 之间，所以，我们更新 low=mid+1。
 对于 a[mid] 大于等于给定值 value 的情况，我们要先看下这个 a[mid] 是不是我们要找的第一个值大于等于给定值的元素。
 如果 a[mid] 前面已经没有元素，或者前面一个元素小于要查找的值 value，那 a[mid] 就是我们要找的元素。这段逻辑对应的代码是第 7 行。
 如果 a[mid-1] 也大于等于要查找的值 value，那说明要查找的元素在 [low, mid-1] 之间，所以，我们将 high 更新为 mid-1。
 * @author dzd
 */
public class BinarySearchDemo3 {

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
            if(a[mid]>=target){
                if (mid==0||a[mid-1]<target){
                    return mid;
                }
                 high=mid-1;
            }else {
                low=mid+1;
            }
        }

        return -1;
    }


}
