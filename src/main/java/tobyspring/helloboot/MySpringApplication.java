package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {

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
        applicationContext.register(applicationClass); // 여기서 부터 읽기 시작해라
        applicationContext.refresh(); // create bean object when the spring container starts up

    }
}
