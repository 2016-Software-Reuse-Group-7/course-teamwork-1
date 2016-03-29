package TeamSeven.client.socket;

import TeamSeven.common.IMessageType;
import TeamSeven.entity.*;
import TeamSeven.util.SerializeTool;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * Created by joshoy on 16/3/22.
 */
public class ClientSocketImpl extends WebSocketClient implements ClientSocket {

    private Account account = null;
    private boolean accessGranted = false;
    private boolean receivedLoginResponse = false;


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
        try {
            IMessageType parsedMsg = (IMessageType) SerializeTool.ObjectFromString(message);
            if (parsedMsg.getMessageType().equals(ServerResponseChat.messageType)) {
                this.handleRespChat((ServerResponseChat) parsedMsg);
            }
            else if (parsedMsg.getMessageType().equals(ServerResponseAccess.messageType)) {
                this.handleRespAccess((ServerResponseAccess) parsedMsg);
            }
            else if (parsedMsg.getMessageType().equals(ServerResponseChatOK.messageType)) {
                this.handleRespOK((ServerResponseChatOK) parsedMsg);
            }
            else if (parsedMsg.getMessageType().equals(ServerResponseChatOverFrequency.messageType)) {
                this.handleRespOverFreq((ServerResponseChatOverFrequency) parsedMsg);
            }
            else if (parsedMsg.getMessageType().equals(ServerResponseRelogin.messageType)) {
                this.handleRespRelogin((ServerResponseRelogin) parsedMsg);
            }
            else {
                throw new ClassNotFoundException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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

    /* 发送对话 */
    public boolean sendChat(String chatContent) throws IOException {
        if (this.isAccessGranted()) {
            Chat chat = new Chat(chatContent, this.getAccount());
            this.sendMessage(SerializeTool.ObjectToString(chat));
            return true;
        }
        else {
            return false;
        }
    }

    /* 发送登录信息 */
    public boolean sendLogin() throws IOException {
        if (this.isAccessGranted()) {
            System.out.println("请勿重复登录.");
            return false;
        }
        else {
            this.setReceivedLoginResponse(false);
            this.sendMessage(SerializeTool.ObjectToString(this.getAccount()));
            return true;
        }
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    /* 接收到对话消息后, 输出用户名, 消息发送时间, 消息内容 */
    private void handleRespChat(ServerResponseChat resp) {
        String formattedOutput = "[" + resp.getUserName() + " @ " + resp.getChatTime().toString() + "]: " + resp.getMessage();
        System.out.println(formattedOutput);
    }
    /* 登录请求发送后收到的返回消息 */
    private void handleRespAccess(ServerResponseAccess resp) {
        /* 如果成功登录 */
        this.setReceivedLoginResponse(true);
        if (resp.isAccessGrant()) {
            System.out.println("登录成功.");
            this.setAccessGranted(true);
        }
        /* 否则 */
        else {
            System.out.println("登录失败, 请检查您的用户名和密码.");
            this.setAccessGranted(false);
        }
    }

    private void handleRespOK(ServerResponseChatOK resp) {
        return;
    }

    private void handleRespOverFreq(ServerResponseChatOverFrequency resp) {
        System.out.println("您发送消息的频率过快. 请稍后再试.");
    }

    private void handleRespRelogin(ServerResponseRelogin resp) {
        System.out.println("系统要求重新登录. 原因: " + resp.getReason());
        this.account = null;
        this.setAccessGranted(false);
    }

    public boolean isAccessGranted() {
        return accessGranted;
    }

    public boolean receivedLoginResp() {
        return this.receivedLoginResponse;
    }

    private void setReceivedLoginResponse(boolean status) {
        this.receivedLoginResponse = status;
    }

    private void setAccessGranted(boolean accessGranted) {
        this.accessGranted = accessGranted;
    }
}
