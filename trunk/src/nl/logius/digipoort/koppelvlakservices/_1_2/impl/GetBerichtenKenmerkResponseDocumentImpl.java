/*
 * An XML document type.
 * Localname: getBerichtenKenmerkResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getBerichtenKenmerkResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetBerichtenKenmerkResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument
{
    
    public GetBerichtenKenmerkResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETBERICHTENKENMERKRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtenKenmerkResponse");
    
    
    /**
     * Gets the "getBerichtenKenmerkResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse getGetBerichtenKenmerkResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse)get_store().find_element_user(GETBERICHTENKENMERKRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getBerichtenKenmerkResponse" element
     */
    public void setGetBerichtenKenmerkResponse(nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse getBerichtenKenmerkResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse)get_store().find_element_user(GETBERICHTENKENMERKRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse)get_store().add_element_user(GETBERICHTENKENMERKRESPONSE$0);
            }
            target.set(getBerichtenKenmerkResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "getBerichtenKenmerkResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse addNewGetBerichtenKenmerkResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse)get_store().add_element_user(GETBERICHTENKENMERKRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML getBerichtenKenmerkResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetBerichtenKenmerkResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenKenmerkResponseDocument.GetBerichtenKenmerkResponse
    {
        
        public GetBerichtenKenmerkResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GETBERICHTENKENMERKRETURN$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtenKenmerkReturn");
        
        
        /**
         * Gets the "getBerichtenKenmerkReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat getGetBerichtenKenmerkReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().find_element_user(GETBERICHTENKENMERKRETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "getBerichtenKenmerkReturn" element
         */
        public void setGetBerichtenKenmerkReturn(nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat getBerichtenKenmerkReturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().find_element_user(GETBERICHTENKENMERKRETURN$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().add_element_user(GETBERICHTENKENMERKRETURN$0);
                }
                target.set(getBerichtenKenmerkReturn);
            }
        }
        
        /**
         * Appends and returns a new empty "getBerichtenKenmerkReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat addNewGetBerichtenKenmerkReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().add_element_user(GETBERICHTENKENMERKRETURN$0);
                return target;
            }
        }
    }
}
