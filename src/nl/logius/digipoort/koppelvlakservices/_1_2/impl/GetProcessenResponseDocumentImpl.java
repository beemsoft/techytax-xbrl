/*
 * An XML document type.
 * Localname: getProcessenResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getProcessenResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetProcessenResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument
{
    
    public GetProcessenResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETPROCESSENRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getProcessenResponse");
    
    
    /**
     * Gets the "getProcessenResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse getGetProcessenResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse)get_store().find_element_user(GETPROCESSENRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getProcessenResponse" element
     */
    public void setGetProcessenResponse(nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse getProcessenResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse)get_store().find_element_user(GETPROCESSENRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse)get_store().add_element_user(GETPROCESSENRESPONSE$0);
            }
            target.set(getProcessenResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "getProcessenResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse addNewGetProcessenResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse)get_store().add_element_user(GETPROCESSENRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML getProcessenResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetProcessenResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetProcessenResponseDocument.GetProcessenResponse
    {
        
        public GetProcessenResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GETPROCESSENRETURN$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getProcessenReturn");
        
        
        /**
         * Gets the "getProcessenReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat getGetProcessenReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat)get_store().find_element_user(GETPROCESSENRETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "getProcessenReturn" element
         */
        public void setGetProcessenReturn(nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat getProcessenReturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat)get_store().find_element_user(GETPROCESSENRETURN$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat)get_store().add_element_user(GETPROCESSENRETURN$0);
                }
                target.set(getProcessenReturn);
            }
        }
        
        /**
         * Appends and returns a new empty "getProcessenReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat addNewGetProcessenReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat)get_store().add_element_user(GETPROCESSENRETURN$0);
                return target;
            }
        }
    }
}
