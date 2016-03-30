package TeamSeven.server;

import TeamSeven.server.socket.ServerSocket;
import TeamSeven.server.socket.ServerSocketImpl;
import TeamSeven.server.ui.ServerUI;
import org.java_websocket.WebSocketImpl;

import java.io.IOException;
import java.net.UnknownHostException;

import TeamSeven.server.ui.ServerLoginUI;
import TeamSeven.server.ui.ServerUI;
/**
 * Created by joshoy on 16/3/22.
 */
public class Server {
    /*
    * 服务端主函数
    * */
    private ServerSocket ss; // = new ServerSocketImpl(port);
    private int port;
    // s.start();

    public Server() {

        // TODO: 端口可设置
        this.port = 8887;

        // 开启新的ServerSocket
        try {
            ss = new ServerSocketImpl(this.port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
     new ServerLoginUI();

    }

    public static void main(String[] args) throws InterruptedException , IOException {
        int port = 8887;
        String ip = "127.0.0.1";

        WebSocketImpl.DEBUG = true;
        /* 启动Server后 */
        ServerSocket ss = new ServerSocketImpl(port);
        new ServerUI(ss);
    }

}
