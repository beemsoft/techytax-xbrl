/*
 * XML Type:  ArrayOfBerichtsoortResultaat
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2;


/**
 * An XML ArrayOfBerichtsoortResultaat(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public interface ArrayOfBerichtsoortResultaat extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ArrayOfBerichtsoortResultaat.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s3A1280A6A40D92135322981C920AADDA").resolveHandle("arrayofberichtsoortresultaat1974type");
    
    /**
     * Gets array of all "berichtsoort" elements
     */
    java.lang.String[] getBerichtsoortArray();
    
    /**
     * Gets ith "berichtsoort" element
     */
    java.lang.String getBerichtsoortArray(int i);
    
    /**
     * Gets (as xml) array of all "berichtsoort" elements
     */
    nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType[] xgetBerichtsoortArray();
    
    /**
     * Gets (as xml) ith "berichtsoort" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType xgetBerichtsoortArray(int i);
    
    /**
     * Returns number of "berichtsoort" element
     */
    int sizeOfBerichtsoortArray();
    
    /**
     * Sets array of all "berichtsoort" element
     */
    void setBerichtsoortArray(java.lang.String[] berichtsoortArray);
    
    /**
     * Sets ith "berichtsoort" element
     */
    void setBerichtsoortArray(int i, java.lang.String berichtsoort);
    
    /**
     * Sets (as xml) array of all "berichtsoort" element
     */
    void xsetBerichtsoortArray(nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType[] berichtsoortArray);
    
    /**
     * Sets (as xml) ith "berichtsoort" element
     */
    void xsetBerichtsoortArray(int i, nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType berichtsoort);
    
    /**
     * Inserts the value as the ith "berichtsoort" element
     */
    void insertBerichtsoort(int i, java.lang.String berichtsoort);
    
    /**
     * Appends the value as the last "berichtsoort" element
     */
    void addBerichtsoort(java.lang.String berichtsoort);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "berichtsoort" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType insertNewBerichtsoort(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "berichtsoort" element
     */
    nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType addNewBerichtsoort();
    
    /**
     * Removes the ith "berichtsoort" element
     */
    void removeBerichtsoort(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat newInstance() {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
