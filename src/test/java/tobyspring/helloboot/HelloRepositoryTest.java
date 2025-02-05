package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired
    HelloRepository helloRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

//    @BeforeEach
//    void init() {
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name VARCHAR(50) primary key, count INT)");
//    }


    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("Jinho")).isNull();
    }

    @Test
    void increaseCount() {
        assertThat(helloRepository.countOf("Jinho")).isEqualTo(0);
        helloRepository.increaseCount("Jinho");
        assertThat(helloRepository.countOf("Jinho")).isEqualTo(1);
        helloRepository.increaseCount("Jinho");
        assertThat(helloRepository.countOf("Jinho")).isEqualTo(2);
    }

}
