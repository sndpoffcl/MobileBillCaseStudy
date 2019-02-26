package com.cg.billing.daoservices;

import java.util.List;

import com.cg.billing.beans.Plan;

public interface PlanDAO  {
	Plan save(Plan plan);
	boolean update(Plan plan);
	Plan findOne(int planId);
	List<Plan> findAll();
}
