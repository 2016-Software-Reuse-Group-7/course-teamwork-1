package TeamSeven.client;

import TeamSeven.client.socket.ClientSocket;
import TeamSeven.client.socket.ClientSocketImpl;
import TeamSeven.entity.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by joshoy on 16/3/29.
 */
public class ClientConsole {
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
        ClientSocket c = null;
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));

        boolean needLogin = true;
        while (needLogin) {
            c = new ClientSocketImpl(serverUri);
            c.connect();
            /* 设置用户名 */
            System.out.println("请输入用户名: ");
            String userName = sysin.readLine();
            System.out.println("请输入密码: ");
            String password = sysin.readLine();

            c.setAccount(new Account(userName, password));

            /* 首先需要验证身份 */
            c.sendLogin();
            while (true) {
                try {
                    System.out.println("登录中...");
                    Thread.sleep(1000);
                    if (c.receivedLoginResp() && c.isAccessGranted()) {
                        needLogin = false;
                        break;
                    }
                    else if ( c.receivedLoginResp() && (!c.isAccessGranted()) ) {
                        System.out.println("登录失败.");
                        needLogin = true;
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        /* 启动Client后 */
        System.out.println("[*] 客户端已启动, 目标服务器: " + serverUri.getHost() + ", 输入exit退出. 其他聊天消息可直接输入.");

        while (false == needLogin) {
            String in = sysin.readLine();
            if (in.equals("exit")) {
                c.close();
                System.out.println("[*] 连接中断.");
                break;
            }
            else {
                c.sendChat(in);
            }
        }
        /*
        new Client(serverUri);
        */
    }
}
