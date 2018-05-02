//Author: Yang Li'19

package selectFile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sendFile.FileReceiveCoordinator;

public class UserInterface {

	private JFrame frame;
	private JList list;
	private DefaultListModel model;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnSubmit;
	private JScrollPane scroll;
	private JLabel lblCount_Var;
	private JLabel lblCount;

	private String servIpAddr;

	private int screenWidth;
	private int screenHeight;
	private int enlargeRatio;

	public UserInterface() {
		screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		enlargeRatio = (int) ((double) screenWidth / (double) 1000);
		initialize();
	}

	private void update() {
		lblCount_Var.setText(String.valueOf(model.getSize()));
	}

	private void submit() throws UnknownHostException, IOException, InterruptedException {
		// The list that contains all the file path that is going to be submitted
		List<File> fileList = new ArrayList<File>();

		for (int i = 0; i < model.getSize(); i++) {
			fileList.add((File) (model.getElementAt(i)));
		}

		for (File file : fileList) {
			FileReceiveCoordinator.sendFileOnce(file, servIpAddr);
			model.removeElementAt(0);
		}
		
		model.clear();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 416 * enlargeRatio, 307 * enlargeRatio);
		frame.setSize(416 * enlargeRatio, 307 * enlargeRatio);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);

		btnAdd = new JButton("Browse");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(23 * enlargeRatio, 214 * enlargeRatio, 88 * enlargeRatio, 30 * enlargeRatio);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String current;
					File[] selectedFiles = fc.getSelectedFiles();
					for (File f : selectedFiles) {
						current = f.getPath();
						model.addElement(f);
					}
				}
				update();
			}
		});

		model = new DefaultListModel();
		list = new JList(model);
		list.setForeground(Color.BLACK);
		list.setBackground(Color.WHITE);
		list.setSelectionMode(2);
		scroll = new JScrollPane(list);
		scroll.setBounds(23 * enlargeRatio, 23 * enlargeRatio, 284 * enlargeRatio, 160 * enlargeRatio);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(121 * enlargeRatio, 214 * enlargeRatio, 88 * enlargeRatio, 30 * enlargeRatio);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] indices = list.getSelectedIndices();
				int i = 0;
				if (indices.length != 0) {
					for (int current : indices) {
						model.removeElementAt(current - i);
						i++;
					}
				}
				update();
			}
		});

		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setBounds(219 * enlargeRatio, 214 * enlargeRatio, 88 * enlargeRatio, 30 * enlargeRatio);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					submit();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update();
			}
		});

		lblCount = new JLabel("Count:");
		lblCount.setBounds(317 * enlargeRatio, 91 * enlargeRatio, 46 * enlargeRatio, 14 * enlargeRatio);

		lblCount_Var = new JLabel("0");
		lblCount_Var.setBounds(327 * enlargeRatio, 117 * enlargeRatio, 34 * enlargeRatio, 14 * enlargeRatio);

		frame.getContentPane().add(btnAdd);
		frame.getContentPane().add(scroll);
		frame.getContentPane().add(btnDelete);
		frame.getContentPane().add(btnSubmit);
		frame.getContentPane().add(lblCount);
		frame.getContentPane().add(lblCount_Var);
		frame.setTitle("KHPC Submission");
	}

	protected JFrame getFrame() {
		return this.frame;
	}

	protected void setServIpAddr(String servIpAddr) {
		this.servIpAddr = servIpAddr;
	}

	public static void initiateInterface() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInterface window = new UserInterface();
		window.getFrame().setVisible(true);

		// ####Need to change this address to the address of actual receiving
		// server!!!!!#######
		window.setServIpAddr("127.0.0.1");
	}
}
