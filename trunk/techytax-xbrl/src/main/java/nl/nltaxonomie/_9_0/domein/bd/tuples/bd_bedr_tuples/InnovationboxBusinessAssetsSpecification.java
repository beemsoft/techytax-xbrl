//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.25 at 08:13:42 PM CEST 
//


package nl.nltaxonomie._9_0.domein.bd.tuples.bd_bedr_tuples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.nltaxonomie._2011.xbrl.xbrl_syntax_extension.Placeholder;
import nl.nltaxonomie._9_0.basis.bd.types.bd_types.Anstring70VItemType;
import nl.nltaxonomie._9_0.basis.sbr.types.nl_types.NonNegativeMonetaryNoDecimalsItemType;


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
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-bedrijven}InnovationBoxBusinessAssetsDescription" minOccurs="0"/>
 *         &lt;element ref="{http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-bedrijven}InnovationBoxBusinessAssetsProductionCosts" minOccurs="0"/>
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
    "innovationBoxBusinessAssetsDescription",
    "innovationBoxBusinessAssetsProductionCosts"
})
public class InnovationboxBusinessAssetsSpecification
    extends Placeholder
{

    @XmlElement(name = "InnovationBoxBusinessAssetsDescription", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-bedrijven")
    protected Anstring70VItemType innovationBoxBusinessAssetsDescription;
    @XmlElement(name = "InnovationBoxBusinessAssetsProductionCosts", namespace = "http://www.nltaxonomie.nl/9.0/basis/bd/items/bd-bedrijven")
    protected NonNegativeMonetaryNoDecimalsItemType innovationBoxBusinessAssetsProductionCosts;

    /**
     * Gets the value of the innovationBoxBusinessAssetsDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Anstring70VItemType }
     *     
     */
    public Anstring70VItemType getInnovationBoxBusinessAssetsDescription() {
        return innovationBoxBusinessAssetsDescription;
    }

    /**
     * Sets the value of the innovationBoxBusinessAssetsDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anstring70VItemType }
     *     
     */
    public void setInnovationBoxBusinessAssetsDescription(Anstring70VItemType value) {
        this.innovationBoxBusinessAssetsDescription = value;
    }

    /**
     * Gets the value of the innovationBoxBusinessAssetsProductionCosts property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public NonNegativeMonetaryNoDecimalsItemType getInnovationBoxBusinessAssetsProductionCosts() {
        return innovationBoxBusinessAssetsProductionCosts;
    }

    /**
     * Sets the value of the innovationBoxBusinessAssetsProductionCosts property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeMonetaryNoDecimalsItemType }
     *     
     */
    public void setInnovationBoxBusinessAssetsProductionCosts(NonNegativeMonetaryNoDecimalsItemType value) {
        this.innovationBoxBusinessAssetsProductionCosts = value;
    }

}