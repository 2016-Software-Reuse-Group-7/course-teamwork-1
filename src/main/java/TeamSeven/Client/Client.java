package TeamSeven.client;

/**
 * Created by joshoy on 16/3/22.
 */

import TeamSeven.client.socket.ClientSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class Client {
    /*
    * 客户端主函数
    * */
    public static void main(String[] args) throws URISyntaxException, IOException {

        String serverIp = "";
        Integer port = 8887;

        try {
            if (!args[0].isEmpty()) {
                serverIp = args[0];
            }
            else {
                serverIp = "127.0.0.1";
            }
            if (!args[1].isEmpty()) {
                port = Integer.parseInt(args[1]);
            }
            else {
                port = 8887;
            }
        } catch (Exception ex) {
            serverIp = "127.0.0.1";
            port = 8887;
        }
        /* 建立新的URI实例 */
        URI serverUri = new URI("ws://" + serverIp + ":" + port.toString());
        /* 建立新的ClientSocket实例 */
        ClientSocket c = new ClientSocket(serverUri);
        /* 设置用户名 */
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名(不输入为匿名用户): ");
        String userName = sysin.readLine();
        c.setUsername(userName);

        c.connect();
        /* 启动Client后 */
        System.out.println("[*] 客户端已启动, 目标服务器: " + serverUri.getHost() + ", 输入restart重连, 输入exit退出. 其他聊天消息可直接输入.");

        while (true) {
            String in = sysin.readLine();
            if (in.equals("exit")) {
                c.close();
                System.out.println("[*] 连接中断.");
                break;
            } else if (in.equals("restart")) {
                c.close();
                c = new ClientSocket(serverUri);
                c.setUsername(userName);
                c.connect();
                System.out.println("[*] 客户端已重启, 目标服务器: " + serverUri.getHost() + ", 输入restart重连, 输入exit退出. 其他聊天消息可直接输入.");
            }
            else {
                c.sendMessage(in);
            }
        }
    }
}
