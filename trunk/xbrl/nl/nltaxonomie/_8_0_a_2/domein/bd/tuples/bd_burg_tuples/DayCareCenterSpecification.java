//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.17 at 01:15:21 PM CEST 
//


package nl.nltaxonomie._8_0_a_2.domein.bd.tuples.bd_burg_tuples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.Placeholder;
import nl.nltaxonomie._8_0.basis.sbr.types.nl_types.NlzipItemType;
import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.Anstring12VItemType;
import nl.nltaxonomie._8_0_a_2.basis.bd.types.bd_types.Anstring80VItemType;


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
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-burgers}DayCareCenterHouseNumber" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-burgers}DayCareCenterIdentification" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-burgers}DayCareCenterName" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0/basis/sbr/items/nl-common-data}PostalCodeNL" minOccurs="0"/>
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
    "dayCareCenterHouseNumber",
    "dayCareCenterIdentification",
    "dayCareCenterName",
    "postalCodeNL"
})
public class DayCareCenterSpecification
    extends Placeholder
{

    @XmlElement(name = "DayCareCenterHouseNumber", namespace = "http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-burgers")
    protected Anstring12VItemType dayCareCenterHouseNumber;
    @XmlElement(name = "DayCareCenterIdentification", namespace = "http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-burgers")
    protected Anstring12VItemType dayCareCenterIdentification;
    @XmlElement(name = "DayCareCenterName", namespace = "http://www.nltaxonomie.nl/8.0.a.2/basis/bd/items/bd-burgers")
    protected Anstring80VItemType dayCareCenterName;
    @XmlElement(name = "PostalCodeNL", namespace = "http://www.nltaxonomie.nl/8.0/basis/sbr/items/nl-common-data")
    protected NlzipItemType postalCodeNL;

    /**
     * Gets the value of the dayCareCenterHouseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring12VItemType }
     *     
     */
    public Anstring12VItemType getDayCareCenterHouseNumber() {
        return dayCareCenterHouseNumber;
    }

    /**
     * Sets the value of the dayCareCenterHouseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring12VItemType }
     *     
     */
    public void setDayCareCenterHouseNumber(Anstring12VItemType value) {
        this.dayCareCenterHouseNumber = value;
    }

    /**
     * Gets the value of the dayCareCenterIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring12VItemType }
     *     
     */
    public Anstring12VItemType getDayCareCenterIdentification() {
        return dayCareCenterIdentification;
    }

    /**
     * Sets the value of the dayCareCenterIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring12VItemType }
     *     
     */
    public void setDayCareCenterIdentification(Anstring12VItemType value) {
        this.dayCareCenterIdentification = value;
    }

    /**
     * Gets the value of the dayCareCenterName property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring80VItemType }
     *     
     */
    public Anstring80VItemType getDayCareCenterName() {
        return dayCareCenterName;
    }

    /**
     * Sets the value of the dayCareCenterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring80VItemType }
     *     
     */
    public void setDayCareCenterName(Anstring80VItemType value) {
        this.dayCareCenterName = value;
    }

    /**
     * Gets the value of the postalCodeNL property.
     * 
     * @return
     *     possible object is
     *     {@link NlzipItemType }
     *     
     */
    public NlzipItemType getPostalCodeNL() {
        return postalCodeNL;
    }

    /**
     * Sets the value of the postalCodeNL property.
     * 
     * @param value
     *     allowed object is
     *     {@link NlzipItemType }
     *     
     */
    public void setPostalCodeNL(NlzipItemType value) {
        this.postalCodeNL = value;
    }

}