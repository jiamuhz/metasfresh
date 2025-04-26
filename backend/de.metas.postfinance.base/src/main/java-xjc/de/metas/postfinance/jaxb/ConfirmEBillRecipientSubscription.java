
package de.metas.postfinance.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BillerID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SubscriptionInitiationToken" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SubscriptionInitiationActivationCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "billerID",
    "subscriptionInitiationToken",
    "subscriptionInitiationActivationCode"
})
@XmlRootElement(name = "ConfirmEBillRecipientSubscription")
public class ConfirmEBillRecipientSubscription {

    @XmlElement(name = "BillerID", required = true, nillable = true)
    protected String billerID;
    @XmlElement(name = "SubscriptionInitiationToken", required = true, nillable = true)
    protected String subscriptionInitiationToken;
    @XmlElement(name = "SubscriptionInitiationActivationCode", required = true, nillable = true)
    protected String subscriptionInitiationActivationCode;

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
     * 获取subscriptionInitiationToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionInitiationToken() {
        return subscriptionInitiationToken;
    }

    /**
     * 设置subscriptionInitiationToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionInitiationToken(String value) {
        this.subscriptionInitiationToken = value;
    }

    /**
     * 获取subscriptionInitiationActivationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionInitiationActivationCode() {
        return subscriptionInitiationActivationCode;
    }

    /**
     * 设置subscriptionInitiationActivationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionInitiationActivationCode(String value) {
        this.subscriptionInitiationActivationCode = value;
    }

}
