package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dao.*;
import model.*;

public class SetupController {
	private EntityManager em;
	SetupDao setupDao;
	
	public SetupController(EntityManager em) {
		this.em = em;
		this.setupDao = new SetupDao(em);
	}
	
	
	/*
	 * inserter un setup
	 */
	public void insertSetup(String eo, double df, int nf, Scene sc, List<Clap> list) {
		Setup s = new Setup();
		s.setExpositionOuverture(eo);
		s.setDistanceFocale(df);
		s.setNumFiltres(nf);
		s.setScene(sc);
		s.setClap(list);
		this.setupDao.insertSetup(s);
	}
	
	/*
	 * supprimer un setup par codeSetup
	 */
	public void deleteSetupById(int id) {
		this.setupDao.deleteSetupById(id);
	}
	
	/*
	 * modifier un setup par codeSetup
	 */
	public void updateSetupById(int id, Setup s) {
		this.setupDao.updateSetupById(id, s);
	}
	
	/*
	 * selectionner un setup par codeSetup
	 */
	public Setup selectSetupById(int id) {
		return this.setupDao.selectSetupById(id);
	}
	
	/*
	 * selectionner tous les setups
	 */
	public List<Setup> selectAll() {
		return this.setupDao.selectAll();
	}
	
	/*
	 * selectionner tous les setups par codeScene
	 */
	public List<Setup> selectAllByScene(int id) {
		return this.setupDao.selectAllByScene(id);
	}
	
	/*
	 * selectionner tous les setups dont codeScene est null.
	 */
	public List<Setup> selectAllDisponible() {
		return this.setupDao.selectAllDisponible();
	}
}
