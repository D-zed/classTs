package designmode.strategymode;

public class OneHandle implements StrategyHandle<Integer>{


    @Override
    public Integer handle(String parm) {
        System.out.println(parm);
         return 1;
    }
}
