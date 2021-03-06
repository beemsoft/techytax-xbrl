//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.10 at 11:10:53 AM CEST 
//


package nl.nltaxonomie.nt13.bd._20181212_a.dictionary.bd_tuples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.Placeholder;
import nl.nltaxonomie.nt13.bd._20181212_a.dictionary.bd_types.Anstring70VItemType;
import nl.nltaxonomie.nt13.bd._20181212_a.dictionary.bd_types.NotificationNumberItemType;
import nl.nltaxonomie.nt13.bd._20181212_a.dictionary.bd_types.Perc32ItemType;
import nl.nltaxonomie.nt13.sbr._20180301.dictionary.nl_types.MonetaryNoDecimalsItemType;
import nl.nltaxonomie.nt13.sbr._20180301.dictionary.nl_types.NonNegativeMonetaryNoDecimalsItemType;
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
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data}BusinessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data}BusinessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data}BusinessAssetEnvironmentalEnergyInvestmentDescription" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data}BusinessAssetEnvironmentalEnergyInvestmentFinancialYearAmount" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data}BusinessAssetEnvironmentalEnergyInvestmentInitialStartingDate" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data}BusinessAssetEnvironmentalEnergyInvestmentNotificationNumber" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data}BusinessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data}BusinessAssetEnvironmentalEnergyInvestmentPercentage" minOccurs="0"/>
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
    "businessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount",
    "businessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear",
    "businessAssetEnvironmentalEnergyInvestmentDescription",
    "businessAssetEnvironmentalEnergyInvestmentFinancialYearAmount",
    "businessAssetEnvironmentalEnergyInvestmentInitialStartingDate",
    "businessAssetEnvironmentalEnergyInvestmentNotificationNumber",
    "businessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear",
    "businessAssetEnvironmentalEnergyInvestmentPercentage"
})
public class BusinessAssetEnvironmentalEnergyInvestmentSpecification
    extends Placeholder
{

    @XmlElement(name = "BusinessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data")
    protected MonetaryNoDecimalsItemType businessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount;
    @XmlElement(name = "BusinessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType businessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear;
    @XmlElement(name = "BusinessAssetEnvironmentalEnergyInvestmentDescription", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data")
    protected Anstring70VItemType businessAssetEnvironmentalEnergyInvestmentDescription;
    @XmlElement(name = "BusinessAssetEnvironmentalEnergyInvestmentFinancialYearAmount", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data")
    protected MonetaryNoDecimalsItemType businessAssetEnvironmentalEnergyInvestmentFinancialYearAmount;
    @XmlElement(name = "BusinessAssetEnvironmentalEnergyInvestmentInitialStartingDate", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data")
    protected DateItemType businessAssetEnvironmentalEnergyInvestmentInitialStartingDate;
    @XmlElement(name = "BusinessAssetEnvironmentalEnergyInvestmentNotificationNumber", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data")
    protected NotificationNumberItemType businessAssetEnvironmentalEnergyInvestmentNotificationNumber;
    @XmlElement(name = "BusinessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data")
    protected MonetaryNoDecimalsItemType businessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear;
    @XmlElement(name = "BusinessAssetEnvironmentalEnergyInvestmentPercentage", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212.a/dictionary/bd-data")
    protected Perc32ItemType businessAssetEnvironmentalEnergyInvestmentPercentage;

    /**
     * Gets the value of the businessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getBusinessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount() {
        return businessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount;
    }

    /**
     * Sets the value of the businessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setBusinessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount(MonetaryNoDecimalsItemType value) {
        this.businessAssetEnvironmentalEnergyInvestmentAllowancePerInvestmentAmount = value;
    }

    /**
     * Gets the value of the businessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getBusinessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear() {
        return businessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear;
    }

    /**
     * Sets the value of the businessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setBusinessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear(NonNegativeMonetaryNoDecimalsItemType value) {
        this.businessAssetEnvironmentalEnergyInvestmentAllowanceThisFinancialYear = value;
    }

    /**
     * Gets the value of the businessAssetEnvironmentalEnergyInvestmentDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring70VItemType }
     *     
     */
    public Anstring70VItemType getBusinessAssetEnvironmentalEnergyInvestmentDescription() {
        return businessAssetEnvironmentalEnergyInvestmentDescription;
    }

    /**
     * Sets the value of the businessAssetEnvironmentalEnergyInvestmentDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring70VItemType }
     *     
     */
    public void setBusinessAssetEnvironmentalEnergyInvestmentDescription(Anstring70VItemType value) {
        this.businessAssetEnvironmentalEnergyInvestmentDescription = value;
    }

    /**
     * Gets the value of the businessAssetEnvironmentalEnergyInvestmentFinancialYearAmount property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getBusinessAssetEnvironmentalEnergyInvestmentFinancialYearAmount() {
        return businessAssetEnvironmentalEnergyInvestmentFinancialYearAmount;
    }

    /**
     * Sets the value of the businessAssetEnvironmentalEnergyInvestmentFinancialYearAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setBusinessAssetEnvironmentalEnergyInvestmentFinancialYearAmount(MonetaryNoDecimalsItemType value) {
        this.businessAssetEnvironmentalEnergyInvestmentFinancialYearAmount = value;
    }

    /**
     * Gets the value of the businessAssetEnvironmentalEnergyInvestmentInitialStartingDate property.
     * 
     * @return
     *     possible object is
     *     {@link DateItemType }
     *     
     */
    public DateItemType getBusinessAssetEnvironmentalEnergyInvestmentInitialStartingDate() {
        return businessAssetEnvironmentalEnergyInvestmentInitialStartingDate;
    }

    /**
     * Sets the value of the businessAssetEnvironmentalEnergyInvestmentInitialStartingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateItemType }
     *     
     */
    public void setBusinessAssetEnvironmentalEnergyInvestmentInitialStartingDate(DateItemType value) {
        this.businessAssetEnvironmentalEnergyInvestmentInitialStartingDate = value;
    }

    /**
     * Gets the value of the businessAssetEnvironmentalEnergyInvestmentNotificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link NotificationNumberItemType }
     *     
     */
    public NotificationNumberItemType getBusinessAssetEnvironmentalEnergyInvestmentNotificationNumber() {
        return businessAssetEnvironmentalEnergyInvestmentNotificationNumber;
    }

    /**
     * Sets the value of the businessAssetEnvironmentalEnergyInvestmentNotificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificationNumberItemType }
     *     
     */
    public void setBusinessAssetEnvironmentalEnergyInvestmentNotificationNumber(NotificationNumberItemType value) {
        this.businessAssetEnvironmentalEnergyInvestmentNotificationNumber = value;
    }

    /**
     * Gets the value of the businessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getBusinessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear() {
        return businessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear;
    }

    /**
     * Sets the value of the businessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setBusinessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear(MonetaryNoDecimalsItemType value) {
        this.businessAssetEnvironmentalEnergyInvestmentPaidThisFinancialYear = value;
    }

    /**
     * Gets the value of the businessAssetEnvironmentalEnergyInvestmentPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Perc32ItemType }
     *     
     */
    public Perc32ItemType getBusinessAssetEnvironmentalEnergyInvestmentPercentage() {
        return businessAssetEnvironmentalEnergyInvestmentPercentage;
    }

    /**
     * Sets the value of the businessAssetEnvironmentalEnergyInvestmentPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Perc32ItemType }
     *     
     */
    public void setBusinessAssetEnvironmentalEnergyInvestmentPercentage(Perc32ItemType value) {
        this.businessAssetEnvironmentalEnergyInvestmentPercentage = value;
    }

}
