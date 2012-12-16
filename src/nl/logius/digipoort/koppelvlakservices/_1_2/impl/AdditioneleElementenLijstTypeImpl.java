/*
 * XML Type:  additioneleElementenLijstType
 * Namespace: http://logius.nl/digipoort/koppelvlakservices/1.2/
 * Java type: nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType
 *
 * Automatically generated - do not modify.
 */
package nl.logius.digipoort.koppelvlakservices._1_2.impl;
/**
 * An XML additioneleElementenLijstType(@http://logius.nl/digipoort/koppelvlakservices/1.2/).
 *
 * This is a complex type.
 */
public class AdditioneleElementenLijstTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements nl.logius.digipoort.koppelvlakservices._1_2.AdditioneleElementenLijstType
{
    
    public AdditioneleElementenLijstTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ELEMENT$0 = 
        new javax.xml.namespace.QName("http://logius.nl/digipoort/koppelvlakservices/1.2/", "element");
    
    
    /**
     * Gets array of all "element" elements
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ElementType[] getElementArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ELEMENT$0, targetList);
            nl.logius.digipoort.koppelvlakservices._1_2.ElementType[] result = new nl.logius.digipoort.koppelvlakservices._1_2.ElementType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "element" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ElementType getElementArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ElementType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementType)get_store().find_element_user(ELEMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "element" element
     */
    public int sizeOfElementArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ELEMENT$0);
        }
    }
    
    /**
     * Sets array of all "element" element
     */
    public void setElementArray(nl.logius.digipoort.koppelvlakservices._1_2.ElementType[] elementArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(elementArray, ELEMENT$0);
        }
    }
    
    /**
     * Sets ith "element" element
     */
    public void setElementArray(int i, nl.logius.digipoort.koppelvlakservices._1_2.ElementType element)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ElementType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementType)get_store().find_element_user(ELEMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(element);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "element" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ElementType insertNewElement(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ElementType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementType)get_store().insert_element_user(ELEMENT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "element" element
     */
    public nl.logius.digipoort.koppelvlakservices._1_2.ElementType addNewElement()
    {
        synchronized (monitor())
        {
            check_orphaned();
            nl.logius.digipoort.koppelvlakservices._1_2.ElementType target = null;
            target = (nl.logius.digipoort.koppelvlakservices._1_2.ElementType)get_store().add_element_user(ELEMENT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "element" element
     */
    public void removeElement(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ELEMENT$0, i);
        }
    }
}
