package naixue.three.vip.samuel.anno.fruit;

import java.lang.reflect.Field;

public class FruitInfoUtil {
    public static void getInfo(Class<Apple> clazz) {

        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = field.getAnnotation(FruitName.class);
                System.out.println("水果名称"+fruitName.value());
            }
        }
    }
}
