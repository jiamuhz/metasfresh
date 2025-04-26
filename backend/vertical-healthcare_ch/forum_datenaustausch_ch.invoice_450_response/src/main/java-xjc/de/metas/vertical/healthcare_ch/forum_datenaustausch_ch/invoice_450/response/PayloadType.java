//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:47 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_450.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>payloadType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="payloadType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credit" type="{http://www.forum-datenaustausch.ch/invoice}creditType" minOccurs="0"/&gt;
 *         &lt;element name="invoice" type="{http://www.forum-datenaustausch.ch/invoice}invoiceType"/&gt;
 *         &lt;element name="reminder" type="{http://www.forum-datenaustausch.ch/invoice}reminderType" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="body" type="{http://www.forum-datenaustausch.ch/invoice}bodyType"/&gt;
 *           &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}EncryptedData"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="type" default="invoice"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *             &lt;enumeration value="invoice"/&gt;
 *             &lt;enumeration value="reminder"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="storno" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;attribute name="copy" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;attribute name="response_timestamp" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedLong"&gt;
 *             &lt;minInclusive value="1420066800"/&gt;
 *             &lt;maxInclusive value="1924902000"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payloadType", propOrder = {
    "credit",
    "invoice",
    "reminder",
    "body",
    "encryptedData"
})
public class PayloadType {

    protected CreditType credit;
    @XmlElement(required = true)
    protected InvoiceType invoice;
    protected ReminderType reminder;
    protected BodyType body;
    @XmlElement(name = "EncryptedData", namespace = "http://www.w3.org/2001/04/xmlenc#")
    protected EncryptedDataType encryptedData;
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "storno")
    protected Boolean storno;
    @XmlAttribute(name = "copy")
    protected Boolean copy;
    @XmlAttribute(name = "response_timestamp", required = true)
    protected int responseTimestamp;

    /**
     * 获取credit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CreditType }
     *     
     */
    public CreditType getCredit() {
        return credit;
    }

    /**
     * 设置credit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CreditType }
     *     
     */
    public void setCredit(CreditType value) {
        this.credit = value;
    }

    /**
     * 获取invoice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link InvoiceType }
     *     
     */
    public InvoiceType getInvoice() {
        return invoice;
    }

    /**
     * 设置invoice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceType }
     *     
     */
    public void setInvoice(InvoiceType value) {
        this.invoice = value;
    }

    /**
     * 获取reminder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReminderType }
     *     
     */
    public ReminderType getReminder() {
        return reminder;
    }

    /**
     * 设置reminder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReminderType }
     *     
     */
    public void setReminder(ReminderType value) {
        this.reminder = value;
    }

    /**
     * 获取body属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BodyType }
     *     
     */
    public BodyType getBody() {
        return body;
    }

    /**
     * 设置body属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BodyType }
     *     
     */
    public void setBody(BodyType value) {
        this.body = value;
    }

    /**
     * 获取encryptedData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EncryptedDataType }
     *     
     */
    public EncryptedDataType getEncryptedData() {
        return encryptedData;
    }

    /**
     * 设置encryptedData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedDataType }
     *     
     */
    public void setEncryptedData(EncryptedDataType value) {
        this.encryptedData = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "invoice";
        } else {
            return type;
        }
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * 获取storno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isStorno() {
        if (storno == null) {
            return false;
        } else {
            return storno;
        }
    }

    /**
     * 设置storno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStorno(Boolean value) {
        this.storno = value;
    }

    /**
     * 获取copy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCopy() {
        if (copy == null) {
            return false;
        } else {
            return copy;
        }
    }

    /**
     * 设置copy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCopy(Boolean value) {
        this.copy = value;
    }

    /**
     * 获取responseTimestamp属性的值。
     * 
     */
    public int getResponseTimestamp() {
        return responseTimestamp;
    }

    /**
     * 设置responseTimestamp属性的值。
     * 
     */
    public void setResponseTimestamp(int value) {
        this.responseTimestamp = value;
    }

}
