package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
      /*  System.out.println("hello world");
        InputStreamReader  isr=new InputStreamReader(System.in);
        BufferedReader  br=new BufferedReader( isr);
        try {
            String readLine = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Scanner  sc=new Scanner(System.in);
         System.out.println("请输入aa");
        Integer aa=sc.nextInt();
        System.out.println("请输入bb");
        Integer bb=sc.nextInt();
        bb=bb+aa;
        aa=bb-aa;
        bb=bb-aa;
        System.out.println("aa"+aa+"bb"+bb);
    }
}
