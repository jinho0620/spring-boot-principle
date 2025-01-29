package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import tobyspring.config.ConditionalOnMyClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalOnMyClass("io.undertow.Undertow")
public class UndertowWebServerConfig {
    @Bean("undertowWebServer")
    public ServletWebServerFactory servletWebServerFactory() {
        return new UndertowServletWebServerFactory();
    }
}
