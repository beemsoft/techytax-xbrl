/*
 * XML Type:  constateringType
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML constateringType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class ConstateringTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType
{
    
    public ConstateringTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CODE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "code");
    private static final javax.xml.namespace.QName BESCHRIJVING$2 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "beschrijving");
    
    
    /**
     * Gets the "code" element
     */
    public java.lang.String getCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "code" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ConstateringcodeType xgetCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringcodeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringcodeType)get_store().find_element_user(CODE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "code" element
     */
    public void setCode(java.lang.String code)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODE$0);
            }
            target.setStringValue(code);
        }
    }
    
    /**
     * Sets (as xml) the "code" element
     */
    public void xsetCode(nl.logius.digipoort.koppelvlakservices._1_2.ConstateringcodeType code)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringcodeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringcodeType)get_store().find_element_user(CODE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringcodeType)get_store().add_element_user(CODE$0);
            }
            target.set(code);
        }
    }
    
    /**
     * Gets the "beschrijving" element
     */
    public java.lang.String getBeschrijving()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BESCHRIJVING$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "beschrijving" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ConstateringbeschrijvingType xgetBeschrijving()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringbeschrijvingType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringbeschrijvingType)get_store().find_element_user(BESCHRIJVING$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "beschrijving" element
     */
    public void setBeschrijving(java.lang.String beschrijving)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BESCHRIJVING$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BESCHRIJVING$2);
            }
            target.setStringValue(beschrijving);
        }
    }
    
    /**
     * Sets (as xml) the "beschrijving" element
     */
    public void xsetBeschrijving(nl.logius.digipoort.koppelvlakservices._1_2.ConstateringbeschrijvingType beschrijving)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringbeschrijvingType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringbeschrijvingType)get_store().find_element_user(BESCHRIJVING$2, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringbeschrijvingType)get_store().add_element_user(BESCHRIJVING$2);
            }
            target.set(beschrijving);
        }
    }
}
