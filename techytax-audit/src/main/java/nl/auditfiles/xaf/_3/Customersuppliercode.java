//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.29 at 03:34:15 PM CEST 
//


package nl.auditfiles.xaf._3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Customersuppliercode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Customersuppliercode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="1"/>
 *     &lt;enumeration value="B"/>
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="O"/>
 *     &lt;enumeration value="S"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Customersuppliercode")
@XmlEnum
public enum Customersuppliercode {

    B,
    C,
    O,
    S;

    public String value() {
        return name();
    }

    public static Customersuppliercode fromValue(String v) {
        return valueOf(v);
    }

}