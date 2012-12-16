/*
 * An XML document type.
 * Localname: getBerichtenRequest
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getBerichtenRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetBerichtenRequestDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument
{
    
    public GetBerichtenRequestDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETBERICHTENREQUEST$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtenRequest");
    
    
    /**
     * Gets the "getBerichtenRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest getGetBerichtenRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest)get_store().find_element_user(GETBERICHTENREQUEST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getBerichtenRequest" element
     */
    public void setGetBerichtenRequest(nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest getBerichtenRequest)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest)get_store().find_element_user(GETBERICHTENREQUEST$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest)get_store().add_element_user(GETBERICHTENREQUEST$0);
            }
            target.set(getBerichtenRequest);
        }
    }
    
    /**
     * Appends and returns a new empty "getBerichtenRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest addNewGetBerichtenRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest)get_store().add_element_user(GETBERICHTENREQUEST$0);
            return target;
        }
    }
    /**
     * An XML getBerichtenRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetBerichtenRequestImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenRequestDocument.GetBerichtenRequest
    {
        
        public GetBerichtenRequestImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName BERICHTSOORT$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtsoort");
        private static final javax.xml.namespace.QName IDENTITEITBELANGHEBBENDE$2 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitBelanghebbende");
        private static final javax.xml.namespace.QName AUTORISATIEADRES$4 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "autorisatieAdres");
        private static final javax.xml.namespace.QName TIJDSTEMPELVANAF$6 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelVanaf");
        private static final javax.xml.namespace.QName TIJDSTEMPELTOT$8 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelTot");
        
        
        /**
         * Gets the "berichtsoort" element
         */
        public java.lang.String getBerichtsoort()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTSOORT$0, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().find_element_user(BERICHTSOORT$0, 0);
                return target;
            }
        }
        
        /**
         * True if has "berichtsoort" element
         */
        public boolean isSetBerichtsoort()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(BERICHTSOORT$0) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTSOORT$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BERICHTSOORT$0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().find_element_user(BERICHTSOORT$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().add_element_user(BERICHTSOORT$0);
                }
                target.set(berichtsoort);
            }
        }
        
        /**
         * Unsets the "berichtsoort" element
         */
        public void unsetBerichtsoort()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(BERICHTSOORT$0, 0);
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
         * Gets the "autorisatieAdres" element
         */
        public java.lang.String getAutorisatieAdres()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTORISATIEADRES$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "autorisatieAdres" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType xgetAutorisatieAdres()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().find_element_user(AUTORISATIEADRES$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "autorisatieAdres" element
         */
        public boolean isSetAutorisatieAdres()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(AUTORISATIEADRES$4) != 0;
            }
        }
        
        /**
         * Sets the "autorisatieAdres" element
         */
        public void setAutorisatieAdres(java.lang.String autorisatieAdres)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTORISATIEADRES$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AUTORISATIEADRES$4);
                }
                target.setStringValue(autorisatieAdres);
            }
        }
        
        /**
         * Sets (as xml) the "autorisatieAdres" element
         */
        public void xsetAutorisatieAdres(nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType autorisatieAdres)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().find_element_user(AUTORISATIEADRES$4, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().add_element_user(AUTORISATIEADRES$4);
                }
                target.set(autorisatieAdres);
            }
        }
        
        /**
         * Unsets the "autorisatieAdres" element
         */
        public void unsetAutorisatieAdres()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(AUTORISATIEADRES$4, 0);
            }
        }
        
        /**
         * Gets the "tijdstempelVanaf" element
         */
        public java.util.Calendar getTijdstempelVanaf()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELVANAF$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getCalendarValue();
            }
        }
        
        /**
         * Gets (as xml) the "tijdstempelVanaf" element
         */
        public org.apache.xmlbeans.XmlDateTime xgetTijdstempelVanaf()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDateTime target = null;
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELVANAF$6, 0);
                return target;
            }
        }
        
        /**
         * True if has "tijdstempelVanaf" element
         */
        public boolean isSetTijdstempelVanaf()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TIJDSTEMPELVANAF$6) != 0;
            }
        }
        
        /**
         * Sets the "tijdstempelVanaf" element
         */
        public void setTijdstempelVanaf(java.util.Calendar tijdstempelVanaf)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELVANAF$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELVANAF$6);
                }
                target.setCalendarValue(tijdstempelVanaf);
            }
        }
        
        /**
         * Sets (as xml) the "tijdstempelVanaf" element
         */
        public void xsetTijdstempelVanaf(org.apache.xmlbeans.XmlDateTime tijdstempelVanaf)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDateTime target = null;
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELVANAF$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELVANAF$6);
                }
                target.set(tijdstempelVanaf);
            }
        }
        
        /**
         * Unsets the "tijdstempelVanaf" element
         */
        public void unsetTijdstempelVanaf()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TIJDSTEMPELVANAF$6, 0);
            }
        }
        
        /**
         * Gets the "tijdstempelTot" element
         */
        public java.util.Calendar getTijdstempelTot()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELTOT$8, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getCalendarValue();
            }
        }
        
        /**
         * Gets (as xml) the "tijdstempelTot" element
         */
        public org.apache.xmlbeans.XmlDateTime xgetTijdstempelTot()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDateTime target = null;
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELTOT$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "tijdstempelTot" element
         */
        public boolean isSetTijdstempelTot()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TIJDSTEMPELTOT$8) != 0;
            }
        }
        
        /**
         * Sets the "tijdstempelTot" element
         */
        public void setTijdstempelTot(java.util.Calendar tijdstempelTot)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELTOT$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELTOT$8);
                }
                target.setCalendarValue(tijdstempelTot);
            }
        }
        
        /**
         * Sets (as xml) the "tijdstempelTot" element
         */
        public void xsetTijdstempelTot(org.apache.xmlbeans.XmlDateTime tijdstempelTot)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDateTime target = null;
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELTOT$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELTOT$8);
                }
                target.set(tijdstempelTot);
            }
        }
        
        /**
         * Unsets the "tijdstempelTot" element
         */
        public void unsetTijdstempelTot()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TIJDSTEMPELTOT$8, 0);
            }
        }
    }
}
