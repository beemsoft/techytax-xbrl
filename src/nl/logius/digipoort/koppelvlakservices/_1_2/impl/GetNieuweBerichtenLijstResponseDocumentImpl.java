/*
 * An XML document type.
 * Localname: getNieuweBerichtenLijstResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getNieuweBerichtenLijstResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetNieuweBerichtenLijstResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument
{
    
    public GetNieuweBerichtenLijstResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETNIEUWEBERICHTENLIJSTRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getNieuweBerichtenLijstResponse");
    
    
    /**
     * Gets the "getNieuweBerichtenLijstResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse getGetNieuweBerichtenLijstResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse)get_store().find_element_user(GETNIEUWEBERICHTENLIJSTRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getNieuweBerichtenLijstResponse" element
     */
    public void setGetNieuweBerichtenLijstResponse(nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse getNieuweBerichtenLijstResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse)get_store().find_element_user(GETNIEUWEBERICHTENLIJSTRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse)get_store().add_element_user(GETNIEUWEBERICHTENLIJSTRESPONSE$0);
            }
            target.set(getNieuweBerichtenLijstResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "getNieuweBerichtenLijstResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse addNewGetNieuweBerichtenLijstResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse)get_store().add_element_user(GETNIEUWEBERICHTENLIJSTRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML getNieuweBerichtenLijstResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetNieuweBerichtenLijstResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenLijstResponseDocument.GetNieuweBerichtenLijstResponse
    {
        
        public GetNieuweBerichtenLijstResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GETNIEUWEBERICHTENLIJSTRETURN$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getNieuweBerichtenLijstReturn");
        
        
        /**
         * Gets the "getNieuweBerichtenLijstReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat getGetNieuweBerichtenLijstReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat)get_store().find_element_user(GETNIEUWEBERICHTENLIJSTRETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "getNieuweBerichtenLijstReturn" element
         */
        public void setGetNieuweBerichtenLijstReturn(nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat getNieuweBerichtenLijstReturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat)get_store().find_element_user(GETNIEUWEBERICHTENLIJSTRETURN$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat)get_store().add_element_user(GETNIEUWEBERICHTENLIJSTRETURN$0);
                }
                target.set(getNieuweBerichtenLijstReturn);
            }
        }
        
        /**
         * Appends and returns a new empty "getNieuweBerichtenLijstReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat addNewGetNieuweBerichtenLijstReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat)get_store().add_element_user(GETNIEUWEBERICHTENLIJSTRETURN$0);
                return target;
            }
        }
    }
}
