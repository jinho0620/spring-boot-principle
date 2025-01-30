package tobyspring.helloboot;

public interface HelloService {
    String sayHello(String name);

    // 구현하지 않으면 return 0
    default int countOf(String name) {
        return 0;
    }
}
