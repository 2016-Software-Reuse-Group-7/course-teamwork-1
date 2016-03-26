package TeamSeven.client.socket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

/**
 * Created by joshoy on 16/3/22.
 */
public class ClientSocket extends WebSocketClient {



    private String Username;

    public ClientSocket(URI serverURI) {
        super(serverURI);
    }

    public ClientSocket(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public ClientSocket(URI serverUri, Draft draft, Map<String, String> headers, int connecttimeout) {
        super(serverUri, draft, headers, connecttimeout);
    }

    /*
    * 客户端状态转移至连接开始
    * */
    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("连接已建立. 远端服务器URI: " + this.getURI());
    }

    /*
    * 客户端状态转移至收到消息
    * */
    @Override
    public void onMessage( String message ) {
        System.out.println(message);
    }

    /*
    * 客户端状态转移至关闭连接
    * */
    @Override
    public void onClose(int code, String reason, boolean remote /* 是否由服务器端关闭 */) {
        System.out.println("连接已由" + ( remote ? "远端" : "本地" ) + "关闭.");
    }

    /*
    * 客户端状态转移至连接出错
    * */
    @Override
    public void onError(Exception ex) {
        System.out.println("连接出错.");
        ex.printStackTrace();
    }

    public void sendMessage(String msg) {
        this.send(msg);
        // System.out.println("[" + this.getUsername() + "(你)]: " + msg);
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String username) {
        if ((username != null) && (!username.trim().equals(""))){
            this.Username = username.trim();
        }
        else {
            this.Username = "匿名用户";
        }
    }

}
