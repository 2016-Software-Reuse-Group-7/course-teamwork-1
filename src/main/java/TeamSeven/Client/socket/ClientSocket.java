package TeamSeven.client.socket;

import TeamSeven.entity.Account;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Created by joshoy on 16/3/27.
 */
public interface ClientSocket {
    public void onOpen(ServerHandshake handshakeData);
    public void onMessage( String message );
    public void onClose(int code, String reason, boolean remote /* 是否由服务器端关闭 */);
    public void onError(Exception ex);
    public void sendMessage(String msg);
    public void setAccount(Account account);
    public Account getAccount();
    public void connect();
    public void close();
}
