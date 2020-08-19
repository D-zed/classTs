package hanshunping.netty.netty;

import io.netty.channel.ChannelHandler;

/**
 *
 * netty的异步模式
 * 1.表示异步的执行结果，可以通过它提供的方法来检测执行是否完成
 * 2.在使用netty编程的时候，拦截操作和转换出入站数据只需要
 * 提供callback 或利用future即可，这使得链式操作简单，高效
 *
 * FutureListener 机制
 * 当future对象刚刚创建时处于非完成状态，调用者可以通过返回的
 * ChannelFuture来获取操作执行状态，注册监听函数来执行完成后的操作
 *
 *
 * Channel 支持关联io操作与对应的处理程序
 * 不同协议，不同的阻塞类型的连接都有不同的channel类型与之对应
 * NioSocketChannel 一部客户端TCP socket连接
 * NioServerSocketChannel 异步服务端的tcpsocket
 * NioDatagramChannel 异步的utp连接
 * NioSctpChannel 异步的客户端 sctp连接
 * NioSctpServerChannel 异步的sctp服务器端连接
 *
 * channelHandler 是一个接口，处理io事件 或拦截io事件 并将其转发到其channelpipe中的下一个
 * 处理程序
 * ChannelHandler
 *    |          |
 *    |                               |
 *  channelOutBoundHandler      channelInBoundHandler
 *
 * 事件的运动方向是 客户端到服务器 出栈  反之为入栈
 *
 * 因为pipeline的内部是一个双向链表 其中每个节点是 channelHandlerContext
 * 其内部是 channelHandler
 * 入栈事件会从链表的头往后传递到最后一个入栈的handler
 * 出栈事件会从链表尾部往前传递到最前边的一个出栈handler
 * 两种类型的handler不干扰
 *
 *
 *
 * netty的目的就是让你的业务逻辑从网络基础应用编码中分离出来，解脱出来
 * @author dengzidi
 */
public class ChannelFutureDemo {
}