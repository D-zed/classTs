package quicksort;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * 复习快排
 * @author dzd
 */
public class QuickSortAgainTest {

    //private static int a[] = {8, 4, 9, 1, 10, 6};

    private static int a[]={11,88,158,42,14,2};

    @Test
    public void test1(){

        quickSort(a,0,a.length-1);

        System.out.println(JSON.toJSONString(a));
    }


    /**
     * 数组的魅力就在于此 每个数据对应的位置非常的明确
     * @param a 需要排序的集合
     * @param left 需要排序的内容的范围的起始
     * @param right 需要排序的内容的范围的终止
     */
    private void quickSort(int[] a,int left,int right) {
        //定义出口，当左边界小于有边界说明还有必要进行排序
        if (left<right){
            int leftIndex=left;
            int rightIndex=right;
            //首先在最左边的数据挖坑然后
            int hole =a[left];
            //比坑小的在左边，比坑大的在右边    一次分治
            while (leftIndex<rightIndex){
                //当右边的大于左边则什么都不管直接移动下表
                while (leftIndex<rightIndex && a[rightIndex]>hole){
                    rightIndex--;
                }
                //能走到这里说明走到的当前位置小于坑所以需要填坑
                if (leftIndex<rightIndex){
                    a[leftIndex]=a[rightIndex];
                    //此时第一个坑已经被锁定所以只能向后移动了
                    leftIndex++;
                }
                //反过来从左向右走下标
                while (leftIndex<rightIndex && a[leftIndex]<hole){
                    leftIndex++;
                }
                if (leftIndex<rightIndex){
                    //能走到这说明此时需要再次填坑了
                    a[rightIndex]=a[leftIndex];
                    rightIndex--;
                }
            }
            //此时左边和右边重合
            //把一开始保存的内容填到这个坑中去
            a[leftIndex]=hole;
            //然后进行左边的治理
            quickSort(a,left,leftIndex-1);
            //再进行右边的治理
            quickSort(a,leftIndex+1,right);
        }

    }


}
