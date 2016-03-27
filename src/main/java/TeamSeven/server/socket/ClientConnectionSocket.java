package TeamSeven.server.socket;

import TeamSeven.entity.Account;
import org.java_websocket.WebSocket;

/**
 * Created by joshoy on 16/3/27.
 */

/*
* 由于原生的WebSocket并不自带账号验证功能,
* 因此我们用一个类封装一个WebSocket以及其身份信息.
* */
public class ClientConnectionSocket {

    /* 封装一个WebSocket连接 */
    private WebSocket connection;
    /* 是否通过身份验证 */
    private boolean isGrantAccess;
    /* 连接的身份信息 */
    private Account account;

    public ClientConnectionSocket(WebSocket conn, Account account) {
        this.setConnection(conn);
        this.setAccount(account);
    }

    public WebSocket getConnection() {
        return connection;
    }

    public void setConnection(WebSocket connection) {
        this.connection = connection;
    }

    public boolean isGrantAccess() {
        return isGrantAccess;
    }

    private void setGrantAccess(boolean grantAccess) {
        this.isGrantAccess = grantAccess;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
