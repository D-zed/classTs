package zhouyang.iostream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyMyself {

    public static void main(String[] args) throws IOException {
        copyFile("E:\\oss-browser-win32-x64.zip","E:\\oss-browsercopy.zip");
    }


    private static void copyFile(String source,String target) throws IOException {

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;

        byte[] b=new byte[1024*1024*20];
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream=new FileOutputStream(target);
            //这个buffer默认读的是8M所以很慢
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            int i=-1;
            while ((i=bufferedInputStream.read(b))!=-1){
                fileOutputStream.write(b,0,i);
            }
            fileOutputStream.flush();
        }catch (Exception e){

        }finally {
            fileOutputStream.close();
            bufferedInputStream.close();
            fileInputStream.close();
        }




    }

}
