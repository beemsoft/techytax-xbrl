/*
 * XML Type:  ArrayOfProcesResultaat
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML ArrayOfProcesResultaat(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class ArrayOfProcesResultaatImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfProcesResultaat
{
    
    public ArrayOfProcesResultaatImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PROCESRESULTAAT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "ProcesResultaat");
    
    
    /**
     * Gets array of all "ProcesResultaat" elements
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat[] getProcesResultaatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(PROCESRESULTAAT$0, targetList);
            nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat[] result = new nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ProcesResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat getProcesResultaatArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat)get_store().find_element_user(PROCESRESULTAAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ProcesResultaat" element
     */
    public int sizeOfProcesResultaatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PROCESRESULTAAT$0);
        }
    }
    
    /**
     * Sets array of all "ProcesResultaat" element
     */
    public void setProcesResultaatArray(nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat[] procesResultaatArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(procesResultaatArray, PROCESRESULTAAT$0);
        }
    }
    
    /**
     * Sets ith "ProcesResultaat" element
     */
    public void setProcesResultaatArray(int i, nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat procesResultaat)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat)get_store().find_element_user(PROCESRESULTAAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(procesResultaat);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ProcesResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat insertNewProcesResultaat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat)get_store().insert_element_user(PROCESRESULTAAT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ProcesResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat addNewProcesResultaat()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ProcesResultaat)get_store().add_element_user(PROCESRESULTAAT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ProcesResultaat" element
     */
    public void removeProcesResultaat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PROCESRESULTAAT$0, i);
        }
    }
}
