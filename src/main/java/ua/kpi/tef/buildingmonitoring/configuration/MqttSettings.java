package ua.kpi.tef.buildingmonitoring.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Primary
@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttSettings {

    private String hostname;
    private int port;
    private String username;
    private String password;
    private String inTopic;
    private String outTopic;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInTopic() {
        return inTopic;
    }

    public MqttSettings setInTopic(String inTopic) {
        this.inTopic = inTopic;
        return this;
    }

    public String getOutTopic() {
        return outTopic;
    }

    public MqttSettings setOutTopic(String outTopic) {
        this.outTopic = outTopic;
        return this;
    }
}


