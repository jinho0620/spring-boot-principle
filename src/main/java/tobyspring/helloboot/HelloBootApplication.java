package tobyspring.helloboot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;



@SpringBootApplication
public class HelloBootApplication {

	// main method 실행이 끝나고 실행되는 method
//	@Bean
//	ApplicationRunner applicationRunner(Environment env) { // Environment : 환경 설정 정보를 추상화 해놓은 bean
//		// interface에 method가 1개만 있을 때는 lambda식으로 쓸 수 있다.
//		return args -> {
//			String name = env.getProperty("my.name");
//			System.out.println("my.name: " + name);
//		};
//	}

	private final JdbcTemplate jdbcTemplate;

	public HelloBootApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostConstruct
	void init() {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name VARCHAR(50) primary key, count INT)");
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloBootApplication.class);
	}
}
