/*
 * An XML document type.
 * Localname: afleverResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2;


/**
 * A document containing one afleverResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public interface AfleverResponseDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AfleverResponseDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("afleverresponsee457doctype");
    
    /**
     * Gets the "afleverResponse" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse getAfleverResponse();
    
    /**
     * Sets the "afleverResponse" element
     */
    void setAfleverResponse(nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse afleverResponse);
    
    /**
     * Appends and returns a new empty "afleverResponse" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse addNewAfleverResponse();
    
    /**
     * An XML afleverResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public interface AfleverResponse extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AfleverResponse.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("afleverresponse6145elemtype");
        
        /**
         * Gets the "kenmerk" element
         */
        java.lang.String getKenmerk();
        
        /**
         * Gets (as xml) the "kenmerk" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType xgetKenmerk();
        
        /**
         * Sets the "kenmerk" element
         */
        void setKenmerk(java.lang.String kenmerk);
        
        /**
         * Sets (as xml) the "kenmerk" element
         */
        void xsetKenmerk(nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType kenmerk);
        
        /**
         * Gets the "berichtsoort" element
         */
        java.lang.String getBerichtsoort();
        
        /**
         * Gets (as xml) the "berichtsoort" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType xgetBerichtsoort();
        
        /**
         * True if has "berichtsoort" element
         */
        boolean isSetBerichtsoort();
        
        /**
         * Sets the "berichtsoort" element
         */
        void setBerichtsoort(java.lang.String berichtsoort);
        
        /**
         * Sets (as xml) the "berichtsoort" element
         */
        void xsetBerichtsoort(nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType berichtsoort);
        
        /**
         * Unsets the "berichtsoort" element
         */
        void unsetBerichtsoort();
        
        /**
         * Gets the "berichtkenmerk" element
         */
        java.lang.String getBerichtkenmerk();
        
        /**
         * Gets (as xml) the "berichtkenmerk" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType xgetBerichtkenmerk();
        
        /**
         * Sets the "berichtkenmerk" element
         */
        void setBerichtkenmerk(java.lang.String berichtkenmerk);
        
        /**
         * Sets (as xml) the "berichtkenmerk" element
         */
        void xsetBerichtkenmerk(nl.logius.digipoort.koppelvlakservices._1_2.BerichtkenmerkType berichtkenmerk);
        
        /**
         * Gets the "tijdstempelAfgeleverd" element
         */
        java.util.Calendar getTijdstempelAfgeleverd();
        
        /**
         * Gets (as xml) the "tijdstempelAfgeleverd" element
         */
        org.apache.xmlbeans.XmlDateTime xgetTijdstempelAfgeleverd();
        
        /**
         * Sets the "tijdstempelAfgeleverd" element
         */
        void setTijdstempelAfgeleverd(java.util.Calendar tijdstempelAfgeleverd);
        
        /**
         * Sets (as xml) the "tijdstempelAfgeleverd" element
         */
        void xsetTijdstempelAfgeleverd(org.apache.xmlbeans.XmlDateTime tijdstempelAfgeleverd);
        
        /**
         * Gets the "statuscode" element
         */
        java.lang.String getStatuscode();
        
        /**
         * Gets (as xml) the "statuscode" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType xgetStatuscode();
        
        /**
         * True if has "statuscode" element
         */
        boolean isSetStatuscode();
        
        /**
         * Sets the "statuscode" element
         */
        void setStatuscode(java.lang.String statuscode);
        
        /**
         * Sets (as xml) the "statuscode" element
         */
        void xsetStatuscode(nl.logius.digipoort.koppelvlakservices._1_2.StatuscodeType statuscode);
        
        /**
         * Unsets the "statuscode" element
         */
        void unsetStatuscode();
        
        /**
         * Gets the "tijdstempelStatus" element
         */
        java.util.Calendar getTijdstempelStatus();
        
        /**
         * Gets (as xml) the "tijdstempelStatus" element
         */
        org.apache.xmlbeans.XmlDateTime xgetTijdstempelStatus();
        
        /**
         * True if has "tijdstempelStatus" element
         */
        boolean isSetTijdstempelStatus();
        
        /**
         * Sets the "tijdstempelStatus" element
         */
        void setTijdstempelStatus(java.util.Calendar tijdstempelStatus);
        
        /**
         * Sets (as xml) the "tijdstempelStatus" element
         */
        void xsetTijdstempelStatus(org.apache.xmlbeans.XmlDateTime tijdstempelStatus);
        
        /**
         * Unsets the "tijdstempelStatus" element
         */
        void unsetTijdstempelStatus();
        
        /**
         * Gets the "statusomschrijving" element
         */
        java.lang.String getStatusomschrijving();
        
        /**
         * Gets (as xml) the "statusomschrijving" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType xgetStatusomschrijving();
        
        /**
         * True if has "statusomschrijving" element
         */
        boolean isSetStatusomschrijving();
        
        /**
         * Sets the "statusomschrijving" element
         */
        void setStatusomschrijving(java.lang.String statusomschrijving);
        
        /**
         * Sets (as xml) the "statusomschrijving" element
         */
        void xsetStatusomschrijving(nl.logius.digipoort.koppelvlakservices._1_2.StatusomschrijvingType statusomschrijving);
        
        /**
         * Unsets the "statusomschrijving" element
         */
        void unsetStatusomschrijving();
        
        /**
         * Gets the "statusFoutcode" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.FoutType getStatusFoutcode();
        
        /**
         * True if has "statusFoutcode" element
         */
        boolean isSetStatusFoutcode();
        
        /**
         * Sets the "statusFoutcode" element
         */
        void setStatusFoutcode(nl.logius.digipoort.koppelvlakservices._1_2.FoutType statusFoutcode);
        
        /**
         * Appends and returns a new empty "statusFoutcode" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.FoutType addNewStatusFoutcode();
        
        /**
         * Unsets the "statusFoutcode" element
         */
        void unsetStatusFoutcode();
        
        /**
         * Gets the "statusdetails" element
         */
        java.lang.String getStatusdetails();
        
        /**
         * Gets (as xml) the "statusdetails" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType xgetStatusdetails();
        
        /**
         * True if has "statusdetails" element
         */
        boolean isSetStatusdetails();
        
        /**
         * Sets the "statusdetails" element
         */
        void setStatusdetails(java.lang.String statusdetails);
        
        /**
         * Sets (as xml) the "statusdetails" element
         */
        void xsetStatusdetails(nl.logius.digipoort.koppelvlakservices._1_2.StatusdetailsType statusdetails);
        
        /**
         * Unsets the "statusdetails" element
         */
        void unsetStatusdetails();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse newInstance() {
              return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument.AfleverResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument newInstance() {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AfleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
