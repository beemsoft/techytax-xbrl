/*
 * An XML document type.
 * Localname: getStatussenProcesResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getStatussenProcesResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetStatussenProcesResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument
{
    
    public GetStatussenProcesResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETSTATUSSENPROCESRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getStatussenProcesResponse");
    
    
    /**
     * Gets the "getStatussenProcesResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse getGetStatussenProcesResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse)get_store().find_element_user(GETSTATUSSENPROCESRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getStatussenProcesResponse" element
     */
    public void setGetStatussenProcesResponse(nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse getStatussenProcesResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse)get_store().find_element_user(GETSTATUSSENPROCESRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse)get_store().add_element_user(GETSTATUSSENPROCESRESPONSE$0);
            }
            target.set(getStatussenProcesResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "getStatussenProcesResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse addNewGetStatussenProcesResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse)get_store().add_element_user(GETSTATUSSENPROCESRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML getStatussenProcesResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetStatussenProcesResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetStatussenProcesResponseDocument.GetStatussenProcesResponse
    {
        
        public GetStatussenProcesResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GETSTATUSSENPROCESRETURN$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getStatussenProcesReturn");
        
        
        /**
         * Gets the "getStatussenProcesReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat getGetStatussenProcesReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat)get_store().find_element_user(GETSTATUSSENPROCESRETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "getStatussenProcesReturn" element
         */
        public void setGetStatussenProcesReturn(nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat getStatussenProcesReturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat)get_store().find_element_user(GETSTATUSSENPROCESRETURN$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat)get_store().add_element_user(GETSTATUSSENPROCESRETURN$0);
                }
                target.set(getStatussenProcesReturn);
            }
        }
        
        /**
         * Appends and returns a new empty "getStatussenProcesReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat addNewGetStatussenProcesReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat)get_store().add_element_user(GETSTATUSSENPROCESRETURN$0);
                return target;
            }
        }
    }
}
