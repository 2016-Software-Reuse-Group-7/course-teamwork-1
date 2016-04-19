package TeamSeven.server;

/**
 * Created by zhao on 2016/4/19.
 */
public class ConfigBean {
    private String host;
    private int port;
    private int maxConnection;
    private int maxMessagePerSecond;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxConnection() {
        return maxConnection;
    }

    public void setMaxConnection(int maxConnection) {
        this.maxConnection = maxConnection;
    }

    public int getMaxMessagePerSecond() {
        return maxMessagePerSecond;
    }

    public void setMaxMessagePerSecond(int maxMessagePerSecond) {
        this.maxMessagePerSecond = maxMessagePerSecond;
    }
}
