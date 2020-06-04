package leetcode.easy07;

import org.junit.Test;

public class HKSort {


    /*
    * �����Ǹ���һ���������������������������������и�С�ӵ㣬��������������������Ļ�����ô�ͷ��� 0�������Ǵ����ʾ�Ļ�������ý�������� long �У�
    * ���ѽ�������͵�������Χ�Ƚϼ��ɡ�
    * */
    @Test
    public void test1() {
        long reverse = reverse(568870);
        System.out.println(reverse);
    }

    public long  reverse(int input){
        long res=0;
        while (input!=0){
            res=res*10+input%10;
            input=input/10;
        }

        return res>Integer.MAX_VALUE || res<Integer.MIN_VALUE ? 0:res;

    }




}
