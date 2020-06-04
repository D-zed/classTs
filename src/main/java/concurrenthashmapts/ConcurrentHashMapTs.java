package concurrenthashmapts;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author µË×ÓµÏ
 * @Description TODO
 * @time 2019/9/5
 */
public class ConcurrentHashMapTs {
  private static final ConcurrentMap<String, AtomicInteger> CACHE_MAP = new ConcurrentHashMap<>();
  private static final String KEY = "test";

  private static class TestThread implements Runnable{
    @Override
    public void run() {
      if(CACHE_MAP.get(KEY)==null){
       /* try {
          Thread.sleep(200);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }*/
        CACHE_MAP.put(KEY, new AtomicInteger());
      }
      CACHE_MAP.get(KEY).incrementAndGet();
    }
  }

  public static void main(String[] args) {
    new Thread(new TestThread()).start();
    new Thread(new TestThread()).start();
    new Thread(new TestThread()).start();
    try {
      Thread.sleep(800);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("´ÎÊý:"+CACHE_MAP.get(KEY).get());
  }
}
