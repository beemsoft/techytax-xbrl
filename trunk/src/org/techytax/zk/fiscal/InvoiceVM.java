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
package org.techytax.zk.fiscal;

import static org.techytax.domain.CostConstants.INVOICE_PAID;
import static org.techytax.domain.CostConstants.INVOICE_SENT;
import static org.techytax.log.AuditType.INVOICE_OVERVIEW;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.techytax.dao.BookValueDao;
import org.techytax.dao.CostDao;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.helper.AmountHelper;
import org.techytax.log.AuditLogger;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;

public class InvoiceVM {

	private ListModelList<InvoiceCheck> invoices;
	private InvoiceCheck selected;
	private CostDao costDao = new CostDao(Cost.class);
	private BookValueDao bookValueDao = new BookValueDao(BookValue.class);	
	private User user = UserCredentialManager.getUser();
	private Date balanceDate = new Date();
	private BigDecimal totalIncome = BigDecimal.ZERO;
	private BigInteger unpaidInvoicesFromPreviousYear = BigInteger.ZERO;
	private BigDecimal paidInvoicesWithoutMatch = BigDecimal.ZERO;
	private BigDecimal unpaidInvoicesFromThisYear = BigDecimal.ZERO;

	public ListModelList<InvoiceCheck> getInvoices() throws Exception {
		if (user != null) {
			AuditLogger.log(INVOICE_OVERVIEW, user);
			Periode period = DateHelper.getPeriodTillDate(balanceDate);
			List<Cost> sentAndPaidInvoicesInPeriod = costDao.getInvoices(period.getBeginDatum(), period.getEindDatum());
			invoices = new ListModelList<>();

			int currentYear = DateHelper.getYear(new Date());

			for (Cost cost : sentAndPaidInvoicesInPeriod) {
				InvoiceCheck invoiceCheck = new InvoiceCheck();
 				if (cost.getCostType().equals(INVOICE_SENT)) {
					invoiceCheck.setDateSent(cost.getDate());
					invoiceCheck.setInvoiceNumber(getInvoiceNumber(cost.getDescription()));
					invoiceCheck.setNetAmount(cost.getAmount());
					invoiceCheck.setVatAmount(cost.getVat());
					totalIncome = totalIncome.add(cost.getAmount());
					invoices.add(invoiceCheck);
				} else {
					if (!invoiceCheck.isPaymentFromPreviousYear()) {

					}
				}
			}
			for (Cost cost : sentAndPaidInvoicesInPeriod) {
				if (cost.getCostType().equals(INVOICE_PAID)) {

					Iterator<InvoiceCheck> sentInvoices = invoices.iterator();
					boolean hasMatchingInvoice = false;
					while (sentInvoices.hasNext()) {
						InvoiceCheck invoiceCheck = sentInvoices.next();
						if (cost.getDescription().contains(invoiceCheck.getInvoiceNumber())) {
							invoiceCheck.setDateReceived(cost.getDate());
							invoiceCheck.setDescriptionReceived(cost.getDescription());
							invoiceCheck.setGrossAmount(cost.getAmount());
							if (cost.getAmount().equals(invoiceCheck.getNetAmount().add(invoiceCheck.getVatAmount()))) {
								invoiceCheck.setInvoiceNumber(invoiceCheck.getInvoiceNumber() + " ok");
							}
							hasMatchingInvoice = true;
							break;
						} else {

						}
					}
					if (!hasMatchingInvoice) {
						paidInvoicesWithoutMatch = paidInvoicesWithoutMatch.add(cost.getAmount());
					}
				}
			}
			Iterator<InvoiceCheck> sentInvoices = invoices.iterator();
			while (sentInvoices.hasNext()) {
				InvoiceCheck invoiceCheck = sentInvoices.next();
				if (invoiceCheck.getDateReceived() == null) {
					unpaidInvoicesFromThisYear = unpaidInvoicesFromThisYear.add(invoiceCheck.getNetAmount().add(invoiceCheck.getVatAmount()));
				}
			}
			BookValue bookValuePreviousYear = bookValueDao.getBookValue(BalanceType.INVOICES_TO_BE_PAID, currentYear - 1);
			if (bookValuePreviousYear != null) {
				unpaidInvoicesFromPreviousYear = bookValuePreviousYear.getSaldo();
			}
		} else {
			Executions.sendRedirect("login.zul");
		}
		return invoices;
	}

	private String getInvoiceNumber(String invoiceDescription) {
		StringTokenizer tokenizer = new StringTokenizer(invoiceDescription);
		tokenizer.nextElement();
		String invoiceNumber = tokenizer.nextToken();
		return invoiceNumber;
	}

	public InvoiceCheck getSelected() {
		return selected;
	}

	@NotifyChange({ "selected" })
	public void setSelected(InvoiceCheck selected) {
		this.selected = selected;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	@NotifyChange("*")
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getTotalIncome() {
		return AmountHelper.formatWithEuroSymbol(totalIncome);
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getUnpaidInvoicesFromPreviousYear() {
		return AmountHelper.formatWithEuroSymbol(unpaidInvoicesFromPreviousYear);
	}

	public void setUnpaidInvoicesFromPreviousYear(BigInteger unpaidInvoicesFromPreviousYear) {
		this.unpaidInvoicesFromPreviousYear = unpaidInvoicesFromPreviousYear;
	}

	public String getPaidInvoicesWithoutMatch() {
		return AmountHelper.formatWithEuroSymbol(paidInvoicesWithoutMatch);
	}

	public void setPaidInvoicesWithoutMatch(BigDecimal paidInvoicesWithoutMatch) {
		this.paidInvoicesWithoutMatch = paidInvoicesWithoutMatch;
	}

	public String getUnpaidInvoicesFromThisYear() {
		return AmountHelper.formatWithEuroSymbol(unpaidInvoicesFromThisYear);
	}

	public void setUnpaidInvoicesFromThisYear(BigDecimal unpaidInvoicesFromThisYear) {
		this.unpaidInvoicesFromThisYear = unpaidInvoicesFromThisYear;
	}
}
