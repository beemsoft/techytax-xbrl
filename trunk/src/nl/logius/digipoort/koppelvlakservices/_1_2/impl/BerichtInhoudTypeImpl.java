/*
 * XML Type:  berichtInhoudType
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML berichtInhoudType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class BerichtInhoudTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType
{
    
    public BerichtInhoudTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MIMETYPE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "mimeType");
    private static final javax.xml.namespace.QName BESTANDSNAAM$2 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "bestandsnaam");
    private static final javax.xml.namespace.QName INHOUD$4 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "inhoud");
    
    
    /**
     * Gets the "mimeType" element
     */
    public java.lang.String getMimeType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MIMETYPE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "mimeType" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType xgetMimeType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType)get_store().find_element_user(MIMETYPE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "mimeType" element
     */
    public void setMimeType(java.lang.String mimeType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MIMETYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MIMETYPE$0);
            }
            target.setStringValue(mimeType);
        }
    }
    
    /**
     * Sets (as xml) the "mimeType" element
     */
    public void xsetMimeType(nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType mimeType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType)get_store().find_element_user(MIMETYPE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType)get_store().add_element_user(MIMETYPE$0);
            }
            target.set(mimeType);
        }
    }
    
    /**
     * Gets the "bestandsnaam" element
     */
    public java.lang.String getBestandsnaam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BESTANDSNAAM$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "bestandsnaam" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam xgetBestandsnaam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam)get_store().find_element_user(BESTANDSNAAM$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "bestandsnaam" element
     */
    public void setBestandsnaam(java.lang.String bestandsnaam)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BESTANDSNAAM$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BESTANDSNAAM$2);
            }
            target.setStringValue(bestandsnaam);
        }
    }
    
    /**
     * Sets (as xml) the "bestandsnaam" element
     */
    public void xsetBestandsnaam(nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam bestandsnaam)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam)get_store().find_element_user(BESTANDSNAAM$2, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam)get_store().add_element_user(BESTANDSNAAM$2);
            }
            target.set(bestandsnaam);
        }
    }
    
    /**
     * Gets the "inhoud" element
     */
    public byte[] getInhoud()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INHOUD$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "inhoud" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud xgetInhoud()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud)get_store().find_element_user(INHOUD$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "inhoud" element
     */
    public void setInhoud(byte[] inhoud)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INHOUD$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(INHOUD$4);
            }
            target.setByteArrayValue(inhoud);
        }
    }
    
    /**
     * Sets (as xml) the "inhoud" element
     */
    public void xsetInhoud(nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud inhoud)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud)get_store().find_element_user(INHOUD$4, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud)get_store().add_element_user(INHOUD$4);
            }
            target.set(inhoud);
        }
    }
    /**
     * An XML mimeType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is an atomic type that is a restriction of nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType$MimeType.
     */
    public static class MimeTypeImpl extends org.apache.xmlbeans.impl.values.JavaStringHolderEx implements nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType
    {
        
        public MimeTypeImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected MimeTypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
    /**
     * An XML bestandsnaam(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is an atomic type that is a restriction of nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType$Bestandsnaam.
     */
    public static class BestandsnaamImpl extends org.apache.xmlbeans.impl.values.JavaStringHolderEx implements nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam
    {
        
        public BestandsnaamImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected BestandsnaamImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
    /**
     * An XML inhoud(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is an atomic type that is a restriction of nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType$Inhoud.
     */
    public static class InhoudImpl extends org.apache.xmlbeans.impl.values.JavaBase64HolderEx implements nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud
    {
        
        public InhoudImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected InhoudImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
}
