package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import view.*;




public class MainController {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpnote.film");
	private static EntityManager em = emf.createEntityManager();
	
	private static SceneController sceneController = new SceneController(em);
	private static LieuController lieuController = new LieuController(em);
	private static TheatreController theatreController = new TheatreController(em);
	private static SetupController setupController = new SetupController(em);
	private static ClapController clapController = new ClapController(em);
	
	
	public static SceneController getSceneController() {
		return sceneController;
	}
	
	public static LieuController getLieuController() {
		return lieuController;
	}
	
	public static TheatreController getTheatreController() {
		return theatreController;
	}
	
	public static SetupController getSetupController() {
		return setupController;
	}
	
	public static ClapController getClapController() {
		return clapController;
	}
	

}
