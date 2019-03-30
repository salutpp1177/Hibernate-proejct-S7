package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.*;

public class SetupDao {
	
	private EntityManager em;
	
	public SetupDao(EntityManager em) {
		this.em = em;
	}
	
	/*
	 * inserter un setup 
	 */
	public void insertSetup(Setup s) {
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
	}
	
	/*
	 * supprimer un setup par codeSetup
	 */
	public void deleteSetupById(int id) {
		em.getTransaction().begin();
		Setup s  = em.find(Setup.class, id);
		em.remove(s);
		em.getTransaction().commit();
	}
	
	/*
	 * modifier un setup par codeSetup
	 */
	public void updateSetupById(int id, Setup s) {
		em.getTransaction().begin();
		Setup setup = em.find(Setup.class, id);
		setup.setDistanceFocale(s.getDistanceFocale());
		setup.setExpositionOuverture(s.getExpositionOuverture());
		setup.setNumFiltres(s.getNumFiltres());
		setup.setCodeSetup(id);
		em.flush();
		em.getTransaction().commit();
	}
	
	/*
	 * selectionner un setup par codeSetup
	 */
	public Setup selectSetupById(int id) {
		String sql = "SELECT s FROM Setup s WHERE s.codeSetup = :codeSetup";
		Query query = em.createQuery(sql);
		query.setParameter("codeSetup", id);
		Setup setup = null;
		try {
			setup = (Setup) query.getSingleResult();
		} catch (NoResultException e) {
			setup = null;
		}
		return setup;
	}
	
	/*
	 * selectionner tous les setups
	 */
	public List<Setup> selectAll() {
		String sql = "SELECT s FROM Setup s";
		TypedQuery<Setup> query = em.createQuery(sql, Setup.class);
		List<Setup> list = new ArrayList<>();
		list = query.getResultList();	
		return list;
	}
	
	
	/*
	 * selelctionner tous les setups par codeScene
	 */
	public List<Setup> selectAllByScene(int id) {
		String sql = "SELECT s FROM Setup s WHERE s.codeScene";
		TypedQuery<Setup> query = em.createNamedQuery(sql, Setup.class);
		List<Setup> list = new ArrayList<>();
		list = query.getResultList();	
		return list;
	}
	
	/*
	 * selectionner tous les setups dont codeScene est null.
	 */
	public List<Setup> selectAllDisponible() {
		String sql = "SELECT * FROM Setup s WHERE s.codeScene IS NULL";
		Query query = em.createNativeQuery(sql);
		List<Setup> list = new ArrayList<>();
		list = query.getResultList();	
		return list;
	}
}
