package TeamSeven.server.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import TeamSeven.common.ITextAreaAppendable;
import TeamSeven.server.socket.ServerSocket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joshoy on 16/3/28.
 */
public class ServerUIOld implements ITextAreaAppendable {
    protected JTextField inputText;
    protected JFrame jf;
    protected JTextArea jTextArea;
    protected JScrollPane jsp;
    protected JButton bexit;
    protected JButton bStart;
    protected JButton bBoardcast;
    protected boolean serverSocketStarted;
    protected ServerSocket serverSocket;

    public ServerUIOld(ServerSocket ss) {
        this.serverSocketStarted = false;
        this.serverSocket = ss;
        this.serverSocket.setTextAreaUI(this);

        jf = new JFrame("Server is on...");
        jf.setBounds(450,150, 600, 400);
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

        inputText = new JTextField();
        inputText.setBounds(50, 320, 390, 30);
        jf.add(inputText);

        bBoardcast = new JButton("Boardcast");
        bBoardcast.setBounds(450, 320, 100, 30);
        this.bindActionOnBtnBoardcast();
        jf.add(bBoardcast);

        bexit = new JButton("Exit");
        bexit.setBounds(200, 275, 100, 30);
        this.bindActionOnBtnExit();
        jf.add(bexit);

        bStart = new JButton("Start");
        bStart.setBounds(50, 275, 100, 30);
        jf.add(bStart);
        this.bindActionOnBtnStart();

        // 显示窗口
        jf.setVisible(true);
    }

    public void appendText(String text) {
        jTextArea.append(text);
    }

    public void appendTextLine(String text) {
        jTextArea.append(text + "\n");
    }

    /* 启动/关闭按钮 */
    protected void bindActionOnBtnStart() {
        this.bStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (false == serverSocketStarted) {
                    bStart.setText("Stop");
                    serverSocket.start();
                    serverSocketStarted = true;
                    appendTextLine("服务器已启动. 端口号: " + serverSocket.getPort());
                }
                else {
                    try {
                        serverSocket.stop();
                        serverSocketStarted = false;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    bStart.setText("Start");
                    appendTextLine("服务器已关闭.");
                }
            }
        });
    }

    /* 关闭Server UI */
    protected void bindActionOnBtnExit() {
        this.bexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (true == serverSocketStarted) {
                    try {
                        serverSocket.stop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                jf.dispose();
            }
        });
    }

    protected void bindActionOnBtnBoardcast() {
        this.bBoardcast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (false == serverSocketStarted) {
                    appendTextLine("请先启动服务器.");
                }
                else {
                    serverSocket.sendBoardcast(inputText.getText());
                    inputText.setText("");
                }
            }
        });
    }

}
