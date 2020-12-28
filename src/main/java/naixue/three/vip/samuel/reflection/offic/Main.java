package naixue.three.vip.samuel.reflection.offic;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        String key = "Word";
        long startTime = System.currentTimeMillis();
        for (int i=0;i<1000000;i++) {
//            WPS wps = getInstanceByKey(key);//21
            WPS wps = getInstanceByKeyReflect(key);//1641
//            wps.processDocument();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

    /**
     * 简单工厂设计模式
     * @return
     */
    public static WPS getInstanceByKey(String key){
        switch (key){
            case "Word":
                return new Word();
            case  "Excel":
                return new Excel();
            case  "Pdf":
                return new Pdf();
            default:
                return null;
        }
    }

    public static WPS getInstanceByKeyReflect(String key){
        WPS wps = null;
        String packageName = "com.nx.vip.samuel.reflection.offic";
        String fullClass = packageName+"."+key;
        try {
            Class<?> clazz = Class.forName(fullClass);
            wps = (WPS) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wps;

    }

}
