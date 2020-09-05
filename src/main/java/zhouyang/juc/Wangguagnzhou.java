package zhouyang.juc;

import java.util.Scanner;

public class Wangguagnzhou {


    public static void main(String[] args) {

        Integer sum=0,count=0,max=0,min=100;
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            if (s.equals(" ")){
                System.out.println("结束");
                break;
            }
            int i=0;
            try {
                 i = Integer.parseInt(s);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("输入的数据类型不为数值类型，请重新输入");
                continue;
            }
            if (i>100||i<0){
                System.out.println("输入的数值不在 1-100区间，请重新输入");
                continue;
            }
            count++;
            sum+=i;
            if (i>max){
                max=i;
            }
            if (i<min){
                min=i;
            }
        }


       int avg= (sum-max-min)/(count-2);
        System.out.println("最高分："+max+",最低分："+min+",平均分 :"+avg);


    }
}
