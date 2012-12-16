/*
 * An XML document type.
 * Localname: aanleverResponse
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2;


/**
 * A document containing one aanleverResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public interface AanleverResponseDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AanleverResponseDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("aanleverresponse432edoctype");
    
    /**
     * Gets the "aanleverResponse" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse getAanleverResponse();
    
    /**
     * Sets the "aanleverResponse" element
     */
    void setAanleverResponse(nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse aanleverResponse);
    
    /**
     * Appends and returns a new empty "aanleverResponse" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse addNewAanleverResponse();
    
    /**
     * An XML aanleverResponse(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public interface AanleverResponse extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AanleverResponse.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("aanleverresponse5567elemtype");
        
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
         * Sets the "berichtsoort" element
         */
        void setBerichtsoort(java.lang.String berichtsoort);
        
        /**
         * Sets (as xml) the "berichtsoort" element
         */
        void xsetBerichtsoort(nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType berichtsoort);
        
        /**
         * Gets the "aanleverkenmerk" element
         */
        java.lang.String getAanleverkenmerk();
        
        /**
         * Gets (as xml) the "aanleverkenmerk" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType xgetAanleverkenmerk();
        
        /**
         * True if has "aanleverkenmerk" element
         */
        boolean isSetAanleverkenmerk();
        
        /**
         * Sets the "aanleverkenmerk" element
         */
        void setAanleverkenmerk(java.lang.String aanleverkenmerk);
        
        /**
         * Sets (as xml) the "aanleverkenmerk" element
         */
        void xsetAanleverkenmerk(nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType aanleverkenmerk);
        
        /**
         * Unsets the "aanleverkenmerk" element
         */
        void unsetAanleverkenmerk();
        
        /**
         * Gets the "eerderAanleverkenmerk" element
         */
        java.lang.String getEerderAanleverkenmerk();
        
        /**
         * Gets (as xml) the "eerderAanleverkenmerk" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType xgetEerderAanleverkenmerk();
        
        /**
         * True if has "eerderAanleverkenmerk" element
         */
        boolean isSetEerderAanleverkenmerk();
        
        /**
         * Sets the "eerderAanleverkenmerk" element
         */
        void setEerderAanleverkenmerk(java.lang.String eerderAanleverkenmerk);
        
        /**
         * Sets (as xml) the "eerderAanleverkenmerk" element
         */
        void xsetEerderAanleverkenmerk(nl.logius.digipoort.koppelvlakservices._1_2.AanleverkenmerkType eerderAanleverkenmerk);
        
        /**
         * Unsets the "eerderAanleverkenmerk" element
         */
        void unsetEerderAanleverkenmerk();
        
        /**
         * Gets the "tijdstempelAangeleverd" element
         */
        java.util.Calendar getTijdstempelAangeleverd();
        
        /**
         * Gets (as xml) the "tijdstempelAangeleverd" element
         */
        org.apache.xmlbeans.XmlDateTime xgetTijdstempelAangeleverd();
        
        /**
         * Sets the "tijdstempelAangeleverd" element
         */
        void setTijdstempelAangeleverd(java.util.Calendar tijdstempelAangeleverd);
        
        /**
         * Sets (as xml) the "tijdstempelAangeleverd" element
         */
        void xsetTijdstempelAangeleverd(org.apache.xmlbeans.XmlDateTime tijdstempelAangeleverd);
        
        /**
         * Gets the "identiteitBelanghebbende" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType getIdentiteitBelanghebbende();
        
        /**
         * Sets the "identiteitBelanghebbende" element
         */
        void setIdentiteitBelanghebbende(nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType identiteitBelanghebbende);
        
        /**
         * Appends and returns a new empty "identiteitBelanghebbende" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType addNewIdentiteitBelanghebbende();
        
        /**
         * Gets the "rolBelanghebbende" element
         */
        java.lang.String getRolBelanghebbende();
        
        /**
         * Gets (as xml) the "rolBelanghebbende" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.RolType xgetRolBelanghebbende();
        
        /**
         * Sets the "rolBelanghebbende" element
         */
        void setRolBelanghebbende(java.lang.String rolBelanghebbende);
        
        /**
         * Sets (as xml) the "rolBelanghebbende" element
         */
        void xsetRolBelanghebbende(nl.logius.digipoort.koppelvlakservices._1_2.RolType rolBelanghebbende);
        
        /**
         * Gets the "identiteitOntvanger" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType getIdentiteitOntvanger();
        
        /**
         * True if has "identiteitOntvanger" element
         */
        boolean isSetIdentiteitOntvanger();
        
        /**
         * Sets the "identiteitOntvanger" element
         */
        void setIdentiteitOntvanger(nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType identiteitOntvanger);
        
        /**
         * Appends and returns a new empty "identiteitOntvanger" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.IdentiteitType addNewIdentiteitOntvanger();
        
        /**
         * Unsets the "identiteitOntvanger" element
         */
        void unsetIdentiteitOntvanger();
        
        /**
         * Gets the "rolOntvanger" element
         */
        java.lang.String getRolOntvanger();
        
        /**
         * Gets (as xml) the "rolOntvanger" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.RolType xgetRolOntvanger();
        
        /**
         * True if has "rolOntvanger" element
         */
        boolean isSetRolOntvanger();
        
        /**
         * Sets the "rolOntvanger" element
         */
        void setRolOntvanger(java.lang.String rolOntvanger);
        
        /**
         * Sets (as xml) the "rolOntvanger" element
         */
        void xsetRolOntvanger(nl.logius.digipoort.koppelvlakservices._1_2.RolType rolOntvanger);
        
        /**
         * Unsets the "rolOntvanger" element
         */
        void unsetRolOntvanger();
        
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
            public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse newInstance() {
              return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument.AanleverResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument newInstance() {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
