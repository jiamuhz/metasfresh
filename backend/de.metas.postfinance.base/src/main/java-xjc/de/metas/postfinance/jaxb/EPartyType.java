
package de.metas.postfinance.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EPartyType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="EPartyType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PRIVATE"/&gt;
 *     &lt;enumeration value="COMPANY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EPartyType", namespace = "http://schemas.datacontract.org/2004/07/eBill.B2BServiceLib.Logic")
@XmlEnum
public enum EPartyType {

    PRIVATE,
    COMPANY;

    public String value() {
        return name();
    }

    public static EPartyType fromValue(String v) {
        return valueOf(v);
    }

}
