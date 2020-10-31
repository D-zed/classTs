package designmode.strategymode;

public class TwoHandle implements StrategyHandle<String>{


    @Override
    public String handle(String parm) {
        System.out.println(parm);
        return "two";
    }
}
