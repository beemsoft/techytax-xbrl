/*
 * An XML document type.
 * Localname: getNieuweStatussenProcesRequest
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getNieuweStatussenProcesRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetNieuweStatussenProcesRequestDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument
{
    
    public GetNieuweStatussenProcesRequestDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETNIEUWESTATUSSENPROCESREQUEST$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getNieuweStatussenProcesRequest");
    
    
    /**
     * Gets the "getNieuweStatussenProcesRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest getGetNieuweStatussenProcesRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest)get_store().find_element_user(GETNIEUWESTATUSSENPROCESREQUEST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getNieuweStatussenProcesRequest" element
     */
    public void setGetNieuweStatussenProcesRequest(nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest getNieuweStatussenProcesRequest)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest)get_store().find_element_user(GETNIEUWESTATUSSENPROCESREQUEST$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest)get_store().add_element_user(GETNIEUWESTATUSSENPROCESREQUEST$0);
            }
            target.set(getNieuweStatussenProcesRequest);
        }
    }
    
    /**
     * Appends and returns a new empty "getNieuweStatussenProcesRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest addNewGetNieuweStatussenProcesRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest)get_store().add_element_user(GETNIEUWESTATUSSENPROCESREQUEST$0);
            return target;
        }
    }
    /**
     * An XML getNieuweStatussenProcesRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetNieuweStatussenProcesRequestImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesRequestDocument.GetNieuweStatussenProcesRequest
    {
        
        public GetNieuweStatussenProcesRequestImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName KENMERK$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "kenmerk");
        private static final javax.xml.namespace.QName AUTORISATIEADRES$2 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "autorisatieAdres");
        private static final javax.xml.namespace.QName TIJDSTEMPELVANAF$4 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelVanaf");
        private static final javax.xml.namespace.QName TIJDSTEMPELTOT$6 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelTot");
        
        
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
        
        /**
         * Gets the "tijdstempelVanaf" element
         */
        public java.util.Calendar getTijdstempelVanaf()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELVANAF$4, 0);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELVANAF$4, 0);
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
                return get_store().count_elements(TIJDSTEMPELVANAF$4) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELVANAF$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELVANAF$4);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELVANAF$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELVANAF$4);
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
                get_store().remove_element(TIJDSTEMPELVANAF$4, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELTOT$6, 0);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELTOT$6, 0);
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
                return get_store().count_elements(TIJDSTEMPELTOT$6) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELTOT$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELTOT$6);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELTOT$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELTOT$6);
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
                get_store().remove_element(TIJDSTEMPELTOT$6, 0);
            }
        }
    }
}
