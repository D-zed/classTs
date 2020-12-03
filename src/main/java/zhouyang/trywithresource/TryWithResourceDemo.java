package zhouyang.trywithresource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 使用tryResource方法控制
 *
 * 使用tryresouce方法控制的资源一定要 实现 AutoCloseable
 * 下边查看BufferedInputStream BufferedOutputStream 都已经实现了 AutoCloseable 并且重写close方法
 * 详细看 TryWithResourceDemo2
 * @author dzd
 */
public class TryWithResourceDemo {

    public static void main(String[] args) {

       /* try(
                BufferedInputStream bin=new BufferedInputStream(new FileInputStream(new File("F:\\HHHHHHH.txt")));
                BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(new File("F:\\HHHHHHH11.txt")));
        ){
            int b;
            while ((b=bin.read())!=-1){
                bout.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try(
                FileInputStream bin=new FileInputStream(new File("F:\\HHHHHHH.txt"));
                FileOutputStream bout=new FileOutputStream(new File("F:\\HHHHHHH11.txt"));
        ){
            int b;
            while ((b=bin.read())!=-1){
                bout.write(b);
            }
            bin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
