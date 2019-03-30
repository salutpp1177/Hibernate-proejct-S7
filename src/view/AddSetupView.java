package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.Scene;
import model.Setup;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.MainController;
import controller.SceneController;
import controller.SetupController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AddSetupView {

	private JFrame frame;
	private Scene sc;
	private SceneController sceneController = MainController.getSceneController();
	private SetupController setupController = MainController.getSetupController();

	/**
	 * Create the application.
	 */
	public AddSetupView(Scene sc) {
		initialize(sc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Scene sc) {
		frame = new JFrame();
		frame.setTitle("Ajouter Setup");
		frame.setBounds(100, 100, 579, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Il y a des setups que vous n'avez pas utilisés :");
		lblNewLabel.setBounds(30, 57, 386, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Veuillez taper le code de scène que vouz voulez ajouter : ");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setBounds(30, 281, 360, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblCodeDuScene = new JLabel(
				"----------------------   Code de la Scène : " + sc.getCodeScene() + "   ---------------------");
		lblCodeDuScene.setForeground(new Color(255, 0, 0));
		lblCodeDuScene.setBounds(34, 23, 500, 16);
		frame.getContentPane().add(lblCodeDuScene);
		
		JTextField textField = new JTextField();
		textField.setBounds(402, 276, 78, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		DefaultTableModel model = new DefaultTableModel();
		Vector data = new Vector();
		Vector name = new Vector();
		String[] ns = {"codeSetup", "distance focale(mm)", "expo Ouverture", "Filtre"};
		for(String k : ns) {
			name.add(k);
		}
		
		List<Setup> li = new ArrayList<>();
		li = setupController.selectAllDisponible();
		for(int i=0; i<li.size(); i++) {
			Vector row = new Vector();
			row.add(li.get(i).getCodeSetup());
			row.add(li.get(i).getDistanceFocale());
			row.add(li.get(i).getExpositionOuverture());
			row.add(li.get(i).getNumFiltres());
			data.add(row);
		}
		
		model.setDataVector(data, name);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 104, 523, 135);
		frame.getContentPane().add(scrollPane);
		
		JButton btnJaiFini = new JButton("J'ai fini !");
		btnJaiFini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = textField.getText();
				int id = Integer.valueOf(s).intValue();
				Setup sp = new Setup();
				sp = setupController.selectSetupById(id);
				List<Setup> list = new ArrayList<>();
				if(sc.getSetup().isEmpty()) {
					list.add(sp);
					sc.setSetup(list);
				} {
					sc.getSetup().add(sp);
				}
				sceneController.updateScene(id, sc);
				frame.dispose();
				MenuView view = new MenuView(sceneController.selectAll());
			}
		});
		btnJaiFini.setBounds(385, 323, 117, 29);
		frame.getContentPane().add(btnJaiFini);
		frame.setVisible(true);
	}
}
