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
 * <p>C_Print_Job_Instructions_StatusEnum的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <pre>
 * &lt;simpleType name="C_Print_Job_Instructions_StatusEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="D"/&gt;
 *     &lt;enumeration value="P"/&gt;
 *     &lt;enumeration value="E"/&gt;
 *     &lt;enumeration value="S"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "C_Print_Job_Instructions_StatusEnum")
@XmlEnum
public enum CPrintJobInstructionsStatusEnum {

    @XmlEnumValue("D")
    Done("D"),
    @XmlEnumValue("P")
    Pending("P"),
    @XmlEnumValue("E")
    Error("E"),
    @XmlEnumValue("S")
    Send("S");
    private final String value;

    CPrintJobInstructionsStatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CPrintJobInstructionsStatusEnum fromValue(String v) {
        for (CPrintJobInstructionsStatusEnum c: CPrintJobInstructionsStatusEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
