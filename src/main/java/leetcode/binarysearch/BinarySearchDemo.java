package leetcode.binarysearch;

/**
 * https://juejin.im/post/6884964370716033031
 * 二分查找 easy
 * @author dzd
 */
public class BinarySearchDemo {

  static int[] a={1,2,3,4,5,6,7,8,9,0};
  static int target=2;
    public static void main(String[] args) {
        int bsearch = bsearch(a, a.length, target);
        System.out.println(target+"在数组的index :"+bsearch);
    }

    public static int bsearch(int[] a, int n, int value) {

        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }


}
