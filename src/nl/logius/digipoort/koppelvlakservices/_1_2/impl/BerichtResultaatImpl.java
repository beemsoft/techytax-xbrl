/*
 * XML Type:  BerichtResultaat
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML BerichtResultaat(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class BerichtResultaatImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat
{
    
    public BerichtResultaatImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KENMERK$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "kenmerk");
    private static final javax.xml.namespace.QName BERICHTSOORT$2 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtsoort");
    private static final javax.xml.namespace.QName BERICHTKENMERK$4 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtkenmerk");
    private static final javax.xml.namespace.QName AANLEVERKENMERK$6 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "aanleverkenmerk");
    private static final javax.xml.namespace.QName EERDERAANLEVERKENMERK$8 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "eerderAanleverkenmerk");
    private static final javax.xml.namespace.QName TIJDSTEMPELAANGELEVERD$10 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelAangeleverd");
    private static final javax.xml.namespace.QName IDENTITEITBELANGHEBBENDE$12 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitBelanghebbende");
    private static final javax.xml.namespace.QName ROLBELANGHEBBENDE$14 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "rolBelanghebbende");
    private static final javax.xml.namespace.QName IDENTITEITAANLEVERAAR$16 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitAanleveraar");
    private static final javax.xml.namespace.QName IDENTITEITONTVANGER$18 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitOntvanger");
    private static final javax.xml.namespace.QName ROLONTVANGER$20 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "rolOntvanger");
    private static final javax.xml.namespace.QName BERICHTINHOUD$22 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtInhoud");
    private static final javax.xml.namespace.QName BERICHTBIJLAGEN$24 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtBijlagen");
    private static final javax.xml.namespace.QName CONSTATERINGENLIJST$26 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "constateringenLijst");
    private static final javax.xml.namespace.QName ADDITIONELEELEMENTENLIJST$28 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "additioneleElementenLijst");
    
    
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
     * Gets the "berichtkenmerk" element
     */
    public java.lang.String getBerichtkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTKENMERK$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "berichtkenmerk" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType xgetBerichtkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType)get_store().find_element_user(BERICHTKENMERK$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "berichtkenmerk" element
     */
    public void setBerichtkenmerk(java.lang.String berichtkenmerk)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTKENMERK$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BERICHTKENMERK$4);
            }
            target.setStringValue(berichtkenmerk);
        }
    }
    
    /**
     * Sets (as xml) the "berichtkenmerk" element
     */
    public void xsetBerichtkenmerk(nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType berichtkenmerk)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType)get_store().find_element_user(BERICHTKENMERK$4, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType)get_store().add_element_user(BERICHTKENMERK$4);
            }
            target.set(berichtkenmerk);
        }
    }
    
    /**
     * Gets the "aanleverkenmerk" element
     */
    public java.lang.String getAanleverkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AANLEVERKENMERK$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "aanleverkenmerk" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType xgetAanleverkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().find_element_user(AANLEVERKENMERK$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "aanleverkenmerk" element
     */
    public boolean isSetAanleverkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(AANLEVERKENMERK$6) != 0;
        }
    }
    
    /**
     * Sets the "aanleverkenmerk" element
     */
    public void setAanleverkenmerk(java.lang.String aanleverkenmerk)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AANLEVERKENMERK$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AANLEVERKENMERK$6);
            }
            target.setStringValue(aanleverkenmerk);
        }
    }
    
    /**
     * Sets (as xml) the "aanleverkenmerk" element
     */
    public void xsetAanleverkenmerk(nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType aanleverkenmerk)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().find_element_user(AANLEVERKENMERK$6, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().add_element_user(AANLEVERKENMERK$6);
            }
            target.set(aanleverkenmerk);
        }
    }
    
    /**
     * Unsets the "aanleverkenmerk" element
     */
    public void unsetAanleverkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(AANLEVERKENMERK$6, 0);
        }
    }
    
    /**
     * Gets the "eerderAanleverkenmerk" element
     */
    public java.lang.String getEerderAanleverkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EERDERAANLEVERKENMERK$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "eerderAanleverkenmerk" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType xgetEerderAanleverkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().find_element_user(EERDERAANLEVERKENMERK$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "eerderAanleverkenmerk" element
     */
    public boolean isSetEerderAanleverkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(EERDERAANLEVERKENMERK$8) != 0;
        }
    }
    
    /**
     * Sets the "eerderAanleverkenmerk" element
     */
    public void setEerderAanleverkenmerk(java.lang.String eerderAanleverkenmerk)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EERDERAANLEVERKENMERK$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EERDERAANLEVERKENMERK$8);
            }
            target.setStringValue(eerderAanleverkenmerk);
        }
    }
    
    /**
     * Sets (as xml) the "eerderAanleverkenmerk" element
     */
    public void xsetEerderAanleverkenmerk(nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType eerderAanleverkenmerk)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().find_element_user(EERDERAANLEVERKENMERK$8, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().add_element_user(EERDERAANLEVERKENMERK$8);
            }
            target.set(eerderAanleverkenmerk);
        }
    }
    
    /**
     * Unsets the "eerderAanleverkenmerk" element
     */
    public void unsetEerderAanleverkenmerk()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(EERDERAANLEVERKENMERK$8, 0);
        }
    }
    
    /**
     * Gets the "tijdstempelAangeleverd" element
     */
    public java.util.Calendar getTijdstempelAangeleverd()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELAANGELEVERD$10, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "tijdstempelAangeleverd" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetTijdstempelAangeleverd()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELAANGELEVERD$10, 0);
            return target;
        }
    }
    
    /**
     * Sets the "tijdstempelAangeleverd" element
     */
    public void setTijdstempelAangeleverd(java.util.Calendar tijdstempelAangeleverd)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELAANGELEVERD$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELAANGELEVERD$10);
            }
            target.setCalendarValue(tijdstempelAangeleverd);
        }
    }
    
    /**
     * Sets (as xml) the "tijdstempelAangeleverd" element
     */
    public void xsetTijdstempelAangeleverd(org.apache.xmlbeans.XmlDateTime tijdstempelAangeleverd)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELAANGELEVERD$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELAANGELEVERD$10);
            }
            target.set(tijdstempelAangeleverd);
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
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$12, 0);
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
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$12, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$12);
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
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$12);
            return target;
        }
    }
    
    /**
     * Gets the "rolBelanghebbende" element
     */
    public java.lang.String getRolBelanghebbende()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLBELANGHEBBENDE$14, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "rolBelanghebbende" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.RolType xgetRolBelanghebbende()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.RolType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLBELANGHEBBENDE$14, 0);
            return target;
        }
    }
    
    /**
     * Sets the "rolBelanghebbende" element
     */
    public void setRolBelanghebbende(java.lang.String rolBelanghebbende)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLBELANGHEBBENDE$14, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ROLBELANGHEBBENDE$14);
            }
            target.setStringValue(rolBelanghebbende);
        }
    }
    
    /**
     * Sets (as xml) the "rolBelanghebbende" element
     */
    public void xsetRolBelanghebbende(nl.logius.digipoort.koppelvlakservices._1_2.RolType rolBelanghebbende)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.RolType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLBELANGHEBBENDE$14, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().add_element_user(ROLBELANGHEBBENDE$14);
            }
            target.set(rolBelanghebbende);
        }
    }
    
    /**
     * Gets the "identiteitAanleveraar" element
     */
    public java.lang.String getIdentiteitAanleveraar()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IDENTITEITAANLEVERAAR$16, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "identiteitAanleveraar" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitsnummerType xgetIdentiteitAanleveraar()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitsnummerType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitsnummerType)get_store().find_element_user(IDENTITEITAANLEVERAAR$16, 0);
            return target;
        }
    }
    
    /**
     * True if has "identiteitAanleveraar" element
     */
    public boolean isSetIdentiteitAanleveraar()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(IDENTITEITAANLEVERAAR$16) != 0;
        }
    }
    
    /**
     * Sets the "identiteitAanleveraar" element
     */
    public void setIdentiteitAanleveraar(java.lang.String identiteitAanleveraar)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IDENTITEITAANLEVERAAR$16, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(IDENTITEITAANLEVERAAR$16);
            }
            target.setStringValue(identiteitAanleveraar);
        }
    }
    
    /**
     * Sets (as xml) the "identiteitAanleveraar" element
     */
    public void xsetIdentiteitAanleveraar(nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitsnummerType identiteitAanleveraar)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitsnummerType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitsnummerType)get_store().find_element_user(IDENTITEITAANLEVERAAR$16, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitsnummerType)get_store().add_element_user(IDENTITEITAANLEVERAAR$16);
            }
            target.set(identiteitAanleveraar);
        }
    }
    
    /**
     * Unsets the "identiteitAanleveraar" element
     */
    public void unsetIdentiteitAanleveraar()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(IDENTITEITAANLEVERAAR$16, 0);
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
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITONTVANGER$18, 0);
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
            return get_store().count_elements(IDENTITEITONTVANGER$18) != 0;
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
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITONTVANGER$18, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITONTVANGER$18);
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
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITONTVANGER$18);
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
            get_store().remove_element(IDENTITEITONTVANGER$18, 0);
        }
    }
    
    /**
     * Gets the "rolOntvanger" element
     */
    public java.lang.String getRolOntvanger()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLONTVANGER$20, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "rolOntvanger" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.RolType xgetRolOntvanger()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.RolType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLONTVANGER$20, 0);
            return target;
        }
    }
    
    /**
     * True if has "rolOntvanger" element
     */
    public boolean isSetRolOntvanger()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ROLONTVANGER$20) != 0;
        }
    }
    
    /**
     * Sets the "rolOntvanger" element
     */
    public void setRolOntvanger(java.lang.String rolOntvanger)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLONTVANGER$20, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ROLONTVANGER$20);
            }
            target.setStringValue(rolOntvanger);
        }
    }
    
    /**
     * Sets (as xml) the "rolOntvanger" element
     */
    public void xsetRolOntvanger(nl.logius.digipoort.koppelvlakservices._1_2.RolType rolOntvanger)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.RolType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLONTVANGER$20, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().add_element_user(ROLONTVANGER$20);
            }
            target.set(rolOntvanger);
        }
    }
    
    /**
     * Unsets the "rolOntvanger" element
     */
    public void unsetRolOntvanger()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ROLONTVANGER$20, 0);
        }
    }
    
    /**
     * Gets the "berichtInhoud" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType getBerichtInhoud()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().find_element_user(BERICHTINHOUD$22, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "berichtInhoud" element
     */
    public void setBerichtInhoud(nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType berichtInhoud)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().find_element_user(BERICHTINHOUD$22, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().add_element_user(BERICHTINHOUD$22);
            }
            target.set(berichtInhoud);
        }
    }
    
    /**
     * Appends and returns a new empty "berichtInhoud" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType addNewBerichtInhoud()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().add_element_user(BERICHTINHOUD$22);
            return target;
        }
    }
    
    /**
     * Gets the "berichtBijlagen" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType getBerichtBijlagen()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType)get_store().find_element_user(BERICHTBIJLAGEN$24, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "berichtBijlagen" element
     */
    public boolean isSetBerichtBijlagen()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BERICHTBIJLAGEN$24) != 0;
        }
    }
    
    /**
     * Sets the "berichtBijlagen" element
     */
    public void setBerichtBijlagen(nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType berichtBijlagen)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType)get_store().find_element_user(BERICHTBIJLAGEN$24, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType)get_store().add_element_user(BERICHTBIJLAGEN$24);
            }
            target.set(berichtBijlagen);
        }
    }
    
    /**
     * Appends and returns a new empty "berichtBijlagen" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType addNewBerichtBijlagen()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType)get_store().add_element_user(BERICHTBIJLAGEN$24);
            return target;
        }
    }
    
    /**
     * Unsets the "berichtBijlagen" element
     */
    public void unsetBerichtBijlagen()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BERICHTBIJLAGEN$24, 0);
        }
    }
    
    /**
     * Gets the "constateringenLijst" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType getConstateringenLijst()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType)get_store().find_element_user(CONSTATERINGENLIJST$26, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "constateringenLijst" element
     */
    public boolean isSetConstateringenLijst()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CONSTATERINGENLIJST$26) != 0;
        }
    }
    
    /**
     * Sets the "constateringenLijst" element
     */
    public void setConstateringenLijst(nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType constateringenLijst)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType)get_store().find_element_user(CONSTATERINGENLIJST$26, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType)get_store().add_element_user(CONSTATERINGENLIJST$26);
            }
            target.set(constateringenLijst);
        }
    }
    
    /**
     * Appends and returns a new empty "constateringenLijst" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType addNewConstateringenLijst()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType)get_store().add_element_user(CONSTATERINGENLIJST$26);
            return target;
        }
    }
    
    /**
     * Unsets the "constateringenLijst" element
     */
    public void unsetConstateringenLijst()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CONSTATERINGENLIJST$26, 0);
        }
    }
    
    /**
     * Gets the "additioneleElementenLijst" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType getAdditioneleElementenLijst()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType)get_store().find_element_user(ADDITIONELEELEMENTENLIJST$28, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "additioneleElementenLijst" element
     */
    public boolean isSetAdditioneleElementenLijst()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ADDITIONELEELEMENTENLIJST$28) != 0;
        }
    }
    
    /**
     * Sets the "additioneleElementenLijst" element
     */
    public void setAdditioneleElementenLijst(nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType additioneleElementenLijst)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType)get_store().find_element_user(ADDITIONELEELEMENTENLIJST$28, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType)get_store().add_element_user(ADDITIONELEELEMENTENLIJST$28);
            }
            target.set(additioneleElementenLijst);
        }
    }
    
    /**
     * Appends and returns a new empty "additioneleElementenLijst" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType addNewAdditioneleElementenLijst()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType)get_store().add_element_user(ADDITIONELEELEMENTENLIJST$28);
            return target;
        }
    }
    
    /**
     * Unsets the "additioneleElementenLijst" element
     */
    public void unsetAdditioneleElementenLijst()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ADDITIONELEELEMENTENLIJST$28, 0);
        }
    }
}
