package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.*;
import model.*;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;

public class InfoView {

	private JFrame frame;
	private int codeScene;
	private SceneController sceneController = MainController.getSceneController();

	/**
	 * Create the application.
	 */
	public InfoView(int codeScene) {
		initialize(codeScene);
	}

	private void initTitle(String[] str, Vector v) {
		for (String s : str) {
			v.add(s);
		}
	}

	private void initSetupData(Setup s, Vector v) {
		v.add(s.getCodeSetup());
		v.add(s.getDistanceFocale());
		v.add(s.getExpositionOuverture());
		v.add(s.getNumFiltres());
	}

	private void initClapData(Clap c, Vector v) {
		v.add(c.getSetup().getCodeSetup());
		v.add(c.getCodeClap());
		v.add(c.getCodeBobine());
		v.add(c.getTempsSequence());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int codeScene) {
		frame = new JFrame();
		frame.setTitle("Information");
		frame.setBounds(100, 100, 656, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Scene scene = sceneController.selectSceneById(codeScene);
		int code = 0;
		String CodeType = null;
		if (scene.getLieu() != null) {
			code = scene.getLieu().getCodeLieu();
			CodeType = "Lieu(En plein air)";
		}
		if (scene.getTheatre() != null) {
			code = scene.getTheatre().getCodeTheatre();
			CodeType = "Theatre(A l'interieur)";
		}
		JLabel lblCodeDuScene = new JLabel(
				"-----------------------------   Code de la Scène : " + codeScene + "   -----------------------------");
		lblCodeDuScene.setBounds(6, 49, 633, 29);
		lblCodeDuScene.setBackground(new Color(240, 255, 240));
		lblCodeDuScene.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodeDuScene.setForeground(new Color(0, 128, 128));

		if (scene.getSetup() != null) {
			DefaultTableModel model = new DefaultTableModel();
			DefaultTableModel m2 = new DefaultTableModel();
			JTable t = new JTable(model);
			JTable t2 = new JTable(m2);
			double TempTotal = 0.0;
			String[] title1 = { "codeSetup", "distance focale(mm)", "expo Ouverture", "Filtre" };
			Vector name1 = new Vector();
			this.initTitle(title1, name1);

			String[] title2 = { "codeSetup", "codeClap", "code de Bobine", "Temps de séquence(min)" };
			Vector name2 = new Vector();
			this.initTitle(title2, name2);
			List<Setup> listSetup = scene.getSetup();
			Vector data = new Vector();
			Vector datat2 = new Vector();

			if (!listSetup.isEmpty()) {
				for (int i = 0; i < listSetup.size(); i++) {
					Vector rowSetup = new Vector();
					this.initSetupData(listSetup.get(i), rowSetup);
					data.add(rowSetup);
					if (!listSetup.get(i).getClap().isEmpty()) {
						for (Clap c : listSetup.get(i).getClap()) {
							Vector rowClap = new Vector();
							this.initClapData(c, rowClap);
							datat2.add(rowClap);
							TempTotal += c.getTempsSequence();
						}
					}
				}
			}

			model.setDataVector(data, name1);
			frame.getContentPane().setLayout(null);
			JScrollPane scrollPane = new JScrollPane(t);
			scrollPane.setBounds(6, 252, 644, 118);
			frame.getContentPane().add(scrollPane);

			m2.setDataVector(datat2, name2);
			JScrollPane scrollPane_1 = new JScrollPane(t2);
			scrollPane_1.setBounds(6, 410, 644, 149);
			frame.getContentPane().add(scrollPane_1);
			frame.getContentPane().add(lblCodeDuScene);

			JLabel lblInformationDeSes = new JLabel("Information de ses setups :");
			lblInformationDeSes.setForeground(new Color(0, 100, 0));
			lblInformationDeSes.setBounds(6, 231, 250, 16);
			frame.getContentPane().add(lblInformationDeSes);

			JLabel lblNewLabel = new JLabel("Information de ses claps :");
			lblNewLabel.setForeground(new Color(0, 100, 0));
			lblNewLabel.setBounds(6, 382, 259, 16);
			frame.getContentPane().add(lblNewLabel);

			JLabel lblLe = new JLabel("Le temps totale écoulé : " + TempTotal + " mins");
			lblLe.setBounds(332, 382, 318, 16);
			frame.getContentPane().add(lblLe);
		}

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 644, 39);
		panel.setBackground(new Color(176, 224, 230));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnAjouterUnSetup = new JButton("Ajouter un setup pour la scene");
		btnAjouterUnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AddSetupView view = new AddSetupView(scene);
			}
		});
		btnAjouterUnSetup.setBounds(230, 5, 237, 29);
		panel.add(btnAjouterUnSetup);
		btnAjouterUnSetup.setForeground(new Color(65, 105, 225));

		JButton btnCrerUneScene = new JButton("Créer une scene");
		btnCrerUneScene.setBounds(479, 5, 159, 29);
		panel.add(btnCrerUneScene);
		btnCrerUneScene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CreateSceneView create = new CreateSceneView();
			}
		});
		btnCrerUneScene.setForeground(new Color(0, 0, 255));

		JButton btnRetournerMenu = new JButton("Retourner à Menu");
		btnRetournerMenu.setBackground(new Color(255, 255, 255));
		btnRetournerMenu.setBounds(6, 5, 200, 29);
		panel.add(btnRetournerMenu);
		btnRetournerMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MenuView menu = new MenuView(sceneController.selectAll());

			}
		});
		btnRetournerMenu.setForeground(new Color(0, 0, 139));

		JLabel lblDescription = new JLabel("Description : " + scene.getDescription());
		lblDescription.setBounds(22, 80, 617, 16);
		frame.getContentPane().add(lblDescription);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBounds(6, 108, 644, 111);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		if (scene.getTheatre() != null) {

			JLabel lblNewLabel_1 = new JLabel(" Voici la scène à l'intérieur");
			lblNewLabel_1.setForeground(new Color(72, 61, 139));
			lblNewLabel_1.setBounds(6, 33, 281, 16);
			panel_1.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("Son code de théâtre est : " + scene.getTheatre().getCodeTheatre());
			lblNewLabel_2.setBounds(6, 61, 281, 16);
			panel_1.add(lblNewLabel_2);
		}
		if (scene.getLieu() != null) {
			JLabel lblVoiciLaScne = new JLabel("Voici la scène en plein air");
			lblVoiciLaScne.setBounds(6, 6, 281, 16);
			panel_1.add(lblVoiciLaScne);
			lblVoiciLaScne.setForeground(new Color(165, 42, 42));

			JLabel lblCodeDeLieu = new JLabel("Son code de lieu : " + scene.getLieu().getCodeLieu());
			lblCodeDeLieu.setBounds(6, 34, 252, 16);
			panel_1.add(lblCodeDeLieu);

			JLabel lblEndroit = new JLabel("Endroit : " + scene.getLieu().getEndroit());
			lblEndroit.setBounds(6, 61, 304, 16);
			panel_1.add(lblEndroit);

			JLabel lblNewLabel_3 = new JLabel("Période :" + scene.getLieu().getPeriode());
			lblNewLabel_3.setBounds(347, 61, 258, 16);
			panel_1.add(lblNewLabel_3);

			JLabel lblDescription_1 = new JLabel("Description :" + scene.getLieu().getDescription());
			lblDescription_1.setBounds(6, 89, 504, 16);
			panel_1.add(lblDescription_1);

		}

		frame.setVisible(true);
	}
}
