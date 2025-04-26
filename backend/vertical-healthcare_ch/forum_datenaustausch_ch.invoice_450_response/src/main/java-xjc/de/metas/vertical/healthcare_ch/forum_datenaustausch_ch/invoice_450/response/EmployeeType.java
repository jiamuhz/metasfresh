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


/**
 * <p>employeeType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="employeeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="familyname" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_35"/&gt;
 *         &lt;element name="givenname" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_35" minOccurs="0"/&gt;
 *         &lt;element name="telecom" type="{http://www.forum-datenaustausch.ch/invoice}telecomAddressType" minOccurs="0"/&gt;
 *         &lt;element name="online" type="{http://www.forum-datenaustausch.ch/invoice}onlineAddressType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="salutation" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_35" /&gt;
 *       &lt;attribute name="title" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_35" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeType", propOrder = {
    "familyname",
    "givenname",
    "telecom",
    "online"
})
public class EmployeeType {

    @XmlElement(required = true)
    protected String familyname;
    protected String givenname;
    protected TelecomAddressType telecom;
    protected OnlineAddressType online;
    @XmlAttribute(name = "salutation")
    protected String salutation;
    @XmlAttribute(name = "title")
    protected String title;

    /**
     * 获取familyname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyname() {
        return familyname;
    }

    /**
     * 设置familyname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyname(String value) {
        this.familyname = value;
    }

    /**
     * 获取givenname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenname() {
        return givenname;
    }

    /**
     * 设置givenname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenname(String value) {
        this.givenname = value;
    }

    /**
     * 获取telecom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TelecomAddressType }
     *     
     */
    public TelecomAddressType getTelecom() {
        return telecom;
    }

    /**
     * 设置telecom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TelecomAddressType }
     *     
     */
    public void setTelecom(TelecomAddressType value) {
        this.telecom = value;
    }

    /**
     * 获取online属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OnlineAddressType }
     *     
     */
    public OnlineAddressType getOnline() {
        return online;
    }

    /**
     * 设置online属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineAddressType }
     *     
     */
    public void setOnline(OnlineAddressType value) {
        this.online = value;
    }

    /**
     * 获取salutation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * 设置salutation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalutation(String value) {
        this.salutation = value;
    }

    /**
     * 获取title属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置title属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

}
