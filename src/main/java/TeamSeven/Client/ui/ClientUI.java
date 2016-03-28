package TeamSeven.client.ui;

import TeamSeven.client.Client;
import TeamSeven.client.socket.ClientSocket;
import TeamSeven.client.socket.ClientSocketImpl;
import TeamSeven.entity.Account;
import TeamSeven.util.SerializeTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joshoy on 16/3/28.
 */
public class ClientUI {
    public ClientUI(final URI serverUri, final String userName, final String password) {
        /* 建立新的ClientSocket实例 */
        final ClientSocket c = new ClientSocketImpl(serverUri);
        /* 设置用户名 */
        c.setAccount(new Account(userName, password));
        c.connect();

        /* 首先需要验证身份 */
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
                                         // new Client(serverUri);
                                     }
                                 }
        );
        jf.add(brelog);
        jf.setVisible(true);
    }
}



