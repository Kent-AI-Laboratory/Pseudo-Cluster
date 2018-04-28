package fileSelect;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Tester {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInterface window = new UserInterface();
		window.getFrame().setVisible(true);
	}
}
