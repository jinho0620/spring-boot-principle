package tobyspring.helloboot;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;


@Configuration // 이 class는 구성 정보를 가진 class다. -> spring container가 이 안에 @bean이 붙은 factory method가 있겠구나라고 판단.
@ComponentScan // 이 annotation이 붙은 package부터 하위 package들의 @Component가 붙은 class를 전부 뒤져서 bean으로 등록
public class HelloBootApplication {
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	public static void main(String[] args) {
		// annotation이 붙은 java code를 읽어서 구성 정보를 읽어오는 spring container
		// @Configuration이 붙은 class를 가장 먼저 등록
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() { // refresh() method가 실행되는 시점에 hook으로 진행되게 된다.
				super.onRefresh();

				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
				// dispatcher servlet은 호출할 controller가 필요하기 때문에 spring container가 필요하다.
				// -> setter로 spring container를 주입
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", dispatcherServlet)
							.addMapping("/*");
				});
				webServer.start(); // localhost:8080 에 접근하면 404를 던진다. -> tomcat 이 잘 떴음을 확인할 수 있다.

			}
		};
		applicationContext.register(HelloBootApplication.class); // 여기서 부터 읽기 시작해라
		applicationContext.refresh(); // create bean object when the spring container starts up

	}

}
