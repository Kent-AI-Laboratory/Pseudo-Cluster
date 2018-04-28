//Author: Yang Li'19

package fileSelect;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

	public UserInterface() {
		initialize();
	}

	public void update() {
		lblCount_Var.setText(String.valueOf(model.getSize()));
	}

	public String[] submit() {
		File[] files = new File[model.getSize()];
		String[] result = new String[model.getSize()];
		for (int i = 0; i < result.length; i++) {
			files[i] = (File) (model.getElementAt(i));
			result[i] = files[i].getPath();
			System.out.println(result[i]);
		}

		model.clear();

		return result;
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
		list.setForeground(Color.GREEN);
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
				submit();
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
	
	public JFrame getFrame() {
		return this.frame;
	} 
}
