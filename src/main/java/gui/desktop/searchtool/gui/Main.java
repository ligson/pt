package gui.desktop.searchtool.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.collections.CollectionUtils;

import gui.desktop.searchtool.SearchServiceImpl;
import gui.desktop.searchtool.gui.listener.KeyboardHook;
import gui.desktop.searchtool.model.PageModel;
import gui.desktop.searchtool.service.SearchService;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 341162064964310681L;
	private JTextField searchBox = new JTextField();
	private JList<File> jList = new JList<>();
	private static SearchService searchService = new SearchServiceImpl();
	private static int offset = 0;
	private KeyboardHook hook = new KeyboardHook(this);

	private void textChange() {
		String key = searchBox.getText().trim();
		if (key != "\n" && key != "\r" && key.length() != 0) {
			offset = offset < 0 ? 0 : offset;
			PageModel<File> pageModel = searchService.search(key, offset, 10);
			if (pageModel.getTotal() > 0 && CollectionUtils.isNotEmpty(pageModel.getDatas())) {
				jList.setListData(pageModel.getDatas().toArray(new File[pageModel.getDatas().size()]));
				jList.setSelectedIndex(0);
			}
		}
	}

	public JTextField getSearchBox() {
		return searchBox;
	}

	public void setSearchBox(JTextField searchBox) {
		this.searchBox = searchBox;
	}

	public JList<File> getjList() {
		return jList;
	}

	public void setjList(JList<File> jList) {
		this.jList = jList;
	}

	public KeyboardHook getHook() {
		return hook;
	}

	public void setHook(KeyboardHook hook) {
		this.hook = hook;
	}

	public Main() {
		setSize(300, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(searchBox, BorderLayout.NORTH);
		add(jList, BorderLayout.CENTER);
		searchBox.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				offset = 0;
				textChange();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				offset = 0;
				textChange();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				offset = 0;
				textChange();
			}
		});
		searchBox.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					jList.setSelectedIndex(jList.getSelectedIndex() - 1);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					jList.setSelectedIndex(jList.getSelectedIndex() + 1);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					offset -= 10;
					textChange();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					offset += 10;
					textChange();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		searchBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File file = jList.getSelectedValue();
				try {
					Runtime.getRuntime().exec("cmd /c start " + file.getAbsolutePath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		Thread thread = new Thread(getHook());
		thread.start();
	}

	public void triggerVisible() {
		if (isVisible()) {
			setVisible(false);
		} else {
			setVisible(true);
		}
	}

	public static void main(String[] args) throws Exception {
		final Main main = new Main();
		main.setVisible(true);
		SysTray sysTray = new SysTray(main);
		sysTray.init();

	}
}
