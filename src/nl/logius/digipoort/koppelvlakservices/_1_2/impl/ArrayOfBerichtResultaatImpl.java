/*
 * XML Type:  ArrayOfBerichtResultaat
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML ArrayOfBerichtResultaat(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class ArrayOfBerichtResultaatImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtResultaat
{
    
    public ArrayOfBerichtResultaatImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BERICHTRESULTAAT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "BerichtResultaat");
    
    
    /**
     * Gets array of all "BerichtResultaat" elements
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat[] getBerichtResultaatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(BERICHTRESULTAAT$0, targetList);
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat[] result = new nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "BerichtResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat getBerichtResultaatArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat)get_store().find_element_user(BERICHTRESULTAAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "BerichtResultaat" element
     */
    public int sizeOfBerichtResultaatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BERICHTRESULTAAT$0);
        }
    }
    
    /**
     * Sets array of all "BerichtResultaat" element
     */
    public void setBerichtResultaatArray(nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat[] berichtResultaatArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(berichtResultaatArray, BERICHTRESULTAAT$0);
        }
    }
    
    /**
     * Sets ith "BerichtResultaat" element
     */
    public void setBerichtResultaatArray(int i, nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat berichtResultaat)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat)get_store().find_element_user(BERICHTRESULTAAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(berichtResultaat);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "BerichtResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat insertNewBerichtResultaat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat)get_store().insert_element_user(BERICHTRESULTAAT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "BerichtResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat addNewBerichtResultaat()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtResultaat)get_store().add_element_user(BERICHTRESULTAAT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "BerichtResultaat" element
     */
    public void removeBerichtResultaat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BERICHTRESULTAAT$0, i);
        }
    }
}
