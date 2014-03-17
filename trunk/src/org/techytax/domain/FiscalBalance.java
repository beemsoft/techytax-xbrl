package org.techytax.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FiscalBalance {
	
	private BigDecimal totalPurchaseCost;
	
	private BigInteger beginSaldo;
	
	private BigInteger endSaldo;
	
	private BigInteger totalRemainingValue;

	public BigInteger getBeginSaldo() {
		return beginSaldo;
	}

	public void setBeginSaldo(BigInteger beginSaldo) {
		this.beginSaldo = beginSaldo;
	}

	public BigInteger getEndSaldo() {
		return endSaldo;
	}

	public void setEndSaldo(BigInteger endSaldo) {
		this.endSaldo = endSaldo;
	}

	public BigDecimal getTotalPurchaseCost() {
		return totalPurchaseCost;
	}

	public void setTotalPurchaseCost(BigDecimal totalPurchaseCost) {
		this.totalPurchaseCost = totalPurchaseCost;
	}

	public BigInteger getTotalRemainingValue() {
		return totalRemainingValue;
	}

	public void setTotalRemainingValue(BigInteger totalRemainingValue) {
		this.totalRemainingValue = totalRemainingValue;
	}

}
