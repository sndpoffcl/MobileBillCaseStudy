package com.cg.billing.beans;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Bill {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int billID; 
	private int noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits;
	private String billMonth;
	private double totalBillAmount, localSMSAmount, stdSMSAmount, localCallAmount, stdCallAmount, internetDataUsageAmount,stateGST,centralGST;
	
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bill(int billID, int noOfLocalSMS, int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls,
			int internetDataUsageUnits, String billMonth, float totalBillAmount, float localSMSAmount,
			float stdSMSAmount, float localCallAmount, float stdCallAmount, float internetDataUsageAmount,
			float stateGST, float centralGST) {
		super();
		this.billID = billID;
		this.noOfLocalSMS = noOfLocalSMS;
		this.noOfStdSMS = noOfStdSMS;
		this.noOfLocalCalls = noOfLocalCalls;
		this.noOfStdCalls = noOfStdCalls;
		this.internetDataUsageUnits = internetDataUsageUnits;
		this.billMonth = billMonth;
		this.totalBillAmount = totalBillAmount;
		this.localSMSAmount = localSMSAmount;
		this.stdSMSAmount = stdSMSAmount;
		this.localCallAmount = localCallAmount;
		this.stdCallAmount = stdCallAmount;
		this.internetDataUsageAmount = internetDataUsageAmount;
		this.stateGST = stateGST;
		this.centralGST = centralGST;
	}

	public Bill(int noOfLocalSMS2, int noOfStdSMS2, int noOfLocalCalls2, int noOfStdCalls2, int internetDataUsageUnits2,
			String billMonth2, double totalBillAfterTaxes, double costOfLocalSms, double costOfStdSms,
			double costOfLocalCalls, double costOfStdCalls, double costOfDataUsage, double sgst, double cgst) {
		
		this.noOfLocalSMS = noOfLocalSMS2;
		this.noOfStdSMS = noOfStdSMS2;
		this.noOfLocalCalls = noOfLocalCalls2;
		this.noOfStdCalls = noOfStdCalls2;
		this.internetDataUsageUnits = internetDataUsageUnits2;
		this.billMonth = billMonth2;
		this.totalBillAmount = totalBillAfterTaxes;
		this.localSMSAmount = costOfLocalSms;
		this.stdSMSAmount = costOfStdSms;
		this.localCallAmount = costOfLocalCalls;
		this.stdCallAmount = costOfStdCalls;
		this.internetDataUsageAmount = costOfDataUsage;
		this.stateGST = sgst;
		this.centralGST = cgst;
	}

	public int getBillID() {
		return billID;
	}

	public void setBillID(int billID) {
		this.billID = billID;
	}

	public int getNoOfLocalSMS() {
		return noOfLocalSMS;
	}

	public void setNoOfLocalSMS(int noOfLocalSMS) {
		this.noOfLocalSMS = noOfLocalSMS;
	}

	public int getNoOfStdSMS() {
		return noOfStdSMS;
	}

	public void setNoOfStdSMS(int noOfStdSMS) {
		this.noOfStdSMS = noOfStdSMS;
	}

	public int getNoOfLocalCalls() {
		return noOfLocalCalls;
	}

	public void setNoOfLocalCalls(int noOfLocalCalls) {
		this.noOfLocalCalls = noOfLocalCalls;
	}

	public int getNoOfStdCalls() {
		return noOfStdCalls;
	}

	public void setNoOfStdCalls(int noOfStdCalls) {
		this.noOfStdCalls = noOfStdCalls;
	}

	public int getInternetDataUsageUnits() {
		return internetDataUsageUnits;
	}

	public void setInternetDataUsageUnits(int internetDataUsageUnits) {
		this.internetDataUsageUnits = internetDataUsageUnits;
	}

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public double getTotalBillAmount() {
		return totalBillAmount;
	}

	public void setTotalBillAmount(float totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}

	public double getLocalSMSAmount() {
		return localSMSAmount;
	}

	public void setLocalSMSAmount(float localSMSAmount) {
		this.localSMSAmount = localSMSAmount;
	}

	public double getStdSMSAmount() {
		return stdSMSAmount;
	}

	public void setStdSMSAmount(float stdSMSAmount) {
		this.stdSMSAmount = stdSMSAmount;
	}

	public double getLocalCallAmount() {
		return localCallAmount;
	}

	public void setLocalCallAmount(float localCallAmount) {
		this.localCallAmount = localCallAmount;
	}

	public double getStdCallAmount() {
		return stdCallAmount;
	}

	public void setStdCallAmount(float stdCallAmount) {
		this.stdCallAmount = stdCallAmount;
	}

	public double getInternetDataUsageAmount() {
		return internetDataUsageAmount;
	}

	public void setInternetDataUsageAmount(float internetDataUsageAmount) {
		this.internetDataUsageAmount = internetDataUsageAmount;
	}

	public double getStateGST() {
		return stateGST;
	}

	public void setStateGST(float stateGST) {
		this.stateGST = stateGST;
	}

	public double getCentralGST() {
		return centralGST;
	}

	public void setCentralGST(float centralGST) {
		this.centralGST = centralGST;
	}

	
	
}