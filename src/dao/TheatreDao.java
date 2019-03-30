package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Theatre;

public class TheatreDao {
	private EntityManager em;

	public TheatreDao(EntityManager em) {
		this.em = em;
	}
	/*
	 * Inserter un Theatre
	 */
	public void insertTheatre(Theatre theatre) {
		em.getTransaction().begin();
		em.persist(theatre);
		em.getTransaction().commit();
	}
	/*
	 *  Supprimer un theatre par codetheatre
	 */
	public void deleteTheatreById(int id) {
		em.getTransaction().begin();
		Theatre theatre  = em.find(Theatre.class, id);
		em.remove(theatre);
		em.getTransaction().commit();
	}
	/*
	 * Modifier un theatre par codetheatre 
	 */
	public void updateTheatreById(int id, Theatre theatre) {
		em.getTransaction().begin();
		Theatre newtheatre = em.find(Theatre.class, id);
		newtheatre.setCodeTheatre(id);
		em.flush();
		em.getTransaction().commit();
	}
	
	/*
	 * Selectionner un Theatre par codeTheatre
	 */
	public Theatre selectTheatreById(int id) {	
		String sql = "SELECT l FROM Theatre l WHERE l.codeTheatre = :codeTheatre";
		Query query = em.createQuery(sql);
		query.setParameter("codeTheatre", id);
		Theatre theatre = null;
		try{
			theatre = (Theatre) query.getSingleResult();
		} catch (NoResultException e){
			theatre = null;
		}	
		return theatre;
	}
}
