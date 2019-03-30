package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Clap;
import model.Setup;

public class ClapDao {
	
	private EntityManager em;
	
	public ClapDao(EntityManager em) {
		this.em = em;
	}
	
	/*
	 * Inserter un clap
	 */
	public void insertClap(Clap c) {
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}
	
	/*
	 *  Supprimer un clap par codeClap
	 */
	public void deleteClapById(int id) {
		em.getTransaction().begin();
		Clap c  = em.find(Clap.class, id);
		em.remove(c);
		em.getTransaction().commit();
	}
	/*
	 * Modifier un Clap par codeClap 
	 */
	public void updateClapById(int id, Clap c) {
		em.getTransaction().begin();
		Clap  clap = em.find(Clap.class, id);
		clap.setTempsSequence(c.getTempsSequence());
		clap.setCodeBobine(c.getCodeBobine());
		clap.setCodeClap(id);
		em.flush();
		em.getTransaction().commit();
	}
	
	/*
	 * Selectionner un clap par codeClap
	 */
	public Clap selectClapById(int id) {	
		String sql = "SELECT c FROM Clap c WHERE c.codeClap = :codeClap";
		Query query = em.createQuery(sql);
		query.setParameter("codeClap", id);
		Clap clap = null;
		try{
			clap = (Clap) query.getSingleResult();
		} catch (NoResultException e){
			clap = null;
		}	
		return clap;
	}
	
	/*
	 * Selectionner tous les claps
	 */
	public List<Clap> selectAll() {
		String sql = "Select c FROM Clap c";
		TypedQuery<Clap> query = em.createQuery(sql, Clap.class);
		List<Clap> list = new ArrayList<>();
		list = query.getResultList();	
		return list;
	}
	
	/*
	 * Selectionner tous les claps par codeSetup 
	 */
	public List<Clap> selectAllBySetup(int codeSetup) {
		String sql = "SELECT c FROM Clap c WHERE c.codeSetup";
		TypedQuery<Clap> query = em.createNamedQuery(sql, Clap.class);
		List<Clap> list = new ArrayList<>();
		list = query.getResultList();	
		return list;
	}
	
	
	/*
	 * selectionner tous les setups dont codeSetup est null.
	 */
	public List<Clap> selectAllDisponible() {
		String sql = "SELECT c FROM Clap c WHERE c.codeSetup IS NULL";
		TypedQuery<Clap> query = em.createNamedQuery(sql, Clap.class);
		List<Clap> list = new ArrayList<>();
		list = query.getResultList();	
		return list;
	}
	
	
}
