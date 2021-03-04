package leetcode.quicksort;

import com.alibaba.fastjson.JSON;

/**
 * ////https://blog.csdn.net/MoreWindows/article/details/6684558  挖坑填坑法
 * @author dzd
 */
public class MyQuickSort1027 {

    private static int a[]={11,88,158,42,14,2};

    public static void main(String[] args) {
     //数组  左边界下标   右边界下标
        quickSort(a,0,a.length-1);

        System.out.println(JSON.toJSONString(a));
    }

    private static void quickSort(int[] a, int left, int right) {
        if (left<right){
            //分别设置指针的初始值
            //左指针
            int leftTmp=left;
            //右指针
            int rightTmp=right;
            int hole=a[left];
            while (leftTmp<rightTmp){
                //如果指针位置的数据大于hole则符合规则指针左移
                while (leftTmp<rightTmp&&a[rightTmp]>hole){
                    rightTmp--;
                }
                if (leftTmp<rightTmp){
                    a[leftTmp]=a[rightTmp];
                    leftTmp++;
                }

                //如果指针位置的数据小于hole则符合规则指针右移
                while (leftTmp<rightTmp&&a[leftTmp]<hole){
                    leftTmp++;
                }
                if (leftTmp<rightTmp){
                    a[rightTmp]=a[leftTmp];
                    rightTmp--;
                }
            }
            a[rightTmp]=hole;

            quickSort(a,left,rightTmp-1);
            quickSort(a,rightTmp+1,right);
        }
    }
}