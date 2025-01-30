package tobyspring.config.autoconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.MyPropertiesConfiguration;

import java.util.Map;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
    @Bean
    BeanPostProcessor propertyPostProcessor(Environment env) {
        return new BeanPostProcessor() {
            @Override // Bean 초기화 이후에 이 method를 진행해라 -> 모든 bean object를 만들고나서 이게 실행된다.
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                // @MyConfigurationProperties annotation이 붙은 class를 찾고 거기에만 property 주입
                MyPropertiesConfiguration annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyPropertiesConfiguration.class);
                if (annotation == null) return bean;
                Map<String, Object> attrs = AnnotationUtils.getAnnotationAttributes(annotation);
                String prefix = (String) attrs.get("prefix");

                // bind를 시도했는데 없다면, bean.getClass() 의 object를 만들어서 return 한다.
                // bean.getClass()의 property(field)의 이름이 prefix 뒤에 붙은 것과 일치하는지 확인
                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
