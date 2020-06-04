package Ts_2_25;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ts225 {

    public static void main(String[] args) {
    }
    //1、写出一个方法，实现字符串返转，如输入abc，则输出cba
    @Test
    public  void  fanzhuan(){
        String fanzhuan="abcdefg";
        char[] chars = fanzhuan.toCharArray();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = chars.length-1; i>=0 ; i--) {
            stringBuilder.append(chars[i]);
        }
        System.out.println("反转前"+fanzhuan+"反转后"+stringBuilder);
    }
    @Test
    public void zhuandaxie(){
        //3、实现一个金额小写转换大写的方法，如输入3268.98元，输出叁仟贰佰陆拾捌元玖角捌分
         double dd=3268.98;
        Map<Integer,String> map=new HashMap<>();


    }
    @Test
    public void FIleCopy() throws IOException {
        //4、编写一个程序将d:/java目录下所有.java文件复制到d:/jad目录下，并将原文件扩展名从.java改为.jad
        File file = new File("D:/java");
        boolean file1 = file.isDirectory();
        FileInputStream fileInputStream=null;
        FileOutputStream fileOutputStream=null;
        if (file1){
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.getName().endsWith(".java")){
                     fileInputStream = new FileInputStream(file2);
                     fileOutputStream = new FileOutputStream("d:/jad/" + file2.getName() + ".jad");
                    byte [] by=new byte[1024];
                    int len=0;
                    while ((len=fileInputStream.read(by))!=-1){
                        fileOutputStream.write(by, 0, len);
                    }
                }
            }
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
