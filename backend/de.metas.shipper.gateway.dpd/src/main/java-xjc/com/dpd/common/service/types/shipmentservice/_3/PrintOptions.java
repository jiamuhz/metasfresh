//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:23:44 PM CST 
//


package com.dpd.common.service.types.shipmentservice._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Options how to return the parcel labels
 * 
 * <p>printOptions complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="printOptions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="printerLanguage"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="PDF"/&gt;
 *               &lt;enumeration value="ZPL"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="paperFormat"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="A4"/&gt;
 *               &lt;enumeration value="A6"/&gt;
 *               &lt;enumeration value="A7"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="printer" type="{http://dpd.com/common/service/types/ShipmentService/3.2}printer" minOccurs="0"/&gt;
 *         &lt;element name="startPosition" type="{http://dpd.com/common/service/types/ShipmentService/3.2}startPosition" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "printOptions", propOrder = {
    "printerLanguage",
    "paperFormat",
    "printer",
    "startPosition"
})
public class PrintOptions {

    @XmlElement(required = true)
    protected String printerLanguage;
    @XmlElement(required = true)
    protected String paperFormat;
    protected Printer printer;
    @XmlSchemaType(name = "string")
    protected StartPosition startPosition;

    /**
     * 获取printerLanguage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrinterLanguage() {
        return printerLanguage;
    }

    /**
     * 设置printerLanguage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrinterLanguage(String value) {
        this.printerLanguage = value;
    }

    /**
     * 获取paperFormat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaperFormat() {
        return paperFormat;
    }

    /**
     * 设置paperFormat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaperFormat(String value) {
        this.paperFormat = value;
    }

    /**
     * 获取printer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Printer }
     *     
     */
    public Printer getPrinter() {
        return printer;
    }

    /**
     * 设置printer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Printer }
     *     
     */
    public void setPrinter(Printer value) {
        this.printer = value;
    }

    /**
     * 获取startPosition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StartPosition }
     *     
     */
    public StartPosition getStartPosition() {
        return startPosition;
    }

    /**
     * 设置startPosition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StartPosition }
     *     
     */
    public void setStartPosition(StartPosition value) {
        this.startPosition = value;
    }

}
