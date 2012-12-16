/*
 * An XML document type.
 * Localname: aanleverResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one aanleverResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class AanleverResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument
{
    
    public AanleverResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AANLEVERRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "aanleverResponse");
    
    
    /**
     * Gets the "aanleverResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse getAanleverResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse)get_store().find_element_user(AANLEVERRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "aanleverResponse" element
     */
    public void setAanleverResponse(nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse aanleverResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse)get_store().find_element_user(AANLEVERRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse)get_store().add_element_user(AANLEVERRESPONSE$0);
            }
            target.set(aanleverResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "aanleverResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse addNewAanleverResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse)get_store().add_element_user(AANLEVERRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML aanleverResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class AanleverResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse
    {
        
        public AanleverResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName KENMERK$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "kenmerk");
        private static final javax.xml.namespace.QName BERICHTSOORT$2 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtsoort");
        private static final javax.xml.namespace.QName AANLEVERKENMERK$4 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "aanleverkenmerk");
        private static final javax.xml.namespace.QName EERDERAANLEVERKENMERK$6 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "eerderAanleverkenmerk");
        private static final javax.xml.namespace.QName TIJDSTEMPELAANGELEVERD$8 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelAangeleverd");
        private static final javax.xml.namespace.QName IDENTITEITBELANGHEBBENDE$10 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitBelanghebbende");
        private static final javax.xml.namespace.QName ROLBELANGHEBBENDE$12 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "rolBelanghebbende");
        private static final javax.xml.namespace.QName IDENTITEITONTVANGER$14 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitOntvanger");
        private static final javax.xml.namespace.QName ROLONTVANGER$16 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "rolOntvanger");
        private static final javax.xml.namespace.QName AUTORISATIEADRES$18 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "autorisatieAdres");
        private static final javax.xml.namespace.QName STATUSCODE$20 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statuscode");
        private static final javax.xml.namespace.QName TIJDSTEMPELSTATUS$22 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelStatus");
        private static final javax.xml.namespace.QName STATUSOMSCHRIJVING$24 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statusomschrijving");
        private static final javax.xml.namespace.QName STATUSFOUTCODE$26 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statusFoutcode");
        private static final javax.xml.namespace.QName STATUSDETAILS$28 = 
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
         * Gets the "aanleverkenmerk" element
         */
        public java.lang.String getAanleverkenmerk()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AANLEVERKENMERK$4, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().find_element_user(AANLEVERKENMERK$4, 0);
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
                return get_store().count_elements(AANLEVERKENMERK$4) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AANLEVERKENMERK$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AANLEVERKENMERK$4);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().find_element_user(AANLEVERKENMERK$4, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().add_element_user(AANLEVERKENMERK$4);
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
                get_store().remove_element(AANLEVERKENMERK$4, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EERDERAANLEVERKENMERK$6, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().find_element_user(EERDERAANLEVERKENMERK$6, 0);
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
                return get_store().count_elements(EERDERAANLEVERKENMERK$6) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EERDERAANLEVERKENMERK$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EERDERAANLEVERKENMERK$6);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().find_element_user(EERDERAANLEVERKENMERK$6, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType)get_store().add_element_user(EERDERAANLEVERKENMERK$6);
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
                get_store().remove_element(EERDERAANLEVERKENMERK$6, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELAANGELEVERD$8, 0);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELAANGELEVERD$8, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELAANGELEVERD$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELAANGELEVERD$8);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELAANGELEVERD$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELAANGELEVERD$8);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$10, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$10, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$10);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$10);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLBELANGHEBBENDE$12, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLBELANGHEBBENDE$12, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLBELANGHEBBENDE$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ROLBELANGHEBBENDE$12);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLBELANGHEBBENDE$12, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().add_element_user(ROLBELANGHEBBENDE$12);
                }
                target.set(rolBelanghebbende);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITONTVANGER$14, 0);
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
                return get_store().count_elements(IDENTITEITONTVANGER$14) != 0;
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITONTVANGER$14, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITONTVANGER$14);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITONTVANGER$14);
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
                get_store().remove_element(IDENTITEITONTVANGER$14, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLONTVANGER$16, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLONTVANGER$16, 0);
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
                return get_store().count_elements(ROLONTVANGER$16) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLONTVANGER$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ROLONTVANGER$16);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLONTVANGER$16, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().add_element_user(ROLONTVANGER$16);
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
                get_store().remove_element(ROLONTVANGER$16, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTORISATIEADRES$18, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().find_element_user(AUTORISATIEADRES$18, 0);
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
                return get_store().count_elements(AUTORISATIEADRES$18) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTORISATIEADRES$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AUTORISATIEADRES$18);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().find_element_user(AUTORISATIEADRES$18, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().add_element_user(AUTORISATIEADRES$18);
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
                get_store().remove_element(AUTORISATIEADRES$18, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSCODE$20, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().find_element_user(STATUSCODE$20, 0);
                return target;
            }
        }
        
        /**
         * True if has "statuscode" element
         */
        public boolean isSetStatuscode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(STATUSCODE$20) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSCODE$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSCODE$20);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().find_element_user(STATUSCODE$20, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().add_element_user(STATUSCODE$20);
                }
                target.set(statuscode);
            }
        }
        
        /**
         * Unsets the "statuscode" element
         */
        public void unsetStatuscode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(STATUSCODE$20, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELSTATUS$22, 0);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELSTATUS$22, 0);
                return target;
            }
        }
        
        /**
         * True if has "tijdstempelStatus" element
         */
        public boolean isSetTijdstempelStatus()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TIJDSTEMPELSTATUS$22) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELSTATUS$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELSTATUS$22);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELSTATUS$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELSTATUS$22);
                }
                target.set(tijdstempelStatus);
            }
        }
        
        /**
         * Unsets the "tijdstempelStatus" element
         */
        public void unsetTijdstempelStatus()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TIJDSTEMPELSTATUS$22, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSOMSCHRIJVING$24, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType)get_store().find_element_user(STATUSOMSCHRIJVING$24, 0);
                return target;
            }
        }
        
        /**
         * True if has "statusomschrijving" element
         */
        public boolean isSetStatusomschrijving()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(STATUSOMSCHRIJVING$24) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSOMSCHRIJVING$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSOMSCHRIJVING$24);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType)get_store().find_element_user(STATUSOMSCHRIJVING$24, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType)get_store().add_element_user(STATUSOMSCHRIJVING$24);
                }
                target.set(statusomschrijving);
            }
        }
        
        /**
         * Unsets the "statusomschrijving" element
         */
        public void unsetStatusomschrijving()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(STATUSOMSCHRIJVING$24, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSFOUTCODE$26, 0);
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
                return get_store().count_elements(STATUSFOUTCODE$26) != 0;
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSFOUTCODE$26, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSFOUTCODE$26);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSFOUTCODE$26);
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
                get_store().remove_element(STATUSFOUTCODE$26, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETAILS$28, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().find_element_user(STATUSDETAILS$28, 0);
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
                return get_store().count_elements(STATUSDETAILS$28) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETAILS$28, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSDETAILS$28);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().find_element_user(STATUSDETAILS$28, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().add_element_user(STATUSDETAILS$28);
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
                get_store().remove_element(STATUSDETAILS$28, 0);
            }
        }
    }
}
