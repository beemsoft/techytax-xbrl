/**
 * Copyright 2012 Hans Beemsterboer
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
package org.techytax.struts.form;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;

public class CostForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private long id = 0;
	private BigDecimal amount;
	private BigDecimal vat;
	private String date;
	private String description;
	private long costTypeId;
	
	private BigDecimal splitAmount = new BigDecimal("0");
	private BigDecimal splitVat = new BigDecimal("0");
	private String splitDescription;
	private long splitCostTypeId;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCostTypeId() {
		return costTypeId;
	}

	public void setCostTypeId(long costTypeId) {
		this.costTypeId = costTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public BigDecimal getSplitAmount() {
		return splitAmount;
	}

	public void setSplitAmount(BigDecimal splitAmount) {
		this.splitAmount = splitAmount;
	}

	public BigDecimal getSplitVat() {
		return splitVat;
	}

	public void setSplitVat(BigDecimal splitVat) {
		this.splitVat = splitVat;
	}

	public String getSplitDescription() {
		return splitDescription;
	}

	public void setSplitDescription(String splitDescription) {
		this.splitDescription = splitDescription;
	}

	public long getSplitCostTypeId() {
		return splitCostTypeId;
	}

	public void setSplitCostTypeId(long splitCostTypeId) {
		this.splitCostTypeId = splitCostTypeId;
	}

}
