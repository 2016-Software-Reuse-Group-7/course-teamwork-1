package TeamSeven.client;

/**
 * Created by joshoy on 16/3/22.
 */

import TeamSeven.client.socket.ClientSocket;
import TeamSeven.client.socket.ClientSocketImpl;
import TeamSeven.entity.Account;
import TeamSeven.entity.Chat;
import TeamSeven.util.LogTool;
import TeamSeven.util.SerializeTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class ClientWithTimer {
    /*
    * 客户端主函数
    * */
    public static void main(String[] args) throws URISyntaxException, IOException {

        String serverIp = "127.0.0.1";
        Integer port = 8887;
        /* 建立新的URI实例 */
        URI serverUri = new URI("ws://" + serverIp + ":" + port.toString());
        /* 建立新的ClientSocket实例 */
        ClientSocket c = new ClientSocketImpl(serverUri);
        /* 设置用户名 */
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名: ");
        final String userName = sysin.readLine();
        System.out.println("请输入密码: ");
        String password = sysin.readLine();

        c.setAccount(new Account(userName, password));
        c.connect();

        /* 首先需要验证身份 */
        String accountMsg = SerializeTool.ObjectToString(c.getAccount());
        c.sendMessage(accountMsg);


         /* 开始定时记录 */
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                LogTool.log( userName );
            }
        };
        Timer timer = new Timer();
        timer.schedule( task, 0, 60000 );


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
                c = new ClientSocketImpl(serverUri);
                // c.setUsername(userName);
                c.connect();
                System.out.println("[*] 客户端已重启, 目标服务器: " + serverUri.getHost() + ", 输入restart重连, 输入exit退出. 其他聊天消息可直接输入.");
            }
            else {
                /*Chat msg = new Chat(in);
                String encodedMsg = SerializeTool.ObjectToString(msg);
                c.send(encodedMsg);*/
            }
        }
    }
}
