package TeamSeven.Client.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by joshoy on 16/3/28.
 */
public class ClientLoginUI {

    /* Data models */
    protected String userName;
    protected String password;

    /* View */
    protected JFrame jFrame;
    protected JLabel labelUsername;
    protected JLabel labelPassword;
    protected JTextField textFieldUsername;
    protected JPasswordField textFieldPassword;
    protected JButton loginButton;
    protected JLabel labelPort;
    protected JLabel labelIP;
    protected JTextField textFieldPort;
    protected JTextField textFieldIP;
    public ClientLoginUI() {
        /* initialize JFrame */
        jFrame = new JFrame();
        jFrame.setBounds(400, 100, 400, 500);
        jFrame.setResizable(false);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Initialize components */
        labelUsername = new JLabel("UserId");
        labelUsername.setBounds(60, 50, 100, 30);
        jFrame.add(labelUsername);

        labelPassword = new JLabel("Password");
        labelPassword.setBounds(60, 100, 100, 30);
        jFrame.add(labelPassword);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(180, 50, 130, 30);
        jFrame.add(textFieldUsername);

        textFieldPassword = new JPasswordField();
        textFieldPassword.setBounds(180, 100, 130, 30);
        jFrame.add(textFieldPassword);

        labelPort = new JLabel("Port");
        labelPort.setBounds(60, 150, 100, 30);
        jFrame.add(labelPort);

        labelIP= new JLabel("IP");
        labelIP.setBounds(60, 200, 100, 30);
        jFrame.add(labelIP);

        textFieldPort = new JTextField();
        textFieldPort.setBounds(180, 150, 130, 30);
        textFieldPort.setText("8887");
        jFrame.add(textFieldPort);

        textFieldIP = new JTextField();
        textFieldIP.setBounds(180, 200, 130, 30);
        textFieldIP.setText("127.0.0.1");
        jFrame.add(textFieldIP);

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 355, 150, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}});
        jFrame.add(loginButton);
        jFrame.setVisible(true);

    }

    public void bindLoginAction(ActionListener listener) {
        this.loginButton.addActionListener(listener);
    }

}
