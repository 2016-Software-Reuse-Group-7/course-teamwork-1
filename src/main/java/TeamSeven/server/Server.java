package TeamSeven.server;

import TeamSeven.server.socket.ServerSocket;
import TeamSeven.server.socket.ServerSocketImpl;
import org.java_websocket.WebSocketImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            bexit.setBounds(200, 275, 100, 30);
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
            JButton brestart = new JButton("Restart");
            brestart.setBounds(50, 275, 100, 30);
            brestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new Server();
            }
        });
        jf.add(brestart);

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
        new Server();
    }



}
