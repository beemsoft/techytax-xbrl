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
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.MonetaryNoDecimals10VItemType;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Test11ItemType;
import org.xbrl._2003.instance.DateTimeItemType;


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
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DonationReceiverAmount" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DonationReceiverDateOfBirth" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DonationReceiverDonationAmount" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DonationReceiverDonationDate" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DonationReceiverIdentificationNumber" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DonationReceiverInitials" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DonationReceiverInstitutionName" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DonationReceiverPrefix" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}DonationReceiverSurname" minOccurs="0"/>
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
    "donationReceiverAmount",
    "donationReceiverDateOfBirth",
    "donationReceiverDonationAmount",
    "donationReceiverDonationDate",
    "donationReceiverIdentificationNumber",
    "donationReceiverInitials",
    "donationReceiverInstitutionName",
    "donationReceiverPrefix",
    "donationReceiverSurname"
})
public class DonationReceiverSpecification
    extends Placeholder
{

    @XmlElement(name = "DonationReceiverAmount", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected MonetaryNoDecimals10VItemType donationReceiverAmount;
    @XmlElement(name = "DonationReceiverDateOfBirth", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected DateTimeItemType donationReceiverDateOfBirth;
    @XmlElement(name = "DonationReceiverDonationAmount", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected MonetaryNoDecimals10VItemType donationReceiverDonationAmount;
    @XmlElement(name = "DonationReceiverDonationDate", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected DateTimeItemType donationReceiverDonationDate;
    @XmlElement(name = "DonationReceiverIdentificationNumber", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Test11ItemType donationReceiverIdentificationNumber;
    @XmlElement(name = "DonationReceiverInitials", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring10VItemType donationReceiverInitials;
    @XmlElement(name = "DonationReceiverInstitutionName", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring200VItemType donationReceiverInstitutionName;
    @XmlElement(name = "DonationReceiverPrefix", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring10VItemType donationReceiverPrefix;
    @XmlElement(name = "DonationReceiverSurname", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring200VItemType donationReceiverSurname;

    /**
     * Gets the value of the donationReceiverAmount property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimals10VItemType }
     *     
     */
    public MonetaryNoDecimals10VItemType getDonationReceiverAmount() {
        return donationReceiverAmount;
    }

    /**
     * Sets the value of the donationReceiverAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimals10VItemType }
     *     
     */
    public void setDonationReceiverAmount(MonetaryNoDecimals10VItemType value) {
        this.donationReceiverAmount = value;
    }

    /**
     * Gets the value of the donationReceiverDateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeItemType }
     *     
     */
    public DateTimeItemType getDonationReceiverDateOfBirth() {
        return donationReceiverDateOfBirth;
    }

    /**
     * Sets the value of the donationReceiverDateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeItemType }
     *     
     */
    public void setDonationReceiverDateOfBirth(DateTimeItemType value) {
        this.donationReceiverDateOfBirth = value;
    }

    /**
     * Gets the value of the donationReceiverDonationAmount property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimals10VItemType }
     *     
     */
    public MonetaryNoDecimals10VItemType getDonationReceiverDonationAmount() {
        return donationReceiverDonationAmount;
    }

    /**
     * Sets the value of the donationReceiverDonationAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimals10VItemType }
     *     
     */
    public void setDonationReceiverDonationAmount(MonetaryNoDecimals10VItemType value) {
        this.donationReceiverDonationAmount = value;
    }

    /**
     * Gets the value of the donationReceiverDonationDate property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeItemType }
     *     
     */
    public DateTimeItemType getDonationReceiverDonationDate() {
        return donationReceiverDonationDate;
    }

    /**
     * Sets the value of the donationReceiverDonationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeItemType }
     *     
     */
    public void setDonationReceiverDonationDate(DateTimeItemType value) {
        this.donationReceiverDonationDate = value;
    }

    /**
     * Gets the value of the donationReceiverIdentificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Test11ItemType }
     *     
     */
    public Test11ItemType getDonationReceiverIdentificationNumber() {
        return donationReceiverIdentificationNumber;
    }

    /**
     * Sets the value of the donationReceiverIdentificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Test11ItemType }
     *     
     */
    public void setDonationReceiverIdentificationNumber(Test11ItemType value) {
        this.donationReceiverIdentificationNumber = value;
    }

    /**
     * Gets the value of the donationReceiverInitials property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getDonationReceiverInitials() {
        return donationReceiverInitials;
    }

    /**
     * Sets the value of the donationReceiverInitials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setDonationReceiverInitials(Anstring10VItemType value) {
        this.donationReceiverInitials = value;
    }

    /**
     * Gets the value of the donationReceiverInstitutionName property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring200VItemType }
     *     
     */
    public Anstring200VItemType getDonationReceiverInstitutionName() {
        return donationReceiverInstitutionName;
    }

    /**
     * Sets the value of the donationReceiverInstitutionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring200VItemType }
     *     
     */
    public void setDonationReceiverInstitutionName(Anstring200VItemType value) {
        this.donationReceiverInstitutionName = value;
    }

    /**
     * Gets the value of the donationReceiverPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getDonationReceiverPrefix() {
        return donationReceiverPrefix;
    }

    /**
     * Sets the value of the donationReceiverPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setDonationReceiverPrefix(Anstring10VItemType value) {
        this.donationReceiverPrefix = value;
    }

    /**
     * Gets the value of the donationReceiverSurname property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring200VItemType }
     *     
     */
    public Anstring200VItemType getDonationReceiverSurname() {
        return donationReceiverSurname;
    }

    /**
     * Sets the value of the donationReceiverSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring200VItemType }
     *     
     */
    public void setDonationReceiverSurname(Anstring200VItemType value) {
        this.donationReceiverSurname = value;
    }

}