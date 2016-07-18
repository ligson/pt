package gui.desktop.searchtool.gui;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/***
 * 系统托盘
 * 
 * @author ligson
 *
 */
public class SysTray extends MouseAdapter implements ActionListener {
	private Main main;
	private SystemTray systemTray = SystemTray.getSystemTray();
	private Image image = Toolkit.getDefaultToolkit()
			.getImage(Main.class.getClassLoader().getResource("assets/img/tray_icon.png"));

	private PopupMenu trayMenu = new PopupMenu();
	private MenuItem setItem = new MenuItem("设置");
	private MenuItem helpItem = new MenuItem("帮助");
	private MenuItem exitItem = new MenuItem("退出");

	private TrayIcon trayIcon = new TrayIcon(image, "桌面搜索", trayMenu);

	public SysTray(Main main) {
		super();
		this.main = main;

	}

	public void init() throws Exception {
		exitItem.addActionListener(this);
		trayMenu.add(setItem);
		trayMenu.add(helpItem);
		trayMenu.add(exitItem);
		systemTray.add(trayIcon);
		trayIcon.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(exitItem)) {
			main.getHook().setEnable(false);
			main.setVisible(false);
			systemTray.remove(trayIcon);
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(trayIcon)) {
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				main.triggerVisible();
			}
		}
	}

}
