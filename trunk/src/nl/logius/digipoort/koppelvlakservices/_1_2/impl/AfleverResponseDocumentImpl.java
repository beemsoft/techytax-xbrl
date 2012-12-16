/*
 * An XML document type.
 * Localname: afleverResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one afleverResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class AfleverResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument
{
    
    public AfleverResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AFLEVERRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "afleverResponse");
    
    
    /**
     * Gets the "afleverResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse getAfleverResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse)get_store().find_element_user(AFLEVERRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "afleverResponse" element
     */
    public void setAfleverResponse(nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse afleverResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse)get_store().find_element_user(AFLEVERRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse)get_store().add_element_user(AFLEVERRESPONSE$0);
            }
            target.set(afleverResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "afleverResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse addNewAfleverResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse)get_store().add_element_user(AFLEVERRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML afleverResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class AfleverResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse
    {
        
        public AfleverResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName KENMERK$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "kenmerk");
        private static final javax.xml.namespace.QName BERICHTSOORT$2 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtsoort");
        private static final javax.xml.namespace.QName BERICHTKENMERK$4 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtkenmerk");
        private static final javax.xml.namespace.QName TIJDSTEMPELAFGELEVERD$6 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelAfgeleverd");
        private static final javax.xml.namespace.QName STATUSCODE$8 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statuscode");
        private static final javax.xml.namespace.QName TIJDSTEMPELSTATUS$10 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelStatus");
        private static final javax.xml.namespace.QName STATUSOMSCHRIJVING$12 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statusomschrijving");
        private static final javax.xml.namespace.QName STATUSFOUTCODE$14 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statusFoutcode");
        private static final javax.xml.namespace.QName STATUSDETAILS$16 = 
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
         * True if has "berichtsoort" element
         */
        public boolean isSetBerichtsoort()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(BERICHTSOORT$2) != 0;
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
         * Unsets the "berichtsoort" element
         */
        public void unsetBerichtsoort()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(BERICHTSOORT$2, 0);
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
         * Gets the "tijdstempelAfgeleverd" element
         */
        public java.util.Calendar getTijdstempelAfgeleverd()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELAFGELEVERD$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getCalendarValue();
            }
        }
        
        /**
         * Gets (as xml) the "tijdstempelAfgeleverd" element
         */
        public org.apache.xmlbeans.XmlDateTime xgetTijdstempelAfgeleverd()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDateTime target = null;
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELAFGELEVERD$6, 0);
                return target;
            }
        }
        
        /**
         * Sets the "tijdstempelAfgeleverd" element
         */
        public void setTijdstempelAfgeleverd(java.util.Calendar tijdstempelAfgeleverd)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELAFGELEVERD$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELAFGELEVERD$6);
                }
                target.setCalendarValue(tijdstempelAfgeleverd);
            }
        }
        
        /**
         * Sets (as xml) the "tijdstempelAfgeleverd" element
         */
        public void xsetTijdstempelAfgeleverd(org.apache.xmlbeans.XmlDateTime tijdstempelAfgeleverd)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDateTime target = null;
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELAFGELEVERD$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELAFGELEVERD$6);
                }
                target.set(tijdstempelAfgeleverd);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSCODE$8, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().find_element_user(STATUSCODE$8, 0);
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
                return get_store().count_elements(STATUSCODE$8) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSCODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSCODE$8);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().find_element_user(STATUSCODE$8, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().add_element_user(STATUSCODE$8);
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
                get_store().remove_element(STATUSCODE$8, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELSTATUS$10, 0);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELSTATUS$10, 0);
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
                return get_store().count_elements(TIJDSTEMPELSTATUS$10) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIJDSTEMPELSTATUS$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIJDSTEMPELSTATUS$10);
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
                target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIJDSTEMPELSTATUS$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIJDSTEMPELSTATUS$10);
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
                get_store().remove_element(TIJDSTEMPELSTATUS$10, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSOMSCHRIJVING$12, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType)get_store().find_element_user(STATUSOMSCHRIJVING$12, 0);
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
                return get_store().count_elements(STATUSOMSCHRIJVING$12) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSOMSCHRIJVING$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSOMSCHRIJVING$12);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType)get_store().find_element_user(STATUSOMSCHRIJVING$12, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType)get_store().add_element_user(STATUSOMSCHRIJVING$12);
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
                get_store().remove_element(STATUSOMSCHRIJVING$12, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSFOUTCODE$14, 0);
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
                return get_store().count_elements(STATUSFOUTCODE$14) != 0;
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSFOUTCODE$14, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSFOUTCODE$14);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSFOUTCODE$14);
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
                get_store().remove_element(STATUSFOUTCODE$14, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETAILS$16, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().find_element_user(STATUSDETAILS$16, 0);
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
                return get_store().count_elements(STATUSDETAILS$16) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETAILS$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSDETAILS$16);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().find_element_user(STATUSDETAILS$16, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().add_element_user(STATUSDETAILS$16);
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
                get_store().remove_element(STATUSDETAILS$16, 0);
            }
        }
    }
}
