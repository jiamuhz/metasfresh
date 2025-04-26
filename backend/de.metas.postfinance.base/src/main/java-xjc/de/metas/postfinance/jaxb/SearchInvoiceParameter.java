
package de.metas.postfinance.jaxb;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>SearchInvoiceParameter complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SearchInvoiceParameter"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BillerID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="eBillAccountID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AmountFrom" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="AmountTo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="State" type="{http://schemas.datacontract.org/2004/07/eBill.B2BServiceLib.Logic}State" minOccurs="0"/&gt;
 *         &lt;element name="DeliveryDateFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="DeliveryDateTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="PaymentDueDateFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="PaymentDueDateTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInvoiceParameter", namespace = "http://swisspost_ch.ebs.ebill.b2bservice", propOrder = {
    "billerID",
    "transactionID",
    "eBillAccountID",
    "amountFrom",
    "amountTo",
    "state",
    "deliveryDateFrom",
    "deliveryDateTo",
    "paymentDueDateFrom",
    "paymentDueDateTo"
})
public class SearchInvoiceParameter {

    @XmlElement(name = "BillerID", required = true, nillable = true)
    protected String billerID;
    @XmlElementRef(name = "TransactionID", namespace = "http://swisspost_ch.ebs.ebill.b2bservice", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transactionID;
    @XmlElementRef(name = "eBillAccountID", namespace = "http://swisspost_ch.ebs.ebill.b2bservice", type = JAXBElement.class, required = false)
    protected JAXBElement<String> eBillAccountID;
    @XmlElement(name = "AmountFrom")
    protected BigDecimal amountFrom;
    @XmlElement(name = "AmountTo")
    protected BigDecimal amountTo;
    @XmlElementRef(name = "State", namespace = "http://swisspost_ch.ebs.ebill.b2bservice", type = JAXBElement.class, required = false)
    protected JAXBElement<State> state;
    @XmlElement(name = "DeliveryDateFrom")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deliveryDateFrom;
    @XmlElement(name = "DeliveryDateTo")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deliveryDateTo;
    @XmlElement(name = "PaymentDueDateFrom")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paymentDueDateFrom;
    @XmlElement(name = "PaymentDueDateTo")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paymentDueDateTo;

    /**
     * 获取billerID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillerID() {
        return billerID;
    }

    /**
     * 设置billerID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillerID(String value) {
        this.billerID = value;
    }

    /**
     * 获取transactionID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransactionID() {
        return transactionID;
    }

    /**
     * 设置transactionID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransactionID(JAXBElement<String> value) {
        this.transactionID = value;
    }

    /**
     * 获取eBillAccountID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEBillAccountID() {
        return eBillAccountID;
    }

    /**
     * 设置eBillAccountID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEBillAccountID(JAXBElement<String> value) {
        this.eBillAccountID = value;
    }

    /**
     * 获取amountFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    /**
     * 设置amountFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountFrom(BigDecimal value) {
        this.amountFrom = value;
    }

    /**
     * 获取amountTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountTo() {
        return amountTo;
    }

    /**
     * 设置amountTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountTo(BigDecimal value) {
        this.amountTo = value;
    }

    /**
     * 获取state属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link State }{@code >}
     *     
     */
    public JAXBElement<State> getState() {
        return state;
    }

    /**
     * 设置state属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link State }{@code >}
     *     
     */
    public void setState(JAXBElement<State> value) {
        this.state = value;
    }

    /**
     * 获取deliveryDateFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeliveryDateFrom() {
        return deliveryDateFrom;
    }

    /**
     * 设置deliveryDateFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeliveryDateFrom(XMLGregorianCalendar value) {
        this.deliveryDateFrom = value;
    }

    /**
     * 获取deliveryDateTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeliveryDateTo() {
        return deliveryDateTo;
    }

    /**
     * 设置deliveryDateTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeliveryDateTo(XMLGregorianCalendar value) {
        this.deliveryDateTo = value;
    }

    /**
     * 获取paymentDueDateFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentDueDateFrom() {
        return paymentDueDateFrom;
    }

    /**
     * 设置paymentDueDateFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentDueDateFrom(XMLGregorianCalendar value) {
        this.paymentDueDateFrom = value;
    }

    /**
     * 获取paymentDueDateTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentDueDateTo() {
        return paymentDueDateTo;
    }

    /**
     * 设置paymentDueDateTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentDueDateTo(XMLGregorianCalendar value) {
        this.paymentDueDateTo = value;
    }

}
