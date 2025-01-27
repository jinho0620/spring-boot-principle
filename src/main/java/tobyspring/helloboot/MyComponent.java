package tobyspring.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// meta annotation들
@Retention(RetentionPolicy.RUNTIME) // 이 annotation이 언제까지 살아있을 것인가?
@Target(ElementType.TYPE) // annotation을 적용할 대상을 지정한다. 여기서는 class나 interface 같은 type에 붙인다.
@Component
public @interface MyComponent {
}
