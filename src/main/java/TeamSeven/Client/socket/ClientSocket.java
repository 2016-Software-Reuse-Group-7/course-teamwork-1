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
    * 客户端状态转移至收到消息
    * */
    @Override
    public void onMessage( String message ) {
        // TODO
        System.out.println( "received: " + message );
    }

    /*
    * 客户端状态转移至关闭连接
    * */
    @Override
    public void onClose(int code, String reason, boolean remote /* 是否由服务器端关闭 */) {
        // TODO
        System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) );
    }

    /*
    * 客户端状态转移至访问出错
    * */
    @Override
    public void onError(Exception ex) {
        // TODO
        ex.printStackTrace();
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        System.out.println( "Opened connection" );
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }


}
