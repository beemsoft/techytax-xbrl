/*
 * An XML document type.
 * Localname: getBerichtsoortenResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getBerichtsoortenResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetBerichtsoortenResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument
{
    
    public GetBerichtsoortenResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETBERICHTSOORTENRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtsoortenResponse");
    
    
    /**
     * Gets the "getBerichtsoortenResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse getGetBerichtsoortenResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse)get_store().find_element_user(GETBERICHTSOORTENRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getBerichtsoortenResponse" element
     */
    public void setGetBerichtsoortenResponse(nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse getBerichtsoortenResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse)get_store().find_element_user(GETBERICHTSOORTENRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse)get_store().add_element_user(GETBERICHTSOORTENRESPONSE$0);
            }
            target.set(getBerichtsoortenResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "getBerichtsoortenResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse addNewGetBerichtsoortenResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse)get_store().add_element_user(GETBERICHTSOORTENRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML getBerichtsoortenResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetBerichtsoortenResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtsoortenResponseDocument.GetBerichtsoortenResponse
    {
        
        public GetBerichtsoortenResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GETBERICHTSOORTENRETURN$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtsoortenReturn");
        
        
        /**
         * Gets the "getBerichtsoortenReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat getGetBerichtsoortenReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat)get_store().find_element_user(GETBERICHTSOORTENRETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "getBerichtsoortenReturn" element
         */
        public void setGetBerichtsoortenReturn(nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat getBerichtsoortenReturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat)get_store().find_element_user(GETBERICHTSOORTENRETURN$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat)get_store().add_element_user(GETBERICHTSOORTENRETURN$0);
                }
                target.set(getBerichtsoortenReturn);
            }
        }
        
        /**
         * Appends and returns a new empty "getBerichtsoortenReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat addNewGetBerichtsoortenReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat)get_store().add_element_user(GETBERICHTSOORTENRETURN$0);
                return target;
            }
        }
    }
}
