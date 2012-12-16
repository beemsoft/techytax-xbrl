/*
 * An XML document type.
 * Localname: getBerichtsoortenRequest
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getBerichtsoortenRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetBerichtsoortenRequestDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument
{
    
    public GetBerichtsoortenRequestDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETBERICHTSOORTENREQUEST$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtsoortenRequest");
    
    
    /**
     * Gets the "getBerichtsoortenRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest getGetBerichtsoortenRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest)get_store().find_element_user(GETBERICHTSOORTENREQUEST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getBerichtsoortenRequest" element
     */
    public void setGetBerichtsoortenRequest(nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest getBerichtsoortenRequest)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest)get_store().find_element_user(GETBERICHTSOORTENREQUEST$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest)get_store().add_element_user(GETBERICHTSOORTENREQUEST$0);
            }
            target.set(getBerichtsoortenRequest);
        }
    }
    
    /**
     * Appends and returns a new empty "getBerichtsoortenRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest addNewGetBerichtsoortenRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest)get_store().add_element_user(GETBERICHTSOORTENREQUEST$0);
            return target;
        }
    }
    /**
     * An XML getBerichtsoortenRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetBerichtsoortenRequestImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenRequestDocument.GetBerichtsoortenRequest
    {
        
        public GetBerichtsoortenRequestImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName IDENTITEITBELANGHEBBENDE$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "identiteitBelanghebbende");
        private static final javax.xml.namespace.QName AUTORISATIEADRES$2 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "autorisatieAdres");
        
        
        /**
         * Gets the "identiteitBelanghebbende" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType getIdentiteitBelanghebbende()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$0, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().find_element_user(IDENTITEITBELANGHEBBENDE$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType)get_store().add_element_user(IDENTITEITBELANGHEBBENDE$0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTORISATIEADRES$2, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().find_element_user(AUTORISATIEADRES$2, 0);
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
                return get_store().count_elements(AUTORISATIEADRES$2) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTORISATIEADRES$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AUTORISATIEADRES$2);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().find_element_user(AUTORISATIEADRES$2, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType)get_store().add_element_user(AUTORISATIEADRES$2);
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
                get_store().remove_element(AUTORISATIEADRES$2, 0);
            }
        }
    }
}
