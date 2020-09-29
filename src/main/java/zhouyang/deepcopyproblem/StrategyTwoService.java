package zhouyang.deepcopyproblem;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class StrategyTwoService implements StrategyService{
    @Override
    public void sendMsg(List<Msg> msgList, List<String> deviceidList) {
        for (Msg msg : msgList) {
            msg.setDataId("TwoService_"+msg.getDataId());
            System.out.println(msg.getDataId()+" "+ JSON.toJSONString(deviceidList));
        }
    }
}
