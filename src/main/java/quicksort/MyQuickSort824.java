package quicksort;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class MyQuickSort824 {

    private static int a[] = {8, 4, 9, 1, 10, 6};

@Test
public void test1(){
    this.sort(a,0,a.length-1);

    System.out.println(JSON.toJSONString(a));
}
    /**
     * 先编写分治代码进行分治
     */
    public void sort(int [] a,int left ,int right){
        //如果左边小于右边则进行对比交换
        if (left<right){

            int leftIndex=left;
            int rightIndex=right;
            //首先挖坑
            int hole=a[left];
            while (leftIndex<rightIndex){
                //遵循左小右大的原则， 并且右边下标杆左移 左边杆右移
                while (leftIndex<rightIndex && a[rightIndex]>hole){
                    rightIndex--;
                }
                //此时的rightIndex符合填坑的条件
                if(leftIndex<rightIndex){
                    a[leftIndex]=a[rightIndex];
                    //由于此时坑已经被填上了，所以需要进行后移一位
                    leftIndex++;

                }

                while (leftIndex<rightIndex &&a[leftIndex]<hole){
                    leftIndex++;
                }
                if (leftIndex<rightIndex){
                    a[rightIndex]=a[leftIndex];
                    rightIndex--;
                }
            }


            a[leftIndex]=hole;

            sort(a,left,leftIndex-1);

            sort(a,leftIndex+1,right);

        }

    }
}
