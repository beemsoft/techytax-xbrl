/*
 * An XML document type.
 * Localname: getBerichtenLijstResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * A document containing one getBerichtenLijstResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public class GetBerichtenLijstResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument
{
    
    public GetBerichtenLijstResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETBERICHTENLIJSTRESPONSE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtenLijstResponse");
    
    
    /**
     * Gets the "getBerichtenLijstResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse getGetBerichtenLijstResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse)get_store().find_element_user(GETBERICHTENLIJSTRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getBerichtenLijstResponse" element
     */
    public void setGetBerichtenLijstResponse(nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse getBerichtenLijstResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse)get_store().find_element_user(GETBERICHTENLIJSTRESPONSE$0, 0);
            if (target == null)
            {
                target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse)get_store().add_element_user(GETBERICHTENLIJSTRESPONSE$0);
            }
            target.set(getBerichtenLijstResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "getBerichtenLijstResponse" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse addNewGetBerichtenLijstResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse)get_store().add_element_user(GETBERICHTENLIJSTRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML getBerichtenLijstResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public static class GetBerichtenLijstResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.GetBerichtenLijstResponseDocument.GetBerichtenLijstResponse
    {
        
        public GetBerichtenLijstResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GETBERICHTENLIJSTRETURN$0 = 
            new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "getBerichtenLijstReturn");
        
        
        /**
         * Gets the "getBerichtenLijstReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat getGetBerichtenLijstReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat)get_store().find_element_user(GETBERICHTENLIJSTRETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "getBerichtenLijstReturn" element
         */
        public void setGetBerichtenLijstReturn(nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat getBerichtenLijstReturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat)get_store().find_element_user(GETBERICHTENLIJSTRETURN$0, 0);
                if (target == null)
                {
                    target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat)get_store().add_element_user(GETBERICHTENLIJSTRETURN$0);
                }
                target.set(getBerichtenLijstReturn);
            }
        }
        
        /**
         * Appends and returns a new empty "getBerichtenLijstReturn" element
         */
        public nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat addNewGetBerichtenLijstReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat target = null;
                target = (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat)get_store().add_element_user(GETBERICHTENLIJSTRETURN$0);
                return target;
            }
        }
    }
}
