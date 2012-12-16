/*
 * An XML document type.
 * Localname: zetStatusResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one zetStatusResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class ZetStatusResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument
{
    
    public ZetStatusResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ZETSTATUSRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "zetStatusResponse");
    
    
    /**
     * Gets the "zetStatusResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse getZetStatusResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse)get_store().find_element_user(ZETSTATUSRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "zetStatusResponse" element
     */
    public void setZetStatusResponse(nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse zetStatusResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse)get_store().find_element_user(ZETSTATUSRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse)get_store().add_element_user(ZETSTATUSRESPONSE$0);
            }
            target.set(zetStatusResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "zetStatusResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse addNewZetStatusResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse)get_store().add_element_user(ZETSTATUSRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML zetStatusResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class ZetStatusResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ZetStatusResponseDocument.ZetStatusResponse
    {
        
        public ZetStatusResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName KENMERK$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "kenmerk");
        private static final javax.xml.namespace.QName STATUSCODE$2 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "statuscode");
        
        
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
         * Gets the "statuscode" element
         */
        public java.lang.String getStatuscode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSCODE$2, 0);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().find_element_user(STATUSCODE$2, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSCODE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSCODE$2);
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
                target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().find_element_user(STATUSCODE$2, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType)get_store().add_element_user(STATUSCODE$2);
                }
                target.set(statuscode);
            }
        }
    }
}
