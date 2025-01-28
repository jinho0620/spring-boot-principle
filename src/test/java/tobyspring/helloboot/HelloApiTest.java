package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

public class HelloApiTest {
    @Test
    void helloApi() {
        // http localhost:8000/hello?name=Jinho
        TestRestTemplate rest = new TestRestTemplate(); // 그냥 RestTemplate 은 exception을 던짐

        ResponseEntity<String> res
                = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Jinho");

        // status 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header(content-type) text/plain
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Jinho
        assertThat(res.getBody()).isEqualTo("Hello Jinho");

    }
}
