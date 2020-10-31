package designmode.strategymode;

public class StrategyMode {

    public <T> T query(StrategyHandle<T> strategyHandle,String param){
        T handle = strategyHandle.handle(param);
        return handle;
    }

    public static void main(String[] args) {
        StrategyMode strategyMode=new StrategyMode();

        OneHandle oneHandle=new OneHandle();
        Integer one = strategyMode.query(oneHandle, "one");
        System.out.println(one);
        System.out.println("--------------策略2-----------------------");
        TwoHandle twoHandle=new TwoHandle();
        String two = strategyMode.query(twoHandle, "two");
        System.out.println(two);



    }
}


