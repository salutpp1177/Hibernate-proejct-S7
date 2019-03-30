package controller;

import java.util.List;

import javax.persistence.EntityManager;

import dao.*;
import model.*;

public class SceneController {
	private EntityManager em;
	SceneDao sceneDao;
	
	public SceneController(EntityManager em) {
		this.em = em;
		this.sceneDao = new SceneDao(em);
	}
	
	/* 
	 * inserter une scene
	 */
	public void insertScene(String description, int codeTheatre, int codeLieu, List<Setup> listSetup) {
		Scene scene = new Scene();
		if ( codeLieu != 0) {
			Lieu lieu = MainController.getLieuController().selectLieuById(codeLieu);
			scene.setLieu(lieu);
		}
		
		if ( codeTheatre != 0) {
			Theatre theatre = MainController.getTheatreController().selectTheatreById(codeTheatre);
			scene.setTheatre(theatre);
		}
		if(listSetup != null ) {
			scene.setSetup(listSetup);
		}
		scene.setDescription(description);
		this.sceneDao.insertScene(scene);
	}
	
	/*
	 * supprimer une scene par codeScene
	 */
	public void deleteSceneById(int id) {
		this.sceneDao.deleteSceneById(id);
	}
	
	/*
	 * modifier une scene
	 */
	public void updateScene(int id, Scene sc) {
		this.sceneDao.updateSceneById(id, sc);
	}
	
	/*
	 * selectionner une scene par codeScene
	 */
	public Scene selectSceneById(int id) {
		return this.sceneDao.selectSceneById(id);
	}
	
	public Scene selectSceneByDescription(String s) {
		return this.sceneDao.selectSceneByDescription(s);
	}
	/*
	 * selectionner toutes les scenes
	 */
	public List<Scene> selectAll() {
		return this.sceneDao.selectAll();
	}
	
	//public 
}
