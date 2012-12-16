/*
 * XML Type:  ArrayOfBerichtsoortResultaat
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML ArrayOfBerichtsoortResultaat(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class ArrayOfBerichtsoortResultaatImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ArrayOfBerichtsoortResultaat
{
    
    public ArrayOfBerichtsoortResultaatImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BERICHTSOORT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "berichtsoort");
    
    
    /**
     * Gets array of all "berichtsoort" elements
     */
    public java.lang.String[] getBerichtsoortArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(BERICHTSOORT$0, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "berichtsoort" element
     */
    public java.lang.String getBerichtsoortArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTSOORT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "berichtsoort" elements
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType[] xgetBerichtsoortArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(BERICHTSOORT$0, targetList);
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType[] result = new nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "berichtsoort" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType xgetBerichtsoortArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().find_element_user(BERICHTSOORT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)target;
        }
    }
    
    /**
     * Returns number of "berichtsoort" element
     */
    public int sizeOfBerichtsoortArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BERICHTSOORT$0);
        }
    }
    
    /**
     * Sets array of all "berichtsoort" element
     */
    public void setBerichtsoortArray(java.lang.String[] berichtsoortArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(berichtsoortArray, BERICHTSOORT$0);
        }
    }
    
    /**
     * Sets ith "berichtsoort" element
     */
    public void setBerichtsoortArray(int i, java.lang.String berichtsoort)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BERICHTSOORT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(berichtsoort);
        }
    }
    
    /**
     * Sets (as xml) array of all "berichtsoort" element
     */
    public void xsetBerichtsoortArray(nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType[]berichtsoortArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(berichtsoortArray, BERICHTSOORT$0);
        }
    }
    
    /**
     * Sets (as xml) ith "berichtsoort" element
     */
    public void xsetBerichtsoortArray(int i, nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType berichtsoort)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().find_element_user(BERICHTSOORT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(berichtsoort);
        }
    }
    
    /**
     * Inserts the value as the ith "berichtsoort" element
     */
    public void insertBerichtsoort(int i, java.lang.String berichtsoort)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(BERICHTSOORT$0, i);
            target.setStringValue(berichtsoort);
        }
    }
    
    /**
     * Appends the value as the last "berichtsoort" element
     */
    public void addBerichtsoort(java.lang.String berichtsoort)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BERICHTSOORT$0);
            target.setStringValue(berichtsoort);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "berichtsoort" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType insertNewBerichtsoort(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().insert_element_user(BERICHTSOORT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "berichtsoort" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType addNewBerichtsoort()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtsoortType)get_store().add_element_user(BERICHTSOORT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "berichtsoort" element
     */
    public void removeBerichtsoort(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BERICHTSOORT$0, i);
        }
    }
}
