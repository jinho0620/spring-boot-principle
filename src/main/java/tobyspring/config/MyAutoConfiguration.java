package tobyspring.config;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// 하위에 bean method를 호출하면 해당 object가 매번 생성된다. (singleton이 아니게 됨)
@Configuration(proxyBeanMethods = false)
public @interface MyAutoConfiguration {
}
