package tobyspring.helloboot;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@RestController // @ResponseBody를 포함 -> 하위에 있는 모든 method의 return 값을 body 에 넣어서 return 한다.
@RequestMapping("/hello")
public class HelloController {
    private final HelloService helloService;

    // spring container looks up beans if there's a class that implements HelloService and injects it.
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
//    @ResponseBody // string return하게 되면 dispatcher servlet이 해당 이름에 맞는 view를 찾으려고 하기 때문에 해당 annotation을 달아서 body에 넣어준다.
//    @RequestMapping(value = "/hello", method = RequestMethod.GET) // 윗줄과 같다.
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
