/*
 * An XML document type.
 * Localname: getProcessenRequest
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getProcessenRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetProcessenRequestDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument
{
    
    public GetProcessenRequestDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETPROCESSENREQUEST$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getProcessenRequest");
    
    
    /**
     * Gets the "getProcessenRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest getGetProcessenRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest)get_store().find_element_user(GETPROCESSENREQUEST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getProcessenRequest" element
     */
    public void setGetProcessenRequest(nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest getProcessenRequest)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest)get_store().find_element_user(GETPROCESSENREQUEST$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest)get_store().add_element_user(GETPROCESSENREQUEST$0);
            }
            target.set(getProcessenRequest);
        }
    }
    
    /**
     * Appends and returns a new empty "getProcessenRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest addNewGetProcessenRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest)get_store().add_element_user(GETPROCESSENREQUEST$0);
            return target;
        }
    }
    /**
     * An XML getProcessenRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetProcessenRequestImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenRequestDocument.GetProcessenRequest
    {
        
        public GetProcessenRequestImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName BERICHTSOORT$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtsoort");
        private static final javax.xml.namespace.QName IDENTITEITBELANGHEBBENDE$2 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitBelanghebbende");
        private static final javax.xml.namespace.QName AUTORISATIEADRES$4 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "autorisatieAdres");
        
        
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
    }
}
