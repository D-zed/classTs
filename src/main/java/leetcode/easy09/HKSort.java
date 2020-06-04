package leetcode.easy09;

import org.junit.Test;

public class HKSort {


    /*
    * �������ж�һ���з����������Ƿ��ǻ��ģ�Ҳ�������������������ԭ������ͬ�����ȸ����϶����ǣ����������Ƿ���һ������ͨ�Ľⷨ��
    * ����ֱ��������Ļ�������Ȼ��͸���ֵ�Ƚϼ���
    * */

    //�ҵ�˼·ץ��Ϊ�ַ���
    @Test
    public void test1() {

        //ת��Ϊ�ַ���
        Boolean huiWen = isHuiWen(-225522);
        System.out.println(huiWen);
    }

    @Test
    public void test2(){
        //��������� Ȼ��ԱȾ�ok��   hiahia ����Ǵ��е�˼·0��

        Boolean huiWen2 = isHuiWen2(77777);
        System.out.println(huiWen2);
    }


    @Test
    public void test3(){
        //�Ż�֮������ֻ��Ҫ�Ƚ�һ�뼴��
        //��������������ʱ�򼴿�
    }

    public Boolean isHuiWen2(int input){

        int init =input;
        if (input<0){
            return false;
        }
        int result=0;
        for (; input !=0; input/=10) {

            result=result*10+input%10;

        }
        if (result==init){
            return true;

        }
        return false;
    }


    public Boolean isHuiWen(int input){

        String s = input + "";
        char[] chars = s.toCharArray();
        int length = chars.length-1;
        for (int i = 0; i <=length; i++) {
            int i1 = length - i;
            if (chars[i]!=chars[i1]){
              return false;
            }
        }
        return true;
    }




}
