/*
 * XML Type:  constateringenLijstType
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML constateringenLijstType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class ConstateringenLijstTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.ConstateringenLijstType
{
    
    public ConstateringenLijstTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CONSTATERING$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "constatering");
    
    
    /**
     * Gets array of all "constatering" elements
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType[] getConstateringArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CONSTATERING$0, targetList);
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType[] result = new nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "constatering" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType getConstateringArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType)get_store().find_element_user(CONSTATERING$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "constatering" element
     */
    public int sizeOfConstateringArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CONSTATERING$0);
        }
    }
    
    /**
     * Sets array of all "constatering" element
     */
    public void setConstateringArray(nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType[] constateringArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(constateringArray, CONSTATERING$0);
        }
    }
    
    /**
     * Sets ith "constatering" element
     */
    public void setConstateringArray(int i, nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType constatering)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType)get_store().find_element_user(CONSTATERING$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(constatering);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "constatering" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType insertNewConstatering(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType)get_store().insert_element_user(CONSTATERING$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "constatering" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType addNewConstatering()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ConstateringType)get_store().add_element_user(CONSTATERING$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "constatering" element
     */
    public void removeConstatering(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CONSTATERING$0, i);
        }
    }
}
