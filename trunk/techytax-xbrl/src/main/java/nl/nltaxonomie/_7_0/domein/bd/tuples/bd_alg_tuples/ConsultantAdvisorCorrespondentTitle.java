//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.16 at 05:37:42 PM CET 
//


package nl.nltaxonomie._7_0.domein.bd.tuples.bd_alg_tuples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.Placeholder;
import nl.nltaxonomie._7_0.basis.bd.types.bd_types.Anstring10VItemType;
import nl.nltaxonomie._7_0.basis.bd.types.bd_types.Anstring14VItemType;
import nl.nltaxonomie._7_0.basis.bd.types.bd_types.Anstring200VItemType;


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
 *         &lt;element ref="{http://www.nltaxonomie.nl/7.0/basis/bd/items/bd-algemeen}Initials" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/7.0/basis/bd/items/bd-algemeen}Prefix" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/7.0/basis/bd/items/bd-algemeen}SurnameSignificantPart" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/7.0/basis/bd/items/bd-algemeen}TelephoneNumber" minOccurs="0"/>
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
    "initials",
    "prefix",
    "surnameSignificantPart",
    "telephoneNumber"
})
public class ConsultantAdvisorCorrespondentTitle
    extends Placeholder
{

    @XmlElement(name = "Initials", namespace = "http://www.nltaxonomie.nl/7.0/basis/bd/items/bd-algemeen")
    protected Anstring10VItemType initials;
    @XmlElement(name = "Prefix", namespace = "http://www.nltaxonomie.nl/7.0/basis/bd/items/bd-algemeen")
    protected Anstring10VItemType prefix;
    @XmlElement(name = "SurnameSignificantPart", namespace = "http://www.nltaxonomie.nl/7.0/basis/bd/items/bd-algemeen")
    protected Anstring200VItemType surnameSignificantPart;
    @XmlElement(name = "TelephoneNumber", namespace = "http://www.nltaxonomie.nl/7.0/basis/bd/items/bd-algemeen")
    protected Anstring14VItemType telephoneNumber;

    /**
     * Gets the value of the initials property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getInitials() {
        return initials;
    }

    /**
     * Sets the value of the initials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setInitials(Anstring10VItemType value) {
        this.initials = value;
    }

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getPrefix() {
        return prefix;
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setPrefix(Anstring10VItemType value) {
        this.prefix = value;
    }

    /**
     * Gets the value of the surnameSignificantPart property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring200VItemType }
     *     
     */
    public Anstring200VItemType getSurnameSignificantPart() {
        return surnameSignificantPart;
    }

    /**
     * Sets the value of the surnameSignificantPart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring200VItemType }
     *     
     */
    public void setSurnameSignificantPart(Anstring200VItemType value) {
        this.surnameSignificantPart = value;
    }

    /**
     * Gets the value of the telephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring14VItemType }
     *     
     */
    public Anstring14VItemType getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets the value of the telephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring14VItemType }
     *     
     */
    public void setTelephoneNumber(Anstring14VItemType value) {
        this.telephoneNumber = value;
    }

}