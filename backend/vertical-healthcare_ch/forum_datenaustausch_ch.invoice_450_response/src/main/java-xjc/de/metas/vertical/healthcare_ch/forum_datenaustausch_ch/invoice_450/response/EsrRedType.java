//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:47 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_450.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>esrRedType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="esrRedType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bank" type="{http://www.forum-datenaustausch.ch/invoice}esrAddressType" minOccurs="0"/&gt;
 *         &lt;element name="creditor" type="{http://www.forum-datenaustausch.ch/invoice}esrAddressType"/&gt;
 *         &lt;element name="payment_reason" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_35" maxOccurs="4" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="payment_to" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *             &lt;enumeration value="postal_account"/&gt;
 *             &lt;enumeration value="bank_account"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="post_account" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="[0-9]{2}-[0-9]{1,6}-[0-9]"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="iban"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="(LI|CH)[0-9]{7}[0-9A-Z]{12}"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="reference_number"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="[0-9A-Z]{10}"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="coding_line1" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="([0-9]{27}\+ 07[0-9]{7}&gt;|[0-9]{9}&gt;)"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="coding_line2" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="[0-9]{9}&gt;"/&gt;
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
@XmlType(name = "esrRedType", propOrder = {
    "bank",
    "creditor",
    "paymentReason"
})
public class EsrRedType {

    protected EsrAddressType bank;
    @XmlElement(required = true)
    protected EsrAddressType creditor;
    @XmlElement(name = "payment_reason")
    protected List<String> paymentReason;
    @XmlAttribute(name = "payment_to", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String paymentTo;
    @XmlAttribute(name = "post_account", required = true)
    protected String postAccount;
    @XmlAttribute(name = "iban")
    protected String iban;
    @XmlAttribute(name = "reference_number")
    protected String referenceNumber;
    @XmlAttribute(name = "coding_line1", required = true)
    protected String codingLine1;
    @XmlAttribute(name = "coding_line2", required = true)
    protected String codingLine2;

    /**
     * 获取bank属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EsrAddressType }
     *     
     */
    public EsrAddressType getBank() {
        return bank;
    }

    /**
     * 设置bank属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EsrAddressType }
     *     
     */
    public void setBank(EsrAddressType value) {
        this.bank = value;
    }

    /**
     * 获取creditor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EsrAddressType }
     *     
     */
    public EsrAddressType getCreditor() {
        return creditor;
    }

    /**
     * 设置creditor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EsrAddressType }
     *     
     */
    public void setCreditor(EsrAddressType value) {
        this.creditor = value;
    }

    /**
     * Gets the value of the paymentReason property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentReason property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentReason().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPaymentReason() {
        if (paymentReason == null) {
            paymentReason = new ArrayList<String>();
        }
        return this.paymentReason;
    }

    /**
     * 获取paymentTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTo() {
        return paymentTo;
    }

    /**
     * 设置paymentTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTo(String value) {
        this.paymentTo = value;
    }

    /**
     * 获取postAccount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostAccount() {
        return postAccount;
    }

    /**
     * 设置postAccount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostAccount(String value) {
        this.postAccount = value;
    }

    /**
     * 获取iban属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIban() {
        return iban;
    }

    /**
     * 设置iban属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIban(String value) {
        this.iban = value;
    }

    /**
     * 获取referenceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * 设置referenceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
    }

    /**
     * 获取codingLine1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodingLine1() {
        return codingLine1;
    }

    /**
     * 设置codingLine1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodingLine1(String value) {
        this.codingLine1 = value;
    }

    /**
     * 获取codingLine2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodingLine2() {
        return codingLine2;
    }

    /**
     * 设置codingLine2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodingLine2(String value) {
        this.codingLine2 = value;
    }

}
