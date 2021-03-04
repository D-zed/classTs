package leetcode.bubble;

import com.alibaba.fastjson.JSON;

/**
 * 冒泡
 * @author dzd
 */
public class MyBubble {
    private static int a[]={11,88,158,158,9999,42,14,2};

    private void bubble(){
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                 if (a[i]>a[j]){
                     int tmp=a[i];
                     a[i]=a[j];
                     a[j]=tmp;
                 }
            }
        }
    }

    public static void main(String[] args) {
        MyBubble myBubble=new MyBubble();
        myBubble.bubble();
        System.out.println(JSON.toJSONString(a));
    }
}