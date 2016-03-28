package TeamSeven.client.ui;

import javax.swing.*;
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

    public ClientLoginUI() {
        /* initialize JFrame */
        jFrame = new JFrame();
        jFrame.setBounds(450, 200, 370, 250);
        jFrame.setResizable(false);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Initialize components */
        labelUsername = new JLabel("UserId");
        labelUsername.setBounds(70, 70, 80, 25);
        jFrame.add(labelUsername);

        labelPassword = new JLabel("Password");
        labelPassword.setBounds(70, 105, 80, 25);
        jFrame.add(labelPassword);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(180, 70, 130, 25);
        jFrame.add(textFieldUsername);

        textFieldPassword = new JPasswordField();
        textFieldPassword.setBounds(180, 105, 130, 25);
        jFrame.add(textFieldPassword);

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 155, 150, 30);
    }

    public void bindLoginAction(ActionListener listener) {
        this.loginButton.addActionListener(listener);
    }

}
