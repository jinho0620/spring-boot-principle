package tobyspring.helloboot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootApplication;


@MySpringBootApplication
public class HelloBootApplication {

	@Bean
	ApplicationRunner applicationRunner(Environment env) { // Environment : 환경 설정 정보를 추상화 해놓은 bean
		// interface에 method가 1개만 있을 때는 lambda식으로 쓸 수 있다.
		return args -> {
			String name = env.getProperty("my.name");
			System.out.println("my.name: " + name);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloBootApplication.class);
	}
}
