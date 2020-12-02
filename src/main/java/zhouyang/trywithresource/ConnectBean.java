package zhouyang.trywithresource;

/**
 * @author dzd
 */
public class ConnectBean implements AutoCloseable{

    public ConnectBean(){
        System.out.println("连接");
    }

    public void send(String content){
        System.out.println("连接好了发送消息");
    }

    @Override
    public void close(){
        System.out.println("关闭连接");
    }
}
