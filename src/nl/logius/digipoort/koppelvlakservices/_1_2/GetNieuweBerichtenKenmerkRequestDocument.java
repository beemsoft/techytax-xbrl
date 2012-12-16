/*
 * An XML document type.
 * Localname: getNieuweBerichtenKenmerkRequest
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2;


/**
 * A document containing one getNieuweBerichtenKenmerkRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public interface GetNieuweBerichtenKenmerkRequestDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(GetNieuweBerichtenKenmerkRequestDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("getnieuweberichtenkenmerkrequest0f52doctype");
    
    /**
     * Gets the "getNieuweBerichtenKenmerkRequest" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument.GetNieuweBerichtenKenmerkRequest getGetNieuweBerichtenKenmerkRequest();
    
    /**
     * Sets the "getNieuweBerichtenKenmerkRequest" element
     */
    void setGetNieuweBerichtenKenmerkRequest(nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument.GetNieuweBerichtenKenmerkRequest getNieuweBerichtenKenmerkRequest);
    
    /**
     * Appends and returns a new empty "getNieuweBerichtenKenmerkRequest" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument.GetNieuweBerichtenKenmerkRequest addNewGetNieuweBerichtenKenmerkRequest();
    
    /**
     * An XML getNieuweBerichtenKenmerkRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public interface GetNieuweBerichtenKenmerkRequest extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(GetNieuweBerichtenKenmerkRequest.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("getnieuweberichtenkenmerkrequest92e7elemtype");
        
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
         * Gets the "autorisatieAdres" element
         */
        java.lang.String getAutorisatieAdres();
        
        /**
         * Gets (as xml) the "autorisatieAdres" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType xgetAutorisatieAdres();
        
        /**
         * True if has "autorisatieAdres" element
         */
        boolean isSetAutorisatieAdres();
        
        /**
         * Sets the "autorisatieAdres" element
         */
        void setAutorisatieAdres(java.lang.String autorisatieAdres);
        
        /**
         * Sets (as xml) the "autorisatieAdres" element
         */
        void xsetAutorisatieAdres(nl.logius.digipoort.koppelvlakservices._1_2.AutorisatieAdresType autorisatieAdres);
        
        /**
         * Unsets the "autorisatieAdres" element
         */
        void unsetAutorisatieAdres();
        
        /**
         * Gets the "tijdstempelVanaf" element
         */
        java.util.Calendar getTijdstempelVanaf();
        
        /**
         * Gets (as xml) the "tijdstempelVanaf" element
         */
        org.apache.xmlbeans.XmlDateTime xgetTijdstempelVanaf();
        
        /**
         * True if has "tijdstempelVanaf" element
         */
        boolean isSetTijdstempelVanaf();
        
        /**
         * Sets the "tijdstempelVanaf" element
         */
        void setTijdstempelVanaf(java.util.Calendar tijdstempelVanaf);
        
        /**
         * Sets (as xml) the "tijdstempelVanaf" element
         */
        void xsetTijdstempelVanaf(org.apache.xmlbeans.XmlDateTime tijdstempelVanaf);
        
        /**
         * Unsets the "tijdstempelVanaf" element
         */
        void unsetTijdstempelVanaf();
        
        /**
         * Gets the "tijdstempelTot" element
         */
        java.util.Calendar getTijdstempelTot();
        
        /**
         * Gets (as xml) the "tijdstempelTot" element
         */
        org.apache.xmlbeans.XmlDateTime xgetTijdstempelTot();
        
        /**
         * True if has "tijdstempelTot" element
         */
        boolean isSetTijdstempelTot();
        
        /**
         * Sets the "tijdstempelTot" element
         */
        void setTijdstempelTot(java.util.Calendar tijdstempelTot);
        
        /**
         * Sets (as xml) the "tijdstempelTot" element
         */
        void xsetTijdstempelTot(org.apache.xmlbeans.XmlDateTime tijdstempelTot);
        
        /**
         * Unsets the "tijdstempelTot" element
         */
        void unsetTijdstempelTot();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument.GetNieuweBerichtenKenmerkRequest newInstance() {
              return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument.GetNieuweBerichtenKenmerkRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument.GetNieuweBerichtenKenmerkRequest newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument.GetNieuweBerichtenKenmerkRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument newInstance() {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.GetNieuweBerichtenKenmerkRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
