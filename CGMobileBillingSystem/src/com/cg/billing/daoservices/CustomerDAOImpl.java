package com.cg.billing.daoservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.billing.beans.Customer;

public class CustomerDAOImpl implements CustomerDAO{
	
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");

	@Override
	public Customer saveCustomer(Customer customer) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		entityManager.close();
		return customer;
		
	}

	@Override
	public boolean upDateCustomer(Customer customer) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public Customer findOne(int customerId) {
		return entityManagerFactory.createEntityManager().find(Customer.class,customerId);
	}

	@Override
	public List<Customer> findAll() {
		Query query = entityManagerFactory.createEntityManager().createQuery("from Customer c",Customer.class);
		return (List<Customer>)query.getResultList();
	}

}
