/*
 * An XML document type.
 * Localname: getNieuweStatussenProcesResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getNieuweStatussenProcesResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetNieuweStatussenProcesResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument
{
    
    public GetNieuweStatussenProcesResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETNIEUWESTATUSSENPROCESRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getNieuweStatussenProcesResponse");
    
    
    /**
     * Gets the "getNieuweStatussenProcesResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse getGetNieuweStatussenProcesResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse)get_store().find_element_user(GETNIEUWESTATUSSENPROCESRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getNieuweStatussenProcesResponse" element
     */
    public void setGetNieuweStatussenProcesResponse(nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse getNieuweStatussenProcesResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse)get_store().find_element_user(GETNIEUWESTATUSSENPROCESRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse)get_store().add_element_user(GETNIEUWESTATUSSENPROCESRESPONSE$0);
            }
            target.set(getNieuweStatussenProcesResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "getNieuweStatussenProcesResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse addNewGetNieuweStatussenProcesResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse)get_store().add_element_user(GETNIEUWESTATUSSENPROCESRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML getNieuweStatussenProcesResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetNieuweStatussenProcesResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweStatussenProcesResponseDocument.GetNieuweStatussenProcesResponse
    {
        
        public GetNieuweStatussenProcesResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GETNIEUWESTATUSSENPROCESRETURN$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getNieuweStatussenProcesReturn");
        
        
        /**
         * Gets the "getNieuweStatussenProcesReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat getGetNieuweStatussenProcesReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat)get_store().find_element_user(GETNIEUWESTATUSSENPROCESRETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "getNieuweStatussenProcesReturn" element
         */
        public void setGetNieuweStatussenProcesReturn(nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat getNieuweStatussenProcesReturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat)get_store().find_element_user(GETNIEUWESTATUSSENPROCESRETURN$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat)get_store().add_element_user(GETNIEUWESTATUSSENPROCESRETURN$0);
                }
                target.set(getNieuweStatussenProcesReturn);
            }
        }
        
        /**
         * Appends and returns a new empty "getNieuweStatussenProcesReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat addNewGetNieuweStatussenProcesReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat)get_store().add_element_user(GETNIEUWESTATUSSENPROCESRETURN$0);
                return target;
            }
        }
    }
}
