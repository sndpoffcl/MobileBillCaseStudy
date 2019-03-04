package com.cg.billing.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.billing.beans.Address;
import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.daoservices.BillDAO;
import com.cg.billing.daoservices.BillDAOImpl;
import com.cg.billing.daoservices.CustomerDAO;
import com.cg.billing.daoservices.CustomerDAOImpl;
import com.cg.billing.daoservices.PlanDAO;
import com.cg.billing.daoservices.PlanDAOImpl;
import com.cg.billing.daoservices.PostpaidAccountDAO;
import com.cg.billing.daoservices.PostpaidAccountDAOImpl;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

public class BillingServicesImpl implements BillingServices{
	
	CustomerDAO customerDaoServices = new CustomerDAOImpl();
	BillDAO billDaoServices = new BillDAOImpl();
	PlanDAO planDaoServices = new PlanDAOImpl();
	PostpaidAccountDAO posDaoServices = new PostpaidAccountDAOImpl();

	@Override
	public List<Plan> getPlanAllDetails() {
		return planDaoServices.findAll();
	}

	@Override
	public int acceptCustomerDetails(String firstName, String lastName, String emailID, String dateOfBirth,
			String billingAddressCity, String billingAddressState, int billingAddressPinCode, String homeAddressCity,
			String homeAddressState, int homeAddressPinCode) {
		
		Customer customer  = new Customer(firstName , lastName , emailID , dateOfBirth,new ArrayList<Address>() ,new HashMap<Long, PostpaidAccount>() );
		customer =  customerDaoServices.saveCustomer(customer);
		return customer.getCustomerID();
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
		
		PostpaidAccount posAccount = new PostpaidAccount(customerDaoServices.findOne(customerID), planDaoServices.findOne(planID), new HashMap<Integer,Bill>());
		posAccount =  posDaoServices.save(posAccount);
		return posAccount.getMobileNo();
	}

	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException {
		
		double costOfLocalSms,costOfLocalCalls,costOfStdSms,costOfStdCalls,costOfDataUsage;
		
		Plan plan = getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getPlan();
		//calculating local sms rate
		if(noOfLocalSMS<plan.getFreeLocalSMS()) {
			 costOfLocalSms = 0;
		}else {
			 costOfLocalSms = (noOfLocalSMS-plan.getFreeLocalSMS())*plan.getLocalSMSRate();
		}
		//calculating local calls rate
		if(noOfLocalCalls<plan.getFreeLocalCalls()) {
			 costOfLocalCalls = 0;
		}else {
			 costOfLocalCalls = (noOfLocalCalls-plan.getFreeLocalCalls())*plan.getLocalCallRate();
		}
		//calculating std sms rate
		if(noOfStdSMS<plan.getFreeStdSMS()) {
			 costOfStdSms = 0;
		}else {
			 costOfStdSms = (noOfStdSMS-plan.getFreeStdSMS())*plan.getStdSMSRate();
		}
		// calculating std call rate
		if(noOfStdCalls<plan.getFreeStdCalls()) {
			 costOfStdCalls = 0;
		}else {
			 costOfStdCalls = (noOfStdCalls-plan.getFreeStdCalls())*plan.getStdCallRate();
		}
		//calculating data usage charges
		if(internetDataUsageUnits<plan.getFreeInternetDataUsageUnits()) {
			 costOfDataUsage = 0;
		}else {
			 costOfDataUsage = (internetDataUsageUnits-plan.getFreeInternetDataUsageUnits())*plan.getInternetDataUsageRate();
		}
		
		double totalBillBeforeTaxes =  costOfDataUsage + costOfLocalCalls + costOfLocalSms + costOfStdCalls + costOfStdSms ; 
		double cgst = 0.05*totalBillBeforeTaxes;
		double sgst = 0.05*totalBillBeforeTaxes;
		
		double totalBillAfterTaxes = totalBillBeforeTaxes + cgst + sgst ; 
		
		Bill newBill = new Bill(noOfLocalSMS , noOfStdSMS , noOfLocalCalls , noOfStdCalls , internetDataUsageUnits , billMonth , totalBillAfterTaxes , costOfLocalSms , costOfStdSms , costOfLocalCalls , costOfStdCalls , costOfDataUsage , sgst , cgst);
		newBill = billDaoServices.save(newBill);
		return newBill.getTotalBillAmount();
	}

	@Override
	public Customer getCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		return customerDaoServices.findOne(customerID);
	}

	@Override
	public List<Customer> getAllCustomerDetails() {
		return customerDaoServices.findAll();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		
		return getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo);
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException {
		
		return  (List<PostpaidAccount>) getCustomerDetails(customerID).getPostpaidAccounts().values();
	}

	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException {
		//TODO
		return getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getBills().get(billMonth);
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {

		return (List<Bill>) getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getBills().values();
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).setPlan(planDaoServices.findOne(planID));
		return true;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		
		PostpaidAccount posAccount = getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo);
		return posDaoServices.deleteOne(posAccount);
		
	}

	@Override
	public boolean removeCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		return customerDaoServices.deleteOne(customerID);
	}

	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		return getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getPlan();
	}

}
