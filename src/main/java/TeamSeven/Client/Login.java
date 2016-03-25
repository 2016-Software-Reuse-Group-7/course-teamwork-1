package TeamSeven.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login {

    public static void main(String[] args) {
        final String userName = "abc";
        final String passwrod = "123";
        final JFrame jFrame = new JFrame("Login");
        jFrame.setBounds(450,200, 370, 250);
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
                if(userName.equals(text1.getText()) && passwrod.equals(text2.getText())) {
                    ClientUI client =new ClientUI();
                    jFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "错误", "提示", JOptionPane.ERROR_MESSAGE);
                    text1.setText("");
                    text2.setText("");
                }

            }
        });
        jFrame.add(button);

        jFrame.setVisible(true);
    }


}