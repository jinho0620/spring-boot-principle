package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name VARCHAR(50) primary key, count INT)");
    }

    @Test
    void insertAndQuery() {
        jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "Jinho", 3);
        jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "Jisung", 1);

        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM hello", Long.class);
        Assertions.assertThat(count).isEqualTo(2);
    }
    @Test // 위의 test가 끝나고 rollback이 제대로 되었는지 확인하기 위함
    void insertAndQuery2() {
        jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "Jinho", 3);
        jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "Jisung", 1);

        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM hello", Long.class);
        Assertions.assertThat(count).isEqualTo(2);
    }
}
