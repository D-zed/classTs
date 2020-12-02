package zhouyang.trywithresource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 使用tryResource方法控制
 *
 * 使用tryresouce方法控制的资源一定要 实现 AutoCloseable
 * 下边查看BufferedInputStream BufferedOutputStream 都已经实现了 AutoCloseable 并且重写close方法
 * 这样就不用写套娃 finnal了 ，其实这算是一种语法糖 最终的编译代码依然是套娃
 * @author dzd
 */
public class TryWithResourceDemo2 {

    public static void main(String[] args) {

        try(
                ConnectBean connectBean=new ConnectBean();
        ){
            connectBean.send("发送消息");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
