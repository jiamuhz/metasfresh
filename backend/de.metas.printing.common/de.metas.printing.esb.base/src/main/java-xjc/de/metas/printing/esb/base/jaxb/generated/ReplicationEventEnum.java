//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:18:04 PM CST 
//


package de.metas.printing.esb.base.jaxb.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ReplicationEventEnum的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <pre>
 * &lt;simpleType name="ReplicationEventEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="3"/&gt;
 *     &lt;enumeration value="5"/&gt;
 *     &lt;enumeration value="9"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ReplicationEventEnum")
@XmlEnum
public enum ReplicationEventEnum {

    @XmlEnumValue("3")
    BeforeDelete("3"),
    @XmlEnumValue("5")
    AfterChange("5"),
    @XmlEnumValue("9")
    BeforeDeleteReplication("9");
    private final String value;

    ReplicationEventEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReplicationEventEnum fromValue(String v) {
        for (ReplicationEventEnum c: ReplicationEventEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
