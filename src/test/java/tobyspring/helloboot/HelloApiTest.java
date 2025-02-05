package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // Tomcat을 properties에 있는 port에 띄움
@Transactional
public class HelloApiTest {
    @Test
    void helloApi() {
        // http localhost:8000/hello?name=Jinho
        TestRestTemplate rest = new TestRestTemplate(); // 그냥 RestTemplate 은 exception을 던짐

        ResponseEntity<String> res
                = rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "Jinho");

        // status 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header(content-type) text/plain
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Jinho
        assertThat(res.getBody()).isEqualTo("*Hello Jinho*");

    }

    @Test
    void failsHelloApi() {
        // http localhost:8000/hello?name=Jinho
        TestRestTemplate rest = new TestRestTemplate(); // 그냥 RestTemplate 은 exception을 던짐

        ResponseEntity<String> res
                = rest.getForEntity("http://localhost:9090/app/hello?name=", String.class);

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
