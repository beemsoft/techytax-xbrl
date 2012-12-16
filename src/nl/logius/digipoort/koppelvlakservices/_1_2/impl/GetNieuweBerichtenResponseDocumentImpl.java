/*
 * An XML document type.
 * Localname: getNieuweBerichtenResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getNieuweBerichtenResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetNieuweBerichtenResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument
{
    
    public GetNieuweBerichtenResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETNIEUWEBERICHTENRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getNieuweBerichtenResponse");
    
    
    /**
     * Gets the "getNieuweBerichtenResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse getGetNieuweBerichtenResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse)get_store().find_element_user(GETNIEUWEBERICHTENRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getNieuweBerichtenResponse" element
     */
    public void setGetNieuweBerichtenResponse(nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse getNieuweBerichtenResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse)get_store().find_element_user(GETNIEUWEBERICHTENRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse)get_store().add_element_user(GETNIEUWEBERICHTENRESPONSE$0);
            }
            target.set(getNieuweBerichtenResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "getNieuweBerichtenResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse addNewGetNieuweBerichtenResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse)get_store().add_element_user(GETNIEUWEBERICHTENRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML getNieuweBerichtenResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetNieuweBerichtenResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenResponseDocument.GetNieuweBerichtenResponse
    {
        
        public GetNieuweBerichtenResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GETNIEUWEBERICHTENRETURN$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getNieuweBerichtenReturn");
        
        
        /**
         * Gets the "getNieuweBerichtenReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat getGetNieuweBerichtenReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().find_element_user(GETNIEUWEBERICHTENRETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "getNieuweBerichtenReturn" element
         */
        public void setGetNieuweBerichtenReturn(nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat getNieuweBerichtenReturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().find_element_user(GETNIEUWEBERICHTENRETURN$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().add_element_user(GETNIEUWEBERICHTENRETURN$0);
                }
                target.set(getNieuweBerichtenReturn);
            }
        }
        
        /**
         * Appends and returns a new empty "getNieuweBerichtenReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat addNewGetNieuweBerichtenReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat)get_store().add_element_user(GETNIEUWEBERICHTENRETURN$0);
                return target;
            }
        }
    }
}
