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
package org.techytax.helper;

import static org.techytax.log.AuditType.SEND_AUDIT_FILE;

import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import nl.auditfiles.xaf._3.Accounttype;
import nl.auditfiles.xaf._3.Auditfile;
import nl.auditfiles.xaf._3.Auditfile.Company;
import nl.auditfiles.xaf._3.Auditfile.Company.CustomersSuppliers;
import nl.auditfiles.xaf._3.Auditfile.Company.CustomersSuppliers.CustomerSupplier;
import nl.auditfiles.xaf._3.Auditfile.Company.CustomersSuppliers.CustomerSupplier.StreetAddress;
import nl.auditfiles.xaf._3.Auditfile.Company.GeneralLedger;
import nl.auditfiles.xaf._3.Auditfile.Company.GeneralLedger.LedgerAccount;
import nl.auditfiles.xaf._3.Auditfile.Company.OpeningBalance;
import nl.auditfiles.xaf._3.Auditfile.Company.Periods;
import nl.auditfiles.xaf._3.Auditfile.Company.Periods.Period;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions.Journal;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions.Journal.Transaction;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions.Journal.Transaction.TrLine;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions.Journal.Transaction.TrLine.Vat;
import nl.auditfiles.xaf._3.Auditfile.Header;
import nl.auditfiles.xaf._3.CountrycodeIso3166;
import nl.auditfiles.xaf._3.CurrencycodeIso4217;
import nl.auditfiles.xaf._3.Debitcredittype;
import nl.auditfiles.xaf._3.ObjectFactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.cache.CostTypeCache;
import org.techytax.jpa.dao.CostDao;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostType;
import org.techytax.domain.Customer;
import org.techytax.domain.FiscalPeriod;
import org.techytax.domain.User;
import org.techytax.external.domain.ExternalCostType;
import org.techytax.jpa.dao.CustomerDao;
import org.techytax.log.AuditLogger;
import org.techytax.util.DateHelper;
import org.techytax.util.VersionHelper;
import org.zkoss.zul.Filedownload;

@Component
public class DutchAuditFileHelper {
	
	@Autowired
	private AuditLogger auditLogger;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CostDao costDao;
	
	@Autowired
	CostTypeCache costTypeCache;

	public void sendAuditFile(User user, FiscalPeriod fiscalPeriod) {
		auditLogger.log(SEND_AUDIT_FILE, user);
		try {
			List<Cost> allCosts = new ArrayList<>();
			if (fiscalPeriod != null) {
				allCosts = costDao.getCostsInPeriod(fiscalPeriod);
				// TODO: sort costs
			}
			List<Customer> customers = customerDao.findAll(user);
			String message = createAuditFile(allCosts, customers, user);
			Filedownload.save(message, "XAF", user.getCompanyName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Transaction createTransaction(Cost cost) throws Exception {
		ObjectFactory objectFactory = new ObjectFactory();
		Transaction transaction = objectFactory.createAuditfileCompanyTransactionsJournalTransaction();
		transaction.setAmnt(cost.getAmount());
		transaction.setDesc(cost.getDescription().trim());
		transaction.setNr(Long.toString(cost.getId()));
		transaction.setTrDt(DateHelper.getDate(DateHelper.getDate(cost.getDate())));
		if (cost.getCostType().isBijschrijving() || cost.getCostType() == CostConstants.INVOICE_SENT) {
			transaction.setAmntTp(Debitcredittype.C);
		} else {
			transaction.setAmntTp(Debitcredittype.D);
		}
		if (cost.getVat() != null && cost.getVat().floatValue() > 0) {
			TrLine trLine = objectFactory.createAuditfileCompanyTransactionsJournalTransactionTrLine();
			Vat vat = objectFactory.createAuditfileCompanyTransactionsJournalTransactionTrLineVat();
			vat.setVatAmnt(cost.getVat());
			if (cost.getCostType().isBijschrijving() || cost.getCostType() == CostConstants.INVOICE_SENT) {
				vat.setVatAmntTp(Debitcredittype.C);
			} else {
				vat.setVatAmntTp(Debitcredittype.D);
			}
			trLine.getVat().add(vat);
			trLine.setAccID(Long.toString(cost.getCostTypeId()));
			transaction.getTrLine().add(trLine);
		}
		return transaction;
	}

	public String createAuditFile(List<Cost> costList, List<Customer> customers, User user) throws DatatypeConfigurationException {

		JAXBContext jc = null;
		Marshaller m = null;
		try {
			jc = JAXBContext.newInstance("nl.auditfiles.xaf._3");
			m = jc.createMarshaller();
			StringWriter writer = new StringWriter();
			m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ObjectFactory objectFactory = new ObjectFactory();
			Auditfile auditfile = objectFactory.createAuditfile();
			Company company = objectFactory.createAuditfileCompany();
			company.setCompanyName(user.getCompanyName());
			if (user.getChamberOfCommerceNumber() != null) {
				company.setCompanyIdent(Long.toString(user.getChamberOfCommerceNumber()));
			}
			company.setTaxRegIdent(user.getFiscalNumber());
			company.setTaxRegistrationCountry(CountrycodeIso3166.NL);
			
			nl.auditfiles.xaf._3.Auditfile.Company.StreetAddress address = objectFactory.createAuditfileCompanyStreetAddress();
			address.setStreetname(user.getCompanyAddress());
			address.setCity(user.getCompanyCity());
			address.setPostalCode(user.getCompanyZipCode());
			address.setCountry(CountrycodeIso3166.NL);
			company.getStreetAddress().add(address);

			CustomersSuppliers customersElement = objectFactory.createAuditfileCompanyCustomersSuppliers();
			for (Customer customer : customers) {
				CustomerSupplier customerElement = objectFactory.createAuditfileCompanyCustomersSuppliersCustomerSupplier();
				customerElement.setContact(customer.getContact());
				customerElement.setEMail(customer.getEmailInvoice());
				customerElement.setCustSupName(customer.getName());
				customerElement.setContact(customer.getContact());
				if (customer.getCommerceNr() != null) {
					customerElement.setCommerceNr(customer.getCommerceNr().toString());
				}
				customerElement.setFax(customer.getFax());
				customerElement.setTelephone(customer.getTelephone());
				customerElement.setWebsite(customer.getWebsite());
				StreetAddress customerAddress = objectFactory.createAuditfileCompanyCustomersSuppliersCustomerSupplierStreetAddress();
				customerAddress.setStreetname(customer.getAddress());
				customerAddress.setCity(customer.getCity());
				if (customer.getNumber() != null) {
					customerAddress.setNumber(customer.getNumber().toString());
				}
				customerAddress.setNumberExtension(customer.getNumberExtension());
				customerAddress.setPostalCode(customer.getPostalCode());
				customerAddress.setCountry(CountrycodeIso3166.NL);
				customerElement.getStreetAddress().add(customerAddress);
				customersElement.getCustomerSupplier().add(customerElement);
			}
			company.setCustomersSuppliers(customersElement);

			GeneralLedger generalLedger = objectFactory.createAuditfileCompanyGeneralLedger();
			
			for (CostType costType : costTypeCache.getCostTypes()) {
				LedgerAccount ledgerAccount = objectFactory.createAuditfileCompanyGeneralLedgerLedgerAccount();
				ExternalCostType externalCostType = costType.getExternalCostType();
				if (externalCostType != null) {
					ledgerAccount.setAccDesc(externalCostType.getDescription());
					ledgerAccount.setAccID(Long.toString(costType.getId()));
					if ("B".equals(externalCostType.getCode().substring(0,  1))) {
						ledgerAccount.setAccTp(Accounttype.B);
					} else {
						ledgerAccount.setAccTp(Accounttype.P);
					}
					ledgerAccount.setLeadCrossRef(externalCostType.getCode());
					generalLedger.getLedgerAccount().add(ledgerAccount);
				}
			}
			company.setGeneralLedger(generalLedger);

			OpeningBalance openingBalance = objectFactory.createAuditfileCompanyOpeningBalance();
			// company.setOpeningBalance(openingBalance);

			Periods periods = objectFactory.createAuditfileCompanyPeriods();
			Period period = objectFactory.createAuditfileCompanyPeriodsPeriod();
			period.setPeriodNumber(new BigInteger("1")); // referenced by
															// transactions
			period.setPeriodDesc("Period");
			periods.getPeriod().add(period);
			// company.setPeriods(periods);

			auditfile.setCompany(company);

			Header header = createHeader(costList, objectFactory);
			auditfile.setHeader(header);
			
			// Transactions
			Transactions transactions = objectFactory.createAuditfileCompanyTransactions();
			transactions.setLinesCount(new BigInteger(Long.toString(costList.size())));

			String currentKostenSoortOmschrijving = null;
			Journal journal = null;

			for (Cost cost : costList) {
				String kostenSoortOmschrijving = cost.getCostType().getOmschrijving();
				Transaction transaction = createTransaction(cost);
				if (!kostenSoortOmschrijving.equals(currentKostenSoortOmschrijving)) {
					currentKostenSoortOmschrijving = kostenSoortOmschrijving;
					if (journal != null) {
						// Add the previous journal
						transactions.getJournal().add(journal);
					}
					// Start a new journal
					journal = objectFactory.createAuditfileCompanyTransactionsJournal();
					ExternalCostType externalCostType = cost.getCostType().getExternalCostType();
					if (externalCostType != null) {
						journal.setDesc(externalCostType.getDescription());
						journal.setJrnID(externalCostType.getCode());
					}
					journal.setOffsetAccID(Long.toString(cost.getCostType().getId()));
				}
				journal.getTransaction().add(transaction);
			}
			if (journal != null) {
				transactions.getJournal().add(journal);
			}
			company.setTransactions(transactions);
			auditfile.setCompany(company);

			m.marshal(auditfile, writer);
			System.out.println(writer.toString());
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Header createHeader(List<Cost> costList, ObjectFactory objectFactory) throws Exception {
		Header header = objectFactory.createAuditfileHeader();
		header.setCurCode(CurrencycodeIso4217.EUR);
		int year = 0;
		Date startDate = null;
		Date endDate = null;
		if (costList != null && costList.size() > 0) {
			Cost firstCost = (Cost) costList.get(0);
			startDate = firstCost.getDate();
			Cost lastCost = (Cost) costList.get(costList.size() - 1);
			endDate = lastCost.getDate();
			year = DateHelper.getYear(startDate);
		} else {
			year = DateHelper.getYear(new Date());
		}
		header.setFiscalYear(Integer.toString(year));
		header.setDateCreated(DateHelper.getDate(DateHelper.getDate(new Date())));
		header.setSoftwareDesc("TechyTax");
		header.setSoftwareVersion(VersionHelper.getVersion());
		if (startDate != null) {
			header.setStartDate(DateHelper.getDateForXml(startDate));
		}
		if (endDate != null) {
			header.setEndDate(DateHelper.getDateForXml(endDate));
		}
		return header;
	}
	
	public void importAuditFile(InputStream inputStream, User user) throws DatatypeConfigurationException {

		JAXBContext jc = null;
		Unmarshaller m = null;
		try {
			jc = JAXBContext.newInstance("nl.auditfiles.xaf._3");
			m = jc.createUnmarshaller();
			
			
			Auditfile auditFile = (Auditfile) m.unmarshal(inputStream);
			
			System.out.println(auditFile.getHeader().getSoftwareDesc());
			
			List<Journal> journals = auditFile.getCompany().getTransactions().getJournal();
			for (Journal journal : journals) {
				String accID = journal.getOffsetAccID();
				for (Transaction transaction : journal.getTransaction()) {
					Cost importedCost = new Cost();
					importedCost.setAmount(transaction.getAmnt());
					if (StringUtils.isNotEmpty(accID)) {
						importedCost.setCostType(costTypeCache.getCostType(Long.parseLong(accID)));
					}
					importedCost.setDate(transaction.getTrDt().toGregorianCalendar().getTime());
					importedCost.setDescription(transaction.getDesc());
					importedCost.setUser(user);
					for (TrLine trLine : transaction.getTrLine()) {
						if (!trLine.getVat().isEmpty()) {
							Vat vat = trLine.getVat().get(0);
							importedCost.setVat(vat.getVatAmnt());
						}
					}
					costDao.persistEntity(importedCost);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
