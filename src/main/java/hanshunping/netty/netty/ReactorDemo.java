package hanshunping.netty.netty;

/**
 * @author dzd
 * nio的类库和api复杂使用麻烦，需要熟练的socketchannel和bytebuffer
 * 还要具备java多线程编程的能力，nio编程设计reactor模式必须非常熟悉网络编程
 * 和多线程编程
 *
 * netty 一个异步的基于事件驱动的网络框架，为了快速开发高性能的server client
 * rtsp（real time steam protocal 实时流协议，视频的，这个netty也支持） rtp
 * 总之netty真的很强大
 *
 * netty 对nio进行了包装
 * netty再大数据分布式领域，游戏行业，elasticsearch都是netty
 *
 * 支持的协议多 社区强大
 *
 *
 * 且其中使用的是reactor模型 采用了io复用模型（多路复用），也就是说所有的阻塞handler
 * 最终变成一个servicehandler （bio则是需要每一个连接都有一个handler来处理，read的时候会阻塞），也就是说只有一个阻塞来监听消息
 * 最终这个handler进行一个任务的分发（所以reactor也可以叫做 分发者模式，而且selector也起到了一个
 * 事件通知的作用所以也可以叫做通知者模式） 由后置的handler进行处理
 *（在标准模型中Handler是对象 其中是处理方法）
 * 最基本的还是解决了 传统io的服务器连接上限的问题，以及传统io的连接阻塞的问题
 *  多个连接复用同一套阻塞机制，并且在分发任务 由线程池处理完美的复用线程
 *
 *  其实毕竟netty是nio框架，从宏观上看 有点像 nio加了个线程池的感觉 当然还是有很多其他的加强的
 */
public class ReactorDemo {

}
