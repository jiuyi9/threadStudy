package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD , ElementType.TYPE })
public @interface MyAnnotion {
	//为注解定义属性值
	String color();
	//带缺省值
	String name() default "zxt" ;
}
