package Ts_2_25;

public class DANLI {
    private DANLI danli=null;
    //私有化构造方法
    private DANLI(){
    }
    public DANLI getDanli(){
        synchronized (danli){
            if (danli==null){
                synchronized (this){
                    danli=new DANLI();
                }
            }
        }
        return danli;
    }

}
