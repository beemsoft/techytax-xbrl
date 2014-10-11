/**
 * Copyright 2014 Hans Beemsterboer
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
package org.techytax.report.helper;

import java.math.BigDecimal;

public class Invoice {

	private float unitsOfWork;
	private String consumerAddress;
	private String consumerName;
	private int vat;
	private String vatIdNr;
	private String chamberOfCommerceNumber;
	private String email;
	private String emailBcc;
	private String emailCc;
	private int invoiceNumber;
	private int year;
	private String month;
	private BigDecimal netAmount;

	private String activityDescription;
	private BigDecimal rate;
	private BigDecimal totalAmount;
	private BigDecimal vatAmount;
	private int discountPercentage;
	private BigDecimal discount;
	private BigDecimal netAmountAfterDiscount;	

	public float getUnitsOfWork() {
		return unitsOfWork;
	}

	public String getConsumerAddress() {
		return consumerAddress;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public int getVat() {
		return vat;
	}

	public String getVatIdNr() {
		return vatIdNr;
	}

	public String getEmail() {
		return email;
	}

	public String getEmailBcc() {
		return emailBcc;
	}

	public String getEmailCc() {
		return emailCc;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public int getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public void setUnitsOfWork(float unitsOfWork) {
		this.unitsOfWork = unitsOfWork;
	}

	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public void setVatIdNr(String vatIdNr) {
		this.vatIdNr = vatIdNr;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public BigDecimal getNetAmountAfterDiscount() {
		return netAmountAfterDiscount;
	}

	public void setNetAmountAfterDiscount(BigDecimal netAmountAfterDiscount) {
		this.netAmountAfterDiscount = netAmountAfterDiscount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getChamberOfCommerceNumber() {
		return chamberOfCommerceNumber;
	}

	public void setChamberOfCommerceNumber(String chamberOfCommerceNumber) {
		this.chamberOfCommerceNumber = chamberOfCommerceNumber;
	}

}
