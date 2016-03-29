package TeamSeven.server;

import TeamSeven.server.socket.ServerSocket;
import TeamSeven.server.socket.ServerSocketImpl;
import TeamSeven.server.ui.ServerUIOld;
import org.java_websocket.WebSocketImpl;

import java.io.IOException;

/**
 * Created by joshoy on 16/3/22.
 */
public class Server {
    /*
    * 服务端主函数
    * */

    public static void main(String[] args) throws InterruptedException , IOException {
        int port = 8887;
        String ip = "127.0.0.1";

        WebSocketImpl.DEBUG = true;
        /* 启动Server后 */
        ServerSocket ss = new ServerSocketImpl(port);
        new ServerUIOld(ss);
    }

}
