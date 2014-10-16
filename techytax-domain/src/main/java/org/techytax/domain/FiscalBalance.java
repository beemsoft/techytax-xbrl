package org.techytax.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Data;

@Data
public class FiscalBalance {
	
	private BigDecimal totalPurchaseCost;
	
	private BigInteger beginSaldo;
	
	private BigInteger endSaldo;
	
	private BigInteger totalRemainingValue;

}
