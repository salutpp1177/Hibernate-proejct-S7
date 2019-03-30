package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.Vector;

import java.awt.Color;
import model.*;
import controller.*;
import view.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class MenuView {

	private JFrame frame;
	private List<Scene> listScene;
	
	
	public List<Scene> getListScene() {
		return listScene;
	}

	public void setListScene(List<Scene> list) {
		this.listScene = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			this.listScene.add(list.get(i));
		}
	}

	/**
	 * Create the application.
	 */
	public MenuView(List<Scene> list) {
		initialize(list);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<Scene> list) {
		frame = new JFrame();
		frame.setTitle("Menu");
		frame.setBounds(100, 100, 587, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		this.setListScene(list);
		int size = list.size();
		
		JLabel lblNewLabel = new JLabel("Veuillez choisir une scene pour voir le contenu :");
		lblNewLabel.setForeground(SystemColor.controlHighlight);
		lblNewLabel.setBounds(29, 27, 474, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCrerUneScene = new JButton("Je veux créer une nouvelle scene");
		btnCrerUneScene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CreateSceneView view = new CreateSceneView();
			}
		});
		btnCrerUneScene.setForeground(Color.RED);
		btnCrerUneScene.setBackground(Color.WHITE);
		btnCrerUneScene.setBounds(29, 285, 523, 29);
		frame.getContentPane().add(btnCrerUneScene);
		
		DefaultTableModel model = new DefaultTableModel();
		Vector data = new Vector();
		Vector names = new Vector();
		String[] headerName = {"CodeScene", "Description"};
		names.add(headerName[0]);
		names.add(headerName[1]);
		
		for(int i=0; i<size; i++) {
			Vector row = new Vector();
			row.add(list.get(i).getCodeScene());
			row.add(list.get(i).getDescription());
			data.add(row);
			
		}
		
		model.setDataVector(data, names);
		JTable table = new JTable(model);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.getColumnModel().getColumn(0).setMaxWidth(210);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				int row = table.rowAtPoint(event.getPoint());
				int id = (int) table.getModel().getValueAt(row, 0);
				frame.dispose();
				InfoView view = new InfoView(id);
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(29, 74, 523, 188);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
			
	}

}
