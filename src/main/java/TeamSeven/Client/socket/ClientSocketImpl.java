package TeamSeven.Client.socket;

import TeamSeven.entity.Account;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

/**
 * Created by joshoy on 16/3/22.
 */
public class ClientSocketImpl extends WebSocketClient implements ClientSocket {

    private Account account;

    public ClientSocketImpl(URI serverURI) {
        super(serverURI);
    }

    public ClientSocketImpl(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public ClientSocketImpl(URI serverUri, Draft draft, Map<String, String> headers, int connecttimeout) {
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
    public void onMessage(String message) {
        
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

    /* 发送消息(序列化后) */
    public void sendMessage(String msg) {
        this.send(msg);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }
}
