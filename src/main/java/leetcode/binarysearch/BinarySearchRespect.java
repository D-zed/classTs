package leetcode.binarysearch;

/**
 * start 17:50 end
 *   static int[] a={1,2,3,4,5,6,7,8,9,0};
 * @author dzd
 */
public class BinarySearchRespect {
    static int[] a={0,1,2,3,4,5,6,7,8,9};

    static int target=5;

    public static int find(){
        int high=9;
        int low=0;
        int mid;
        while (low<high){
            mid=(low+high)/2;
            if (a[mid]>target){
                high=mid;
            }
            if (a[mid]<target){
                low=mid;
            }
            if (a[mid]==target){
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int i = find();
        System.out.println(i);
    }

}