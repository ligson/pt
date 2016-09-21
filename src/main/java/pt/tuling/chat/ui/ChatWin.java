package pt.tuling.chat.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.apache.commons.lang3.StringUtils;

import pt.tuling.chat.service.ChatService;
import pt.tuling.chat.service.impl.TLChatServiceImpl;

/**
 * Created by ligson on 2016/9/19.
 * 
 * @author ligson
 */
public class ChatWin extends JFrame implements ActionListener {
	private JScrollPane jScrollPane = new JScrollPane();
	private JTextPane jTextArea = new JTextPane();
	private JTextField field = new JTextField();
	ChatService chatService = new TLChatServiceImpl();

	ChatWin() {
		setSize(500, 400);
		add(jScrollPane, BorderLayout.CENTER);
		add(field, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jScrollPane.setViewportView(jTextArea);
		field.addActionListener(this);
		setLocationRelativeTo(null);
		jTextArea.setEditable(false);
	}

	public static void main(String[] args) {
		ChatWin chatWin = new ChatWin();
		chatWin.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == field) {
			String msg = field.getText();
			if (StringUtils.isNotEmpty(msg)) {
				String rec = chatService.sendMsg(msg);
				Document doc = jTextArea.getDocument();
				SimpleAttributeSet blueSet = new SimpleAttributeSet();
				StyleConstants.setForeground(blueSet, Color.BLUE);// 设置文字颜色
				StyleConstants.setFontSize(blueSet, 12);// 设置字体大小

				SimpleAttributeSet blackSet = new SimpleAttributeSet();
				StyleConstants.setForeground(blackSet, Color.BLACK);// 设置文字颜色
				StyleConstants.setFontSize(blackSet, 12);// 设置字体大小

				try {
					doc.insertString(doc.getLength(), "我:\n", blueSet);
					doc.insertString(doc.getLength(), msg + "\n", blackSet);

					if (StringUtils.isNotEmpty(rec)) {
						doc.insertString(doc.getLength(), "机器人:\n", blueSet);
						doc.insertString(doc.getLength(), rec + "\n", blackSet);
					}
					field.setText(null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
