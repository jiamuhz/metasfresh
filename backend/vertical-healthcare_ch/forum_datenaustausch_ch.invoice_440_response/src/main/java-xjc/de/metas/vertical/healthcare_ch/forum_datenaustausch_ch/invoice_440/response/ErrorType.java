//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:48 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_440.response;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>errorType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="errorType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.forum-datenaustausch.ch/invoice}notificationType"&gt;
 *       &lt;attribute name="error_value" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_350" /&gt;
 *       &lt;attribute name="valid_value" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_350" /&gt;
 *       &lt;attribute name="record_id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "errorType")
public class ErrorType
    extends NotificationType
{

    @XmlAttribute(name = "error_value")
    protected String errorValue;
    @XmlAttribute(name = "valid_value")
    protected String validValue;
    @XmlAttribute(name = "record_id")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger recordId;

    /**
     * 获取errorValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorValue() {
        return errorValue;
    }

    /**
     * 设置errorValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorValue(String value) {
        this.errorValue = value;
    }

    /**
     * 获取validValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidValue() {
        return validValue;
    }

    /**
     * 设置validValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidValue(String value) {
        this.validValue = value;
    }

    /**
     * 获取recordId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRecordId() {
        return recordId;
    }

    /**
     * 设置recordId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRecordId(BigInteger value) {
        this.recordId = value;
    }

}
