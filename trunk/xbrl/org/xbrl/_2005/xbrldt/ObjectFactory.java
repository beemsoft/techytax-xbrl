//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.16 at 05:37:52 PM CET 
//


package org.xbrl._2005.xbrldt;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.xbrl._2003.instance.StringItemType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.xbrl._2005.xbrldt package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DimensionItem_QNAME = new QName("http://xbrl.org/2005/xbrldt", "dimensionItem");
    private final static QName _HypercubeItem_QNAME = new QName("http://xbrl.org/2005/xbrldt", "hypercubeItem");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.xbrl._2005.xbrldt
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xbrl.org/2005/xbrldt", name = "dimensionItem", substitutionHeadNamespace = "http://www.xbrl.org/2003/instance", substitutionHeadName = "item")
    public JAXBElement<StringItemType> createDimensionItem(StringItemType value) {
        return new JAXBElement<StringItemType>(_DimensionItem_QNAME, StringItemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xbrl.org/2005/xbrldt", name = "hypercubeItem", substitutionHeadNamespace = "http://www.xbrl.org/2003/instance", substitutionHeadName = "item")
    public JAXBElement<StringItemType> createHypercubeItem(StringItemType value) {
        return new JAXBElement<StringItemType>(_HypercubeItem_QNAME, StringItemType.class, null, value);
    }

}