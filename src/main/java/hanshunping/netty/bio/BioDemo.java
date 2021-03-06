package hanshunping.netty.bio;

import zhouyang.juc.ThreadPoolUtil;
import java.lang.String;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这个测试的时候使用 telnet 127.0.0.1 6666
 * ctrl+]  然后 send 发送消息即可向服务器发送消息
 * 服务端只需要指定端口即可
 *
 * 缺点 当前线程暂时没数据读取则阻塞在哪里造成资源的浪费
 * 高并发的情况下需要启用大量的线程处理，对系统资源压力过大
 *
 * bio模型是一中同步阻塞模型 适合链接数目较小且固定的架构，这种方式
 * 对服务器资源要求比较高
 *
 * 传统io的阻塞问题
 */
public class BioDemo {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket=new ServerSocket(6666);
        System.out.println("socket服务器启动了");
        while (true){
            System.out.println("等待链接创建---->"+Thread.currentThread().getName());
            final   Socket  accept = serverSocket.accept();
            System.out.println("链接创建---->"+Thread.currentThread().getName());
            //建立连接并且阻塞于此
            ThreadPoolUtil.execute(()->{
                    hander(accept);
            });
        }
    }

    public static void hander(Socket socket){
        byte[] bytes=new byte[1024];
        try {
            System.out.println("这里处理了么");
            InputStream inputStream = socket.getInputStream();
            System.out.println("这里处理了么2");
            while (true){
                //经debug之后发线 建立连接之后socket 就会进入阻塞状态阻塞到这里 即使没有了消息过来也会阻塞于此，如果有很多连接存在的话，就会导致系统线程的
                //爆炸
                int read = inputStream.read(bytes);
                //不等于-1说明读取成功了
                if (read!=-1){
                    System.out.println(
                            new String(bytes,0,read)
                    );
                }else {
                    break;
                }
            }
            System.out.println("这里处理了么3");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("关闭socket链接");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
