/*
 * An XML document type.
 * Localname: statusinformatieFault
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.StatusinformatieFaultDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one statusinformatieFault(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class StatusinformatieFaultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.StatusinformatieFaultDocument
{
    
    public StatusinformatieFaultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STATUSINFORMATIEFAULT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statusinformatieFault");
    
    
    /**
     * Gets the "statusinformatieFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType getStatusinformatieFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSINFORMATIEFAULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "statusinformatieFault" element
     */
    public void setStatusinformatieFault(nl.logius.digipoort.koppelvlakservices._1_2.FoutType statusinformatieFault)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSINFORMATIEFAULT$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSINFORMATIEFAULT$0);
            }
            target.set(statusinformatieFault);
        }
    }
    
    /**
     * Appends and returns a new empty "statusinformatieFault" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType addNewStatusinformatieFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSINFORMATIEFAULT$0);
            return target;
        }
    }
}
