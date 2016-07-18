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
import gui.desktop.searchtool.model.PageModel;
import gui.desktop.searchtool.service.SearchService;

public class Main extends JFrame {
	private JTextField searchBox = new JTextField();
	private JList<File> jList = new JList<>();
	private static SearchService searchService = new SearchServiceImpl();

	private void textChange(String key) {
		if (key != "\n" && key != "\r" && key.length() != 0) {
			PageModel<File> pageModel = searchService.search(key, 0, 10);
			if (pageModel.getTotal() > 0 && CollectionUtils.isNotEmpty(pageModel.getDatas())) {
				jList.setListData(pageModel.getDatas().toArray(new File[pageModel.getDatas().size()]));
				jList.setSelectedIndex(0);
				// jList.requestFocus();
				// setSize(getWidth(), searchBox.getHeight()+jList.getHeight());
			}
		}
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
				textChange(searchBox.getText().trim());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				textChange(searchBox.getText().trim());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				textChange(searchBox.getText().trim());
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
					Process process = Runtime.getRuntime().exec("cmd /c start "+file.getAbsolutePath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.setVisible(true);
	}
}
