package com.cg.billing.daoservices;

import java.util.List;

import com.cg.billing.beans.Bill;

public interface BillDAO {
  Bill save(Bill bill);
  boolean update(Bill bill);
  Bill findOne(int billId);
  List<Bill> findAll();
  boolean deleteOne(int billId);
  boolean deleteAll();
  
}
