package app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import view.MenuView;
import controller.*;
import model.*;

public class Laucher {

	private static void init() {

		TheatreController theatrectrl = MainController.getTheatreController();
		theatrectrl.insertTheatre();
		theatrectrl.insertTheatre();
		theatrectrl.insertTheatre();

		LieuController lieuctrl = MainController.getLieuController();
		lieuctrl.insertLieu("Rue Nationale", "Tours Centre", "journée");
		lieuctrl.insertLieu("RU", "Fac 2 lions", "journée");
		lieuctrl.insertLieu("Arret", "Fac 2 lions", "nocturne");
		lieuctrl.insertLieu("Salle de DI", "Polytech Tours", "nocturne");

		// setup_1
		Setup setup1 = new Setup();
		setup1.setExpositionOuverture("Il lit un roman au bibliothèque");
		setup1.setDistanceFocale(11.333);
		setup1.setNumFiltres(1);
		// clap_1
		Clap clap1 = new Clap();
		clap1.setTempsSequence(1.80);
		clap1.setCodeBobine(2);
		// clap_2
		Clap clap2 = new Clap();
		clap2.setTempsSequence(2.50);
		clap2.setCodeBobine(4);
		List<Clap> listClap1 = new ArrayList<Clap>();
		listClap1.add(clap1);
		listClap1.add(clap2);
		setup1.setClap(listClap1);

		// setup_2
		Setup setup2 = new Setup();
		setup2.setExpositionOuverture("Elle recherche des information au bibliothèque");
		setup2.setDistanceFocale(12.465);
		setup2.setNumFiltres(4);
		// clap_3
		Clap clap3 = new Clap();
		clap3.setTempsSequence(3.01);
		clap3.setCodeBobine(4);
		// clap_4
		Clap clap4 = new Clap();
		clap4.setTempsSequence(1.65);
		clap4.setCodeBobine(3);
		// clap_5
		Clap clap5 = new Clap();
		clap5.setTempsSequence(2.33);
		clap5.setCodeBobine(3);
		List<Clap> listClap2 = new ArrayList<Clap>();
		listClap2.add(clap3);
		listClap2.add(clap4);
		listClap2.add(clap5);
		setup2.setClap(listClap2);

		List<Setup> listSetup = new ArrayList<Setup>();
		listSetup.add(setup1);
		listSetup.add(setup2);
		SceneController scenectrl = MainController.getSceneController();
		scenectrl.insertScene("La première fois qu'ils se sont rencontrés", 2, 0, listSetup);

		// setup_3
		Setup setup3 = new Setup();
		setup3.setExpositionOuverture("Elle fait du shopping en centre ville");
		setup3.setDistanceFocale(18.88);
		setup3.setNumFiltres(2);
		// clap_6
		Clap clap6 = new Clap();
		clap6.setTempsSequence(0.75);
		clap6.setCodeBobine(2);
		List<Clap> listClap3 = new ArrayList<Clap>();
		listClap3.add(clap6);
		setup3.setClap(listClap3);

		// setup_4
		Setup setup4 = new Setup();
		setup4.setExpositionOuverture("Il visite dans la cathédrale");
		setup4.setDistanceFocale(12.00);
		setup4.setNumFiltres(5);
		// clap_7
		Clap clap7 = new Clap();
		clap7.setTempsSequence(1.50);
		clap7.setCodeBobine(3);
		List<Clap> listClap4 = new ArrayList<Clap>();
		listClap4.add(clap7);
		setup4.setClap(listClap4);

		List<Setup> listSetup2 = new ArrayList<Setup>();
		listSetup2.add(setup3);
		listSetup2.add(setup4);
		SceneController scenectrl1 = MainController.getSceneController();
		scenectrl1.insertScene("La deuxième fois qu'ils se sont rencontrés", 0, 1, listSetup2);


	}

	public static void main(String[] args) {
		init();
		SceneController scenectrl = MainController.getSceneController();
		MenuView mainview = new MenuView(scenectrl.selectAll());

	}

}
