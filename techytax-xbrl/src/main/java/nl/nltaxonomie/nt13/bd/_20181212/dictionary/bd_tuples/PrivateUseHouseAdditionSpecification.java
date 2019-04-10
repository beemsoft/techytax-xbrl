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
import nl.nltaxonomie.nt13.bd._20181212.dictionary.bd_types.Perc32ItemType;
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
 *         &lt;choice minOccurs="0">
 *           &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-tuples}AddressAbroadPresentation" minOccurs="0"/>
 *           &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-tuples}AddressPresentation" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PrivateUseHouseAdditionImmovablePropertyLawValue" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PrivateUseHouseAdditionOwnHouseValue" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PrivateUseHouseAdditionPeriodEnd" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PrivateUseHouseAdditionPeriodStart" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PrivateUseHouseAdditionRightsPercentage" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data}PrivateUseHouseAdditionWithdrawal" minOccurs="0"/>
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
    "privateUseHouseAdditionImmovablePropertyLawValue",
    "privateUseHouseAdditionOwnHouseValue",
    "privateUseHouseAdditionPeriodEnd",
    "privateUseHouseAdditionPeriodStart",
    "privateUseHouseAdditionRightsPercentage",
    "privateUseHouseAdditionWithdrawal"
})
public class PrivateUseHouseAdditionSpecification
    extends Placeholder
{

    @XmlElement(name = "AddressAbroadPresentation", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-tuples")
    protected AddressAbroadPresentation addressAbroadPresentation;
    @XmlElement(name = "AddressPresentation", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-tuples")
    protected AddressPresentation addressPresentation;
    @XmlElement(name = "PrivateUseHouseAdditionImmovablePropertyLawValue", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected MonetaryNoDecimalsItemType privateUseHouseAdditionImmovablePropertyLawValue;
    @XmlElement(name = "PrivateUseHouseAdditionOwnHouseValue", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType privateUseHouseAdditionOwnHouseValue;
    @XmlElement(name = "PrivateUseHouseAdditionPeriodEnd", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected DateItemType privateUseHouseAdditionPeriodEnd;
    @XmlElement(name = "PrivateUseHouseAdditionPeriodStart", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected DateItemType privateUseHouseAdditionPeriodStart;
    @XmlElement(name = "PrivateUseHouseAdditionRightsPercentage", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected Perc32ItemType privateUseHouseAdditionRightsPercentage;
    @XmlElement(name = "PrivateUseHouseAdditionWithdrawal", namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/dictionary/bd-data")
    protected NonNegativeMonetaryNoDecimalsItemType privateUseHouseAdditionWithdrawal;

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
     * Gets the value of the privateUseHouseAdditionImmovablePropertyLawValue property.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public MonetaryNoDecimalsItemType getPrivateUseHouseAdditionImmovablePropertyLawValue() {
        return privateUseHouseAdditionImmovablePropertyLawValue;
    }

    /**
     * Sets the value of the privateUseHouseAdditionImmovablePropertyLawValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryNoDecimalsItemType }
     *     
     */
    public void setPrivateUseHouseAdditionImmovablePropertyLawValue(MonetaryNoDecimalsItemType value) {
        this.privateUseHouseAdditionImmovablePropertyLawValue = value;
    }

    /**
     * Gets the value of the privateUseHouseAdditionOwnHouseValue property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getPrivateUseHouseAdditionOwnHouseValue() {
        return privateUseHouseAdditionOwnHouseValue;
    }

    /**
     * Sets the value of the privateUseHouseAdditionOwnHouseValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setPrivateUseHouseAdditionOwnHouseValue(NonNegativeMonetaryNoDecimalsItemType value) {
        this.privateUseHouseAdditionOwnHouseValue = value;
    }

    /**
     * Gets the value of the privateUseHouseAdditionPeriodEnd property.
     * 
     * @return
     *     possible object is
     *     {@link DateItemType }
     *     
     */
    public DateItemType getPrivateUseHouseAdditionPeriodEnd() {
        return privateUseHouseAdditionPeriodEnd;
    }

    /**
     * Sets the value of the privateUseHouseAdditionPeriodEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateItemType }
     *     
     */
    public void setPrivateUseHouseAdditionPeriodEnd(DateItemType value) {
        this.privateUseHouseAdditionPeriodEnd = value;
    }

    /**
     * Gets the value of the privateUseHouseAdditionPeriodStart property.
     * 
     * @return
     *     possible object is
     *     {@link DateItemType }
     *     
     */
    public DateItemType getPrivateUseHouseAdditionPeriodStart() {
        return privateUseHouseAdditionPeriodStart;
    }

    /**
     * Sets the value of the privateUseHouseAdditionPeriodStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateItemType }
     *     
     */
    public void setPrivateUseHouseAdditionPeriodStart(DateItemType value) {
        this.privateUseHouseAdditionPeriodStart = value;
    }

    /**
     * Gets the value of the privateUseHouseAdditionRightsPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Perc32ItemType }
     *     
     */
    public Perc32ItemType getPrivateUseHouseAdditionRightsPercentage() {
        return privateUseHouseAdditionRightsPercentage;
    }

    /**
     * Sets the value of the privateUseHouseAdditionRightsPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Perc32ItemType }
     *     
     */
    public void setPrivateUseHouseAdditionRightsPercentage(Perc32ItemType value) {
        this.privateUseHouseAdditionRightsPercentage = value;
    }

    /**
     * Gets the value of the privateUseHouseAdditionWithdrawal property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getPrivateUseHouseAdditionWithdrawal() {
        return privateUseHouseAdditionWithdrawal;
    }

    /**
     * Sets the value of the privateUseHouseAdditionWithdrawal property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setPrivateUseHouseAdditionWithdrawal(NonNegativeMonetaryNoDecimalsItemType value) {
        this.privateUseHouseAdditionWithdrawal = value;
    }

}