//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.10 at 11:10:53 AM CEST 
//


package nl.nltaxonomie.nt13.bd._20181212_b.dictionary.bd_tuples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.Placeholder;
import nl.nltaxonomie.nt13.bd._20181212_b.dictionary.bd_types.Anstring10VItemType;
import nl.nltaxonomie.nt13.bd._20181212_b.dictionary.bd_types.Anstring200VItemType;
import nl.nltaxonomie.nt13.bd._20181212_b.dictionary.bd_types.Anstring70VItemType;
import nl.nltaxonomie.nt13.bd._20181212_b.dictionary.bd_types.GYearItemType;
import nl.nltaxonomie.nt13.bd._20181212_b.dictionary.bd_types.Perc32ItemType;
import nl.nltaxonomie.nt13.bd._20181212_b.dictionary.bd_types.Test11ItemType;
import nl.nltaxonomie.nt13.sbr._20180301.dictionary.nl_types.MonetaryNoDecimalsItemType;
import nl.nltaxonomie.nt13.sbr._20180301.dictionary.nl_types.NonNegativeMonetaryNoDecimalsItemType;
import org.xbrl._2003.instance.BooleanItemType;
import org.xbrl._2003.instance.DateItemType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.nltaxonomie.nl/2011/xbrl/xbrl-syntax-extension}placeholder">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-tuples}AddressAbroadPresentation" minOccurs="0"/>
 *           &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-tuples}AddressPresentation" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveCarMadeAvailable" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveCarMadeAvailableCarBrand" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveCarMadeAvailableCarCataloguePrice" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveCarMadeAvailableCarConstructionYear" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveCarMadeAvailableCarExpensesAllowance" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveCarMadeAvailableCarExpensesAllowanceProvided" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveCarMadeAvailableCarType" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveDateOfBirth" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveDebtEndFinancialYear" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveDebtInterest" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveDebtInterestPercentage" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveDebtToTaxpayerIndication" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveExpensesAllowancesOther" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveExpensesAllowancesOtherProvided" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveIdentificationNumber" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveInitials" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutivePrefix" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveReceivableEndFinancialYear" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveReceivableInterest" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveReceivableInterestPercentage" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveReceivableToTaxpayerIndication" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveRoyaltiesFinancialYearSettled" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveRoyaltiesPaidThisFinancialYear" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveRoyaltiesPaymentDate" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveSalaryRoyaltiesIncluded" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data}ExecutiveSurname" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "addressAbroadPresentation",
    "addressPresentation",
    "executiveCarMadeAvailable",
    "executiveCarMadeAvailableCarBrand",
    "executiveCarMadeAvailableCarCataloguePrice",
    "executiveCarMadeAvailableCarConstructionYear",
    "executiveCarMadeAvailableCarExpensesAllowance",
    "executiveCarMadeAvailableCarExpensesAllowanceProvided",
    "executiveCarMadeAvailableCarType",
    "executiveDateOfBirth",
    "executiveDebtEndFinancialYear",
    "executiveDebtInterest",
    "executiveDebtInterestPercentage",
    "executiveDebtToTaxpayerIndication",
    "executiveExpensesAllowancesOther",
    "executiveExpensesAllowancesOtherProvided",
    "executiveIdentificationNumber",
    "executiveInitials",
    "executivePrefix",
    "executiveReceivableEndFinancialYear",
    "executiveReceivableInterest",
    "executiveReceivableInterestPercentage",
    "executiveReceivableToTaxpayerIndication",
    "executiveRoyaltiesFinancialYearSettled",
    "executiveRoyaltiesPaidThisFinancialYear",
    "executiveRoyaltiesPaymentDate",
    "executiveSalaryRoyaltiesIncluded",
    "executiveSurname"
})
public class ExecutiveSpecification
    extends Placeholder
{

    @XmlElement(name = "AddressAbroadPresentation", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-tuples")
    protected AddressAbroadPresentation addressAbroadPresentation;
    @XmlElement(name = "AddressPresentation", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-tuples")
    protected AddressPresentation addressPresentation;
    @XmlElement(name = "ExecutiveCarMadeAvailable", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected BooleanItemType executiveCarMadeAvailable;
    @XmlElement(name = "ExecutiveCarMadeAvailableCarBrand", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected Anstring70VItemType executiveCarMadeAvailableCarBrand;
    @XmlElement(name = "ExecutiveCarMadeAvailableCarCataloguePrice", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType executiveCarMadeAvailableCarCataloguePrice;
    @XmlElement(name = "ExecutiveCarMadeAvailableCarConstructionYear", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected GYearItemType executiveCarMadeAvailableCarConstructionYear;
    @XmlElement(name = "ExecutiveCarMadeAvailableCarExpensesAllowance", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType executiveCarMadeAvailableCarExpensesAllowance;
    @XmlElement(name = "ExecutiveCarMadeAvailableCarExpensesAllowanceProvided", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected BooleanItemType executiveCarMadeAvailableCarExpensesAllowanceProvided;
    @XmlElement(name = "ExecutiveCarMadeAvailableCarType", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected Anstring70VItemType executiveCarMadeAvailableCarType;
    @XmlElement(name = "ExecutiveDateOfBirth", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected DateItemType executiveDateOfBirth;
    @XmlElement(name = "ExecutiveDebtEndFinancialYear", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType executiveDebtEndFinancialYear;
    @XmlElement(name = "ExecutiveDebtInterest", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected MonetaryNoDecimalsItemType executiveDebtInterest;
    @XmlElement(name = "ExecutiveDebtInterestPercentage", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected Perc32ItemType executiveDebtInterestPercentage;
    @XmlElement(name = "ExecutiveDebtToTaxpayerIndication", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected BooleanItemType executiveDebtToTaxpayerIndication;
    @XmlElement(name = "ExecutiveExpensesAllowancesOther", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType executiveExpensesAllowancesOther;
    @XmlElement(name = "ExecutiveExpensesAllowancesOtherProvided", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected BooleanItemType executiveExpensesAllowancesOtherProvided;
    @XmlElement(name = "ExecutiveIdentificationNumber", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected Test11ItemType executiveIdentificationNumber;
    @XmlElement(name = "ExecutiveInitials", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected Anstring10VItemType executiveInitials;
    @XmlElement(name = "ExecutivePrefix", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected Anstring10VItemType executivePrefix;
    @XmlElement(name = "ExecutiveReceivableEndFinancialYear", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType executiveReceivableEndFinancialYear;
    @XmlElement(name = "ExecutiveReceivableInterest", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected MonetaryNoDecimalsItemType executiveReceivableInterest;
    @XmlElement(name = "ExecutiveReceivableInterestPercentage", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected Perc32ItemType executiveReceivableInterestPercentage;
    @XmlElement(name = "ExecutiveReceivableToTaxpayerIndication", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected BooleanItemType executiveReceivableToTaxpayerIndication;
    @XmlElement(name = "ExecutiveRoyaltiesFinancialYearSettled", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType executiveRoyaltiesFinancialYearSettled;
    @XmlElement(name = "ExecutiveRoyaltiesPaidThisFinancialYear", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType executiveRoyaltiesPaidThisFinancialYear;
    @XmlElement(name = "ExecutiveRoyaltiesPaymentDate", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected DateItemType executiveRoyaltiesPaymentDate;
    @XmlElement(name = "ExecutiveSalaryRoyaltiesIncluded", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected MonetaryNoDecimalsItemType executiveSalaryRoyaltiesIncluded;
    @XmlElement(name = "ExecutiveSurname", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.b/dictionary/bd-data")
    protected Anstring200VItemType executiveSurname;

    /**
     * Gets the value of the addressAbroadPresentation property.
     * 
     * @return
     *     possible object is
     *     {@link AddressAbroadPresentation }
     *     
     */
    public AddressAbroadPresentation getAddressAbroadPresentation() {
        return addressAbroadPresentation;
    }

    /**
     * Sets the value of the addressAbroadPresentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressAbroadPresentation }
     *     
     */
    public void setAddressAbroadPresentation(AddressAbroadPresentation value) {
        this.addressAbroadPresentation = value;
    }

    /**
     * Gets the value of the addressPresentation property.
     * 
     * @return
     *     possible object is
     *     {@link AddressPresentation }
     *     
     */
    public AddressPresentation getAddressPresentation() {
        return addressPresentation;
    }

    /**
     * Sets the value of the addressPresentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressPresentation }
     *     
     */
    public void setAddressPresentation(AddressPresentation value) {
        this.addressPresentation = value;
    }

    /**
     * Gets the value of the executiveCarMadeAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanItemType }
     *     
     */
    public BooleanItemType getExecutiveCarMadeAvailable() {
        return executiveCarMadeAvailable;
    }

    /**
     * Sets the value of the executiveCarMadeAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanItemType }
     *     
     */
    public void setExecutiveCarMadeAvailable(BooleanItemType value) {
        this.executiveCarMadeAvailable = value;
    }

    /**
     * Gets the value of the executiveCarMadeAvailableCarBrand property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring70VItemType }
     *     
     */
    public Anstring70VItemType getExecutiveCarMadeAvailableCarBrand() {
        return executiveCarMadeAvailableCarBrand;
    }

    /**
     * Sets the value of the executiveCarMadeAvailableCarBrand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring70VItemType }
     *     
     */
    public void setExecutiveCarMadeAvailableCarBrand(Anstring70VItemType value) {
        this.executiveCarMadeAvailableCarBrand = value;
    }

    /**
     * Gets the value of the executiveCarMadeAvailableCarCataloguePrice property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getExecutiveCarMadeAvailableCarCataloguePrice() {
        return executiveCarMadeAvailableCarCataloguePrice;
    }

    /**
     * Sets the value of the executiveCarMadeAvailableCarCataloguePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveCarMadeAvailableCarCataloguePrice(NonNegativeMonetaryNoDecimalsItemType value) {
        this.executiveCarMadeAvailableCarCataloguePrice = value;
    }

    /**
     * Gets the value of the executiveCarMadeAvailableCarConstructionYear property.
     * 
     * @return
     *     possible object is
     *     {@link GYearItemType }
     *     
     */
    public GYearItemType getExecutiveCarMadeAvailableCarConstructionYear() {
        return executiveCarMadeAvailableCarConstructionYear;
    }

    /**
     * Sets the value of the executiveCarMadeAvailableCarConstructionYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link GYearItemType }
     *     
     */
    public void setExecutiveCarMadeAvailableCarConstructionYear(GYearItemType value) {
        this.executiveCarMadeAvailableCarConstructionYear = value;
    }

    /**
     * Gets the value of the executiveCarMadeAvailableCarExpensesAllowance property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getExecutiveCarMadeAvailableCarExpensesAllowance() {
        return executiveCarMadeAvailableCarExpensesAllowance;
    }

    /**
     * Sets the value of the executiveCarMadeAvailableCarExpensesAllowance property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveCarMadeAvailableCarExpensesAllowance(NonNegativeMonetaryNoDecimalsItemType value) {
        this.executiveCarMadeAvailableCarExpensesAllowance = value;
    }

    /**
     * Gets the value of the executiveCarMadeAvailableCarExpensesAllowanceProvided property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanItemType }
     *     
     */
    public BooleanItemType getExecutiveCarMadeAvailableCarExpensesAllowanceProvided() {
        return executiveCarMadeAvailableCarExpensesAllowanceProvided;
    }

    /**
     * Sets the value of the executiveCarMadeAvailableCarExpensesAllowanceProvided property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanItemType }
     *     
     */
    public void setExecutiveCarMadeAvailableCarExpensesAllowanceProvided(BooleanItemType value) {
        this.executiveCarMadeAvailableCarExpensesAllowanceProvided = value;
    }

    /**
     * Gets the value of the executiveCarMadeAvailableCarType property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring70VItemType }
     *     
     */
    public Anstring70VItemType getExecutiveCarMadeAvailableCarType() {
        return executiveCarMadeAvailableCarType;
    }

    /**
     * Sets the value of the executiveCarMadeAvailableCarType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring70VItemType }
     *     
     */
    public void setExecutiveCarMadeAvailableCarType(Anstring70VItemType value) {
        this.executiveCarMadeAvailableCarType = value;
    }

    /**
     * Gets the value of the executiveDateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link DateItemType }
     *     
     */
    public DateItemType getExecutiveDateOfBirth() {
        return executiveDateOfBirth;
    }

    /**
     * Sets the value of the executiveDateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateItemType }
     *     
     */
    public void setExecutiveDateOfBirth(DateItemType value) {
        this.executiveDateOfBirth = value;
    }

    /**
     * Gets the value of the executiveDebtEndFinancialYear property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getExecutiveDebtEndFinancialYear() {
        return executiveDebtEndFinancialYear;
    }

    /**
     * Sets the value of the executiveDebtEndFinancialYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveDebtEndFinancialYear(NonNegativeMonetaryNoDecimalsItemType value) {
        this.executiveDebtEndFinancialYear = value;
    }

    /**
     * Gets the value of the executiveDebtInterest property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getExecutiveDebtInterest() {
        return executiveDebtInterest;
    }

    /**
     * Sets the value of the executiveDebtInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveDebtInterest(MonetaryNoDecimalsItemType value) {
        this.executiveDebtInterest = value;
    }

    /**
     * Gets the value of the executiveDebtInterestPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Perc32ItemType }
     *     
     */
    public Perc32ItemType getExecutiveDebtInterestPercentage() {
        return executiveDebtInterestPercentage;
    }

    /**
     * Sets the value of the executiveDebtInterestPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Perc32ItemType }
     *     
     */
    public void setExecutiveDebtInterestPercentage(Perc32ItemType value) {
        this.executiveDebtInterestPercentage = value;
    }

    /**
     * Gets the value of the executiveDebtToTaxpayerIndication property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanItemType }
     *     
     */
    public BooleanItemType getExecutiveDebtToTaxpayerIndication() {
        return executiveDebtToTaxpayerIndication;
    }

    /**
     * Sets the value of the executiveDebtToTaxpayerIndication property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanItemType }
     *     
     */
    public void setExecutiveDebtToTaxpayerIndication(BooleanItemType value) {
        this.executiveDebtToTaxpayerIndication = value;
    }

    /**
     * Gets the value of the executiveExpensesAllowancesOther property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getExecutiveExpensesAllowancesOther() {
        return executiveExpensesAllowancesOther;
    }

    /**
     * Sets the value of the executiveExpensesAllowancesOther property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveExpensesAllowancesOther(NonNegativeMonetaryNoDecimalsItemType value) {
        this.executiveExpensesAllowancesOther = value;
    }

    /**
     * Gets the value of the executiveExpensesAllowancesOtherProvided property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanItemType }
     *     
     */
    public BooleanItemType getExecutiveExpensesAllowancesOtherProvided() {
        return executiveExpensesAllowancesOtherProvided;
    }

    /**
     * Sets the value of the executiveExpensesAllowancesOtherProvided property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanItemType }
     *     
     */
    public void setExecutiveExpensesAllowancesOtherProvided(BooleanItemType value) {
        this.executiveExpensesAllowancesOtherProvided = value;
    }

    /**
     * Gets the value of the executiveIdentificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Test11ItemType }
     *     
     */
    public Test11ItemType getExecutiveIdentificationNumber() {
        return executiveIdentificationNumber;
    }

    /**
     * Sets the value of the executiveIdentificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Test11ItemType }
     *     
     */
    public void setExecutiveIdentificationNumber(Test11ItemType value) {
        this.executiveIdentificationNumber = value;
    }

    /**
     * Gets the value of the executiveInitials property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getExecutiveInitials() {
        return executiveInitials;
    }

    /**
     * Sets the value of the executiveInitials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setExecutiveInitials(Anstring10VItemType value) {
        this.executiveInitials = value;
    }

    /**
     * Gets the value of the executivePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getExecutivePrefix() {
        return executivePrefix;
    }

    /**
     * Sets the value of the executivePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setExecutivePrefix(Anstring10VItemType value) {
        this.executivePrefix = value;
    }

    /**
     * Gets the value of the executiveReceivableEndFinancialYear property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getExecutiveReceivableEndFinancialYear() {
        return executiveReceivableEndFinancialYear;
    }

    /**
     * Sets the value of the executiveReceivableEndFinancialYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveReceivableEndFinancialYear(NonNegativeMonetaryNoDecimalsItemType value) {
        this.executiveReceivableEndFinancialYear = value;
    }

    /**
     * Gets the value of the executiveReceivableInterest property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getExecutiveReceivableInterest() {
        return executiveReceivableInterest;
    }

    /**
     * Sets the value of the executiveReceivableInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveReceivableInterest(MonetaryNoDecimalsItemType value) {
        this.executiveReceivableInterest = value;
    }

    /**
     * Gets the value of the executiveReceivableInterestPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Perc32ItemType }
     *     
     */
    public Perc32ItemType getExecutiveReceivableInterestPercentage() {
        return executiveReceivableInterestPercentage;
    }

    /**
     * Sets the value of the executiveReceivableInterestPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Perc32ItemType }
     *     
     */
    public void setExecutiveReceivableInterestPercentage(Perc32ItemType value) {
        this.executiveReceivableInterestPercentage = value;
    }

    /**
     * Gets the value of the executiveReceivableToTaxpayerIndication property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanItemType }
     *     
     */
    public BooleanItemType getExecutiveReceivableToTaxpayerIndication() {
        return executiveReceivableToTaxpayerIndication;
    }

    /**
     * Sets the value of the executiveReceivableToTaxpayerIndication property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanItemType }
     *     
     */
    public void setExecutiveReceivableToTaxpayerIndication(BooleanItemType value) {
        this.executiveReceivableToTaxpayerIndication = value;
    }

    /**
     * Gets the value of the executiveRoyaltiesFinancialYearSettled property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getExecutiveRoyaltiesFinancialYearSettled() {
        return executiveRoyaltiesFinancialYearSettled;
    }

    /**
     * Sets the value of the executiveRoyaltiesFinancialYearSettled property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveRoyaltiesFinancialYearSettled(NonNegativeMonetaryNoDecimalsItemType value) {
        this.executiveRoyaltiesFinancialYearSettled = value;
    }

    /**
     * Gets the value of the executiveRoyaltiesPaidThisFinancialYear property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getExecutiveRoyaltiesPaidThisFinancialYear() {
        return executiveRoyaltiesPaidThisFinancialYear;
    }

    /**
     * Sets the value of the executiveRoyaltiesPaidThisFinancialYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveRoyaltiesPaidThisFinancialYear(NonNegativeMonetaryNoDecimalsItemType value) {
        this.executiveRoyaltiesPaidThisFinancialYear = value;
    }

    /**
     * Gets the value of the executiveRoyaltiesPaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link DateItemType }
     *     
     */
    public DateItemType getExecutiveRoyaltiesPaymentDate() {
        return executiveRoyaltiesPaymentDate;
    }

    /**
     * Sets the value of the executiveRoyaltiesPaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateItemType }
     *     
     */
    public void setExecutiveRoyaltiesPaymentDate(DateItemType value) {
        this.executiveRoyaltiesPaymentDate = value;
    }

    /**
     * Gets the value of the executiveSalaryRoyaltiesIncluded property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getExecutiveSalaryRoyaltiesIncluded() {
        return executiveSalaryRoyaltiesIncluded;
    }

    /**
     * Sets the value of the executiveSalaryRoyaltiesIncluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setExecutiveSalaryRoyaltiesIncluded(MonetaryNoDecimalsItemType value) {
        this.executiveSalaryRoyaltiesIncluded = value;
    }

    /**
     * Gets the value of the executiveSurname property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring200VItemType }
     *     
     */
    public Anstring200VItemType getExecutiveSurname() {
        return executiveSurname;
    }

    /**
     * Sets the value of the executiveSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring200VItemType }
     *     
     */
    public void setExecutiveSurname(Anstring200VItemType value) {
        this.executiveSurname = value;
    }

}
