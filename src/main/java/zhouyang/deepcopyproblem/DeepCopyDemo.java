package zhouyang.deepcopyproblem;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author dzd
 */
public class DeepCopyDemo {

    //(1)
    static Map<Integer,StrategyService> serviceMap=new HashMap<>();
    static {
        serviceMap.put(111,new StrategyOneService());
        serviceMap.put(222,new StrategyTwoService());
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //(2)
        Map<Integer, List<String>> appKeyMap=new HashMap<>();
        //(3)
        ArrayList<String> oneList=new ArrayList<>();
        oneList.add("device_id1");
        appKeyMap.put(111,oneList);


        ArrayList<String> twoList=new ArrayList<>();
        twoList.add("device_id2");
        appKeyMap.put(222,twoList);

        //(4)
        ArrayList<Msg> msgList=new ArrayList<>();
        Msg msg=new Msg();
        msg.setDataId("abc");
        msg.setBody("hello");
        msgList.add(msg);

        //(5)
       /* Iterator<Integer> appKeyitr = appKeyMap.keySet().iterator();

        while (appKeyitr.hasNext()){
            Integer appKey = appKeyitr.next();
            StrategyService strategyService = serviceMap.get(appKey);
            if (null!=strategyService){
                strategyService.sendMsg(msgList,appKeyMap.get(appKey));
            }else {
                System.out.println(String.format("appkey:%s, is not registerd service",appKey));
            }
        }
        此时输出
         TwoService_abc ["device_id2"]
         oneService_TwoService_abc ["device_id1"]
        */

        /*Iterator<Integer> appKeyitr = appKeyMap.keySet().iterator();

        Map<Integer,List<Msg>> keyMsg=new HashMap<>();
        while (appKeyitr.hasNext()){
            //由于这块用的是引用  最终的引用还是同一个位置
            keyMsg.put(appKeyitr.next(),new ArrayList<>(msgList));
        }
        appKeyitr= appKeyMap.keySet().iterator();
        while (appKeyitr.hasNext()){
            Integer appKey = appKeyitr.next();
            StrategyService strategyService = serviceMap.get(appKey);
            if (null!=strategyService){
                strategyService.sendMsg(keyMsg.get(appKey),appKeyMap.get(appKey));
            }else {
                System.out.println(String.format("appkey:%s, is not registerd service",appKey));
            }
        }
        此时虽然把 每个key重新关联了一个 msg 但是这个msg最终引用还是那一份就在堆上 所以此时还是会面临对象属性被修改的问题
        */


        Iterator<Integer> appKeyitr = appKeyMap.keySet().iterator();

        Map<Integer,List<Msg>> keyMsg=new HashMap<>();
        while (appKeyitr.hasNext()){
            //由于这块用的是引用  最终的引用还是同一个位置
            Iterator<Msg> iterator = msgList.iterator();
            List<Msg> msgCloneList=new ArrayList<>();
            while (iterator.hasNext()){
                Msg next = iterator.next();
                //使用阿帕奇的深克隆 对集合中的每个对象拷贝重新构建集合最终再次赋值给消息列表
                next = (Msg) BeanUtils.cloneBean(next);
                msgCloneList.add(next);
            }
            keyMsg.put(appKeyitr.next(),msgCloneList);
        }
        appKeyitr= appKeyMap.keySet().iterator();
        while (appKeyitr.hasNext()){
            Integer appKey = appKeyitr.next();
            StrategyService strategyService = serviceMap.get(appKey);
            if (null!=strategyService){
                strategyService.sendMsg(keyMsg.get(appKey),appKeyMap.get(appKey));
            }else {
                System.out.println(String.format("appkey:%s, is not registerd service",appKey));
            }
        }




    }
}
