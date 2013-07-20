/**
 * Copyright 2013 Hans Beemsterboer
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
package org.techytax.digipoort;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.ACAPITALstring4FItemType;
import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.Anstring25VItemType;
import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.Anstring2FItemType;
import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.Anstring35VItemType;
import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.DateTimeItemType;
import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.MessageReferenceSupplierVATItemType;
import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.MonetaryNoDecimals10VItemType;
import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.MonetaryNoDecimals9VItemType;
import nl.nltaxonomie._8_0_a_2.domein.bd.tuples.bd_ob_tuples.CorrespondentDeclarantSpecification;

import org.techytax.domain.Balans;
import org.techytax.domain.Periode;
import org.techytax.domain.VatDeclarationData;
import org.techytax.helper.AmountHelper;
import org.techytax.util.DateHelper;
import org.xbrl._2003.instance.Context;
import org.xbrl._2003.instance.ContextEntityType;
import org.xbrl._2003.instance.ContextEntityType.Identifier;
import org.xbrl._2003.instance.ContextPeriodType;
import org.xbrl._2003.instance.ObjectFactory;
import org.xbrl._2003.instance.Unit;
import org.xbrl._2003.instance.Xbrl;
import org.xbrl._2003.xlink.SimpleType;

public class XbrlUpdateHelper {

	private static final String TEST_FISCAL_NUMBER = "001000045B93";

	public static String getTestFiscalNumber() {
		return TEST_FISCAL_NUMBER;
	}
	
	public static String createXbrlInstance(VatDeclarationData vatDeclarationData) {
		ObjectFactory xbrlObjectFactory = null;
		JAXBContext jc = null;
		Marshaller m = null;
		try {
			xbrlObjectFactory = new ObjectFactory();
			jc = JAXBContext.newInstance(new Class[] { org.xbrl._2003.instance.ObjectFactory.class,
					nl.nltaxonomie._8_0_a_2.domein.bd.tuples.bd_ob_tuples.ObjectFactory.class,
					nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.ObjectFactory.class, org.xbrl._2006.xbrldi.ObjectFactory.class,
					org.xbrl._2003.xlink.ObjectFactory.class, nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.ObjectFactory.class,
					org.xbrl._2003.linkbase.ObjectFactory.class, org.xbrl._2005.xbrldt.ObjectFactory.class,
					nl.nltaxonomie._8_0_a_2.domein.bd.axes.bd_axes.ObjectFactory.class,
					nl.nltaxonomie._8_0_a_2.basis.bd.domains.bd_domains.ObjectFactory.class, nl.nltaxonomie.iso.iso4217.ObjectFactory.class,
					nl.nltaxonomie._8_0_a_2.basis.bd.items.bd_algemeen.ObjectFactory.class });
			m = jc.createMarshaller();
			StringWriter writer = new StringWriter();
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			Xbrl xbrl = xbrlObjectFactory.createXbrl();

			org.xbrl._2003.xlink.ObjectFactory xlinkObjectFactory = new org.xbrl._2003.xlink.ObjectFactory();
			SimpleType simpleType = xlinkObjectFactory.createSimpleType();
			simpleType.setType("simple");
			simpleType.setHref("http://www.nltaxonomie.nl/8.0.a.2/report/bd/entrypoints/bd-rpt-ob-aangifte-2014.xsd");
			xbrl.getSchemaRef().add(simpleType);
			xbrl.getOtherAttributes().put(new QName("xml:lang"), "nl");

			Context context = xbrlObjectFactory.createContext();
			context.setId("Msg");

			ContextEntityType contextEntityType = xbrlObjectFactory.createContextEntityType();
			Identifier identifier = xbrlObjectFactory.createContextEntityTypeIdentifier();
			identifier.setScheme("www.belastingdienst.nl/omzetbelastingnummer");
			identifier.setValue(vatDeclarationData.getFiscalNumber());
			ContextPeriodType period = xbrlObjectFactory.createContextPeriodType();
			period.setStartDate(DateHelper.getDate(vatDeclarationData.getStartDate()));
			period.setEndDate(DateHelper.getDate(vatDeclarationData.getEndDate()));
			contextEntityType.setIdentifier(identifier);
			context.setEntity(contextEntityType);
			context.setPeriod(period);
			xbrl.getItemOrTupleOrContext().add(context);
			
			Unit unit = xbrlObjectFactory.createUnit();
			unit.setId("u0");
			QName qName = new QName("iso4217:EUR");
			unit.getMeasure().add(qName);
			xbrl.getItemOrTupleOrContext().add(unit);

			nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.ObjectFactory bdTypeObjectFactory = new nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.ObjectFactory();
			DateTimeItemType dateTime = bdTypeObjectFactory.createDateTimeItemType();
			dateTime.setValue(DateHelper.getTimeStamp(new Date()));
			dateTime.setContextRef(context);
			JAXBElement<DateTimeItemType> dateTimeWrapper = new JAXBElement<DateTimeItemType>(new QName("http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-algemeen", "DateTimeCreation"), DateTimeItemType.class, dateTime);
			xbrl.getItemOrTupleOrContext().add(dateTimeWrapper);

			MessageReferenceSupplierVATItemType supplier = new MessageReferenceSupplierVATItemType();
			supplier.setValue("OB-TXTAX-13");
			supplier.setContextRef(context);
			JAXBElement<MessageReferenceSupplierVATItemType> supplierWrapper = new JAXBElement<MessageReferenceSupplierVATItemType>(new QName("http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-algemeen", "MessageReferenceSupplierVAT"), MessageReferenceSupplierVATItemType.class, supplier);
			xbrl.getItemOrTupleOrContext().add(supplierWrapper);
			
			ACAPITALstring4FItemType code = bdTypeObjectFactory.createACAPITALstring4FItemType();
			code.setValue("TXTX");
			code.setContextRef(context);
			JAXBElement<ACAPITALstring4FItemType> codeWrapper = new JAXBElement<ACAPITALstring4FItemType>(new QName("http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-algemeen", "SoftwareSupplierCode"), ACAPITALstring4FItemType.class, code);
			xbrl.getItemOrTupleOrContext().add(codeWrapper);
		
			Anstring2FItemType version = bdTypeObjectFactory.createAnstring2FItemType();
			version.setValue("13");
			version.setContextRef(context);
			JAXBElement<Anstring2FItemType> versionWrapper = new JAXBElement<Anstring2FItemType>(new QName("http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-algemeen", "VersionNumberApplication"), Anstring2FItemType.class, version);
			xbrl.getItemOrTupleOrContext().add(versionWrapper);		

	
			MonetaryNoDecimals10VItemType valueAddedTaxOnInput = bdTypeObjectFactory.createMonetaryNoDecimals10VItemType();
			valueAddedTaxOnInput.setDecimals("INF");
			valueAddedTaxOnInput.setContextRef(context);
			valueAddedTaxOnInput.setUnitRef(unit);
			valueAddedTaxOnInput.setValue(vatDeclarationData.getValueAddedTaxOnInput());
			JAXBElement<MonetaryNoDecimals10VItemType> valueAddedTaxOnInputWrapper = new JAXBElement<MonetaryNoDecimals10VItemType>(new QName("http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-omzetbelasting", "ValueAddedTaxOnInput"), MonetaryNoDecimals10VItemType.class, valueAddedTaxOnInput);
			xbrl.getItemOrTupleOrContext().add(valueAddedTaxOnInputWrapper);
			
			MonetaryNoDecimals10VItemType valueAddedTaxOwed = bdTypeObjectFactory.createMonetaryNoDecimals10VItemType();
			valueAddedTaxOwed.setDecimals("INF");
			valueAddedTaxOwed.setContextRef(context);
			valueAddedTaxOwed.setUnitRef(unit);
			valueAddedTaxOwed.setValue(vatDeclarationData.getValueAddedTaxOwed());
			JAXBElement<MonetaryNoDecimals10VItemType> valueAddedTaxOwedWrapper = new JAXBElement<MonetaryNoDecimals10VItemType>(new QName("http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-omzetbelasting", "ValueAddedTaxOwed"), MonetaryNoDecimals10VItemType.class, valueAddedTaxOwed);
			xbrl.getItemOrTupleOrContext().add(valueAddedTaxOwedWrapper);			

			MonetaryNoDecimals9VItemType valueAddedTaxOwedToBePaidBack = bdTypeObjectFactory.createMonetaryNoDecimals9VItemType();
			valueAddedTaxOwedToBePaidBack.setDecimals("INF");
			valueAddedTaxOwedToBePaidBack.setContextRef(context);
			valueAddedTaxOwedToBePaidBack.setUnitRef(unit);
			valueAddedTaxOwedToBePaidBack.setValue(vatDeclarationData.getValueAddedTaxOwedToBePaidBack());
			JAXBElement<MonetaryNoDecimals9VItemType> valueAddedTaxOwedToBePaidBackWrapper = new JAXBElement<MonetaryNoDecimals9VItemType>(new QName("http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-omzetbelasting", "ValueAddedTaxOwedToBePaidBack"), MonetaryNoDecimals9VItemType.class, valueAddedTaxOwedToBePaidBack);
			xbrl.getItemOrTupleOrContext().add(valueAddedTaxOwedToBePaidBackWrapper);

			MonetaryNoDecimals9VItemType valueAddedTaxPrivateUse = bdTypeObjectFactory.createMonetaryNoDecimals9VItemType();
			valueAddedTaxPrivateUse.setDecimals("INF");
			valueAddedTaxPrivateUse.setContextRef(context);
			valueAddedTaxPrivateUse.setUnitRef(unit);
			valueAddedTaxPrivateUse.setValue(vatDeclarationData.getValueAddedTaxPrivateUse());
			JAXBElement<MonetaryNoDecimals9VItemType> valueAddedTaxPrivateUseWrapper = new JAXBElement<MonetaryNoDecimals9VItemType>(new QName("http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-omzetbelasting", "ValueAddedTaxPrivateUse"), MonetaryNoDecimals9VItemType.class, valueAddedTaxPrivateUse);
			xbrl.getItemOrTupleOrContext().add(valueAddedTaxPrivateUseWrapper);

			MonetaryNoDecimals9VItemType valueAddedTaxSuppliesServicesGeneralTariff = bdTypeObjectFactory.createMonetaryNoDecimals9VItemType();
			valueAddedTaxSuppliesServicesGeneralTariff.setDecimals("INF");
			valueAddedTaxSuppliesServicesGeneralTariff.setContextRef(context);
			valueAddedTaxSuppliesServicesGeneralTariff.setUnitRef(unit);
			valueAddedTaxSuppliesServicesGeneralTariff.setValue(vatDeclarationData.getValueAddedTaxSuppliesServicesGeneralTariff());
			JAXBElement<MonetaryNoDecimals9VItemType> valueAddedTaxSuppliesServicesGeneralTariffWrapper = new JAXBElement<MonetaryNoDecimals9VItemType>(new QName("http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-omzetbelasting", "ValueAddedTaxSuppliesServicesGeneralTariff"), MonetaryNoDecimals9VItemType.class, valueAddedTaxSuppliesServicesGeneralTariff);
			xbrl.getItemOrTupleOrContext().add(valueAddedTaxSuppliesServicesGeneralTariffWrapper);

			nl.nltaxonomie._8_0_a_2.domein.bd.tuples.bd_ob_tuples.ObjectFactory bdObObjectFactory = new nl.nltaxonomie._8_0_a_2.domein.bd.tuples.bd_ob_tuples.ObjectFactory();
			CorrespondentDeclarantSpecification declarant = bdObObjectFactory.createCorrespondentDeclarantSpecification();
			Anstring35VItemType name = bdTypeObjectFactory.createAnstring35VItemType();
			name.setValue(vatDeclarationData.getName());
			name.setContextRef(context);
			declarant.setCorrespondentDeclarantContactName(name);
			Anstring25VItemType phoneNumber = bdTypeObjectFactory.createAnstring25VItemType();
			phoneNumber.setValue(vatDeclarationData.getPhoneNumber());
			phoneNumber.setContextRef(context);
			declarant.setCorrespondentDeclarantContactTelephoneNumber(phoneNumber);
			JAXBElement<CorrespondentDeclarantSpecification> declarantWrapper = new JAXBElement<CorrespondentDeclarantSpecification>(new QName("http://www.nltaxonomie.nl/8.0.a.2/domein/bd/tuples/bd-ob-tuples", "CorrespondentDeclarantSpecification"), CorrespondentDeclarantSpecification.class, declarant);
			xbrl.getItemOrTupleOrContext().add(declarantWrapper);

			m.marshal(xbrl, writer);

			System.out.println(writer.toString());
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void addBalanceData(VatDeclarationData vatDeclarationData, Balans balans) {
		BigInteger totaleKosten = AmountHelper.roundToInteger(balans.getTotaleKosten());
		BigInteger correction = AmountHelper.roundToInteger(balans.getCorrection());
		BigInteger totaleBaten = AmountHelper.roundToInteger(balans.getTotaleBaten());
		BigInteger owed = totaleBaten.subtract(totaleKosten).subtract(correction);
		vatDeclarationData.setValueAddedTaxOwed(owed);
		vatDeclarationData.setValueAddedTaxOnInput(totaleKosten.add(correction));
		vatDeclarationData.setValueAddedTaxOwedToBePaidBack(owed);
		vatDeclarationData.setValueAddedTaxPrivateUse(correction);
		vatDeclarationData.setValueAddedTaxSuppliesServicesGeneralTariff(totaleBaten);
	}	
	
	public static void main(String[] args) {
//		createTestXbrlInstance();
		VatDeclarationData vatDeclarationData = new VatDeclarationData();
		vatDeclarationData.setValueAddedTaxOnInput(new BigInteger("600"));
		vatDeclarationData.setValueAddedTaxOwed(BigInteger.valueOf(50));
		vatDeclarationData.setStartDate(new Date());
		vatDeclarationData.setEndDate(new Date());
		vatDeclarationData.setName("Test");
		vatDeclarationData.setPhoneNumber("1234");
		createXbrlInstance(vatDeclarationData);
	}

	public static String createTestXbrlInstance() {
		VatDeclarationData vatDeclarationData = new VatDeclarationData();
		Periode period = DateHelper.getLatestVatPeriod();
		vatDeclarationData.setStartDate(period.getBeginDatum());
		vatDeclarationData.setEndDate(period.getEindDatum());
		vatDeclarationData.setFiscalNumber(TEST_FISCAL_NUMBER);
		vatDeclarationData.setName("Test");
		vatDeclarationData.setPhoneNumber("12345678");
		Balans balans = new Balans();
		balans.setTotaleKosten(BigDecimal.valueOf(95));
		balans.setCorrection(BigDecimal.valueOf(5));
		balans.setTotaleBaten(BigDecimal.valueOf(40));
		addBalanceData(vatDeclarationData, balans);
		return createXbrlInstance(vatDeclarationData);
	}
}
