package org.techytax.zk.account;

import java.math.BigDecimal;

public class AccountCheckData {

	private BigDecimal accountBalance;
	private BigDecimal grossIncome;
	private BigDecimal paidInvoices;
	private BigDecimal taxBalance;
	private BigDecimal costBalance;
	private BigDecimal savingBalance;
	private BigDecimal privateWithdrawalBalance;
	private BigDecimal interest;
	private BigDecimal doubleCheck;
	private BigDecimal actualBalance;	
	
	public boolean getCorrect() {
		return actualBalance == null || doubleCheck.equals(actualBalance);
	}

	public BigDecimal getGrossIncome() {
		return grossIncome;
	}

	public BigDecimal getPaidInvoices() {
		return paidInvoices;
	}

	public BigDecimal getTaxBalance() {
		return taxBalance;
	}

	public BigDecimal getCostBalance() {
		return costBalance;
	}

	public BigDecimal getSavingBalance() {
		return savingBalance;
	}

	public BigDecimal getPrivateWithdrawalBalance() {
		return privateWithdrawalBalance;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setGrossIncome(BigDecimal grossIncome) {
		this.grossIncome = grossIncome;
	}

	public void setPaidInvoices(BigDecimal paidInvoices) {
		this.paidInvoices = paidInvoices;
	}

	public void setTaxBalance(BigDecimal taxBalance) {
		this.taxBalance = taxBalance;
	}

	public void setCostBalance(BigDecimal costBalance) {
		this.costBalance = costBalance;
	}

	public void setSavingBalance(BigDecimal savingBalance) {
		this.savingBalance = savingBalance;
	}

	public void setPrivateWithdrawalBalance(BigDecimal privateWithdrawalBalance) {
		this.privateWithdrawalBalance = privateWithdrawalBalance;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getDoubleCheck() {
		return doubleCheck;
	}

	public void setDoubleCheck(BigDecimal doubleCheck) {
		this.doubleCheck = doubleCheck;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public BigDecimal getActualBalance() {
		return actualBalance;
	}

	public void setActualBalance(BigDecimal actualBalance) {
		this.actualBalance = actualBalance;
	}
}
