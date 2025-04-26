//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:43:39 PM CST 
//


package de.metas.postfinance.ybinvoice.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * complextype Reference
 * 
 * <p>FixedReference complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="FixedReference"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReferencePosition"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ReferenceType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="OrderNumberByBuyer"/&gt;
 *               &lt;enumeration value="OrderNumberBySupplier"/&gt;
 *               &lt;enumeration value="CreditAdviceNumber"/&gt;
 *               &lt;enumeration value="BillNumber"/&gt;
 *               &lt;enumeration value="DispatchNotificationNumber"/&gt;
 *               &lt;enumeration value="DeliveryNoteNumber"/&gt;
 *               &lt;enumeration value="OrderReplyNumber"/&gt;
 *               &lt;enumeration value="BidNumber"/&gt;
 *               &lt;enumeration value="ContractNumber"/&gt;
 *               &lt;enumeration value="ReceiptNumber"/&gt;
 *               &lt;enumeration value="OurSign"/&gt;
 *               &lt;enumeration value="YourSign"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ReferenceValue"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FixedReference", propOrder = {
    "referencePosition",
    "referenceType",
    "referenceValue"
})
public class FixedReference {

    @XmlElement(name = "ReferencePosition", required = true)
    protected String referencePosition;
    @XmlElement(name = "ReferenceType", required = true)
    protected String referenceType;
    @XmlElement(name = "ReferenceValue", required = true)
    protected String referenceValue;

    /**
     * 获取referencePosition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencePosition() {
        return referencePosition;
    }

    /**
     * 设置referencePosition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencePosition(String value) {
        this.referencePosition = value;
    }

    /**
     * 获取referenceType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * 设置referenceType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceType(String value) {
        this.referenceType = value;
    }

    /**
     * 获取referenceValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceValue() {
        return referenceValue;
    }

    /**
     * 设置referenceValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceValue(String value) {
        this.referenceValue = value;
    }

}
