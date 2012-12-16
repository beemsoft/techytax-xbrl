/*
 * XML Type:  ProcesResultaat
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML ProcesResultaat(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class ProcesResultaatImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat
{
    
    public ProcesResultaatImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KENMERK$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "kenmerk");
    private static final javax.xml.namespace.QName BERICHTSOORT$2 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtsoort");
    private static final javax.xml.namespace.QName IDENTITEITBELANGHEBBENDE$4 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitBelanghebbende");
    private static final javax.xml.namespace.QName IDENTITEITONTVANGER$6 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitOntvanger");
    
    
    /**
     * Gets the "kenmerk" element
     */
    public java.lang.String getKenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KENMERK$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "kenmerk" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType xgetKenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType)get_store().find_element_user(KENMERK$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "kenmerk" element
     */
    public void setKenmerk(java.lang.String kenmerk)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KENMERK$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(KENMERK$0);
            }
            target.setStringValue(kenmerk);
        }
    }
    
    /**
     * Sets (as xml) the "kenmerk" element
     */
    public void xsetKenmerk(nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType kenmerk)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType)get_store().find_element_user(KENMERK$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType)get_store().add_element_user(KENMERK$0);
            }
            target.set(kenmerk);
        }
    }
    
    /**
     * Gets the "berichtsoort" element
     */
    public java.lang.String getBerichtsoort()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTSOORT$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "berichtsoort" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType xgetBerichtsoort()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().find_element_user(BERICHTSOORT$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "berichtsoort" element
     */
    public void setBerichtsoort(java.lang.String berichtsoort)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTSOORT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BERICHTSOORT$2);
            }
            target.setStringValue(berichtsoort);
        }
    }
    
    /**
     * Sets (as xml) the "berichtsoort" element
     */
    public void xsetBerichtsoort(nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType berichtsoort)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().find_element_user(BERICHTSOORT$2, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().add_element_user(BERICHTSOORT$2);
            }
            target.set(berichtsoort);
        }
    }
    
    /**
     * Gets the "identiteitBelanghebbende" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType getIdentiteitBelanghebbende()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "identiteitBelanghebbende" element
     */
    public void setIdentiteitBelanghebbende(nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType identiteitBelanghebbende)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$4, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$4);
            }
            target.set(identiteitBelanghebbende);
        }
    }
    
    /**
     * Appends and returns a new empty "identiteitBelanghebbende" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType addNewIdentiteitBelanghebbende()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$4);
            return target;
        }
    }
    
    /**
     * Gets the "identiteitOntvanger" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType getIdentiteitOntvanger()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITONTVANGER$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "identiteitOntvanger" element
     */
    public boolean isSetIdentiteitOntvanger()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(IDENTITEITONTVANGER$6) != 0;
        }
    }
    
    /**
     * Sets the "identiteitOntvanger" element
     */
    public void setIdentiteitOntvanger(nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType identiteitOntvanger)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITONTVANGER$6, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITONTVANGER$6);
            }
            target.set(identiteitOntvanger);
        }
    }
    
    /**
     * Appends and returns a new empty "identiteitOntvanger" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType addNewIdentiteitOntvanger()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITONTVANGER$6);
            return target;
        }
    }
    
    /**
     * Unsets the "identiteitOntvanger" element
     */
    public void unsetIdentiteitOntvanger()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(IDENTITEITONTVANGER$6, 0);
        }
    }
}
