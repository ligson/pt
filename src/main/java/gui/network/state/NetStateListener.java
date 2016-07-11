package gui.network.state;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import org.apache.commons.lang3.time.DateFormatUtils;

public class NetStateListener extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean closed = false;
	private JScrollPane jPanel = new JScrollPane();
	private JTextArea textArea = new JTextArea();

	public NetStateListener() {
		this.setTitle("链接失败!");
		this.setSize(500, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		textArea.setEditable(false);
		jPanel.setViewportView(textArea);
		add(jPanel, BorderLayout.CENTER);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textArea.setRows(10);
	}

	@Override
	public void run() {
		while (true) {
			try {
				URL url = new URL("http://www.baidu.com");
				URLConnection con = url.openConnection();
				InputStream inputStream = con.getInputStream();
				inputStream.read();
				String msg = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + " 网络状态ok!\n\r";
				textArea.append(msg);
			} catch (IOException e) {
				String msg = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + " 网络链接失败!" + e.getMessage()
						+ "\n\r";
				textArea.append(msg);
			}
			//jPanel.getVerticalScrollBar().

		}

	}

	public static void main(String[] args) {
		NetStateListener listener = new NetStateListener();
		Thread thread = new Thread(listener);
		thread.start();
		listener.setVisible(true);
	}

}
