package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary // 여러 개의 후보 구현체가 있을 때, 우선적으로 주입
public class HelloDecorator implements HelloService {
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
