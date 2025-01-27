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
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;


@SpringBootApplication
public class HelloBootApplication {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext(); // spring container
		applicationContext.registerBean(HelloController.class);
		applicationContext.refresh(); // create bean object when the spring container starts up


//		TomcatServletWebServerFactory 자체가 tomcat servlet web server는 아니다.
//		tomcat servlet web server를 만드는 복잡한 생성과정과 설정을 하고, 생성 요청을 하면 생성을 해준다.
//		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
//		아래와 같이 Jetty 로 web servlet container 를 갈아낄 수도 있다. 이를 위해 받는 type을 interface로 바꿔준다.
//		ServletWebServerFactory serverFactory = new JettyServletWebServerFactory();
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		WebServer webServer = serverFactory.getWebServer(servletContext -> {
				servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어처리와 같은 공통 기능들
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						String name = req.getParameter("name");

						HelloController helloController = applicationContext.getBean(HelloController.class);
						String ret = helloController.hello(name);

						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");
        });
		webServer.start(); // localhost:8080 에 접근하면 404를 던진다. -> tomcat 이 잘 떴음을 확인할 수 있다.


	}

}
