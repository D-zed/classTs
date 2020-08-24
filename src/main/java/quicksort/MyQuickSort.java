package quicksort;

import com.alibaba.fastjson.JSONObject;

public class MyQuickSort {

    private static int a[]={11,88,158,42,14,2};

    public static void main(String[] args) {
        quickSort(a,0,a.length-1);

        System.out.println(JSONObject.toJSONString(a));
    }

    ////https://blog.csdn.net/MoreWindows/article/details/6684558  挖坑填坑法
    private static void quickSort(int[] a,int left,int right){
        if (left < right){
            //挖坑填坑思想
            //如果左边小于右边则
            int i=left;
            int j=right;
            //首先将left位置的数赋值给hole 所以这个位置可以视为一个坑，也即是这个坑可以使用别的数据进行填坑  ，并且坑里的值也就是我们的基准值比他小的在左边，大的在右边
            int hole=a[left];
            while (i<j){
                //如果左边标杆小于右边标杆 并且右边标杆处的值比坑的大则 将右边标杆向左移
                //直到不满足这个条件 ，则此时的标杆就是刚才的坑填进来的地方
                while (i<j && a[j] >=hole){
                   j--;
                }
                //此时需要考虑填坑
                if (i<j){
                    a[i]=a[j];
                    //此时的新坑为j标杆处
                    //i标杆也要相应的后一一位
                    i++;
                }
                while (i<j && a[i]<hole){
                    i++;
                }
                if (i<j){
                    a[j]=a[i];
                    j--;
                }
            }
            //一顿循环之后比坑大的在坑右边，比坑小的在坑的左边
            //一顿操作之后此时其实ij是相等的也就是说 i j都是坑的位置
            //填坑
            a[i]=hole;

            //以上只是一次填坑的过程每次都会产生左右两区，所以每次都要再次重复填坑
            //每次填坑的左右起始值
            quickSort(a,left,i-1);
            quickSort(a,i+1,right);
        }

    }


}
