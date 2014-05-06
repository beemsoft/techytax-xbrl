/**
 * Copyright 2011 Hans Beemsterboer
 * 
 * This file is part of the TechyTax program.
 *
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.domain;

import java.math.BigInteger;

public class PrivatWithdrawal {

	private BigInteger totaleOnttrekking;

	private BigInteger withdrawalCash;
	
	private BigInteger withdrawalPrivateUsageBusinessCar;

	public BigInteger getTotaleOnttrekking() {
		return totaleOnttrekking;
	}

	public BigInteger getWithdrawalCash() {
		return withdrawalCash;
	}

	public void setTotaleOnttrekking(BigInteger totaleOnttrekking) {
		this.totaleOnttrekking = totaleOnttrekking;
	}

	public void setWithdrawalCash(BigInteger withdrawalCash) {
		this.withdrawalCash = withdrawalCash;
	}

	public BigInteger getWithdrawalPrivateUsageBusinessCar() {
		return withdrawalPrivateUsageBusinessCar;
	}

	public void setWithdrawalPrivateUsageBusinessCar(BigInteger withdrawalPrivateUsageBusinessCar) {
		this.withdrawalPrivateUsageBusinessCar = withdrawalPrivateUsageBusinessCar;
	}

}
