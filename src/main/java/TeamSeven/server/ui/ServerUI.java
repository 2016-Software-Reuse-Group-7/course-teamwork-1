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
    protected JButton bport;
    protected JButton bstart;
    protected JButton brestart;
    protected JLabel labelPort;
    public ServerUI() {
        jf = new JFrame("Server is on...");
        jf.setBounds(410,150, 710, 400);
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


        labelPort = new JLabel("Port：");
        labelPort.setBounds(520, 50, 50, 30);
        jf.add(labelPort);

        bport = new JButton("");
        bport.setBounds(570, 50, 100, 30);
        bport.setEnabled(false);
        jf.add(bport);

        bstart = new JButton("Start");
        bstart.setBounds(550, 150, 100, 30);
        bstart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        jf.add(bstart);
        bexit = new JButton("Exit");
        bexit.setBounds(550, 200, 100, 30);
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
        brestart.setBounds(550, 250, 100, 30);
        brestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new ServerLoginUI();
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
