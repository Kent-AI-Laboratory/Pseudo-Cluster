package fileSelect;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Initializer {
	public static void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInterface window = new UserInterface();
		window.getFrame().setVisible(true);
		
		//####Need to change this address to the address of actual receiving server!!!!!#######
		window.setServIpAddr("127.0.0.1");
	}
}