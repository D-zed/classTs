package zhouyang.concurrentmapproblem;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * ConcurrentHashMap的可能出现的问题
 * 直播的场景 每个直播间对应一个topic 每个用户进入直播间会把自己的设备ID绑定到这个topic上 一个topic 多个用户
 * topic list<ID>
 *
 * @author dzd
 */
public class ConcurrentHashMapDemo {
    // (1)
    static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        //(2) 进入直播间topic1 线程1
        /*new Thread(()->{

            ArrayList<String> list1 = new ArrayList<>();
            list1.add("device1");
            list1.add("device2");
            map.put("topic1",list1);
            System.out.println(JSON.toJSONString(map));
        }).start();


        //(2) 进入直播间topic1 线程2
        new Thread(()->{

            ArrayList<String> list1 = new ArrayList<>();
            list1.add("device11");
            list1.add("device22");
            map.put("topic1",list1);
            System.out.println(JSON.toJSONString(map));
        }).start();

        //(2) 进入直播间topic2 线程3
        new Thread(()->{
            ArrayList<String> list1 = new ArrayList<>();
            list1.add("device111");
            list1.add("device222");
            map.put("topic2",list1);
            System.out.println(JSON.toJSONString(map));
        }).start();*/

        /*
        以上写法的输出结果
        {"topic1":["device11","device22"],"topic2":["device111","device222"]}
        {"topic1":["device11","device22"],"topic2":["device111","device222"]}
        {"topic1":["device11","device22"],"topic2":["device111","device222"]}
        丢失了一部分数据
         */


        //以下改良

        //(2) 进入直播间topic1 线程1
        new Thread(() -> {

            CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>();
            list1.add("device1");
            list1.add("device2");
            List<String> topic1 = map.putIfAbsent("topic1", list1);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {


            }
            //如果存在则返回
            if (topic1 != null) {
                topic1.addAll(list1);
            }
            System.out.println(JSON.toJSONString(map));
        }).start();


        //(2) 进入直播间topic1 线程2
        new Thread(() -> {

            List<String> list1 = new CopyOnWriteArrayList<>();
            list1.add("device11");
            list1.add("device22");
            List<String> topic1 = map.putIfAbsent("topic1", list1);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {


            }
            if (topic1 != null) {
                topic1.addAll(list1);
            }

            System.out.println(JSON.toJSONString(map));
        }).start();

        //(2) 进入直播间topic2 线程3
        new Thread(() -> {
            List<String> list1 = new CopyOnWriteArrayList<>();
            list1.add("device111");
            list1.add("device222");
            List<String> topic2 = map.put("topic2", list1);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {


            }
            if (topic2 != null) {
                topic2.addAll(list1);
            }
            System.out.println(JSON.toJSONString(map));
        }).start();

    }
}
