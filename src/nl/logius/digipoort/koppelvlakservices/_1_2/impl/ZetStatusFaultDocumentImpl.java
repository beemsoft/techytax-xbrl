/*
 * An XML document type.
 * Localname: zetStatusFault
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusFaultDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one zetStatusFault(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class ZetStatusFaultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusFaultDocument
{
    
    public ZetStatusFaultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ZETSTATUSFAULT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "zetStatusFault");
    
    
    /**
     * Gets the "zetStatusFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType getZetStatusFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(ZETSTATUSFAULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "zetStatusFault" element
     */
    public void setZetStatusFault(nl.logius.digipoort.koppelvlakservices._1_2.FoutType zetStatusFault)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(ZETSTATUSFAULT$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(ZETSTATUSFAULT$0);
            }
            target.set(zetStatusFault);
        }
    }
    
    /**
     * Appends and returns a new empty "zetStatusFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType addNewZetStatusFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(ZETSTATUSFAULT$0);
            return target;
        }
    }
}
