//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.25 at 08:13:42 PM CEST 
//


package nl.nltaxonomie._9_0.domein.bd.tuples.bd_burg_tuples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.Placeholder;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Anstring34VItemType;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Anstring70VItemType;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.NonNegativeInteger2FItemType;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Test11ItemType;
import nl.nltaxonomie._9_0.basis.sbr.types.nl_types.MonetaryNoDecimalsItemType;


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
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsAccountHolderIdentificationNumber" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsBalance" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsBalanceBenefit" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsBalanceBox3BankAccountSavings" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsBalanceOriginalCurrency" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsBalanceType" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsBankAccountNumber" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsBankName" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsBankProductLabel" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsCommonInterestIndication" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}AssetsOnBankAccountsRecordType" minOccurs="0"/>
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
    "assetsOnBankAccountsAccountHolderIdentificationNumber",
    "assetsOnBankAccountsBalance",
    "assetsOnBankAccountsBalanceBenefit",
    "assetsOnBankAccountsBalanceBox3BankAccountSavings",
    "assetsOnBankAccountsBalanceOriginalCurrency",
    "assetsOnBankAccountsBalanceType",
    "assetsOnBankAccountsBankAccountNumber",
    "assetsOnBankAccountsBankName",
    "assetsOnBankAccountsBankProductLabel",
    "assetsOnBankAccountsCommonInterestIndication",
    "assetsOnBankAccountsRecordType"
})
public class AssetsOnBankAccountsSpecification
    extends Placeholder
{

    @XmlElement(name = "AssetsOnBankAccountsAccountHolderIdentificationNumber", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Test11ItemType assetsOnBankAccountsAccountHolderIdentificationNumber;
    @XmlElement(name = "AssetsOnBankAccountsBalance", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected MonetaryNoDecimalsItemType assetsOnBankAccountsBalance;
    @XmlElement(name = "AssetsOnBankAccountsBalanceBenefit", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected MonetaryNoDecimalsItemType assetsOnBankAccountsBalanceBenefit;
    @XmlElement(name = "AssetsOnBankAccountsBalanceBox3BankAccountSavings", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected MonetaryNoDecimalsItemType assetsOnBankAccountsBalanceBox3BankAccountSavings;
    @XmlElement(name = "AssetsOnBankAccountsBalanceOriginalCurrency", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected MonetaryNoDecimalsItemType assetsOnBankAccountsBalanceOriginalCurrency;
    @XmlElement(name = "AssetsOnBankAccountsBalanceType", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected NonNegativeInteger2FItemType assetsOnBankAccountsBalanceType;
    @XmlElement(name = "AssetsOnBankAccountsBankAccountNumber", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring34VItemType assetsOnBankAccountsBankAccountNumber;
    @XmlElement(name = "AssetsOnBankAccountsBankName", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring70VItemType assetsOnBankAccountsBankName;
    @XmlElement(name = "AssetsOnBankAccountsBankProductLabel", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring70VItemType assetsOnBankAccountsBankProductLabel;
    @XmlElement(name = "AssetsOnBankAccountsCommonInterestIndication", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected NonNegativeInteger2FItemType assetsOnBankAccountsCommonInterestIndication;
    @XmlElement(name = "AssetsOnBankAccountsRecordType", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected NonNegativeInteger2FItemType assetsOnBankAccountsRecordType;

    /**
     * Gets the value of the assetsOnBankAccountsAccountHolderIdentificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Test11ItemType }
     *     
     */
    public Test11ItemType getAssetsOnBankAccountsAccountHolderIdentificationNumber() {
        return assetsOnBankAccountsAccountHolderIdentificationNumber;
    }

    /**
     * Sets the value of the assetsOnBankAccountsAccountHolderIdentificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Test11ItemType }
     *     
     */
    public void setAssetsOnBankAccountsAccountHolderIdentificationNumber(Test11ItemType value) {
        this.assetsOnBankAccountsAccountHolderIdentificationNumber = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsBalance property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getAssetsOnBankAccountsBalance() {
        return assetsOnBankAccountsBalance;
    }

    /**
     * Sets the value of the assetsOnBankAccountsBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setAssetsOnBankAccountsBalance(MonetaryNoDecimalsItemType value) {
        this.assetsOnBankAccountsBalance = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsBalanceBenefit property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getAssetsOnBankAccountsBalanceBenefit() {
        return assetsOnBankAccountsBalanceBenefit;
    }

    /**
     * Sets the value of the assetsOnBankAccountsBalanceBenefit property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setAssetsOnBankAccountsBalanceBenefit(MonetaryNoDecimalsItemType value) {
        this.assetsOnBankAccountsBalanceBenefit = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsBalanceBox3BankAccountSavings property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getAssetsOnBankAccountsBalanceBox3BankAccountSavings() {
        return assetsOnBankAccountsBalanceBox3BankAccountSavings;
    }

    /**
     * Sets the value of the assetsOnBankAccountsBalanceBox3BankAccountSavings property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setAssetsOnBankAccountsBalanceBox3BankAccountSavings(MonetaryNoDecimalsItemType value) {
        this.assetsOnBankAccountsBalanceBox3BankAccountSavings = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsBalanceOriginalCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getAssetsOnBankAccountsBalanceOriginalCurrency() {
        return assetsOnBankAccountsBalanceOriginalCurrency;
    }

    /**
     * Sets the value of the assetsOnBankAccountsBalanceOriginalCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setAssetsOnBankAccountsBalanceOriginalCurrency(MonetaryNoDecimalsItemType value) {
        this.assetsOnBankAccountsBalanceOriginalCurrency = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsBalanceType property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeInteger2FItemType }
     *     
     */
    public NonNegativeInteger2FItemType getAssetsOnBankAccountsBalanceType() {
        return assetsOnBankAccountsBalanceType;
    }

    /**
     * Sets the value of the assetsOnBankAccountsBalanceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeInteger2FItemType }
     *     
     */
    public void setAssetsOnBankAccountsBalanceType(NonNegativeInteger2FItemType value) {
        this.assetsOnBankAccountsBalanceType = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsBankAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring34VItemType }
     *     
     */
    public Anstring34VItemType getAssetsOnBankAccountsBankAccountNumber() {
        return assetsOnBankAccountsBankAccountNumber;
    }

    /**
     * Sets the value of the assetsOnBankAccountsBankAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring34VItemType }
     *     
     */
    public void setAssetsOnBankAccountsBankAccountNumber(Anstring34VItemType value) {
        this.assetsOnBankAccountsBankAccountNumber = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsBankName property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring70VItemType }
     *     
     */
    public Anstring70VItemType getAssetsOnBankAccountsBankName() {
        return assetsOnBankAccountsBankName;
    }

    /**
     * Sets the value of the assetsOnBankAccountsBankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring70VItemType }
     *     
     */
    public void setAssetsOnBankAccountsBankName(Anstring70VItemType value) {
        this.assetsOnBankAccountsBankName = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsBankProductLabel property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring70VItemType }
     *     
     */
    public Anstring70VItemType getAssetsOnBankAccountsBankProductLabel() {
        return assetsOnBankAccountsBankProductLabel;
    }

    /**
     * Sets the value of the assetsOnBankAccountsBankProductLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring70VItemType }
     *     
     */
    public void setAssetsOnBankAccountsBankProductLabel(Anstring70VItemType value) {
        this.assetsOnBankAccountsBankProductLabel = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsCommonInterestIndication property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeInteger2FItemType }
     *     
     */
    public NonNegativeInteger2FItemType getAssetsOnBankAccountsCommonInterestIndication() {
        return assetsOnBankAccountsCommonInterestIndication;
    }

    /**
     * Sets the value of the assetsOnBankAccountsCommonInterestIndication property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeInteger2FItemType }
     *     
     */
    public void setAssetsOnBankAccountsCommonInterestIndication(NonNegativeInteger2FItemType value) {
        this.assetsOnBankAccountsCommonInterestIndication = value;
    }

    /**
     * Gets the value of the assetsOnBankAccountsRecordType property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeInteger2FItemType }
     *     
     */
    public NonNegativeInteger2FItemType getAssetsOnBankAccountsRecordType() {
        return assetsOnBankAccountsRecordType;
    }

    /**
     * Sets the value of the assetsOnBankAccountsRecordType property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeInteger2FItemType }
     *     
     */
    public void setAssetsOnBankAccountsRecordType(NonNegativeInteger2FItemType value) {
        this.assetsOnBankAccountsRecordType = value;
    }

}