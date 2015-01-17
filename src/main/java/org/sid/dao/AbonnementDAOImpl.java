package org.sid.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.sid.entities.Abonnement;

public class AbonnementDAOImpl implements IAbonnementDAO {
	@PersistenceContext
	private EntityManager em;
	@Override
	public void addAbonnement(Abonnement ab) {
		em.persist(ab);		
	}

	@Override
	public List<Abonnement> listAbonnements(boolean actif) {
		Query req=em.createQuery("select ab from Abonnement ab where ab.actif=:x");
		req.setParameter("x",actif);
		return req.getResultList();
	}

	@Override
	public List<Abonnement> listAbonnements(Date d1, Date d2) {
		Query req=em.createQuery("select ab from Abonnement ab where ab.dateAbonnement between :x and :y");
		req.setParameter("x",d1);
		req.setParameter("y", d2);
		return req.getResultList();
	}

	@Override
	public Abonnement getAbonnement(Long idAb) {
		// TODO Auto-generated method stub
		return em.find(Abonnement.class, idAb);
	}

	@Override
	public void deleteAbonnement(Long idAb) {
		Abonnement ab=getAbonnement(idAb);
		em.remove(ab);
	}

	@Override
	public void updateAbonnement(Abonnement ab) {
		em.merge(ab);
		
	}

	@Override
	public void consommer(Long idAb, double mt) {
		Abonnement ab=getAbonnement(idAb);
		ab.setSolde(ab.getSolde()-mt);
		em.persist(ab);
	}

}
