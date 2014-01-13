//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.09 at 10:28:44 PM CET 
//


package nl.nltaxonomie._8_0.domein.bd.tuples.bd_alg_tuples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.Placeholder;
import nl.nltaxonomie._8_0.basis.bd.types.bd_types.Anstring10VItemType;
import nl.nltaxonomie._8_0.basis.bd.types.bd_types.Anstring200VItemType;
import nl.nltaxonomie._8_0.basis.bd.types.bd_types.Test11ItemType;
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
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen}FinancialYearEnd"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen}FinancialYearStart"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen}TaxpayerIdentificationNumber"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen}TaxpayerInitials" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen}TaxpayerPrefix" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen}TaxpayerSurname" minOccurs="0"/>
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
    "financialYearEnd",
    "financialYearStart",
    "taxpayerIdentificationNumber",
    "taxpayerInitials",
    "taxpayerPrefix",
    "taxpayerSurname"
})
public class AppointPostsSpecification
    extends Placeholder
{

    @XmlElement(name = "FinancialYearEnd", namespace = "http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen", required = true)
    protected DateItemType financialYearEnd;
    @XmlElement(name = "FinancialYearStart", namespace = "http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen", required = true)
    protected DateItemType financialYearStart;
    @XmlElement(name = "TaxpayerIdentificationNumber", namespace = "http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen", required = true)
    protected Test11ItemType taxpayerIdentificationNumber;
    @XmlElement(name = "TaxpayerInitials", namespace = "http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen")
    protected Anstring10VItemType taxpayerInitials;
    @XmlElement(name = "TaxpayerPrefix", namespace = "http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen")
    protected Anstring10VItemType taxpayerPrefix;
    @XmlElement(name = "TaxpayerSurname", namespace = "http://www.nltaxonomie.nl/8.0/basis/bd/items/bd-algemeen")
    protected Anstring200VItemType taxpayerSurname;

    /**
     * Gets the value of the financialYearEnd property.
     * 
     * @return
     *     possible object is
     *     {@link DateItemType }
     *     
     */
    public DateItemType getFinancialYearEnd() {
        return financialYearEnd;
    }

    /**
     * Sets the value of the financialYearEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateItemType }
     *     
     */
    public void setFinancialYearEnd(DateItemType value) {
        this.financialYearEnd = value;
    }

    /**
     * Gets the value of the financialYearStart property.
     * 
     * @return
     *     possible object is
     *     {@link DateItemType }
     *     
     */
    public DateItemType getFinancialYearStart() {
        return financialYearStart;
    }

    /**
     * Sets the value of the financialYearStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateItemType }
     *     
     */
    public void setFinancialYearStart(DateItemType value) {
        this.financialYearStart = value;
    }

    /**
     * Gets the value of the taxpayerIdentificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Test11ItemType }
     *     
     */
    public Test11ItemType getTaxpayerIdentificationNumber() {
        return taxpayerIdentificationNumber;
    }

    /**
     * Sets the value of the taxpayerIdentificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Test11ItemType }
     *     
     */
    public void setTaxpayerIdentificationNumber(Test11ItemType value) {
        this.taxpayerIdentificationNumber = value;
    }

    /**
     * Gets the value of the taxpayerInitials property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getTaxpayerInitials() {
        return taxpayerInitials;
    }

    /**
     * Sets the value of the taxpayerInitials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setTaxpayerInitials(Anstring10VItemType value) {
        this.taxpayerInitials = value;
    }

    /**
     * Gets the value of the taxpayerPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring10VItemType }
     *     
     */
    public Anstring10VItemType getTaxpayerPrefix() {
        return taxpayerPrefix;
    }

    /**
     * Sets the value of the taxpayerPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring10VItemType }
     *     
     */
    public void setTaxpayerPrefix(Anstring10VItemType value) {
        this.taxpayerPrefix = value;
    }

    /**
     * Gets the value of the taxpayerSurname property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring200VItemType }
     *     
     */
    public Anstring200VItemType getTaxpayerSurname() {
        return taxpayerSurname;
    }

    /**
     * Sets the value of the taxpayerSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring200VItemType }
     *     
     */
    public void setTaxpayerSurname(Anstring200VItemType value) {
        this.taxpayerSurname = value;
    }

}
