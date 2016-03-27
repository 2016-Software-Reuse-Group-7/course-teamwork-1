package TeamSeven.server;

import TeamSeven.server.socket.ServerSocket;
import TeamSeven.server.socket.ServerSocketImpl;
import org.java_websocket.WebSocketImpl;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by joshoy on 16/3/22.
 */
public class Server {
    /*
    * 服务端主函数
    * */
    public Server() {

            final JFrame jf = new JFrame("Server is on...");
            jf.setBounds(410,150, 600, 400);
            jf.setResizable(false);
            jf.setLayout(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            final JTextArea jTextArea = new JTextArea();
            jTextArea.setLineWrap(true);//激活自动换行功能
            jTextArea.setWrapStyleWord(true);//激活断行不断字功能
            jTextArea.setEditable(false);
            //为JTextArea添加滚动条
            JScrollPane jsp = new JScrollPane(jTextArea);
            jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
            jsp.setBounds(50, 30, 440, 200);

            jf.add(jsp);

            JButton bexit = new JButton("Exit");
            bexit.setBounds(200, 275, 150, 30);
            bexit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Date day=new Date();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    jTextArea.append(df.format(day)+"\n");
                    jTextArea.append("server log out. "+"\n\n");
                    jf.dispose();
                }
            });
            jf.add(bexit);

            jf.setVisible(true);

    }
    public static void main(String[] args) throws InterruptedException , IOException {
        WebSocketImpl.DEBUG = true;
        int port = 8887;

        try {
            /* 自定义端口 */
            port = Integer.parseInt(args[0]);
        } catch ( Exception ex ) {
        }

        ServerSocket s = new ServerSocketImpl(port);
        s.start();

        /* 启动Server后 */
        System.out.println("[*] 服务器已启动, 端口" + port + ", 输入restart重启, 输入exit退出. 其他广播消息可直接输入.");
        new Server();
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
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
