package TeamSeven.client;

import org.java_websocket.WebSocket;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/22.
 */
public class Account implements Serializable {

    private String userName;
    private String password;

    public Account(String userName, String password) {
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
