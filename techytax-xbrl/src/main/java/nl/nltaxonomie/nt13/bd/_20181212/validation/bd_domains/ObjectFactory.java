//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.10 at 11:10:53 AM CEST 
//


package nl.nltaxonomie.nt13.bd._20181212.validation.bd_domains;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.xbrl._2003.instance.StringItemType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nl.nltaxonomie.nt13.bd._20181212.validation.bd_domains package. 
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

    private final static QName _TaxpayerDomain_QNAME = new QName("http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", "TaxpayerDomain");
    private final static QName _EarningsSetupDomain_QNAME = new QName("http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", "EarningsSetupDomain");
    private final static QName _TimeDomain_QNAME = new QName("http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", "TimeDomain");
    private final static QName _ResidenceDomain_QNAME = new QName("http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", "ResidenceDomain");
    private final static QName _CompanySerialNumberDomain_QNAME = new QName("http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", "CompanySerialNumberDomain");
    private final static QName _PartyDomain_QNAME = new QName("http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", "PartyDomain");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nl.nltaxonomie.nt13.bd._20181212.validation.bd_domains
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", name = "TaxpayerDomain", substitutionHeadNamespace = "http://www.nltaxonomie.nl/2011/xbrl/xbrl-syntax-extension", substitutionHeadName = "domainItem")
    public JAXBElement<StringItemType> createTaxpayerDomain(StringItemType value) {
        return new JAXBElement<StringItemType>(_TaxpayerDomain_QNAME, StringItemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", name = "EarningsSetupDomain", substitutionHeadNamespace = "http://www.nltaxonomie.nl/2011/xbrl/xbrl-syntax-extension", substitutionHeadName = "domainItem")
    public JAXBElement<StringItemType> createEarningsSetupDomain(StringItemType value) {
        return new JAXBElement<StringItemType>(_EarningsSetupDomain_QNAME, StringItemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", name = "TimeDomain", substitutionHeadNamespace = "http://www.nltaxonomie.nl/2011/xbrl/xbrl-syntax-extension", substitutionHeadName = "domainItem")
    public JAXBElement<StringItemType> createTimeDomain(StringItemType value) {
        return new JAXBElement<StringItemType>(_TimeDomain_QNAME, StringItemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", name = "ResidenceDomain", substitutionHeadNamespace = "http://www.nltaxonomie.nl/2011/xbrl/xbrl-syntax-extension", substitutionHeadName = "domainItem")
    public JAXBElement<StringItemType> createResidenceDomain(StringItemType value) {
        return new JAXBElement<StringItemType>(_ResidenceDomain_QNAME, StringItemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", name = "CompanySerialNumberDomain")
    public JAXBElement<Integer> createCompanySerialNumberDomain(Integer value) {
        return new JAXBElement<Integer>(_CompanySerialNumberDomain_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nltaxonomie.nl/nt13/bd/20181212/validation/bd-domains", name = "PartyDomain", substitutionHeadNamespace = "http://www.nltaxonomie.nl/2011/xbrl/xbrl-syntax-extension", substitutionHeadName = "domainItem")
    public JAXBElement<StringItemType> createPartyDomain(StringItemType value) {
        return new JAXBElement<StringItemType>(_PartyDomain_QNAME, StringItemType.class, null, value);
    }

}