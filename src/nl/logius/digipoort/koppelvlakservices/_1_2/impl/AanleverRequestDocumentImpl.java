/*
 * An XML document type.
 * Localname: aanleverRequest
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one aanleverRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class AanleverRequestDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument
{
    
    public AanleverRequestDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AANLEVERREQUEST$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "aanleverRequest");
    
    
    /**
     * Gets the "aanleverRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest getAanleverRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest)get_store().find_element_user(AANLEVERREQUEST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "aanleverRequest" element
     */
    public void setAanleverRequest(nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest aanleverRequest)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest)get_store().find_element_user(AANLEVERREQUEST$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest)get_store().add_element_user(AANLEVERREQUEST$0);
            }
            target.set(aanleverRequest);
        }
    }
    
    /**
     * Appends and returns a new empty "aanleverRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest addNewAanleverRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest)get_store().add_element_user(AANLEVERREQUEST$0);
            return target;
        }
    }
    /**
     * An XML aanleverRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class AanleverRequestImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest
    {
        
        public AanleverRequestImpl(org.apache.xmlbeans.SchemaType sType)
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
        private static final javax.xml.namespace.QName IDENTITEITBELANGHEBBENDE$8 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitBelanghebbende");
        private static final javax.xml.namespace.QName ROLBELANGHEBBENDE$10 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "rolBelanghebbende");
        private static final javax.xml.namespace.QName IDENTITEITONTVANGER$12 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitOntvanger");
        private static final javax.xml.namespace.QName ROLONTVANGER$14 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "rolOntvanger");
        private static final javax.xml.namespace.QName BERICHTINHOUD$16 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtInhoud");
        private static final javax.xml.namespace.QName BERICHTBIJLAGEN$18 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtBijlagen");
        private static final javax.xml.namespace.QName AUTORISATIEADRES$20 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "autorisatieAdres");
        
        
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
         * True if has "kenmerk" element
         */
        public boolean isSetKenmerk()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(KENMERK$0) != 0;
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
         * Unsets the "kenmerk" element
         */
        public void unsetKenmerk()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(KENMERK$0, 0);
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
         * Gets the "identiteitBelanghebbende" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType getIdentiteitBelanghebbende()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$8, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$8, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$8);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$8);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLBELANGHEBBENDE$10, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLBELANGHEBBENDE$10, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLBELANGHEBBENDE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ROLBELANGHEBBENDE$10);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLBELANGHEBBENDE$10, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().add_element_user(ROLBELANGHEBBENDE$10);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITONTVANGER$12, 0);
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
                return get_store().count_elements(IDENTITEITONTVANGER$12) != 0;
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITONTVANGER$12, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITONTVANGER$12);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITONTVANGER$12);
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
                get_store().remove_element(IDENTITEITONTVANGER$12, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLONTVANGER$14, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLONTVANGER$14, 0);
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
                return get_store().count_elements(ROLONTVANGER$14) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROLONTVANGER$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ROLONTVANGER$14);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().find_element_user(ROLONTVANGER$14, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.RolType)get_store().add_element_user(ROLONTVANGER$14);
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
                get_store().remove_element(ROLONTVANGER$14, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().find_element_user(BERICHTINHOUD$16, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().find_element_user(BERICHTINHOUD$16, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().add_element_user(BERICHTINHOUD$16);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().add_element_user(BERICHTINHOUD$16);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType)get_store().find_element_user(BERICHTBIJLAGEN$18, 0);
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
                return get_store().count_elements(BERICHTBIJLAGEN$18) != 0;
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType)get_store().find_element_user(BERICHTBIJLAGEN$18, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType)get_store().add_element_user(BERICHTBIJLAGEN$18);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType)get_store().add_element_user(BERICHTBIJLAGEN$18);
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
                get_store().remove_element(BERICHTBIJLAGEN$18, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTORISATIEADRES$20, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().find_element_user(AUTORISATIEADRES$20, 0);
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
                return get_store().count_elements(AUTORISATIEADRES$20) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTORISATIEADRES$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AUTORISATIEADRES$20);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().find_element_user(AUTORISATIEADRES$20, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().add_element_user(AUTORISATIEADRES$20);
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
                get_store().remove_element(AUTORISATIEADRES$20, 0);
            }
        }
    }
}
