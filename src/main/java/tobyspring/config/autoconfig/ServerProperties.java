package tobyspring.config.autoconfig;

import tobyspring.config.MyPropertiesConfiguration;

//@Component
@MyPropertiesConfiguration(prefix = "server")
public class ServerProperties {
    private String contextPath; // server.contextPath

    private int port; // server.port

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
