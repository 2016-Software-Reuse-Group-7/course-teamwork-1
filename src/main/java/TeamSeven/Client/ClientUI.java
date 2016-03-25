package TeamSeven.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class ClientUI {

    public ClientUI() {

        final String userName = "abc";
        final String passwrod = "123";
        final JFrame jf = new JFrame("Hello,"+userName);
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
        jsp.setBounds(50, 30, 390, 200);

        jf.add(jsp);

        final JTextField text1 = new JTextField();
        text1.setBounds(50, 280, 390, 30);
        jf.add(text1);

        JButton button = new JButton("Send");
        button.setBounds(460, 280, 80, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                            Date day=new Date();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            jTextArea.append(df.format(day)+"\n");
                            jTextArea.append(userName+": "+text1.getText()+"\n\n");
                            text1.setText("");
                        }
                    });
        jf.add(button);

        JButton bexit = new JButton("Exit");
        bexit.setBounds(460, 235, 80, 30);
        bexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                            Date day=new Date();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            jTextArea.append(df.format(day)+"\n");
                            jTextArea.append(userName+" log out. "+"\n\n");
                            text1.setText("");
                            jf.dispose();
                        }
                    });
        jf.add(bexit);

        jf.setVisible(true);
                }


            }

