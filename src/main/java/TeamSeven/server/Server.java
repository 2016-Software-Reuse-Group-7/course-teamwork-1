package TeamSeven.server;

import TeamSeven.server.socket.ServerSocket;
import TeamSeven.server.socket.ServerSocketImpl;
import org.java_websocket.WebSocketImpl;

import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by joshoy on 16/3/22.
 */
public class Server {
    /*
    * 服务端主函数
    * */
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

        /* 开始定时记录 */
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                Date dt = new Date();
                DateFormat df = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
                String time = df.format( dt );

                try {

                    String fileName = "./log/server.txt";
                    FileWriter writer = new FileWriter( fileName, true );
                    System.out.println( time + "  接收消息数量:   忽略消息数量:  " );  //received/ignored messages number
                    writer.write( time + "  接收消息数量:   忽略消息数量:  \n" );
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println( "error" );
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0, 60000);


        /* 启动Server后 */
        System.out.println("[*] 服务器已启动, 端口" + port + ", 输入restart重启, 输入exit退出. 其他广播消息可直接输入.");
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
