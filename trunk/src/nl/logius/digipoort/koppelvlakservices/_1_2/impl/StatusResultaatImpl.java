/*
 * XML Type:  StatusResultaat
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML StatusResultaat(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class StatusResultaatImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat
{
    
    public StatusResultaatImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KENMERK$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "kenmerk");
    private static final javax.xml.namespace.QName IDENTITEITBELANGHEBBENDE$2 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitBelanghebbende");
    private static final javax.xml.namespace.QName STATUSCODE$4 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statuscode");
    private static final javax.xml.namespace.QName TIJDSTEMPELSTATUS$6 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelStatus");
    private static final javax.xml.namespace.QName STATUSOMSCHRIJVING$8 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statusomschrijving");
    private static final javax.xml.namespace.QName STATUSFOUTCODE$10 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statusFoutcode");
    private static final javax.xml.namespace.QName STATUSDETAILS$12 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statusdetails");
    
    
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
     * Gets the "identiteitBelanghebbende" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType getIdentiteitBelanghebbende()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$2, 0);
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
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$2, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$2);
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
            target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$2);
            return target;
        }
    }
    
    /**
     * Gets the "statuscode" element
     */
    public java.lang.String getStatuscode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSCODE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "statuscode" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType xgetStatuscode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().find_element_user(STATUSCODE$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "statuscode" element
     */
    public void setStatuscode(java.lang.String statuscode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSCODE$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSCODE$4);
            }
            target.setStringValue(statuscode);
        }
    }
    
    /**
     * Sets (as xml) the "statuscode" element
     */
    public void xsetStatuscode(nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType statuscode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().find_element_user(STATUSCODE$4, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().add_element_user(STATUSCODE$4);
            }
            target.set(statuscode);
        }
    }
    
    /**
     * Gets the "tijdstempelStatus" element
     */
    public java.util.Calendar getTijdstempelStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELSTATUS$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "tijdstempelStatus" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetTijdstempelStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELSTATUS$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "tijdstempelStatus" element
     */
    public void setTijdstempelStatus(java.util.Calendar tijdstempelStatus)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELSTATUS$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELSTATUS$6);
            }
            target.setCalendarValue(tijdstempelStatus);
        }
    }
    
    /**
     * Sets (as xml) the "tijdstempelStatus" element
     */
    public void xsetTijdstempelStatus(org.apache.xmlbeans.XmlDateTime tijdstempelStatus)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELSTATUS$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELSTATUS$6);
            }
            target.set(tijdstempelStatus);
        }
    }
    
    /**
     * Gets the "statusomschrijving" element
     */
    public java.lang.String getStatusomschrijving()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSOMSCHRIJVING$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "statusomschrijving" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType xgetStatusomschrijving()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType)get_store().find_element_user(STATUSOMSCHRIJVING$8, 0);
            return target;
        }
    }
    
    /**
     * Sets the "statusomschrijving" element
     */
    public void setStatusomschrijving(java.lang.String statusomschrijving)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSOMSCHRIJVING$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSOMSCHRIJVING$8);
            }
            target.setStringValue(statusomschrijving);
        }
    }
    
    /**
     * Sets (as xml) the "statusomschrijving" element
     */
    public void xsetStatusomschrijving(nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType statusomschrijving)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType)get_store().find_element_user(STATUSOMSCHRIJVING$8, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType)get_store().add_element_user(STATUSOMSCHRIJVING$8);
            }
            target.set(statusomschrijving);
        }
    }
    
    /**
     * Gets the "statusFoutcode" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType getStatusFoutcode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSFOUTCODE$10, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "statusFoutcode" element
     */
    public boolean isSetStatusFoutcode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(STATUSFOUTCODE$10) != 0;
        }
    }
    
    /**
     * Sets the "statusFoutcode" element
     */
    public void setStatusFoutcode(nl.logius.digipoort.koppelvlakservices._1_2.FoutType statusFoutcode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSFOUTCODE$10, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSFOUTCODE$10);
            }
            target.set(statusFoutcode);
        }
    }
    
    /**
     * Appends and returns a new empty "statusFoutcode" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.FoutType addNewStatusFoutcode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSFOUTCODE$10);
            return target;
        }
    }
    
    /**
     * Unsets the "statusFoutcode" element
     */
    public void unsetStatusFoutcode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(STATUSFOUTCODE$10, 0);
        }
    }
    
    /**
     * Gets the "statusdetails" element
     */
    public java.lang.String getStatusdetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETAILS$12, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "statusdetails" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType xgetStatusdetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().find_element_user(STATUSDETAILS$12, 0);
            return target;
        }
    }
    
    /**
     * True if has "statusdetails" element
     */
    public boolean isSetStatusdetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(STATUSDETAILS$12) != 0;
        }
    }
    
    /**
     * Sets the "statusdetails" element
     */
    public void setStatusdetails(java.lang.String statusdetails)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETAILS$12, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSDETAILS$12);
            }
            target.setStringValue(statusdetails);
        }
    }
    
    /**
     * Sets (as xml) the "statusdetails" element
     */
    public void xsetStatusdetails(nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType statusdetails)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().find_element_user(STATUSDETAILS$12, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().add_element_user(STATUSDETAILS$12);
            }
            target.set(statusdetails);
        }
    }
    
    /**
     * Unsets the "statusdetails" element
     */
    public void unsetStatusdetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(STATUSDETAILS$12, 0);
        }
    }
}
