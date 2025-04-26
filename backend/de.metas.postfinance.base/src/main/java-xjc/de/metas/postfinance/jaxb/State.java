
package de.metas.postfinance.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>State的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="State"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Open"/&gt;
 *     &lt;enumeration value="Paid"/&gt;
 *     &lt;enumeration value="Rejected"/&gt;
 *     &lt;enumeration value="Processing"/&gt;
 *     &lt;enumeration value="Incomplete"/&gt;
 *     &lt;enumeration value="Invalid"/&gt;
 *     &lt;enumeration value="Reported"/&gt;
 *     &lt;enumeration value="Deleted"/&gt;
 *     &lt;enumeration value="Unsigned"/&gt;
 *     &lt;enumeration value="Approved"/&gt;
 *     &lt;enumeration value="Delivered"/&gt;
 *     &lt;enumeration value="Postaldispatched"/&gt;
 *     &lt;enumeration value="Printing"/&gt;
 *     &lt;enumeration value="Return"/&gt;
 *     &lt;enumeration value="Notdelivered"/&gt;
 *     &lt;enumeration value="Delayed"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "State", namespace = "http://schemas.datacontract.org/2004/07/eBill.B2BServiceLib.Logic")
@XmlEnum
public enum State {

    @XmlEnumValue("Open")
    OPEN("Open"),
    @XmlEnumValue("Paid")
    PAID("Paid"),
    @XmlEnumValue("Rejected")
    REJECTED("Rejected"),
    @XmlEnumValue("Processing")
    PROCESSING("Processing"),
    @XmlEnumValue("Incomplete")
    INCOMPLETE("Incomplete"),
    @XmlEnumValue("Invalid")
    INVALID("Invalid"),
    @XmlEnumValue("Reported")
    REPORTED("Reported"),
    @XmlEnumValue("Deleted")
    DELETED("Deleted"),
    @XmlEnumValue("Unsigned")
    UNSIGNED("Unsigned"),
    @XmlEnumValue("Approved")
    APPROVED("Approved"),
    @XmlEnumValue("Delivered")
    DELIVERED("Delivered"),
    @XmlEnumValue("Postaldispatched")
    POSTALDISPATCHED("Postaldispatched"),
    @XmlEnumValue("Printing")
    PRINTING("Printing"),
    @XmlEnumValue("Return")
    RETURN("Return"),
    @XmlEnumValue("Notdelivered")
    NOTDELIVERED("Notdelivered"),
    @XmlEnumValue("Delayed")
    DELAYED("Delayed");
    private final String value;

    State(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static State fromValue(String v) {
        for (State c: State.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
