package hanshunping.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 群聊的客户端
 * @author dzd
 */
public class GroupChatClient {

  private final static  String ADDRESS="127.0.0.1";
    private static final int PORT=3333;
  private  String username;
  private Selector selector;
  private SocketChannel socketChannel;
  public GroupChatClient() throws IOException {
      selector= Selector.open();
      socketChannel=SocketChannel.open(new InetSocketAddress(ADDRESS,PORT));
      socketChannel.configureBlocking(Boolean.FALSE);
      socketChannel.register(selector, SelectionKey.OP_READ);
      username = socketChannel.getLocalAddress().toString().substring(1);
      System.out.println(username+"is ok ...");
  }

  public void sendInfo(String info){
      info=username+"说："+info;
      ByteBuffer wrap = ByteBuffer.wrap(info.getBytes());
      try {
          //将消息写入通道
          socketChannel.write(wrap);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }


  public void readInfo(){
      try{
          //有事件发生的通道
          int select = selector.select();
          if (select>0){
              Set<SelectionKey> selectionKeys = selector.selectedKeys();
              Iterator<SelectionKey> iterator = selectionKeys.iterator();
              while (iterator.hasNext()){
                  SelectionKey key = iterator.next();
                  if (key.isReadable()){
                      SocketChannel channel = (SocketChannel) key.channel();
                      ByteBuffer buffer = ByteBuffer.allocate(1024);
                      channel.read(buffer);
                      System.out.println("读到消息 ："+new String(buffer.array()));
                  }
              }
          }
      }catch (Exception e){
          e.printStackTrace();
      }
  }

    public static void main(String[] args) throws IOException {

      //启动客户端
        GroupChatClient groupChatClient = new GroupChatClient();
        //启动线程
        new Thread(()->{
            while (true){
                groupChatClient.readInfo();
                try {
                    //每三秒读一次
                    TimeUnit.SECONDS.sleep(3);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            groupChatClient.sendInfo(s);
        }
    }
}
