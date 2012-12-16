/*
 * An XML document type.
 * Localname: zetStatusRequest
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one zetStatusRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class ZetStatusRequestDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument
{
    
    public ZetStatusRequestDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ZETSTATUSREQUEST$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "zetStatusRequest");
    
    
    /**
     * Gets the "zetStatusRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest getZetStatusRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest)get_store().find_element_user(ZETSTATUSREQUEST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "zetStatusRequest" element
     */
    public void setZetStatusRequest(nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest zetStatusRequest)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest)get_store().find_element_user(ZETSTATUSREQUEST$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest)get_store().add_element_user(ZETSTATUSREQUEST$0);
            }
            target.set(zetStatusRequest);
        }
    }
    
    /**
     * Appends and returns a new empty "zetStatusRequest" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest addNewZetStatusRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest)get_store().add_element_user(ZETSTATUSREQUEST$0);
            return target;
        }
    }
    /**
     * An XML zetStatusRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class ZetStatusRequestImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusRequestDocument.ZetStatusRequest
    {
        
        public ZetStatusRequestImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName KENMERK$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "kenmerk");
        private static final javax.xml.namespace.QName BERICHTKENMERK$2 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtkenmerk");
        private static final javax.xml.namespace.QName STATUSCODE$4 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statuscode");
        private static final javax.xml.namespace.QName TIJDSTEMPELSTATUS$6 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "tijdstempelStatus");
        private static final javax.xml.namespace.QName STATUSFOUTCODE$8 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statusFoutcode");
        private static final javax.xml.namespace.QName STATUSDETAILS$10 = 
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
         * Gets the "berichtkenmerk" element
         */
        public java.lang.String getBerichtkenmerk()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTKENMERK$2, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType)get_store().find_element_user(BERICHTKENMERK$2, 0);
                return target;
            }
        }
        
        /**
         * True if has "berichtkenmerk" element
         */
        public boolean isSetBerichtkenmerk()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(BERICHTKENMERK$2) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTKENMERK$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BERICHTKENMERK$2);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType)get_store().find_element_user(BERICHTKENMERK$2, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType)get_store().add_element_user(BERICHTKENMERK$2);
                }
                target.set(berichtkenmerk);
            }
        }
        
        /**
         * Unsets the "berichtkenmerk" element
         */
        public void unsetBerichtkenmerk()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(BERICHTKENMERK$2, 0);
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
         * Gets the "statusFoutcode" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.FoutType getStatusFoutcode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.FoutType target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSFOUTCODE$8, 0);
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
                return get_store().count_elements(STATUSFOUTCODE$8) != 0;
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().find_element_user(STATUSFOUTCODE$8, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSFOUTCODE$8);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.FoutType)get_store().add_element_user(STATUSFOUTCODE$8);
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
                get_store().remove_element(STATUSFOUTCODE$8, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETAILS$10, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().find_element_user(STATUSDETAILS$10, 0);
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
                return get_store().count_elements(STATUSDETAILS$10) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETAILS$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSDETAILS$10);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().find_element_user(STATUSDETAILS$10, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType)get_store().add_element_user(STATUSDETAILS$10);
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
                get_store().remove_element(STATUSDETAILS$10, 0);
            }
        }
    }
}
