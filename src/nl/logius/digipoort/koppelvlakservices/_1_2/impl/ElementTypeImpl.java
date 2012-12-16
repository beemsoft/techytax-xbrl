/*
 * XML Type:  elementType
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ElementType
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML elementType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class ElementTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ElementType
{
    
    public ElementTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NAAM$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "naam");
    private static final javax.xml.namespace.QName WAARDE$2 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "waarde");
    
    
    /**
     * Gets the "naam" element
     */
    public java.lang.String getNaam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAAM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "naam" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ElementnaamType xgetNaam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ElementnaamType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementnaamType)get_store().find_element_user(NAAM$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "naam" element
     */
    public void setNaam(java.lang.String naam)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAAM$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NAAM$0);
            }
            target.setStringValue(naam);
        }
    }
    
    /**
     * Sets (as xml) the "naam" element
     */
    public void xsetNaam(nl.logius.digipoort.koppelvlakservices._1_2.ElementnaamType naam)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ElementnaamType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementnaamType)get_store().find_element_user(NAAM$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementnaamType)get_store().add_element_user(NAAM$0);
            }
            target.set(naam);
        }
    }
    
    /**
     * Gets the "waarde" element
     */
    public java.lang.String getWaarde()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(WAARDE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "waarde" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ElementwaardeType xgetWaarde()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ElementwaardeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementwaardeType)get_store().find_element_user(WAARDE$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "waarde" element
     */
    public void setWaarde(java.lang.String waarde)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(WAARDE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(WAARDE$2);
            }
            target.setStringValue(waarde);
        }
    }
    
    /**
     * Sets (as xml) the "waarde" element
     */
    public void xsetWaarde(nl.logius.digipoort.koppelvlakservices._1_2.ElementwaardeType waarde)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ElementwaardeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementwaardeType)get_store().find_element_user(WAARDE$2, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementwaardeType)get_store().add_element_user(WAARDE$2);
            }
            target.set(waarde);
        }
    }
}
