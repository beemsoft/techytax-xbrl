//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.01 at 09:06:01 PM CET 
//


package nl.nltaxonomie._7_0_b_1.domein.kvk.tuples.kvk_tuples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.Placeholder;
import org.xbrl._2003.instance.StringItemType;


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
 *         &lt;element ref="{http://www.nltaxonomie.nl/7.0/basis/sbr/items/nl-common-data}FirstName" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/7.0/basis/sbr/items/nl-common-data}FamilyName" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/7.0.b.1/basis/kvk/items/kvk-data}OrganisationName" minOccurs="0"/>
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
    "firstName",
    "familyName",
    "organisationName"
})
public class SignatureManagementReportSpecification
    extends Placeholder
{

    @XmlElement(name = "FirstName", namespace = "http://www.nltaxonomie.nl/7.0/basis/sbr/items/nl-common-data")
    protected StringItemType firstName;
    @XmlElement(name = "FamilyName", namespace = "http://www.nltaxonomie.nl/7.0/basis/sbr/items/nl-common-data")
    protected StringItemType familyName;
    @XmlElement(name = "OrganisationName", namespace = "http://www.nltaxonomie.nl/7.0.b.1/basis/kvk/items/kvk-data")
    protected StringItemType organisationName;

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link StringItemType }
     *     
     */
    public StringItemType getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringItemType }
     *     
     */
    public void setFirstName(StringItemType value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the familyName property.
     * 
     * @return
     *     possible object is
     *     {@link StringItemType }
     *     
     */
    public StringItemType getFamilyName() {
        return familyName;
    }

    /**
     * Sets the value of the familyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringItemType }
     *     
     */
    public void setFamilyName(StringItemType value) {
        this.familyName = value;
    }

    /**
     * Gets the value of the organisationName property.
     * 
     * @return
     *     possible object is
     *     {@link StringItemType }
     *     
     */
    public StringItemType getOrganisationName() {
        return organisationName;
    }

    /**
     * Sets the value of the organisationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringItemType }
     *     
     */
    public void setOrganisationName(StringItemType value) {
        this.organisationName = value;
    }

}