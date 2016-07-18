package gui.desktop.searchtool.gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JList;

import gui.desktop.searchtool.gui.listener.KeyboardHook;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 341162064964310681L;
	private SearchBox searchBox = new SearchBox(this);
	private JList<File> jList = new JList<>();

	private KeyboardHook hook = new KeyboardHook(this);

	public SearchBox getSearchBox() {
		return searchBox;
	}

	public void setSearchBox(SearchBox searchBox) {
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
