/*
 * An XML document type.
 * Localname: ophaalFault
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.OphaalFaultDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one ophaalFault(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class OphaalFaultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.OphaalFaultDocument
{
    
    public OphaalFaultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OPHAALFAULT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "ophaalFault");
    
    
    /**
     * Gets the "ophaalFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType getOphaalFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(OPHAALFAULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ophaalFault" element
     */
    public void setOphaalFault(nl.logius.digipoort.koppelvlakservices._1_2.FoutType ophaalFault)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(OPHAALFAULT$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(OPHAALFAULT$0);
            }
            target.set(ophaalFault);
        }
    }
    
    /**
     * Appends and returns a new empty "ophaalFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType addNewOphaalFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(OPHAALFAULT$0);
            return target;
        }
    }
}
