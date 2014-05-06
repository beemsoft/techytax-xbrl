package org.techytax.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class VatBalanceWithinEu extends Balance {
	
	BigDecimal turnoverNetEu;
	
	BigInteger vatOutEu;

	public BigDecimal getTurnoverNetEu() {
		return turnoverNetEu;
	}

	public void setTurnoverNetEu(BigDecimal turnoverNetEu) {
		this.turnoverNetEu = turnoverNetEu;
	}

	public BigInteger getVatOutEu() {
		return vatOutEu;
	}

	public void setVatOutEu(BigInteger vatOutEu) {
		this.vatOutEu = vatOutEu;
	}

}
