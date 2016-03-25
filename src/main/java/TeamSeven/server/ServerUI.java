package TeamSeven.server;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
public class ServerUI {

    public static void main(String[] args) {
        final JFrame jf = new JFrame("Server is on...");
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
        jsp.setBounds(50, 30, 440, 200);

        jf.add(jsp);

        JButton bexit = new JButton("Exit");
        bexit.setBounds(200, 275, 150, 30);
        bexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date day=new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jTextArea.append(df.format(day)+"\n");
                jTextArea.append("server log out. "+"\n\n");
                jf.dispose();
            }
        });
        jf.add(bexit);

        jf.setVisible(true);
    }


}

