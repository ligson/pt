package gui.desktop.searchtool.gui;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import sun.awt.shell.ShellFolder;

@SuppressWarnings("restriction")
public class SearchList extends JList<File> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8185370649752511623L;
	private MainWin main;

	public MainWin getMain() {
		return main;
	}

	public void setMain(MainWin main) {
		this.main = main;
	}

	public static Icon toIcon(File file) {
		ShellFolder shellFolder;
		try {
			shellFolder = ShellFolder.getShellFolder(file);
		} catch (FileNotFoundException e) {
			return null;
		}
		Icon icon = new ImageIcon(shellFolder.getIcon(true));
		return icon;
	}

	public SearchList(MainWin main) {
		super();
		this.main = main;
		setCellRenderer(new ListCellRenderer<File>() {
			@Override
			public Component getListCellRendererComponent(JList<? extends File> list, File value, int index,
					boolean isSelected, boolean cellHasFocus) {
				System.out.println(value+"-"+isSelected+"="+cellHasFocus);
				JLabel jLabel = new JLabel(value.getAbsolutePath());
				jLabel.setOpaque(true);
				jLabel.setIcon(toIcon(value));
				//jLabel.set
				if(isSelected){
					jLabel.setBackground(list.getSelectionBackground());
					jLabel.setForeground(list.getSelectionForeground());
				}else{
					jLabel.setBackground(list.getBackground());
					jLabel.setForeground(list.getForeground());
				}
				return jLabel;
			}
		});
	}

}
