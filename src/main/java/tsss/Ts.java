package tsss;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author 邓子迪
 * @Description TODO
 * @time 2019/9/5
 */
public class Ts {

  public static void main(String[] args) {

    Map<String, Set<Integer>> empidToSessionIdsMap=new HashMap<>();
    //todo 修改位socketioclient集合
    Set<Integer> sessionIdSet = empidToSessionIdsMap
        .get("ddd");
    /*sessionIdSet =new HashSet<>();
    sessionIdSet.add(10000000);
    empidToSessionIdsMap.put("ddd",sessionIdSet);*/

    if (sessionIdSet==null){
      sessionIdSet =new HashSet<>();
      sessionIdSet.add(9999999);
      empidToSessionIdsMap.put("ddd",sessionIdSet);
    }else{
      sessionIdSet.add(88888);
    }
    System.out.println(empidToSessionIdsMap);
    System.out.println("ddd");
  }
}
