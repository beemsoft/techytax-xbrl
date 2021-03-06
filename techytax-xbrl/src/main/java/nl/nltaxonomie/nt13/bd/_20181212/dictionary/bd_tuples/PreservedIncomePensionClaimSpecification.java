//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.10 at 11:10:53 AM CEST 
//


package nl.nltaxonomie.nt13.bd._20181212.dictionary.bd_tuples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.Placeholder;
import nl.nltaxonomie.nt13.bd._20181212.dictionary.bd_types.Anstring20VItemType;
import nl.nltaxonomie.nt13.bd._20181212.dictionary.bd_types.Anstring70VItemType;
import nl.nltaxonomie.nt13.sbr._20180301.dictionary.nl_types.MonetaryNoDecimalsItemType;
import org.xbrl._2003.instance.BooleanItemType;


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
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PreservedIncomePensionClaim" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PreservedIncomePensionClaimFairValue" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PreservedIncomePensionClaimInOwnManagement" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PreservedIncomePensionClaimInsuranceCompanyName" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PreservedIncomePensionClaimPolicyNumber" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PreservedIncomePensionClaimPremiumsPaid" minOccurs="0"/>
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
    "preservedIncomePensionClaim",
    "preservedIncomePensionClaimFairValue",
    "preservedIncomePensionClaimInOwnManagement",
    "preservedIncomePensionClaimInsuranceCompanyName",
    "preservedIncomePensionClaimPolicyNumber",
    "preservedIncomePensionClaimPremiumsPaid"
})
public class PreservedIncomePensionClaimSpecification
    extends Placeholder
{

    @XmlElement(name = "PreservedIncomePensionClaim", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected MonetaryNoDecimalsItemType preservedIncomePensionClaim;
    @XmlElement(name = "PreservedIncomePensionClaimFairValue", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected BooleanItemType preservedIncomePensionClaimFairValue;
    @XmlElement(name = "PreservedIncomePensionClaimInOwnManagement", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected BooleanItemType preservedIncomePensionClaimInOwnManagement;
    @XmlElement(name = "PreservedIncomePensionClaimInsuranceCompanyName", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected Anstring70VItemType preservedIncomePensionClaimInsuranceCompanyName;
    @XmlElement(name = "PreservedIncomePensionClaimPolicyNumber", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected Anstring20VItemType preservedIncomePensionClaimPolicyNumber;
    @XmlElement(name = "PreservedIncomePensionClaimPremiumsPaid", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected BooleanItemType preservedIncomePensionClaimPremiumsPaid;

    /**
     * Gets the value of the preservedIncomePensionClaim property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getPreservedIncomePensionClaim() {
        return preservedIncomePensionClaim;
    }

    /**
     * Sets the value of the preservedIncomePensionClaim property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setPreservedIncomePensionClaim(MonetaryNoDecimalsItemType value) {
        this.preservedIncomePensionClaim = value;
    }

    /**
     * Gets the value of the preservedIncomePensionClaimFairValue property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanItemType }
     *     
     */
    public BooleanItemType getPreservedIncomePensionClaimFairValue() {
        return preservedIncomePensionClaimFairValue;
    }

    /**
     * Sets the value of the preservedIncomePensionClaimFairValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanItemType }
     *     
     */
    public void setPreservedIncomePensionClaimFairValue(BooleanItemType value) {
        this.preservedIncomePensionClaimFairValue = value;
    }

    /**
     * Gets the value of the preservedIncomePensionClaimInOwnManagement property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanItemType }
     *     
     */
    public BooleanItemType getPreservedIncomePensionClaimInOwnManagement() {
        return preservedIncomePensionClaimInOwnManagement;
    }

    /**
     * Sets the value of the preservedIncomePensionClaimInOwnManagement property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanItemType }
     *     
     */
    public void setPreservedIncomePensionClaimInOwnManagement(BooleanItemType value) {
        this.preservedIncomePensionClaimInOwnManagement = value;
    }

    /**
     * Gets the value of the preservedIncomePensionClaimInsuranceCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring70VItemType }
     *     
     */
    public Anstring70VItemType getPreservedIncomePensionClaimInsuranceCompanyName() {
        return preservedIncomePensionClaimInsuranceCompanyName;
    }

    /**
     * Sets the value of the preservedIncomePensionClaimInsuranceCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring70VItemType }
     *     
     */
    public void setPreservedIncomePensionClaimInsuranceCompanyName(Anstring70VItemType value) {
        this.preservedIncomePensionClaimInsuranceCompanyName = value;
    }

    /**
     * Gets the value of the preservedIncomePensionClaimPolicyNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring20VItemType }
     *     
     */
    public Anstring20VItemType getPreservedIncomePensionClaimPolicyNumber() {
        return preservedIncomePensionClaimPolicyNumber;
    }

    /**
     * Sets the value of the preservedIncomePensionClaimPolicyNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring20VItemType }
     *     
     */
    public void setPreservedIncomePensionClaimPolicyNumber(Anstring20VItemType value) {
        this.preservedIncomePensionClaimPolicyNumber = value;
    }

    /**
     * Gets the value of the preservedIncomePensionClaimPremiumsPaid property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanItemType }
     *     
     */
    public BooleanItemType getPreservedIncomePensionClaimPremiumsPaid() {
        return preservedIncomePensionClaimPremiumsPaid;
    }

    /**
     * Sets the value of the preservedIncomePensionClaimPremiumsPaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanItemType }
     *     
     */
    public void setPreservedIncomePensionClaimPremiumsPaid(BooleanItemType value) {
        this.preservedIncomePensionClaimPremiumsPaid = value;
    }

}
