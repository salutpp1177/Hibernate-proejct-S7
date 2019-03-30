package controller;

import javax.persistence.EntityManager;

import model.Theatre;
import dao.TheatreDao;



public class TheatreController {
	private EntityManager em;
	TheatreDao theatreDao;
	
	public TheatreController (EntityManager em) {
		this.em = em;
		this.theatreDao = new TheatreDao(em);
	}
	
	/*
	 * ajouter un theatre
	 */
	
	public void insertTheatre() {
		Theatre theatre = new Theatre();
		this.theatreDao.insertTheatre(theatre);
	}
	
	/*
	 * supprimer un theatre par codeTheatre
	 */
	public void deleteTheatreById(int id) {
		this.theatreDao.deleteTheatreById(id);
	}
	
	/*
	 * modifier un theatre par codeTheatre
	 */
	public void updateTheatreById(int id, Theatre theatre) {
		this.theatreDao.updateTheatreById(id, theatre);
	}
	
	/*
	 * selectionner un theatre par id
	 */
	public Theatre selectTheatreById(int id) {
		return this.theatreDao.selectTheatreById(id);
	}
}
