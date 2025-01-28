package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

// unit test
public class HelloControllerTest {
    @Test
    void helloController() {
        // separate logic from service
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Jinho");

        assertThat(ret).isEqualTo("Jinho");
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> name);

        assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
