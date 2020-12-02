package zhouyang.trywithresource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * try catch示例
 * https://www.jianshu.com/p/5734a15f995e
 * 这么一写为了控制可以关闭资源则造成了关闭资源的代码比业务代码还多
 * @author dzd
 */
public class TryCatchDemo {


    public static void main(String[] args) {

        BufferedInputStream bin=null;
        BufferedOutputStream bout=null;
        try {
            bin=new BufferedInputStream(new FileInputStream(new File("F:\\HHHHHHH.txt")));
            bout = new BufferedOutputStream(new FileOutputStream(new File("F:\\out.txt")));
            int b;
            while ((b=bin.read())!=-1){
                bout.write(b);
            }
        }catch (Exception e){

        }finally {
            if (bin!=null){
                try {
                    bin.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (bout!=null){
                try {
                    bout.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
