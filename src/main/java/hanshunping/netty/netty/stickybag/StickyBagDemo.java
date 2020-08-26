package hanshunping.netty.netty.stickybag;

/**
 * 粘包拆包
 * nagle优化算法
 * 客户端可能会将多个发送间隔较小并且 内容较小的包合并成一次发送一个大封包，导致接收端 无法知道具体消息的边界
 * 一次接受的数据其实是多个数据
 *  所以这个时候就有可能发生粘包和拆包的问题
 * @author dzd
 */
public class StickyBagDemo {

}
