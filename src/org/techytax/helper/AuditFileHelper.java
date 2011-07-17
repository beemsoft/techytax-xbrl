package org.techytax.helper;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import nl.auditfiles.xaf._3.Auditfile;
import nl.auditfiles.xaf._3.CurrencyCodeType;
import nl.auditfiles.xaf._3.ObjectFactory;
import nl.auditfiles.xaf._3.Auditfile.Company;
import nl.auditfiles.xaf._3.Auditfile.Header;
import nl.auditfiles.xaf._3.Auditfile.Company.GeneralLedger;
import nl.auditfiles.xaf._3.Auditfile.Company.OpeningBalance;
import nl.auditfiles.xaf._3.Auditfile.Company.Periods;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions;
import nl.auditfiles.xaf._3.Auditfile.Company.GeneralLedger.Basics;
import nl.auditfiles.xaf._3.Auditfile.Company.GeneralLedger.Basics.Basic;
import nl.auditfiles.xaf._3.Auditfile.Company.Periods.Period;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions.Journal;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions.Journal.Transaction;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions.Journal.Transaction.TrLine;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions.Journal.Transaction.TrLine.Currency;
import nl.auditfiles.xaf._3.Auditfile.Company.Transactions.Journal.Transaction.TrLine.Vat;

import org.techytax.domain.Kost;
import org.techytax.props.PropsFactory;


public class AuditFileHelper {
	
	public static String createAuditFile(List<Kost> costList) throws DatatypeConfigurationException {

		
		JAXBContext jc = null;
		Marshaller m = null;
		try {
			// Load properties
			Properties props = PropsFactory.loadProperties();
			
			jc = JAXBContext.newInstance("nl.auditfiles.xaf._3");
			m = jc.createMarshaller();
			StringWriter writer = new StringWriter();
			m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ObjectFactory objectFactory = new ObjectFactory();
			Auditfile auditfile = objectFactory.createAuditfile();
			Company company = objectFactory.createAuditfileCompany();
			company.setCompanyIdent(props.getProperty("company.id"));
			company.setCompanyName(props.getProperty("company.name"));
			company.setTaxRegIdent(props.getProperty("tax.id"));
			company.setTaxRegistrationCountry(props.getProperty("tax.country"));
//			company.setCustomersSuppliers(value)   not yet filled by TechyTax
			GeneralLedger generalLedger = objectFactory.createAuditfileCompanyGeneralLedger();
			Basics basics = objectFactory.createAuditfileCompanyGeneralLedgerBasics();
//			basics.
			Basic basic = objectFactory.createAuditfileCompanyGeneralLedgerBasicsBasic();
//			basic.s
			
			generalLedger.setBasics(basics);
			company.setGeneralLedger(generalLedger);
			
			OpeningBalance openingBalance = objectFactory.createAuditfileCompanyOpeningBalance();
//			openingBalance.s
			company.setOpeningBalance(openingBalance);
			
			Periods periods = objectFactory.createAuditfileCompanyPeriods();
//			periods.set
			Period period = objectFactory.createAuditfileCompanyPeriodsPeriod();
			period.setPeriodNumber(new BigInteger("1")); // referenced by transactions
			period.setPeriodDesc("Period"); 
			periods.getPeriod().add(period);
			company.setPeriods(periods);
			

			auditfile.setCompany(company);
			
			Header header = objectFactory.createAuditfileHeader();
			header.setCurCode(CurrencyCodeType.EUR);
			header.setFiscalYear("2010");
			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			header.setDateCreated(date);
			header.setSoftwareDesc("TechyTax");
			header.setSoftwareVersion("1.4");

			auditfile.setHeader(header);
			
			// Transactions
			Transactions transactions = objectFactory.createAuditfileCompanyTransactions();
			transactions.setLinesCount(new BigInteger("1"));
			transactions.setTotalCredit(new BigDecimal("1"));
			transactions.setTotalDebit(new BigDecimal("1"));
			Journal journal = objectFactory.createAuditfileCompanyTransactionsJournal();
//			journal.setBankAccNr("123");
			journal.setDesc("zakelijke rekening");
			
			Transaction transaction = objectFactory.createAuditfileCompanyTransactionsJournalTransaction();
			transaction.setAmnt(new BigDecimal("1"));
			transaction.setDesc("Transactie");
			transaction.setNr("1");
			transaction.setPeriodNumber(new BigInteger("1"));
			transaction.setTrDt(date);
			transaction.setAmntTp("btw code?");
			
			TrLine trLine = objectFactory.createAuditfileCompanyTransactionsJournalTransactionTrLine();
			Vat vat = objectFactory.createAuditfileCompanyTransactionsJournalTransactionTrLineVat();
			vat.setVatAmnt(new BigDecimal("5.43"));
			trLine.getVat().add(vat);
			
			Currency currency = objectFactory.createAuditfileCompanyTransactionsJournalTransactionTrLineCurrency();
			currency.setCurCode(CurrencyCodeType.EUR);
			
			trLine.setCurrency(currency);
			
			transaction.getTrLine().add(trLine);
			
			
			
		
			
			journal.getTransaction().add(transaction);
			
//			journal.set
			
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
	
	public static void main(String[] args) {
		
	}

}
