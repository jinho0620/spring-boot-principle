package tobyspring.helloboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 기본값 : RetentionPolicy.CLASS -> compile후 .class file까지는 살아있으나, 메모리에 loading되는 Runtime 까지는 적용되지 않는다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // Class, interface (including annotation interface), enum, or record declaration
@Configuration
@ComponentScan
public @interface MySpringBootAnnotation {
}
