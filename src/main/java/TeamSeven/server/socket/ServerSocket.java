package TeamSeven.server.socket;

/**
 * Created by joshoy on 16/3/22.
 */

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

public class ServerSocket extends WebSocketServer {

    public ServerSocket(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public ServerSocket(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
        this.sendToAll("new connection: " + handshake.getResourceDescriptor());
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote /* 是否由远端(客户端)发起 */) {
        this.sendToAll(conn + " has left the room!");
        System.out.println(conn + " has left the room!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        this.sendToAll(message);
        System.out.println(conn + ": " + message);
    }

    // @Override
    public void onFragment(WebSocket conn, Framedata fragment) {
        System.out.println("received fragment: " + fragment);
    }

    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    public void sendToAll(String msg) {
        Collection<WebSocket> con = connections();
        synchronized (con) {
            for (WebSocket c : con) {
                c.send(msg);
            }
        }
    }
}
