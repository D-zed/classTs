package naixue.three.vip.samuel.anno.fruit;


import naixue.three.vip.samuel.anno.ColorEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitColor {
    ColorEnum fruitColor() default ColorEnum.RED;
}
