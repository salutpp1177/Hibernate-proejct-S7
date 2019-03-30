package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dao.ClapDao;
import model.*;

public class ClapController {
	
	private EntityManager em;
	ClapDao clapDao;
	
	public ClapController (EntityManager em) {
		this.em = em;
		this.clapDao = new ClapDao(em);
	}
	/*
	 * ajouter un clap
	 */
	public void insertClap(double temps, int bobine, Setup s) {
		Clap c = new Clap();
		c.setTempsSequence(temps);
		c.setCodeBobine(bobine);
		c.setSetup(s);
		this.clapDao.insertClap(c);
	}
	
	/*
	 * supprimer un clap par codeClap
	 */
	public void deleteClapById(int id) {
		this.clapDao.deleteClapById(id);
	}
	
	/*
	 * modifier un clap par codeClap
	 */
	public void updateClapById(int id, Clap c) {
		this.clapDao.updateClapById(id, c);
	}
	
	/*
	 * selectionner un clap par id
	 */
	public Clap selectClapById(int id) {
		return this.clapDao.selectClapById(id);
	}
	
	/*
	 * selectionner tous les claps
	 */
	public List<Clap> selectAll() {
		return this.clapDao.selectAll();
	}
	
	/*
	 * selectionner tous les claps par codeSetup
	 */
	public List<Clap> selectAllBySetup(int id) {
		return this.clapDao.selectAllBySetup(id);
	}
}
