package naixue.three.vip.samuel.nx.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by glen on 2020/11/15.
 * 定义getter和setter的注解
 */
@Retention(RetentionPolicy.SOURCE) //注解只是在源码中保留
@Target(ElementType.TYPE) //用于修饰类
public @interface LombokData {
}
