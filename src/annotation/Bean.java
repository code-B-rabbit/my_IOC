package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Target({METHOD,TYPE})         //既可以注解在类上,又可以注解到方法上
@Retention(RetentionPolicy.RUNTIME)   //运行时可以检测出注解
public @interface Bean {
}
