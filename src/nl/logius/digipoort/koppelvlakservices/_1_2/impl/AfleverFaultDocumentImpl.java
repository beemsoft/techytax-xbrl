/*
 * An XML document type.
 * Localname: afleverFault
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.AfleverFaultDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one afleverFault(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class AfleverFaultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.AfleverFaultDocument
{
    
    public AfleverFaultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AFLEVERFAULT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "afleverFault");
    
    
    /**
     * Gets the "afleverFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType getAfleverFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(AFLEVERFAULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "afleverFault" element
     */
    public void setAfleverFault(nl.logius.digipoort.koppelvlakservices._1_2.FoutType afleverFault)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(AFLEVERFAULT$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(AFLEVERFAULT$0);
            }
            target.set(afleverFault);
        }
    }
    
    /**
     * Appends and returns a new empty "afleverFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType addNewAfleverFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(AFLEVERFAULT$0);
            return target;
        }
    }
}
