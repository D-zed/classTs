package zhouyang.juc.futureproblem;

import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        if (!executor.isShutdown()){
            if (null!=r&&r instanceof FutureTask){
                //此处之所以设置为 取消是因为FutureTask 自带的修改状态的 方法只有这个
               ((FutureTask) r).cancel(true);
            }
        }
    }
}
