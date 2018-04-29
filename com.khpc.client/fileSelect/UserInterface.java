//Author: Yang Li'19

package fileSelect;

import java.awt.Color;
import java.awt.Font;
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

import clientTransfer.ClientTransferRepeater;

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

	protected UserInterface() {
		initialize();
	}

	private void update() {
		lblCount_Var.setText(String.valueOf(model.getSize()));
	}

	private void submit() throws UnknownHostException, IOException, InterruptedException {
		File[] files = new File[model.getSize()];

		// The list that contains all the file path that is going to be submitted
		List<String> filePathList = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			files[i] = (File) (model.getElementAt(i));
			filePathList.add(files[i].getName());
		}

		ClientTransferRepeater.sendFile(filePathList, servIpAddr);

		model.clear();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 416, 307);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);

		btnAdd = new JButton("Browse");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(23, 214, 88, 30);
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
		scroll.setBounds(23, 23, 284, 160);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(121, 214, 88, 30);
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
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setBounds(219, 214, 88, 30);
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
		lblCount.setFont(new Font("Calibri", Font.BOLD, 13));
		lblCount.setBounds(317, 91, 46, 14);

		lblCount_Var = new JLabel("0");
		lblCount_Var.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblCount_Var.setBounds(327, 117, 34, 14);

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
}
