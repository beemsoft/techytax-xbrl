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
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Anstring10VItemType;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Anstring200VItemType;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Anstring40VItemType;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.MonetaryNoDecimals10VItemType;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Test11ItemType;
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
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DeceasedMentionedAsInsured" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}LifeAccidentInsuranceBenefitInsuranceAmount" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}LifeAccidentInsuranceBenefitInsuranceReceiverCompanyName" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}LifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}LifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}LifeAccidentInsuranceBenefitInsuranceReceiverInitials" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}LifeAccidentInsuranceBenefitInsuranceReceiverPrefix" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}LifeAccidentInsuranceBenefitInsuranceReceiverSurname" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}LifeAccidentInsuranceInsuranceCompanyName" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}LifeAccidentInsuranceLifeInsurancePolicyNumber" minOccurs="0"/>
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
    "deceasedMentionedAsInsured",
    "lifeAccidentInsuranceBenefitInsuranceAmount",
    "lifeAccidentInsuranceBenefitInsuranceReceiverCompanyName",
    "lifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth",
    "lifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber",
    "lifeAccidentInsuranceBenefitInsuranceReceiverInitials",
    "lifeAccidentInsuranceBenefitInsuranceReceiverPrefix",
    "lifeAccidentInsuranceBenefitInsuranceReceiverSurname",
    "lifeAccidentInsuranceInsuranceCompanyName",
    "lifeAccidentInsuranceLifeInsurancePolicyNumber"
})
public class LifeAccidentInsuranceSpecification
    extends Placeholder
{

    @XmlElement(name = "DeceasedMentionedAsInsured", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected BooleanItemType deceasedMentionedAsInsured;
    @XmlElement(name = "LifeAccidentInsuranceBenefitInsuranceAmount", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected MonetaryNoDecimals10VItemType lifeAccidentInsuranceBenefitInsuranceAmount;
    @XmlElement(name = "LifeAccidentInsuranceBenefitInsuranceReceiverCompanyName", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring200VItemType lifeAccidentInsuranceBenefitInsuranceReceiverCompanyName;
    @XmlElement(name = "LifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected DateItemType lifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth;
    @XmlElement(name = "LifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Test11ItemType lifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber;
    @XmlElement(name = "LifeAccidentInsuranceBenefitInsuranceReceiverInitials", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring10VItemType lifeAccidentInsuranceBenefitInsuranceReceiverInitials;
    @XmlElement(name = "LifeAccidentInsuranceBenefitInsuranceReceiverPrefix", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring10VItemType lifeAccidentInsuranceBenefitInsuranceReceiverPrefix;
    @XmlElement(name = "LifeAccidentInsuranceBenefitInsuranceReceiverSurname", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring200VItemType lifeAccidentInsuranceBenefitInsuranceReceiverSurname;
    @XmlElement(name = "LifeAccidentInsuranceInsuranceCompanyName", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring200VItemType lifeAccidentInsuranceInsuranceCompanyName;
    @XmlElement(name = "LifeAccidentInsuranceLifeInsurancePolicyNumber", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring40VItemType lifeAccidentInsuranceLifeInsurancePolicyNumber;

    /**
     * Gets the value of the deceasedMentionedAsInsured property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanItemType }
     *     
     */
    public BooleanItemType getDeceasedMentionedAsInsured() {
        return deceasedMentionedAsInsured;
    }

    /**
     * Sets the value of the deceasedMentionedAsInsured property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanItemType }
     *     
     */
    public void setDeceasedMentionedAsInsured(BooleanItemType value) {
        this.deceasedMentionedAsInsured = value;
    }

    /**
     * Gets the value of the lifeAccidentInsuranceBenefitInsuranceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimals10VItemType }
     *     
     */
    public MonetaryNoDecimals10VItemType getLifeAccidentInsuranceBenefitInsuranceAmount() {
        return lifeAccidentInsuranceBenefitInsuranceAmount;
    }

    /**
     * Sets the value of the lifeAccidentInsuranceBenefitInsuranceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimals10VItemType }
     *     
     */
    public void setLifeAccidentInsuranceBenefitInsuranceAmount(MonetaryNoDecimals10VItemType value) {
        this.lifeAccidentInsuranceBenefitInsuranceAmount = value;
    }

    /**
     * Gets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring200VItemType }
     *     
     */
    public Anstring200VItemType getLifeAccidentInsuranceBenefitInsuranceReceiverCompanyName() {
        return lifeAccidentInsuranceBenefitInsuranceReceiverCompanyName;
    }

    /**
     * Sets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring200VItemType }
     *     
     */
    public void setLifeAccidentInsuranceBenefitInsuranceReceiverCompanyName(Anstring200VItemType value) {
        this.lifeAccidentInsuranceBenefitInsuranceReceiverCompanyName = value;
    }

    /**
     * Gets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link DateItemType }
     *     
     */
    public DateItemType getLifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth() {
        return lifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth;
    }

    /**
     * Sets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateItemType }
     *     
     */
    public void setLifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth(DateItemType value) {
        this.lifeAccidentInsuranceBenefitInsuranceReceiverDateOfBirth = value;
    }

    /**
     * Gets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Test11ItemType }
     *     
     */
    public Test11ItemType getLifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber() {
        return lifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber;
    }

    /**
     * Sets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Test11ItemType }
     *     
     */
    public void setLifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber(Test11ItemType value) {
        this.lifeAccidentInsuranceBenefitInsuranceReceiverIdentificationNumber = value;
    }

    /**
     * Gets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverInitials property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getLifeAccidentInsuranceBenefitInsuranceReceiverInitials() {
        return lifeAccidentInsuranceBenefitInsuranceReceiverInitials;
    }

    /**
     * Sets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverInitials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setLifeAccidentInsuranceBenefitInsuranceReceiverInitials(Anstring10VItemType value) {
        this.lifeAccidentInsuranceBenefitInsuranceReceiverInitials = value;
    }

    /**
     * Gets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getLifeAccidentInsuranceBenefitInsuranceReceiverPrefix() {
        return lifeAccidentInsuranceBenefitInsuranceReceiverPrefix;
    }

    /**
     * Sets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setLifeAccidentInsuranceBenefitInsuranceReceiverPrefix(Anstring10VItemType value) {
        this.lifeAccidentInsuranceBenefitInsuranceReceiverPrefix = value;
    }

    /**
     * Gets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverSurname property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring200VItemType }
     *     
     */
    public Anstring200VItemType getLifeAccidentInsuranceBenefitInsuranceReceiverSurname() {
        return lifeAccidentInsuranceBenefitInsuranceReceiverSurname;
    }

    /**
     * Sets the value of the lifeAccidentInsuranceBenefitInsuranceReceiverSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring200VItemType }
     *     
     */
    public void setLifeAccidentInsuranceBenefitInsuranceReceiverSurname(Anstring200VItemType value) {
        this.lifeAccidentInsuranceBenefitInsuranceReceiverSurname = value;
    }

    /**
     * Gets the value of the lifeAccidentInsuranceInsuranceCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring200VItemType }
     *     
     */
    public Anstring200VItemType getLifeAccidentInsuranceInsuranceCompanyName() {
        return lifeAccidentInsuranceInsuranceCompanyName;
    }

    /**
     * Sets the value of the lifeAccidentInsuranceInsuranceCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring200VItemType }
     *     
     */
    public void setLifeAccidentInsuranceInsuranceCompanyName(Anstring200VItemType value) {
        this.lifeAccidentInsuranceInsuranceCompanyName = value;
    }

    /**
     * Gets the value of the lifeAccidentInsuranceLifeInsurancePolicyNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring40VItemType }
     *     
     */
    public Anstring40VItemType getLifeAccidentInsuranceLifeInsurancePolicyNumber() {
        return lifeAccidentInsuranceLifeInsurancePolicyNumber;
    }

    /**
     * Sets the value of the lifeAccidentInsuranceLifeInsurancePolicyNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring40VItemType }
     *     
     */
    public void setLifeAccidentInsuranceLifeInsurancePolicyNumber(Anstring40VItemType value) {
        this.lifeAccidentInsuranceLifeInsurancePolicyNumber = value;
    }

}