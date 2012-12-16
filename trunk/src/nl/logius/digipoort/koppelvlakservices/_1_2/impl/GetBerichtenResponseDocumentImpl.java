/*
 * An XML document type.
 * Localname: getBerichtenResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getBerichtenResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetBerichtenResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument
{
    
    public GetBerichtenResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETBERICHTENRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtenResponse");
    
    
    /**
     * Gets the "getBerichtenResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse getGetBerichtenResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse)get_store().find_element_user(GETBERICHTENRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getBerichtenResponse" element
     */
    public void setGetBerichtenResponse(nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse getBerichtenResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse)get_store().find_element_user(GETBERICHTENRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse)get_store().add_element_user(GETBERICHTENRESPONSE$0);
            }
            target.set(getBerichtenResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "getBerichtenResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse addNewGetBerichtenResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse)get_store().add_element_user(GETBERICHTENRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML getBerichtenResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetBerichtenResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenResponseDocument.GetBerichtenResponse
    {
        
        public GetBerichtenResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GETBERICHTENRETURN$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtenReturn");
        
        
        /**
         * Gets the "getBerichtenReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat getGetBerichtenReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().find_element_user(GETBERICHTENRETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "getBerichtenReturn" element
         */
        public void setGetBerichtenReturn(nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat getBerichtenReturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().find_element_user(GETBERICHTENRETURN$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().add_element_user(GETBERICHTENRETURN$0);
                }
                target.set(getBerichtenReturn);
            }
        }
        
        /**
         * Appends and returns a new empty "getBerichtenReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat addNewGetBerichtenReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().add_element_user(GETBERICHTENRETURN$0);
                return target;
            }
        }
    }
}
