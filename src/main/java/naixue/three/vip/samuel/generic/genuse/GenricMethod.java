package naixue.three.vip.samuel.generic.genuse;

/**
 * 类上的泛型和方法上的泛型
 * @param <K>
 * @param <V>
 */
public class GenricMethod<K,V> {


    /**
     * 1、实体方法使用类上定义的泛型
     * @param k
     * @param v
     * @return
     */
    public K method1(K k,V v){
        return null;
    }

    public <T> T method02(){ //2、方法上定义泛型
        return null;
    }

    /**
     * 3、静态方法不能使用类上定义的泛型
     * 类上定义的泛型，需要new的时候才知道具体的泛型类别
     * staic
     * @return
     */
//    public static K method04(){
//        return null;
//    }

    public static <E> E method05(){
        return null;
    }


}
