package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.*;

public class SceneDao {
	private EntityManager em;
	
	public SceneDao(EntityManager em) {
		this.em = em;
	}
	
	/*
	 * inserter une scene
	 */
	public void insertScene(Scene sc) {
		em.getTransaction().begin();
		em.persist(sc);
		em.getTransaction().commit();
	}
	
	/*
	 * supprimer une scene par codeScene
	 */
	public void deleteSceneById(int id) {
		em.getTransaction().begin();
		Scene sc  = em.find(Scene.class, id);
		em.remove(sc);
		em.getTransaction().commit();
	}
	
	/* 
	 * modifier une scene par codeScene
	 */
	public void updateSceneById(int id, Scene sc) {
		em.getTransaction().begin();
		Scene scene = em.find(Scene.class, id);
		scene.setCodeScene(id);
		em.flush();
		em.getTransaction().commit();
	}
	
	/*
	 * selectionner une scene par codeScene
	 */
	public Scene selectSceneById(int id) {
		String sql = "SELECT sc FROM Scene sc WHERE sc.codeScene = :codeScene";
		Query query = em.createQuery(sql);
		query.setParameter("codeScene", id);
		Scene scene = null;
		try {
			scene = (Scene) query.getSingleResult();
		} catch (NoResultException e) {
			scene = null;
		}
		
		return scene;
		
	}
	/*
	 * selectionner une scene par description
	 */
	public Scene selectSceneByDescription(String s) {
		String sql = "SELECT sc FROM Scene sc WHERE sc.description like :description";
		Query query = em.createQuery(sql);
		query.setParameter("description", s);
		Scene scene = null;
		try {
			scene = (Scene) query.getSingleResult();
		} catch (NoResultException e) {
			scene = null;
		}
		return scene;
	}
	
	/*
	 * selectionner toutes les scenes
	 */
	public List<Scene> selectAll() {
		String sql = "SELECT sc FROM Scene sc";
		TypedQuery<Scene> query = em.createQuery(sql, Scene.class);
		List<Scene> list = new ArrayList<>();
		list = query.getResultList();	
		return list;
	}
}
