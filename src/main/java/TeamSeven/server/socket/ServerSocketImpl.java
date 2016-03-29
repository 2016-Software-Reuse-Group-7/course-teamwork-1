package TeamSeven.server.socket;

/**
 * Created by joshoy on 16/3/22.
 */

import TeamSeven.common.IMessageType;
import TeamSeven.common.ITextAreaAppendable;
import TeamSeven.entity.*;
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


    protected int port;
    protected ITextAreaAppendable ui;
    // private List<ClientConnectionSocket> clientConnectionList;

    public ServerSocketImpl(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    // clientConnectionList = new ArrayList<ClientConnectionSocket>();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // this.sendToAll("new connection: " + handshake.getResourceDescriptor());
        // System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
        // this.printLineToUITextArea(conn.getRemoteSocketAddress().getAddress().getHostAddress() + "进入了房间.");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote /* 是否由远端(客户端)发起 */) {
        // this.sendToAll(conn + " has left the room!");
        System.out.println(conn + " has left the room!");
        this.printLineToUITextArea(conn.getRemoteSocketAddress() + "离开了房间.");
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
        /* 这里需要加验证 */
        // TODO Send Response
        Date nowDate = new Date();
        if (!VerificationTool.checkOverFrequency(chatObj.getAccount(), nowDate)){
            // Send OverFrequency
            System.out.println("[!] Chat Overfrequent.");
            return;
        }

        if (!VerificationTool.checkOutOfLimit(chatObj.getAccount(), nowDate)){
            //Send Relogin
            System.out.println("[!] Need Relogin.");
            return;
        }

        ServerResponseChatOK respOk = new ServerResponseChatOK();
        conn.send(SerializeTool.ObjectToString(respOk));
        ServerResponseChat respChat = new ServerResponseChat();
        respChat.setChatTime(new Date());
        respChat.setMessage(chatObj.getContent());
        respChat.setUserName(chatObj.getAccount().getUserName());
        this.sendToAll(SerializeTool.ObjectToString(respChat));
        this.printLineToUITextArea("[" + chatObj.getAccount().getUserName() + "@"
                + (new Date()).toString() + "]: " + chatObj.getContent());
    }

    /* 验证账号是否合法 */
    private void handleAccount(Account accountObj, WebSocket conn) throws IOException {
        ServerResponseAccess sr = null;

        if (VerificationTool.registerLoggedAccount(accountObj)) {
            sr = new ServerResponseAccess(true);
            conn.send(SerializeTool.ObjectToString(sr));

            this.printLineToUITextArea(accountObj.getUserName() + "@" + (new Date()).toString()
                    + "(" + conn.getRemoteSocketAddress().getAddress().getHostAddress() + ")"
                    + "进入了房间.");
            ServerResponseChat chat = new ServerResponseChat();
            chat.setChatTime(new Date());
            chat.setMessage(accountObj.getUserName() + "进入了房间.");
            chat.setUserName("[系统消息]");
            this.sendToAll(SerializeTool.ObjectToString(chat));
        }
        else {
            sr = new ServerResponseAccess(false);
            conn.send(SerializeTool.ObjectToString(sr));
        }
    }

    public int getPort() {
        return this.port;
    }

    public void setTextAreaUI(ITextAreaAppendable ui) {
        this.ui = ui;
    }

    public void printLineToUITextArea(String text) {
        if (null != this.ui) {
            this.ui.appendTextLine(text);
        }
    }

    public void sendBoardcast(String text) {
        ServerResponseChat respChat = new ServerResponseChat();
        respChat.setChatTime(new Date());
        respChat.setMessage(text);
        respChat.setUserName("系统消息");
        this.printLineToUITextArea("[系统消息 @ " + (new Date()).toString() + "]: " + text);
        try {
            this.sendToAll(SerializeTool.ObjectToString(respChat));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*else if(VerificationTool.checkAccount(accountObj) == 0){
            conn.send("Wrong password or username !");
            }else{
            conn.send("Duplicate login.Please protect your password~");
        }*/
    }
}
