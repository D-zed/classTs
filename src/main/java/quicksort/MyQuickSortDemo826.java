package quicksort;

import com.alibaba.fastjson.JSONObject;

/**
 * @author dengzidi
 */
public class MyQuickSortDemo826 {
   static int[]a={1,22,3,55,333,100};

    public static void main(String[] args) {
        quickSort(0, a.length - 1, a);
        System.out.println(JSONObject.toJSONString(a));
    }

    public static void quickSort(int left,int right,int []a){

        if (left<right){
            int leftHole=left;
            int rightHole=right;
            int hole=a[left];

            while (leftHole<rightHole){
                while (leftHole<rightHole&&a[rightHole]>hole){
                    rightHole--;
                }
                //按理说走到这里的位置的就要填补到左边的坑里了
                if (leftHole<rightHole){
                    a[leftHole]=a[rightHole];
                }
                while (leftHole<rightHole&&a[leftHole]<hole){
                    leftHole++;
                }
                //按理说走到这里的位置的就要填补到左边的坑里了
                if (leftHole<rightHole){
                    //因为刚才rightHole填补到左边的洞里了 所以这个就要再填补到 rightHole
                    a[rightHole]= a[leftHole];
                }
            }
            a[rightHole]=hole;

            //以上是比坑小的在左坑大的在右，坑位  rightHole=leftHole
            //然后对左边操作
            quickSort(left,leftHole-1,a);
            //对右边操作
            quickSort(leftHole+1,right,a);

        }
    }


}
