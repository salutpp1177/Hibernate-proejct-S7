package controller;

import java.util.List;

import javax.persistence.EntityManager;

import model.Lieu;
import dao.LieuDao;



public class LieuController {
	private EntityManager em;
	LieuDao lieuDao;
	
	public LieuController (EntityManager em) {
		this.em = em;
		this.lieuDao = new LieuDao(em);
	}
	/*
	 * ajouter un lieu
	 */
	public void insertLieu(String endroit, String description, String periode) {
		Lieu lieu = new Lieu();
		lieu.setEndroit(endroit);
		lieu.setDescription(description);
		lieu.setPeriode(periode);
		this.lieuDao.insertLieu(lieu);
	}
	
	/*
	 * supprimer un lieu par codeLieu
	 */
	public void deleteLieuById(int id) {
		this.lieuDao.deleteLieuById(id);
	}
	
	/*
	 * modifier un lieu par codeLieu
	 */
	public void updateLieuById(int id, Lieu lieu) {
		this.lieuDao.updateLieuById(id, lieu);
	}
	
	/*
	 * selectionner un lieu par id
	 */
	public Lieu selectLieuById(int id) {
		return this.lieuDao.selectLieuById(id);
	}
	
	/*
	 * selectionner tous les lieux
	 */
	public List<Lieu> selectAll() {
		return this.lieuDao.selectAll();
	}
	
}
