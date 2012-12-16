/*
 * XML Type:  foutType
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.FoutType
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML foutType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class FoutTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.FoutType
{
    
    public FoutTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FOUTCODE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "foutcode");
    private static final javax.xml.namespace.QName FOUTBESCHRIJVING$2 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "foutbeschrijving");
    
    
    /**
     * Gets the "foutcode" element
     */
    public java.lang.String getFoutcode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FOUTCODE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "foutcode" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutcodeType xgetFoutcode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutcodeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutcodeType)get_store().find_element_user(FOUTCODE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "foutcode" element
     */
    public void setFoutcode(java.lang.String foutcode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FOUTCODE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FOUTCODE$0);
            }
            target.setStringValue(foutcode);
        }
    }
    
    /**
     * Sets (as xml) the "foutcode" element
     */
    public void xsetFoutcode(nl.logius.digipoort.koppelvlakservices._1_2.FoutcodeType foutcode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutcodeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutcodeType)get_store().find_element_user(FOUTCODE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutcodeType)get_store().add_element_user(FOUTCODE$0);
            }
            target.set(foutcode);
        }
    }
    
    /**
     * Gets the "foutbeschrijving" element
     */
    public java.lang.String getFoutbeschrijving()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FOUTBESCHRIJVING$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "foutbeschrijving" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutbeschrijvingType xgetFoutbeschrijving()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutbeschrijvingType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutbeschrijvingType)get_store().find_element_user(FOUTBESCHRIJVING$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "foutbeschrijving" element
     */
    public void setFoutbeschrijving(java.lang.String foutbeschrijving)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FOUTBESCHRIJVING$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FOUTBESCHRIJVING$2);
            }
            target.setStringValue(foutbeschrijving);
        }
    }
    
    /**
     * Sets (as xml) the "foutbeschrijving" element
     */
    public void xsetFoutbeschrijving(nl.logius.digipoort.koppelvlakservices._1_2.FoutbeschrijvingType foutbeschrijving)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutbeschrijvingType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutbeschrijvingType)get_store().find_element_user(FOUTBESCHRIJVING$2, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutbeschrijvingType)get_store().add_element_user(FOUTBESCHRIJVING$2);
            }
            target.set(foutbeschrijving);
        }
    }
}
