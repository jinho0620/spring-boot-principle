package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    void configuration() {
//        MyConfig myConfig = new MyConfig();

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);
        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        Common common() {
            if (this.common == null) this.common = super.common();

            return common;
        }
    }

    // 기본설정 proxyBeanMethods = true -> configuration class를 만들 때
    // spring container는 자동으로 해당 class의 proxyBean을 만든다. (위의 MyConfigProxy와 같은 class)
    // 그래서 이미 만들어놓은 객체를 사용한다.
    // 하지만 아래와 같이 false로 바꾸면 매번 Common이라는 bean을 새로 생성한다.
    // Bean1 과 Bean2이 만약 Common을 dependency로 갖지 않으면 굳이 proxy class를 만들어 비용을 늘일 필요가 없다.
    @Configuration(proxyBeanMethods = false)
    static class MyConfig {
        @Bean
        Common common() { // Factory method
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    private static class Common {

    }

    // Bean1
}
