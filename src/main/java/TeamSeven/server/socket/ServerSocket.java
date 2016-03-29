package TeamSeven.server.socket;

import TeamSeven.common.ITextAreaAppendable;
import TeamSeven.entity.Account;
import TeamSeven.entity.Chat;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.io.IOException;

/**
 * Created by joshoy on 16/3/27.
 */
public interface ServerSocket {
    public void onOpen(WebSocket conn, ClientHandshake handshake);
    public void onClose(WebSocket conn, int code, String reason, boolean remote /* 是否由远端(客户端)发起 */);
    public void onMessage(WebSocket conn, String message);
    public void onError(WebSocket conn, Exception ex);
    public void sendToAll(String msg);
    public void start();
    public void stop() throws IOException, InterruptedException;
    public int getPort();
    public void setTextAreaUI(ITextAreaAppendable ui);
    public void printLineToUITextArea(String text);
}
