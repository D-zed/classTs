package designmode.strategymode;


/**
 * 策略模式接口 统一的结构 处理逻辑高度抽象 具体实现由子类控制
 * 线程的 runable就是策略模式
 * @param <T>
 */
public interface StrategyHandle<T> {
    public T handle(String parm);
}
