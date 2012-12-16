/*
 * XML Type:  berichtInhoudType
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2;


/**
 * An XML berichtInhoudType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public interface BerichtInhoudType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(BerichtInhoudType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("berichtinhoudtype0283type");
    
    /**
     * Gets the "mimeType" element
     */
    java.lang.String getMimeType();
    
    /**
     * Gets (as xml) the "mimeType" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType xgetMimeType();
    
    /**
     * Sets the "mimeType" element
     */
    void setMimeType(java.lang.String mimeType);
    
    /**
     * Sets (as xml) the "mimeType" element
     */
    void xsetMimeType(nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType mimeType);
    
    /**
     * Gets the "bestandsnaam" element
     */
    java.lang.String getBestandsnaam();
    
    /**
     * Gets (as xml) the "bestandsnaam" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam xgetBestandsnaam();
    
    /**
     * Sets the "bestandsnaam" element
     */
    void setBestandsnaam(java.lang.String bestandsnaam);
    
    /**
     * Sets (as xml) the "bestandsnaam" element
     */
    void xsetBestandsnaam(nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam bestandsnaam);
    
    /**
     * Gets the "inhoud" element
     */
    byte[] getInhoud();
    
    /**
     * Gets (as xml) the "inhoud" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud xgetInhoud();
    
    /**
     * Sets the "inhoud" element
     */
    void setInhoud(byte[] inhoud);
    
    /**
     * Sets (as xml) the "inhoud" element
     */
    void xsetInhoud(nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud inhoud);
    
    /**
     * An XML mimeType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is an atomic type that is a restriction of nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType$MimeType.
     */
    public interface MimeType extends org.apache.xmlbeans.XmlNormalizedString
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(MimeType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("mimetype1119elemtype");
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType newValue(java.lang.Object obj) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType) type.newValue( obj ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType newInstance() {
              return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.MimeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML bestandsnaam(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is an atomic type that is a restriction of nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType$Bestandsnaam.
     */
    public interface Bestandsnaam extends org.apache.xmlbeans.XmlNormalizedString
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Bestandsnaam.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("bestandsnaamc308elemtype");
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam newValue(java.lang.Object obj) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam) type.newValue( obj ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam newInstance() {
              return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Bestandsnaam) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML inhoud(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
     *
     * This is an atomic type that is a restriction of nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType$Inhoud.
     */
    public interface Inhoud extends org.apache.xmlbeans.XmlBase64Binary
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Inhoud.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("inhoud438celemtype");
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud newValue(java.lang.Object obj) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud) type.newValue( obj ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud newInstance() {
              return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType.Inhoud) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType newInstance() {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
