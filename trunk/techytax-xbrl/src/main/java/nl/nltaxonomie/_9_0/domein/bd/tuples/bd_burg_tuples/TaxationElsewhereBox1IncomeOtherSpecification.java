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
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Anstring35VItemType;
import nl.nltaxonomie._9_0.basis.sbr.types.nl_types.NonNegativeMonetaryNoDecimalsItemType;
//import org.iso.iso3166.iso3166_countrycodes_2013_07_11.IsoCountryCodes3CharactersItemType;
import nl.nltaxonomie.iso.iso3166.IsoCountryCodes3CharactersItemType;

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
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}TaxationElsewhereBox1IncomeOtherAmount" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}TaxationElsewhereBox1IncomeOtherCountryCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers}TaxationElsewhereBox1IncomeOtherDescription" minOccurs="0"/>
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
    "taxationElsewhereBox1IncomeOtherAmount",
    "taxationElsewhereBox1IncomeOtherCountryCode",
    "taxationElsewhereBox1IncomeOtherDescription"
})
public class TaxationElsewhereBox1IncomeOtherSpecification
    extends Placeholder
{

    @XmlElement(name = "TaxationElsewhereBox1IncomeOtherAmount", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected NonNegativeMonetaryNoDecimalsItemType taxationElsewhereBox1IncomeOtherAmount;
    @XmlElement(name = "TaxationElsewhereBox1IncomeOtherCountryCode", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected IsoCountryCodes3CharactersItemType taxationElsewhereBox1IncomeOtherCountryCode;
    @XmlElement(name = "TaxationElsewhereBox1IncomeOtherDescription", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-burgers")
    protected Anstring35VItemType taxationElsewhereBox1IncomeOtherDescription;

    /**
     * Gets the value of the taxationElsewhereBox1IncomeOtherAmount property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getTaxationElsewhereBox1IncomeOtherAmount() {
        return taxationElsewhereBox1IncomeOtherAmount;
    }

    /**
     * Sets the value of the taxationElsewhereBox1IncomeOtherAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setTaxationElsewhereBox1IncomeOtherAmount(NonNegativeMonetaryNoDecimalsItemType value) {
        this.taxationElsewhereBox1IncomeOtherAmount = value;
    }

    /**
     * Gets the value of the taxationElsewhereBox1IncomeOtherCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link IsoCountryCodes3CharactersItemType }
     *     
     */
    public IsoCountryCodes3CharactersItemType getTaxationElsewhereBox1IncomeOtherCountryCode() {
        return taxationElsewhereBox1IncomeOtherCountryCode;
    }

    /**
     * Sets the value of the taxationElsewhereBox1IncomeOtherCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link IsoCountryCodes3CharactersItemType }
     *     
     */
    public void setTaxationElsewhereBox1IncomeOtherCountryCode(IsoCountryCodes3CharactersItemType value) {
        this.taxationElsewhereBox1IncomeOtherCountryCode = value;
    }

    /**
     * Gets the value of the taxationElsewhereBox1IncomeOtherDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring35VItemType }
     *     
     */
    public Anstring35VItemType getTaxationElsewhereBox1IncomeOtherDescription() {
        return taxationElsewhereBox1IncomeOtherDescription;
    }

    /**
     * Sets the value of the taxationElsewhereBox1IncomeOtherDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring35VItemType }
     *     
     */
    public void setTaxationElsewhereBox1IncomeOtherDescription(Anstring35VItemType value) {
        this.taxationElsewhereBox1IncomeOtherDescription = value;
    }

}