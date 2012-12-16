/*
 * An XML document type.
 * Localname: aanleverRequest
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2;


/**
 * A document containing one aanleverRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/) element.
 *
 * This is a complex type.
 */
public interface AanleverRequestDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AanleverRequestDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("aanleverrequest6a2adoctype");
    
    /**
     * Gets the "aanleverRequest" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest getAanleverRequest();
    
    /**
     * Sets the "aanleverRequest" element
     */
    void setAanleverRequest(nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest aanleverRequest);
    
    /**
     * Appends and returns a new empty "aanleverRequest" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest addNewAanleverRequest();
    
    /**
     * An XML aanleverRequest(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is a complex type.
     */
    public interface AanleverRequest extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AanleverRequest.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("aanleverrequestbe2belemtype");
        
        /**
         * Gets the "kenmerk" element
         */
        java.lang.String getKenmerk();
        
        /**
         * Gets (as xml) the "kenmerk" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType xgetKenmerk();
        
        /**
         * True if has "kenmerk" element
         */
        boolean isSetKenmerk();
        
        /**
         * Sets the "kenmerk" element
         */
        void setKenmerk(java.lang.String kenmerk);
        
        /**
         * Sets (as xml) the "kenmerk" element
         */
        void xsetKenmerk(nl.logius.digipoort.koppelvlakservices._1_2.KenmerkType kenmerk);
        
        /**
         * Unsets the "kenmerk" element
         */
        void unsetKenmerk();
        
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
         * Gets the "berichtInhoud" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType getBerichtInhoud();
        
        /**
         * Sets the "berichtInhoud" element
         */
        void setBerichtInhoud(nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType berichtInhoud);
        
        /**
         * Appends and returns a new empty "berichtInhoud" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType addNewBerichtInhoud();
        
        /**
         * Gets the "berichtBijlagen" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType getBerichtBijlagen();
        
        /**
         * True if has "berichtBijlagen" element
         */
        boolean isSetBerichtBijlagen();
        
        /**
         * Sets the "berichtBijlagen" element
         */
        void setBerichtBijlagen(nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType berichtBijlagen);
        
        /**
         * Appends and returns a new empty "berichtBijlagen" element
         */
        nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType addNewBerichtBijlagen();
        
        /**
         * Unsets the "berichtBijlagen" element
         */
        void unsetBerichtBijlagen();
        
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
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest newInstance() {
              return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument.AanleverRequest) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument newInstance() {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.AanleverRequestDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
