package hanshunping.netty.netty;

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
 *
 * netty的目的就是让你的业务逻辑从网络基础应用编码中分离出来，解脱出来
 * @author dengzidi
 */
public class ChannelFutureDemo {
}
