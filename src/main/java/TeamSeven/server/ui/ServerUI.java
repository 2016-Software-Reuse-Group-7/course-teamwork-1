package TeamSeven.server.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joshoy on 16/3/28.
 */
public class ServerUI {

    protected final JFrame jf;
    protected final JTextArea jTextArea;
    protected JScrollPane jsp;
    protected JButton bexit;
    protected JButton brestart;

    public ServerUI() {
        jf = new JFrame("Server is on...");
        jf.setBounds(410,150, 600, 400);
        jf.setResizable(false);
        jf.setLayout(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);//激活自动换行功能
        jTextArea.setWrapStyleWord(true);//激活断行不断字功能
        jTextArea.setEditable(false); //为JTextArea添加滚动条

        jsp = new JScrollPane(jTextArea);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        jsp.setBounds(50, 30, 440, 200);
        jf.add(jsp);


        bexit = new JButton("Exit");
        bexit.setBounds(200, 275, 100, 30);
        bexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date day = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jTextArea.append(df.format(day)+"\n");
                jTextArea.append("server log out. "+"\n\n");
                jf.dispose();
            }
        });
        jf.add(bexit);

        brestart = new JButton("Restart");
        brestart.setBounds(50, 275, 100, 30);
        brestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new ServerUI();
            }
        });
        jf.add(brestart);

        // 显示窗口
        jf.setVisible(true);
    }

    public void appendTextLine(String text) {
        jTextArea.append(text + "\n");
    }

}
