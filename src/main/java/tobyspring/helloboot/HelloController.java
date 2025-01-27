package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class HelloController {
//    @GetMapping("/hello") // Can take query parameter like /hello?name=Jinho
    public String hello(String name) {
        return "Hello " + name;
    }
}
