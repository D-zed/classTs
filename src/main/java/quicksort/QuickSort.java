package quicksort;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * 划分交换排序（partition-exchange sort），简称快排
 * @author 邓子迪
 */
public class QuickSort {
  private static int a[] = {8, 4, 9, 1, 10, 6};

  public static void main(String args[]) {
    sortNormalArray();
    //sortBigArray();
  }
//https://blog.csdn.net/MoreWindows/article/details/6684558  挖坑填坑法
  private static void sortNormalArray() {
    quickSort(a, 0, a.length - 1);
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + ",");
    }
  }

  /**
   * 对一个大数组排序
   */
  private static void sortBigArray() {
    long currTime = System.currentTimeMillis();
    int[] random = new int[1000000];
    for (int i = 0; i < random.length; i++) {
      random[i] = (int) (Math.random() * 10000000);
    }
    quickSort(random, 0, random.length - 1);
    System.out.println("耗时" + (System.currentTimeMillis() - currTime) + "ms");
    for (int i = 0; i < random.length; i++) {
      System.out.println(random[i] + "");
    }
  }

  /**
   * 调用者请确保传入正确的参数
   *
   * @param a     要排序的数组
   * @param left  0
   * @param right 数组的长度减去1
   */
  private static void quickSort(int[] a, int left, int right) {
    if (left < right) {
      int i = left;
      int j = right;
      int pivot = a[left];
      while (i < j) {
        while (i < j && a[j] >= pivot) {
          j--;
        }
        if (i < j) {
          a[i] = a[j];
          i++;
        }
        while (i < j && a[i] < pivot) {
          i++;
        }
        if (i < j) {
          a[j] = a[i];
          j--;
        }
      }
      a[i] = pivot;

      //递归排序
      quickSort(a, left, i - 1);
      quickSort(a, i + 1, right);
    }
  }

}
