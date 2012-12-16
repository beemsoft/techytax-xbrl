/*
 * An XML document type.
 * Localname: aanleverFault
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one aanleverFault(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class AanleverFaultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.AanleverFaultDocument
{
    
    public AanleverFaultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AANLEVERFAULT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "aanleverFault");
    
    
    /**
     * Gets the "aanleverFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType getAanleverFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(AANLEVERFAULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "aanleverFault" element
     */
    public void setAanleverFault(nl.logius.digipoort.koppelvlakservices._1_2.FoutType aanleverFault)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(AANLEVERFAULT$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(AANLEVERFAULT$0);
            }
            target.set(aanleverFault);
        }
    }
    
    /**
     * Appends and returns a new empty "aanleverFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType addNewAanleverFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(AANLEVERFAULT$0);
            return target;
        }
    }
}
