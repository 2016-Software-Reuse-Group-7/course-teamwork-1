package TeamSeven.server.ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Created by joshoy on 16/3/28.
 */
public class ServerLoginUI {

    /* Data models */
    protected String userName;
    protected String password;

    /* View */
    protected JFrame jFrame;
    protected JLabel labelUsername;
    protected JLabel labelPassword;
    protected JTextField textFieldUsername;
    protected JTextField textFieldPassword;
    protected JButton loginButton;
    protected JLabel labelPort;
    protected JLabel labelIP;
    protected JTextField textFieldPort;
    protected JTextField textFieldIP;
    public ServerLoginUI() {
        /* initialize JFrame */
        jFrame = new JFrame("Server log");
        jFrame.setBounds(450, 200, 350, 230);
        jFrame.setResizable(false);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelPort = new JLabel("Port");
        labelPort.setBounds(60, 50, 100, 30);
        jFrame.add(labelPort);


        textFieldPort = new JTextField();
        textFieldPort.setBounds(180, 50, 130, 30);
        textFieldPort.setText("8887");
        jFrame.add(textFieldPort);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 120, 140, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}});
        jFrame.add(loginButton);
        jFrame.setVisible(true);

    }

    public void bindLoginAction(ActionListener listener) {
        this.loginButton.addActionListener(listener);
    }

}
