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
    protected JLabel labelPort;
    protected JButton bport;
    protected JLabel labelIP;
    protected JButton bip;

    public ClientUI(final URI serverUri, final String userName, final String password) {

        /* Initialize JFrame s*/
        jf = new JFrame("Hello, " + userName);
        jf.setBounds(410, 150, 650, 400);
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

        labelPort = new JLabel("Port：");
        labelPort.setBounds(460, 50, 50, 30);
        jf.add(labelPort);

        bport = new JButton("");
        bport.setBounds(520, 50, 100, 30);
        bport.setEnabled(false);
        bport.setText("8887");
        jf.add(bport);

        labelIP = new JLabel("IP：");
        labelIP.setBounds(460, 100, 50, 30);
        jf.add(labelIP);

        bip = new JButton("");
        bip.setBounds(520, 100, 100, 30);
        bip.setEnabled(false);
        bip.setText("127.0.0.1");

        jf.add(bip);

        button = new JButton("Send");
        button.setBounds(450, 280, 80, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}});
        jf.add(button);


        bexit = new JButton("Exit");
        bexit.setBounds(520, 220, 80, 30);
        bexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}});
        jf.add(bexit);

        brelog = new JButton("Relog");
        brelog.setBounds(520, 170, 80, 30);
        brelog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}});
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



