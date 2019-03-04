package com.cg.billing.daoservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Plan;

public class PlanDAOImpl implements PlanDAO {
	

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
		

	@Override
	public Plan save(Plan plan) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(plan);
		entityManager.getTransaction().commit();
		entityManager.close();
		return plan;
	}

	@Override
	public boolean update(Plan plan) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(plan);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public Plan findOne(int planId) {
		return entityManagerFactory.createEntityManager().find(Plan.class,planId);
	}

	@Override
	public List<Plan> findAll() {
		Query query = entityManagerFactory.createEntityManager().createQuery("from Plan p",Plan.class);
		return (List<Plan>)query.getResultList();
	}

	@Override
	public boolean deleteOne(int planId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(Plan.class, planId));
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

}
