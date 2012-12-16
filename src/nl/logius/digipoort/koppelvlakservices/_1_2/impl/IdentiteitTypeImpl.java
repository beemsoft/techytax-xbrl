/*
 * XML Type:  identiteitType
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML identiteitType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class IdentiteitTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType
{
    
    public IdentiteitTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NUMMER$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "nummer");
    private static final javax.xml.namespace.QName TYPE$2 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "type");
    
    
    /**
     * Gets the "nummer" element
     */
    public java.lang.String getNummer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUMMER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "nummer" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Nummer xgetNummer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Nummer target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Nummer)get_store().find_element_user(NUMMER$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "nummer" element
     */
    public void setNummer(java.lang.String nummer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUMMER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NUMMER$0);
            }
            target.setStringValue(nummer);
        }
    }
    
    /**
     * Sets (as xml) the "nummer" element
     */
    public void xsetNummer(nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Nummer nummer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Nummer target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Nummer)get_store().find_element_user(NUMMER$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Nummer)get_store().add_element_user(NUMMER$0);
            }
            target.set(nummer);
        }
    }
    
    /**
     * Gets the "type" element
     */
    public java.lang.String getType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "type" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Type xgetType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Type target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Type)get_store().find_element_user(TYPE$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "type" element
     */
    public void setType(java.lang.String type)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TYPE$2);
            }
            target.setStringValue(type);
        }
    }
    
    /**
     * Sets (as xml) the "type" element
     */
    public void xsetType(nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Type type)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Type target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Type)get_store().find_element_user(TYPE$2, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Type)get_store().add_element_user(TYPE$2);
            }
            target.set(type);
        }
    }
    /**
     * An XML nummer(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is an atomic type that is a restriction of nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType$Nummer.
     */
    public static class NummerImpl extends org.apache.xmlbeans.impl.values.JavaStringHolderEx implements nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Nummer
    {
        
        public NummerImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected NummerImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
    /**
     * An XML type(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is an atomic type that is a restriction of nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType$Type.
     */
    public static class TypeImpl extends org.apache.xmlbeans.impl.values.JavaStringHolderEx implements nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType.Type
    {
        
        public TypeImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected TypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
}
