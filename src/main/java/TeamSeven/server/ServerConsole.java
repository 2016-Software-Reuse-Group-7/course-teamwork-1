package TeamSeven.server;

import TeamSeven.server.socket.ServerSocket;
import TeamSeven.server.socket.ServerSocketImpl;
import org.java_websocket.WebSocketImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by joshoy on 16/3/29.
 */
public class ServerConsole {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebSocketImpl.DEBUG = true;
        int port = 8887;

        System.out.println("[*] 服务器已启动, 端口" + port + ", 输入restart重启, 输入exit退出. 其他广播消息可直接输入.");
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        ServerSocket s = new ServerSocketImpl(port);
        s.start();

        while (true) {
            String in = sysin.readLine();
            if (in.equals("exit")) {
                s.stop();
                System.out.println("[*] 服务器已退出.");
                break;
            } else if (in.equals("restart")) {
                s.stop();
                s = new ServerSocketImpl(port);
                s.start();
                System.out.println("[*] 服务器已重启, 输入restart重启, 输入exit退出. 其他广播消息可直接输入.");
            }
            else {
                System.out.println("[*] [系统广播] " + in);
                s.sendToAll("[系统广播] " + in);
            }
        }
    }
}
