//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:23:44 PM CST 
//


package com.dpd.common.service.types.shipmentservice._3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>startPosition的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <pre>
 * &lt;simpleType name="startPosition"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="UPPER_LEFT"/&gt;
 *     &lt;enumeration value="UPPER_RIGHT"/&gt;
 *     &lt;enumeration value="LOWER_LEFT"/&gt;
 *     &lt;enumeration value="LOWER_RIGHT"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "startPosition")
@XmlEnum
public enum StartPosition {


    /**
     * Print first label in the upper left corner of the A4 paper.
     * 
     */
    UPPER_LEFT,

    /**
     * Print first label in the upper right corner of the A4 paper.
     * 
     */
    UPPER_RIGHT,

    /**
     * Print first label in the lower left corner of the A4 paper.
     * 
     */
    LOWER_LEFT,

    /**
     * Print first label in the lower right corner of the A4 paper.
     * 
     */
    LOWER_RIGHT;

    public String value() {
        return name();
    }

    public static StartPosition fromValue(String v) {
        return valueOf(v);
    }

}
