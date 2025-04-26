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
 * complextype ybinvoice
 * 
 * <p>ybInvoiceType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ybInvoiceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DeliveryInfo" type="{}DeliveryType"/&gt;
 *         &lt;element name="Bill" type="{}BillType"/&gt;
 *         &lt;element name="Appendix" type="{}AppendixType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ybInvoiceType", propOrder = {
    "deliveryInfo",
    "bill",
    "appendix"
})
public class YbInvoiceType {

    @XmlElement(name = "DeliveryInfo", required = true)
    protected DeliveryType deliveryInfo;
    @XmlElement(name = "Bill", required = true)
    protected BillType bill;
    @XmlElement(name = "Appendix")
    protected AppendixType appendix;

    /**
     * 获取deliveryInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DeliveryType }
     *     
     */
    public DeliveryType getDeliveryInfo() {
        return deliveryInfo;
    }

    /**
     * 设置deliveryInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryType }
     *     
     */
    public void setDeliveryInfo(DeliveryType value) {
        this.deliveryInfo = value;
    }

    /**
     * 获取bill属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BillType }
     *     
     */
    public BillType getBill() {
        return bill;
    }

    /**
     * 设置bill属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BillType }
     *     
     */
    public void setBill(BillType value) {
        this.bill = value;
    }

    /**
     * 获取appendix属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AppendixType }
     *     
     */
    public AppendixType getAppendix() {
        return appendix;
    }

    /**
     * 设置appendix属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AppendixType }
     *     
     */
    public void setAppendix(AppendixType value) {
        this.appendix = value;
    }

}
