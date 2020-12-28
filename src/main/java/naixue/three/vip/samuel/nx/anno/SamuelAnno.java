package naixue.three.vip.samuel.nx.anno;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.ref.SoftReference;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SamuelAnno {
    String value() default "samuel";
}
