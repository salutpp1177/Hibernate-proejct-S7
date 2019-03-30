package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Lieu;

public class LieuDao {
	private EntityManager em;

	public LieuDao(EntityManager em) {
		this.em = em;
	}
	/*
	 * Inserter un lieu
	 */
	public void insertLieu(Lieu lieu) {
		em.getTransaction().begin();
		em.persist(lieu);
		em.getTransaction().commit();
	}
	/*
	 *  Supprimer un lieu par codeLieu
	 */
	public void deleteLieuById(int id) {
		em.getTransaction().begin();
		Lieu lieu  = em.find(Lieu.class, id);
		em.remove(lieu);
		em.getTransaction().commit();
	}
	/*
	 * Modifier un Lieu par codeLieu 
	 */
	public void updateLieuById(int id, Lieu lieu) {
		em.getTransaction().begin();
		Lieu newlieu = em.find(Lieu.class, id);
		newlieu.setEndroit(lieu.getEndroit());
		newlieu.setDescription(lieu.getDescription());
		newlieu.setCodeLieu(id);
		newlieu.setPeriode(lieu.getPeriode());
		em.flush();
		em.getTransaction().commit();
	}
	
	/*
	 * Selectionner un lieu par codeLieu
	 */
	public Lieu selectLieuById(int id) {	
		String sql = "SELECT l FROM Lieu l WHERE l.codeLieu = :codeLieu";
		Query query = em.createQuery(sql);
		query.setParameter("codeLieu", id);
		Lieu lieu = null;
		try{
			lieu = (Lieu) query.getSingleResult();
		} catch (NoResultException e){
			lieu = null;
		}	
		return lieu;
	}
	
	/*
	 * Selectionner tous les lieux 
	 */
	public List<Lieu> selectAll() {
		String sql = "SELECT li FROM Lieu li";
		TypedQuery<Lieu> query = em.createNamedQuery(sql, Lieu.class);
		List<Lieu> list = new ArrayList<>();
		list = query.getResultList();	
		return list;
	}
	
}
