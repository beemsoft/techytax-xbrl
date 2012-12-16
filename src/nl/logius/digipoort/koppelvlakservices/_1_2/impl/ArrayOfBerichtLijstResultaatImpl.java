/*
 * XML Type:  ArrayOfBerichtLijstResultaat
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML ArrayOfBerichtLijstResultaat(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class ArrayOfBerichtLijstResultaatImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtLijstResultaat
{
    
    public ArrayOfBerichtLijstResultaatImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BERICHTLIJSTRESULTAAT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "BerichtLijstResultaat");
    
    
    /**
     * Gets array of all "BerichtLijstResultaat" elements
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat[] getBerichtLijstResultaatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(BERICHTLIJSTRESULTAAT$0, targetList);
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat[] result = new nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "BerichtLijstResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat getBerichtLijstResultaatArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat)get_store().find_element_user(BERICHTLIJSTRESULTAAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "BerichtLijstResultaat" element
     */
    public int sizeOfBerichtLijstResultaatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BERICHTLIJSTRESULTAAT$0);
        }
    }
    
    /**
     * Sets array of all "BerichtLijstResultaat" element
     */
    public void setBerichtLijstResultaatArray(nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat[] berichtLijstResultaatArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(berichtLijstResultaatArray, BERICHTLIJSTRESULTAAT$0);
        }
    }
    
    /**
     * Sets ith "BerichtLijstResultaat" element
     */
    public void setBerichtLijstResultaatArray(int i, nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat berichtLijstResultaat)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat)get_store().find_element_user(BERICHTLIJSTRESULTAAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(berichtLijstResultaat);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "BerichtLijstResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat insertNewBerichtLijstResultaat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat)get_store().insert_element_user(BERICHTLIJSTRESULTAAT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "BerichtLijstResultaat" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat addNewBerichtLijstResultaat()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtLijstResultaat)get_store().add_element_user(BERICHTLIJSTRESULTAAT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "BerichtLijstResultaat" element
     */
    public void removeBerichtLijstResultaat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BERICHTLIJSTRESULTAAT$0, i);
        }
    }
}
