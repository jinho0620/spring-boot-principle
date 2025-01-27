package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService;

    // spring container looks up beans if there's a class that implements HelloService and injects it.
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
