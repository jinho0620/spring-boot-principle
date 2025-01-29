package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.ConditionalOnMyClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalOnMyClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
    // bean의 이름은 기본적으로 factory method 이름을 따라간다.
    // 따라서 같은 이름의 factory method가 있으면 충돌이 날 수 있기 때문에 아래와 같이 이름을 수정할 수 있다.
    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean // 이미 등록된 bean이 있으면 등록 X
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    
}
