package TeamSeven.client;

import org.java_websocket.WebSocket;

/**
 * Created by joshoy on 16/3/22.
 */
public class Logger {

    private String userName;
    private String password;

    public Logger(String userName, String password) {
        // TODO
        this.userName = userName;
        this.password = password;
    }

    /*
    * 客户端登录时调用
    * */
    public boolean Login(WebSocket ws) {
        // TODO
        return false;
    }
}
