package zhouyang.juc;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决hashmap  拿出来又放回去的非原子性操作的问题
 * 乍一看这个程序concurrentHashMap是 安全的  Atomic也是安全的
 * 1 加锁
 * 2
 */
public class ConcurrentHashMapDemo {



  static   ConcurrentHashMap<String,Count> concurrentHashMap=new ConcurrentHashMap<>();

    public static void main(String[] args) {

        //这个如果单线程的时候是 10
        for (int i = 0; i <100; i++) {
           new Thread(()->{

               //这个第一次put是添加第二次是获取 相当于一个 独占锁 只有不存在的时候才put 存在的时候则返回
               concurrentHashMap.putIfAbsent("key", new Count());
               Count  atomicInteger = concurrentHashMap.get("key");
 //              Count atomicInteger = concurrentHashMap.get("key");
//                if (atomicInteger==null){
//                    atomicInteger=new Count();
//                    concurrentHashMap.put("key",atomicInteger);
//                }
               //获取到之后对同一个对象进行
               System.out.println(Thread.currentThread().getName()+"-------"+atomicInteger.atomicInteger.incrementAndGet());
            },Integer.toString(i)).start();
        };

        while (Thread.activeCount()>2){
            Thread.yield();
            //System.out.println("礼让--------");
        }

        Count key = concurrentHashMap.putIfAbsent("key", new Count());
        Count key1 = concurrentHashMap.putIfAbsent("key", new Count());
        System.out.println(key);
        System.out.println(key1);

        System.out.println("礼让结束-----------");
        System.out.println(JSON.toJSONString(concurrentHashMap));
    }
}
class Count{
     AtomicInteger atomicInteger=new AtomicInteger(0);

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }
}