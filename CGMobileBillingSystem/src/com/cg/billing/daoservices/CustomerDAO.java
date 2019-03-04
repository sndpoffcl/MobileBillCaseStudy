package com.cg.billing.daoservices;

import java.util.List;

import com.cg.billing.beans.Customer;

public interface CustomerDAO {
	Customer saveCustomer(Customer customer) ;
	boolean upDateCustomer(Customer customer);
	Customer findOne(int customerId);
	List<Customer> findAll();
	boolean deleteOne(int customerId);
	boolean deleteAll();
}
