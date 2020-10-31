package zhouyang.juc.thread.newthread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 钩子线程 2 实战
 * 为了防止某个程序被重复启动在进程启动时创建一个lock文件进程收到中断信号时候会删除lock文件
 * 以下程序模拟这一个操作
 * 然而强制杀死进程则无法接收到中断信号
 * @author dzd
 */
public class ThreadHook2 {

    private final static String LOCK_PATH="F:\\lock";
    private final static String LOCK_FILE="lock.txt";


    public static void main(String[] args) throws IOException {
        //注册钩子
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("the program received kill SIGNAL");
            getLockFile().toFile().delete();
        }));

        checkRunning();
        //简单模拟程序运行

            try {
                System.out.println("程序运行中");
                TimeUnit.SECONDS.sleep(10);

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    private static void checkRunning() throws IOException {
        Path lockFile = getLockFile();
        if (lockFile.toFile().exists()){
            throw new RuntimeException("程序已经启动了");
        }
        Files.createFile(lockFile);
    }

    private static Path getLockFile(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
