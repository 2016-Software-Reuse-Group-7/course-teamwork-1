package TeamSeven.client;

/**
 * Created by joshoy on 16/3/22.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import TeamSeven.client.socket.ClientSocket;
import TeamSeven.client.socket.ClientSocketImpl;
import TeamSeven.entity.Account;
import TeamSeven.util.SerializeTool;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class Client {

    /*
    * 客户端主函数
    * */
    /*public Client(final URI serverUri ) {
        final String userName = "";
        final String password = "";
        final JFrame jFrame = new JFrame("Login");
        jFrame.setBounds(450, 200, 370, 250);
        jFrame.setResizable(false);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Id");
        label1.setBounds(70, 70, 80, 25);
        jFrame.add(label1);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(70, 105, 80, 25);
        jFrame.add(label2);

        final JTextField text1 = new JTextField();
        text1.setBounds(180, 70, 130, 25);
        jFrame.add(text1);

        final JPasswordField text2 = new JPasswordField();
        text2.setBounds(180, 105, 130, 25);
        jFrame.add(text2);

        JButton button = new JButton("Login");
        button.setBounds(120, 155, 150, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (text1.getText()==null||text1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "please input id", "提示", JOptionPane.ERROR_MESSAGE);
                }
                else if (text2.getText()==null||text2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "please input password", "提示", JOptionPane.ERROR_MESSAGE);
                }
                else if (userName.equals(text1.getText()) && password.equals(text2.getText())) {
                    ClientUI client = new ClientUI(serverUri,userName,password);
                    jFrame.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "wrong password", "提示", JOptionPane.ERROR_MESSAGE);
                    text1.setText("");
                    text2.setText("");
                }
            }
        });
        jFrame.add(button);

        jFrame.setVisible(true);
    }
    */

    /*
    public class ClientUI {
        public ClientUI(final URI serverUri,final String userName,final String password) {
               *//* 建立新的ClientSocket实例 *//*
            final ClientSocket c = new ClientSocketImpl(serverUri);
        *//* 设置用户名 *//*
            c.setAccount(new Account(userName, password));
            c.connect();

        *//* 首先需要验证身份 *//*
            String accountMsg = null;
            try {
                accountMsg = SerializeTool.ObjectToString(c.getAccount());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            c.sendMessage(accountMsg);
            final JFrame jf = new JFrame("Hello," + userName);
            jf.setBounds(410, 150, 600, 400);
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
            jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            jsp.setBounds(50, 30, 390, 200);

            jf.add(jsp);

            final JTextField text1 = new JTextField();
            text1.setBounds(50, 280, 390, 30);
            jf.add(text1);
            final Date day = new Date();
            final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jTextArea.append(df.format(day) + "\n");
            jTextArea.append(userName + ": log in...\n\n");
            JButton button = new JButton("Send");
            button.setBounds(460, 280, 80, 30);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (text1.getText() != null||text1.getText().equals("")) {
                        jTextArea.append(df.format(day) + "\n");
                        jTextArea.append(userName + ": " + text1.getText() + "\n\n");
                        text1.setText("");
                        //       c.sendMessage(text1.getText());
                        //此处添加
                    } else {
                        JOptionPane.showMessageDialog(null, "please input", "Warning", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            jf.add(button);

            JButton bexit = new JButton("Exit");
            bexit.setBounds(460, 235, 80, 30);
            bexit.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            Date day = new Date();
                                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            jTextArea.append(df.format(day) + "\n");
                                            jTextArea.append(userName + " log out. " + "\n\n");
                                            text1.setText("");
                                            jf.dispose();
                                        }
                                    }
            );
            jf.add(bexit);
            JButton brelog = new JButton("Relog");
            brelog.setBounds(460, 185, 80, 30);
            brelog.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             jf.dispose();
                                             new Client(serverUri);
                                         }
                                     }
            );
            jf.add(brelog);
            jf.setVisible(true);
        }
    }*/

    public static void main(String[] args) throws URISyntaxException, IOException {

    }

    public static void mainOld(String[] args) throws URISyntaxException, IOException {
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
