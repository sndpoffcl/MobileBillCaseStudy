package com.cg.billing.daoservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;

public class PostpaidAccountDAOImpl implements PostpaidAccountDAO {
	
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
	
	
	@Override
	public PostpaidAccount save(PostpaidAccount posAccount) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(posAccount);
		entityManager.getTransaction().commit();
		entityManager.close();
		return posAccount;
	}

	@Override
	public boolean update(PostpaidAccount posAccount) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(posAccount);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public PostpaidAccount findOne(long mobileNo) {
		return entityManagerFactory.createEntityManager().find(PostpaidAccount.class, mobileNo);
	}

	@Override
	public List<PostpaidAccount> findAll() {
		Query query = entityManagerFactory.createEntityManager().createQuery("from PostpaidAccount p",PostpaidAccount.class);
		return (List<PostpaidAccount>)query.getResultList();
	}

}
