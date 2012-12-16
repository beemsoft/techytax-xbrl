/*
 * XML Type:  ArrayOfStatusResultaat
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML ArrayOfStatusResultaat(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class ArrayOfStatusResultaatImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfStatusResultaat
{
    
    public ArrayOfStatusResultaatImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STATUSRESULTAAT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "StatusResultaat");
    
    
    /**
     * Gets array of all "StatusResultaat" elements
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat[] getStatusResultaatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(STATUSRESULTAAT$0, targetList);
            nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat[] result = new nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "StatusResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat getStatusResultaatArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat)get_store().find_element_user(STATUSRESULTAAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "StatusResultaat" element
     */
    public int sizeOfStatusResultaatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(STATUSRESULTAAT$0);
        }
    }
    
    /**
     * Sets array of all "StatusResultaat" element
     */
    public void setStatusResultaatArray(nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat[] statusResultaatArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(statusResultaatArray, STATUSRESULTAAT$0);
        }
    }
    
    /**
     * Sets ith "StatusResultaat" element
     */
    public void setStatusResultaatArray(int i, nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat statusResultaat)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat)get_store().find_element_user(STATUSRESULTAAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(statusResultaat);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "StatusResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat insertNewStatusResultaat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat)get_store().insert_element_user(STATUSRESULTAAT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "StatusResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat addNewStatusResultaat()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.StatusResultaat)get_store().add_element_user(STATUSRESULTAAT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "StatusResultaat" element
     */
    public void removeStatusResultaat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(STATUSRESULTAAT$0, i);
        }
    }
}
