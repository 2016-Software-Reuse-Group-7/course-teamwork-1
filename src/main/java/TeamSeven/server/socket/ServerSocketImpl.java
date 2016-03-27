package TeamSeven.server.socket;

/**
 * Created by joshoy on 16/3/22.
 */

import TeamSeven.common.IMessageType;
import TeamSeven.entity.Account;
import TeamSeven.entity.Chat;
import TeamSeven.entity.ServerResponseAccess;
import TeamSeven.util.SerializeTool;
import TeamSeven.util.VerificationTool;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.*;

public class ServerSocketImpl extends WebSocketServer implements ServerSocket {

    // private List<ClientConnectionSocket> clientConnectionList;

    public ServerSocketImpl(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        // clientConnectionList = new ArrayList<ClientConnectionSocket>();
    }

    public ServerSocketImpl(InetSocketAddress address) {
        super(address);
        // clientConnectionList = new ArrayList<ClientConnectionSocket>();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
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
        // this.sendToAll(message);
        // System.out.println(conn + ": " + message);
        try {
            IMessageType parsedObj = (IMessageType)SerializeTool.ObjectFromString(message);  // May throws Exceptions
            if (parsedObj.getMessageType().equals(Chat.messageType)) {
                this.handleChat((Chat)parsedObj, conn);
            }
            // TODO: Add other message types
            else if (parsedObj.getMessageType().equals(Account.messageType)) {
                this.handleAccount((Account)parsedObj, conn);
            }
            else {
                throw new ClassNotFoundException("MessageType Not Found in current object class!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    public void sendToAll(String msg) {
        Collection<WebSocket> con = this.connections();
        synchronized (con) {
            for (WebSocket c : con) {
                c.send(msg);
            }
        }
    }

    /* 收到的消息类型为聊天 */
    private void handleChat(Chat chatObj, WebSocket conn) throws IOException {
        /* TODO: 这里需要加验证 */
        this.sendToAll(SerializeTool.ObjectToString(chatObj));
    }

    /* 验证账号是否合法 */
    private void handleAccount(Account accountObj, WebSocket conn) throws IOException {
        ServerResponseAccess sr = null;
        if (VerificationTool.checkAccount(accountObj)) {
            sr = new ServerResponseAccess(true);
            conn.send(SerializeTool.ObjectToString(sr));
        }
        else {
            conn.send("");
        }
    }
}
