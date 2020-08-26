package zhouyang.mianshi;

import io.netty.util.CharsetUtil;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 介绍byteArray的东西
 * 这个close没有用 ，因为是空的 他底层byte[] 存储， 他会扩容，然而不会缩小 如果需要复用则需要reset 否则会出现问题，不停的扩容
 * https://blog.csdn.net/rcoder/article/details/6118313?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
 *
 * https://blog.csdn.net/noteless/article/details/82720139
 * @author dzd
 */
public class ByteArrayStreamDemo {


    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        for (int i = 0; i < 10; i++) {
            FileInputStream fis=new FileInputStream("F:\\HHHHHHH.txt");

            BufferedInputStream bis=new BufferedInputStream(fis);

            //读取bis流中的下一个字节
            int c=bis.read();

            while(c!=-1){
                baos.write(c);
                c=bis.read();
            }
           // FileOutputStream fileOutputStream = new FileOutputStream("dzd" + i + ".txt");
            //将其写入文件
          //  baos.writeTo(fileOutputStream);
            byte [] retArr=baos.toByteArray();
            System.out.println("次数----------"+i);
            String s = new String(retArr, CharsetUtil.UTF_8);
            System.out.println(s);
            //reset之后就不会扩容了
          //  baos.reset();
            bis.close();
        }
    }

}
