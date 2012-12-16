/*
 * XML Type:  berichtBijlagenType
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML berichtBijlagenType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class BerichtBijlagenTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.BerichtBijlagenType
{
    
    public BerichtBijlagenTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BIJLAGE$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "bijlage");
    
    
    /**
     * Gets array of all "bijlage" elements
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType[] getBijlageArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(BIJLAGE$0, targetList);
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType[] result = new nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "bijlage" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType getBijlageArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().find_element_user(BIJLAGE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "bijlage" element
     */
    public int sizeOfBijlageArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BIJLAGE$0);
        }
    }
    
    /**
     * Sets array of all "bijlage" element
     */
    public void setBijlageArray(nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType[] bijlageArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(bijlageArray, BIJLAGE$0);
        }
    }
    
    /**
     * Sets ith "bijlage" element
     */
    public void setBijlageArray(int i, nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType bijlage)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().find_element_user(BIJLAGE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(bijlage);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "bijlage" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType insertNewBijlage(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().insert_element_user(BIJLAGE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "bijlage" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType addNewBijlage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.BerichtInhoudType)get_store().add_element_user(BIJLAGE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "bijlage" element
     */
    public void removeBijlage(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BIJLAGE$0, i);
        }
    }
}
