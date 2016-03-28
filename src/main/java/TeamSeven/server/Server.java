package TeamSeven.server;

import TeamSeven.server.socket.ServerSocket;
import TeamSeven.server.socket.ServerSocketImpl;
import org.java_websocket.WebSocketImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.io.IOException;

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

        final JFrame jf = new JFrame("Server is on...");
        jf.setBounds(410,150, 600, 400);
        jf.setResizable(false);
        jf.setLayout(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextArea jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);//激活自动换行功能
        jTextArea.setWrapStyleWord(true);//激活断行不断字功能
        jTextArea.setEditable(false); //为JTextArea添加滚动条

        JScrollPane jsp = new JScrollPane(jTextArea);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        jsp.setBounds(50, 30, 440, 200);
        jf.add(jsp);

        JButton bexit = new JButton("Exit");
        bexit.setBounds(200, 275, 100, 30);
        bexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date day = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jTextArea.append(df.format(day)+"\n");
                jTextArea.append("server log out. "+"\n\n");
                /* ServerSocket关闭 */
                try {
                    ss.stop();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                jf.dispose();
            }
        });
        jf.add(bexit);

        JButton brestart = new JButton("Restart");
        brestart.setBounds(50, 275, 100, 30);
        brestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new Server();
            }
        });
        jf.add(brestart);

        // 显示窗口
        jf.setVisible(true);
    }


    public static void main(String[] args) throws InterruptedException , IOException {
        WebSocketImpl.DEBUG = true;
        /* 启动Server后 */
        new Server();
    }

    /*
    public static void mainOld(String[] args) throws IOException, InterruptedException {
        WebSocketImpl.DEBUG = true;
        int port = 8887;

        System.out.println("[*] 服务器已启动, 端口" + port + ", 输入restart重启, 输入exit退出. 其他广播消息可直接输入.");
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        ServerSocket s = new ServerSocketImpl(port);

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
    */

}
