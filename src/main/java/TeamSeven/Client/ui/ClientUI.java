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

    protected JFrame jf;
    protected JTextArea jTextArea;
    protected JScrollPane jsp;
    protected JTextField text1;
    protected JButton button;
    protected JButton bexit;
    protected JButton brelog;

    public ClientUI(final URI serverUri, final String userName, final String password) {

        /* Initialize JFrame s*/
        jf = new JFrame("Hello, " + userName);
        jf.setBounds(410, 150, 600, 400);
        jf.setResizable(false);
        jf.setLayout(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Initialize Components */
        jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);//激活自动换行功能
        jTextArea.setWrapStyleWord(true);//激活断行不断字功能
        jTextArea.setEditable(false);

        //为JTextArea添加滚动条
        jsp = new JScrollPane(jTextArea);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(50, 30, 390, 200);
        jf.add(jsp);

        text1 = new JTextField();
        text1.setBounds(50, 280, 390, 30);
        jf.add(text1);

        button = new JButton("Send");
        button.setBounds(460, 280, 80, 30);
        jf.add(button);


        bexit = new JButton("Exit");
        bexit.setBounds(460, 235, 80, 30);
        jf.add(bexit);

        brelog = new JButton("Relog");
        brelog.setBounds(460, 185, 80, 30);
        jf.add(brelog);
        jf.setVisible(true);
    }

    public void bindActionOnBtnSend(ActionListener listener) {
        this.button.addActionListener(listener);
    }

    public void bindActionOnBtnExit(ActionListener listener) {
        this.bexit.addActionListener(listener);
    }

    public void bindActionOnBtnRelogin(ActionListener listener) {
        this.brelog.addActionListener(listener);
    }

    public void appendTextLine(String text) {
        this.jTextArea.append(text + "\n");
    }
}



