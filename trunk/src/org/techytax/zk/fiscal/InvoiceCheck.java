package org.techytax.zk.fiscal;

import java.math.BigDecimal;
import java.util.Date;

import org.techytax.util.DateHelper;

public class InvoiceCheck {
	
	private static int currentYear = DateHelper.getYear(new Date());

	private BigDecimal grossAmount;
	private BigDecimal netAmount;
	private BigDecimal vatAmount;
	private Date dateSent;
	private Date dateReceived;
	private String invoiceNumber;
	private String descriptionReceived;
	
	public boolean isPaymentFromPreviousYear() {
		if (descriptionReceived == null) {
			return false;
		}
		return descriptionReceived.contains(Integer.toString(currentYear-1));
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getDescriptionReceived() {
		return descriptionReceived;
	}

	public void setDescriptionReceived(String descriptionReceived) {
		this.descriptionReceived = descriptionReceived;
	}
}
