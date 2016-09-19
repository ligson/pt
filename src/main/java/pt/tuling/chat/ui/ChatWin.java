package pt.tuling.chat.ui;

import org.apache.commons.lang3.StringUtils;
import pt.tuling.chat.service.ChatService;
import pt.tuling.chat.service.impl.TLChatServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ligson on 2016/9/19.
 * @author ligson
 */
public class ChatWin extends JFrame implements ActionListener{
    private JScrollPane jScrollPane = new JScrollPane();
    private JTextArea jTextArea = new JTextArea();
    private JTextField field = new JTextField();
    ChatService chatService = new TLChatServiceImpl();
    ChatWin(){
        setSize(300,500);
        add(jScrollPane, BorderLayout.CENTER);
        add(field,BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jScrollPane.setViewportView(jTextArea);
        field.addActionListener(this);
    }
    public static void main(String[] args) {
        ChatWin chatWin = new ChatWin();
        chatWin.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==field){
            String msg = field.getText();
            if(StringUtils.isNotEmpty(msg)){
                String rec = chatService.sendMsg(msg);
                jTextArea.append("\nME:\n");
                jTextArea.append(msg);
                if(StringUtils.isNotEmpty(rec)){
                    jTextArea.append("\nREC:\n");
                    jTextArea.append(rec);
                }
            }
        }
    }
}
