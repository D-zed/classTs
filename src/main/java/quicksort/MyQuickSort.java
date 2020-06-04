package quicksort;

import com.alibaba.fastjson.JSONObject;

public class MyQuickSort {

    private static int a[]={11,88,158,42,14,2};

    public static void main(String[] args) {
        quickSort(a,0,a.length-1);

        System.out.println(JSONObject.toJSONString(a));
    }

    ////https://blog.csdn.net/MoreWindows/article/details/6684558  �ڿ���ӷ�
    private static void quickSort(int[] a,int left,int right){
        if (left < right){
            //�ڿ����˼��
            //������С���ұ���
            int i=left;
            int j=right;
            //���Ƚ�leftλ�õ�����ֵ��hole �������λ�ÿ�����Ϊһ���ӣ�Ҳ��������ӿ���ʹ�ñ�����ݽ������  �����ҿ����ֵҲ�������ǵĻ�׼ֵ����С������ߣ�������ұ�
            int hole=a[left];
            while (i<j){
                //�����߱��С���ұ߱�� �����ұ߱�˴���ֵ�ȿӵĴ��� ���ұ߱��������
                //ֱ��������������� �����ʱ�ı�˾��ǸղŵĿ�������ĵط�
                while (i<j && a[j] >=hole){
                   j--;
                }
                //��ʱ��Ҫ�������
                if (i<j){
                    a[i]=a[j];
                    //��ʱ���¿�Ϊj��˴�
                    //i���ҲҪ��Ӧ�ĺ�һһλ
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
            //һ��ѭ��֮��ȿӴ���ڿ��ұߣ��ȿ�С���ڿӵ����
            //���
            a[i]=hole;

            //����ֻ��һ����ӵĹ���ÿ�ζ��������������������ÿ�ζ�Ҫ�ٴ��ظ����
            //ÿ����ӵ�������ʼֵ
            quickSort(a,left,i-1);
            quickSort(a,i+1,right);
        }

    }


}
