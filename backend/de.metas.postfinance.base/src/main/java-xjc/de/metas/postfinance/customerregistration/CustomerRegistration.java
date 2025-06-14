//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:43:39 PM CST 
//


package de.metas.postfinance.customerregistration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="SubscriptionType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *               &lt;pattern value="\d{1}"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BillerID"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="17"/&gt;
 *               &lt;minLength value="17"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RecipientID"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="17"/&gt;
 *               &lt;maxLength value="17"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RecipientType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *               &lt;enumeration value="PRIVATE"/&gt;
 *               &lt;enumeration value="COMPANY"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Language"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *               &lt;enumeration value="de"/&gt;
 *               &lt;enumeration value="fr"/&gt;
 *               &lt;enumeration value="it"/&gt;
 *               &lt;enumeration value="en"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustomerNameAddress"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;choice&gt;
 *                     &lt;element ref="{}NamePrivate"/&gt;
 *                     &lt;element ref="{}NameCompany"/&gt;
 *                   &lt;/choice&gt;
 *                   &lt;element name="Address"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="70"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Zip"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="70"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="City"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="70"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Country"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *                         &lt;enumeration value="AF"/&gt;
 *                         &lt;enumeration value="AL"/&gt;
 *                         &lt;enumeration value="DZ"/&gt;
 *                         &lt;enumeration value="AS"/&gt;
 *                         &lt;enumeration value="AD"/&gt;
 *                         &lt;enumeration value="AO"/&gt;
 *                         &lt;enumeration value="AI"/&gt;
 *                         &lt;enumeration value="AQ"/&gt;
 *                         &lt;enumeration value="AG"/&gt;
 *                         &lt;enumeration value="AR"/&gt;
 *                         &lt;enumeration value="AM"/&gt;
 *                         &lt;enumeration value="AW"/&gt;
 *                         &lt;enumeration value="AU"/&gt;
 *                         &lt;enumeration value="AT"/&gt;
 *                         &lt;enumeration value="AZ"/&gt;
 *                         &lt;enumeration value="BS"/&gt;
 *                         &lt;enumeration value="BH"/&gt;
 *                         &lt;enumeration value="BD"/&gt;
 *                         &lt;enumeration value="BB"/&gt;
 *                         &lt;enumeration value="BY"/&gt;
 *                         &lt;enumeration value="BE"/&gt;
 *                         &lt;enumeration value="BZ"/&gt;
 *                         &lt;enumeration value="BJ"/&gt;
 *                         &lt;enumeration value="BM"/&gt;
 *                         &lt;enumeration value="BT"/&gt;
 *                         &lt;enumeration value="BO"/&gt;
 *                         &lt;enumeration value="BA"/&gt;
 *                         &lt;enumeration value="BW"/&gt;
 *                         &lt;enumeration value="BV"/&gt;
 *                         &lt;enumeration value="BR"/&gt;
 *                         &lt;enumeration value="IO"/&gt;
 *                         &lt;enumeration value="BN"/&gt;
 *                         &lt;enumeration value="BG"/&gt;
 *                         &lt;enumeration value="BF"/&gt;
 *                         &lt;enumeration value="BI"/&gt;
 *                         &lt;enumeration value="KH"/&gt;
 *                         &lt;enumeration value="CM"/&gt;
 *                         &lt;enumeration value="CA"/&gt;
 *                         &lt;enumeration value="CV"/&gt;
 *                         &lt;enumeration value="KY"/&gt;
 *                         &lt;enumeration value="CF"/&gt;
 *                         &lt;enumeration value="TD"/&gt;
 *                         &lt;enumeration value="CL"/&gt;
 *                         &lt;enumeration value="CN"/&gt;
 *                         &lt;enumeration value="CX"/&gt;
 *                         &lt;enumeration value="CC"/&gt;
 *                         &lt;enumeration value="CO"/&gt;
 *                         &lt;enumeration value="KM"/&gt;
 *                         &lt;enumeration value="CG"/&gt;
 *                         &lt;enumeration value="CD"/&gt;
 *                         &lt;enumeration value="CK"/&gt;
 *                         &lt;enumeration value="CR"/&gt;
 *                         &lt;enumeration value="CI"/&gt;
 *                         &lt;enumeration value="HR"/&gt;
 *                         &lt;enumeration value="CU"/&gt;
 *                         &lt;enumeration value="CY"/&gt;
 *                         &lt;enumeration value="CZ"/&gt;
 *                         &lt;enumeration value="DK"/&gt;
 *                         &lt;enumeration value="DJ"/&gt;
 *                         &lt;enumeration value="DM"/&gt;
 *                         &lt;enumeration value="DO"/&gt;
 *                         &lt;enumeration value="TP"/&gt;
 *                         &lt;enumeration value="EC"/&gt;
 *                         &lt;enumeration value="EG"/&gt;
 *                         &lt;enumeration value="SV"/&gt;
 *                         &lt;enumeration value="GQ"/&gt;
 *                         &lt;enumeration value="ER"/&gt;
 *                         &lt;enumeration value="EE"/&gt;
 *                         &lt;enumeration value="ET"/&gt;
 *                         &lt;enumeration value="FK"/&gt;
 *                         &lt;enumeration value="FO"/&gt;
 *                         &lt;enumeration value="FJ"/&gt;
 *                         &lt;enumeration value="FI"/&gt;
 *                         &lt;enumeration value="FR"/&gt;
 *                         &lt;enumeration value="GF"/&gt;
 *                         &lt;enumeration value="PF"/&gt;
 *                         &lt;enumeration value="TF"/&gt;
 *                         &lt;enumeration value="GA"/&gt;
 *                         &lt;enumeration value="GM"/&gt;
 *                         &lt;enumeration value="GE"/&gt;
 *                         &lt;enumeration value="DE"/&gt;
 *                         &lt;enumeration value="GH"/&gt;
 *                         &lt;enumeration value="GI"/&gt;
 *                         &lt;enumeration value="GR"/&gt;
 *                         &lt;enumeration value="GL"/&gt;
 *                         &lt;enumeration value="GD"/&gt;
 *                         &lt;enumeration value="GP"/&gt;
 *                         &lt;enumeration value="GU"/&gt;
 *                         &lt;enumeration value="GT"/&gt;
 *                         &lt;enumeration value="GN"/&gt;
 *                         &lt;enumeration value="GW"/&gt;
 *                         &lt;enumeration value="GY"/&gt;
 *                         &lt;enumeration value="HT"/&gt;
 *                         &lt;enumeration value="HM"/&gt;
 *                         &lt;enumeration value="VA"/&gt;
 *                         &lt;enumeration value="HN"/&gt;
 *                         &lt;enumeration value="HK"/&gt;
 *                         &lt;enumeration value="HU"/&gt;
 *                         &lt;enumeration value="IS"/&gt;
 *                         &lt;enumeration value="IN"/&gt;
 *                         &lt;enumeration value="ID"/&gt;
 *                         &lt;enumeration value="IR"/&gt;
 *                         &lt;enumeration value="IQ"/&gt;
 *                         &lt;enumeration value="IE"/&gt;
 *                         &lt;enumeration value="IL"/&gt;
 *                         &lt;enumeration value="IT"/&gt;
 *                         &lt;enumeration value="JM"/&gt;
 *                         &lt;enumeration value="JP"/&gt;
 *                         &lt;enumeration value="JO"/&gt;
 *                         &lt;enumeration value="KZ"/&gt;
 *                         &lt;enumeration value="KE"/&gt;
 *                         &lt;enumeration value="KI"/&gt;
 *                         &lt;enumeration value="KP"/&gt;
 *                         &lt;enumeration value="KR"/&gt;
 *                         &lt;enumeration value="KW"/&gt;
 *                         &lt;enumeration value="KG"/&gt;
 *                         &lt;enumeration value="LA"/&gt;
 *                         &lt;enumeration value="LV"/&gt;
 *                         &lt;enumeration value="LB"/&gt;
 *                         &lt;enumeration value="LS"/&gt;
 *                         &lt;enumeration value="LR"/&gt;
 *                         &lt;enumeration value="LY"/&gt;
 *                         &lt;enumeration value="LI"/&gt;
 *                         &lt;enumeration value="LT"/&gt;
 *                         &lt;enumeration value="LU"/&gt;
 *                         &lt;enumeration value="MO"/&gt;
 *                         &lt;enumeration value="MK"/&gt;
 *                         &lt;enumeration value="MG"/&gt;
 *                         &lt;enumeration value="MW"/&gt;
 *                         &lt;enumeration value="MY"/&gt;
 *                         &lt;enumeration value="MV"/&gt;
 *                         &lt;enumeration value="ML"/&gt;
 *                         &lt;enumeration value="MT"/&gt;
 *                         &lt;enumeration value="MH"/&gt;
 *                         &lt;enumeration value="MQ"/&gt;
 *                         &lt;enumeration value="MR"/&gt;
 *                         &lt;enumeration value="MU"/&gt;
 *                         &lt;enumeration value="YT"/&gt;
 *                         &lt;enumeration value="MX"/&gt;
 *                         &lt;enumeration value="FM"/&gt;
 *                         &lt;enumeration value="MD"/&gt;
 *                         &lt;enumeration value="MC"/&gt;
 *                         &lt;enumeration value="MN"/&gt;
 *                         &lt;enumeration value="MS"/&gt;
 *                         &lt;enumeration value="MA"/&gt;
 *                         &lt;enumeration value="MZ"/&gt;
 *                         &lt;enumeration value="MM"/&gt;
 *                         &lt;enumeration value="NA"/&gt;
 *                         &lt;enumeration value="NR"/&gt;
 *                         &lt;enumeration value="NP"/&gt;
 *                         &lt;enumeration value="NL"/&gt;
 *                         &lt;enumeration value="AN"/&gt;
 *                         &lt;enumeration value="NC"/&gt;
 *                         &lt;enumeration value="NZ"/&gt;
 *                         &lt;enumeration value="NI"/&gt;
 *                         &lt;enumeration value="NE"/&gt;
 *                         &lt;enumeration value="NG"/&gt;
 *                         &lt;enumeration value="NU"/&gt;
 *                         &lt;enumeration value="NF"/&gt;
 *                         &lt;enumeration value="MP"/&gt;
 *                         &lt;enumeration value="NO"/&gt;
 *                         &lt;enumeration value="OM"/&gt;
 *                         &lt;enumeration value="PK"/&gt;
 *                         &lt;enumeration value="PW"/&gt;
 *                         &lt;enumeration value="PS"/&gt;
 *                         &lt;enumeration value="PA"/&gt;
 *                         &lt;enumeration value="PG"/&gt;
 *                         &lt;enumeration value="PY"/&gt;
 *                         &lt;enumeration value="PE"/&gt;
 *                         &lt;enumeration value="PH"/&gt;
 *                         &lt;enumeration value="PN"/&gt;
 *                         &lt;enumeration value="PL"/&gt;
 *                         &lt;enumeration value="PT"/&gt;
 *                         &lt;enumeration value="PR"/&gt;
 *                         &lt;enumeration value="QA"/&gt;
 *                         &lt;enumeration value="RE"/&gt;
 *                         &lt;enumeration value="RO"/&gt;
 *                         &lt;enumeration value="RU"/&gt;
 *                         &lt;enumeration value="RW"/&gt;
 *                         &lt;enumeration value="SH"/&gt;
 *                         &lt;enumeration value="KN"/&gt;
 *                         &lt;enumeration value="LC"/&gt;
 *                         &lt;enumeration value="PM"/&gt;
 *                         &lt;enumeration value="VC"/&gt;
 *                         &lt;enumeration value="WS"/&gt;
 *                         &lt;enumeration value="SM"/&gt;
 *                         &lt;enumeration value="ST"/&gt;
 *                         &lt;enumeration value="SA"/&gt;
 *                         &lt;enumeration value="SN"/&gt;
 *                         &lt;enumeration value="SC"/&gt;
 *                         &lt;enumeration value="SL"/&gt;
 *                         &lt;enumeration value="SG"/&gt;
 *                         &lt;enumeration value="SK"/&gt;
 *                         &lt;enumeration value="SI"/&gt;
 *                         &lt;enumeration value="SB"/&gt;
 *                         &lt;enumeration value="SO"/&gt;
 *                         &lt;enumeration value="ZA"/&gt;
 *                         &lt;enumeration value="GS"/&gt;
 *                         &lt;enumeration value="ES"/&gt;
 *                         &lt;enumeration value="LK"/&gt;
 *                         &lt;enumeration value="SD"/&gt;
 *                         &lt;enumeration value="SR"/&gt;
 *                         &lt;enumeration value="SJ"/&gt;
 *                         &lt;enumeration value="SZ"/&gt;
 *                         &lt;enumeration value="SE"/&gt;
 *                         &lt;enumeration value="CH"/&gt;
 *                         &lt;enumeration value="SY"/&gt;
 *                         &lt;enumeration value="TW"/&gt;
 *                         &lt;enumeration value="TJ"/&gt;
 *                         &lt;enumeration value="TZ"/&gt;
 *                         &lt;enumeration value="TH"/&gt;
 *                         &lt;enumeration value="TG"/&gt;
 *                         &lt;enumeration value="TK"/&gt;
 *                         &lt;enumeration value="TO"/&gt;
 *                         &lt;enumeration value="TT"/&gt;
 *                         &lt;enumeration value="TN"/&gt;
 *                         &lt;enumeration value="TR"/&gt;
 *                         &lt;enumeration value="TM"/&gt;
 *                         &lt;enumeration value="TC"/&gt;
 *                         &lt;enumeration value="TV"/&gt;
 *                         &lt;enumeration value="UG"/&gt;
 *                         &lt;enumeration value="UA"/&gt;
 *                         &lt;enumeration value="AE"/&gt;
 *                         &lt;enumeration value="GB"/&gt;
 *                         &lt;enumeration value="US"/&gt;
 *                         &lt;enumeration value="UM"/&gt;
 *                         &lt;enumeration value="UY"/&gt;
 *                         &lt;enumeration value="UZ"/&gt;
 *                         &lt;enumeration value="VU"/&gt;
 *                         &lt;enumeration value="VE"/&gt;
 *                         &lt;enumeration value="VN"/&gt;
 *                         &lt;enumeration value="VG"/&gt;
 *                         &lt;enumeration value="VI"/&gt;
 *                         &lt;enumeration value="WF"/&gt;
 *                         &lt;enumeration value="EH"/&gt;
 *                         &lt;enumeration value="YE"/&gt;
 *                         &lt;enumeration value="YU"/&gt;
 *                         &lt;enumeration value="ZM"/&gt;
 *                         &lt;enumeration value="ZW"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Email" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="255"/&gt;
 *               &lt;pattern value="[\w\-]+@([\w\-]+\.)+[\w\-]{2,4}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="UID" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="12"/&gt;
 *               &lt;pattern value="CHE[0-9]{9}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CreditAccount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="34"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CreditorReference" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="27"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustomerSubscriptionFormField" type="{}CustomerSubscriptionFormField" maxOccurs="6" minOccurs="0"/&gt;
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
    "subscriptionType",
    "billerID",
    "recipientID",
    "recipientType",
    "language",
    "customerNameAddress",
    "email",
    "uid",
    "creditAccount",
    "creditorReference",
    "customerSubscriptionFormField"
})
@XmlRootElement(name = "CustomerRegistration")
public class CustomerRegistration {

    @XmlElement(name = "SubscriptionType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String subscriptionType;
    @XmlElement(name = "BillerID", required = true)
    protected String billerID;
    @XmlElement(name = "RecipientID", required = true)
    protected String recipientID;
    @XmlElement(name = "RecipientType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String recipientType;
    @XmlElement(name = "Language", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String language;
    @XmlElement(name = "CustomerNameAddress", required = true)
    protected CustomerRegistration.CustomerNameAddress customerNameAddress;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "UID")
    protected String uid;
    @XmlElement(name = "CreditAccount")
    protected String creditAccount;
    @XmlElement(name = "CreditorReference")
    protected String creditorReference;
    @XmlElement(name = "CustomerSubscriptionFormField")
    protected List<CustomerSubscriptionFormField> customerSubscriptionFormField;

    /**
     * 获取subscriptionType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * 设置subscriptionType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionType(String value) {
        this.subscriptionType = value;
    }

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
     * 获取recipientID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientID() {
        return recipientID;
    }

    /**
     * 设置recipientID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientID(String value) {
        this.recipientID = value;
    }

    /**
     * 获取recipientType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientType() {
        return recipientType;
    }

    /**
     * 设置recipientType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientType(String value) {
        this.recipientType = value;
    }

    /**
     * 获取language属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置language属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * 获取customerNameAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerRegistration.CustomerNameAddress }
     *     
     */
    public CustomerRegistration.CustomerNameAddress getCustomerNameAddress() {
        return customerNameAddress;
    }

    /**
     * 设置customerNameAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerRegistration.CustomerNameAddress }
     *     
     */
    public void setCustomerNameAddress(CustomerRegistration.CustomerNameAddress value) {
        this.customerNameAddress = value;
    }

    /**
     * 获取email属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * 获取uid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUID() {
        return uid;
    }

    /**
     * 设置uid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUID(String value) {
        this.uid = value;
    }

    /**
     * 获取creditAccount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditAccount() {
        return creditAccount;
    }

    /**
     * 设置creditAccount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditAccount(String value) {
        this.creditAccount = value;
    }

    /**
     * 获取creditorReference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditorReference() {
        return creditorReference;
    }

    /**
     * 设置creditorReference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditorReference(String value) {
        this.creditorReference = value;
    }

    /**
     * Gets the value of the customerSubscriptionFormField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerSubscriptionFormField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerSubscriptionFormField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerSubscriptionFormField }
     * 
     * 
     */
    public List<CustomerSubscriptionFormField> getCustomerSubscriptionFormField() {
        if (customerSubscriptionFormField == null) {
            customerSubscriptionFormField = new ArrayList<CustomerSubscriptionFormField>();
        }
        return this.customerSubscriptionFormField;
    }


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
     *         &lt;choice&gt;
     *           &lt;element ref="{}NamePrivate"/&gt;
     *           &lt;element ref="{}NameCompany"/&gt;
     *         &lt;/choice&gt;
     *         &lt;element name="Address"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="70"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Zip"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="70"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="City"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="70"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Country"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
     *               &lt;enumeration value="AF"/&gt;
     *               &lt;enumeration value="AL"/&gt;
     *               &lt;enumeration value="DZ"/&gt;
     *               &lt;enumeration value="AS"/&gt;
     *               &lt;enumeration value="AD"/&gt;
     *               &lt;enumeration value="AO"/&gt;
     *               &lt;enumeration value="AI"/&gt;
     *               &lt;enumeration value="AQ"/&gt;
     *               &lt;enumeration value="AG"/&gt;
     *               &lt;enumeration value="AR"/&gt;
     *               &lt;enumeration value="AM"/&gt;
     *               &lt;enumeration value="AW"/&gt;
     *               &lt;enumeration value="AU"/&gt;
     *               &lt;enumeration value="AT"/&gt;
     *               &lt;enumeration value="AZ"/&gt;
     *               &lt;enumeration value="BS"/&gt;
     *               &lt;enumeration value="BH"/&gt;
     *               &lt;enumeration value="BD"/&gt;
     *               &lt;enumeration value="BB"/&gt;
     *               &lt;enumeration value="BY"/&gt;
     *               &lt;enumeration value="BE"/&gt;
     *               &lt;enumeration value="BZ"/&gt;
     *               &lt;enumeration value="BJ"/&gt;
     *               &lt;enumeration value="BM"/&gt;
     *               &lt;enumeration value="BT"/&gt;
     *               &lt;enumeration value="BO"/&gt;
     *               &lt;enumeration value="BA"/&gt;
     *               &lt;enumeration value="BW"/&gt;
     *               &lt;enumeration value="BV"/&gt;
     *               &lt;enumeration value="BR"/&gt;
     *               &lt;enumeration value="IO"/&gt;
     *               &lt;enumeration value="BN"/&gt;
     *               &lt;enumeration value="BG"/&gt;
     *               &lt;enumeration value="BF"/&gt;
     *               &lt;enumeration value="BI"/&gt;
     *               &lt;enumeration value="KH"/&gt;
     *               &lt;enumeration value="CM"/&gt;
     *               &lt;enumeration value="CA"/&gt;
     *               &lt;enumeration value="CV"/&gt;
     *               &lt;enumeration value="KY"/&gt;
     *               &lt;enumeration value="CF"/&gt;
     *               &lt;enumeration value="TD"/&gt;
     *               &lt;enumeration value="CL"/&gt;
     *               &lt;enumeration value="CN"/&gt;
     *               &lt;enumeration value="CX"/&gt;
     *               &lt;enumeration value="CC"/&gt;
     *               &lt;enumeration value="CO"/&gt;
     *               &lt;enumeration value="KM"/&gt;
     *               &lt;enumeration value="CG"/&gt;
     *               &lt;enumeration value="CD"/&gt;
     *               &lt;enumeration value="CK"/&gt;
     *               &lt;enumeration value="CR"/&gt;
     *               &lt;enumeration value="CI"/&gt;
     *               &lt;enumeration value="HR"/&gt;
     *               &lt;enumeration value="CU"/&gt;
     *               &lt;enumeration value="CY"/&gt;
     *               &lt;enumeration value="CZ"/&gt;
     *               &lt;enumeration value="DK"/&gt;
     *               &lt;enumeration value="DJ"/&gt;
     *               &lt;enumeration value="DM"/&gt;
     *               &lt;enumeration value="DO"/&gt;
     *               &lt;enumeration value="TP"/&gt;
     *               &lt;enumeration value="EC"/&gt;
     *               &lt;enumeration value="EG"/&gt;
     *               &lt;enumeration value="SV"/&gt;
     *               &lt;enumeration value="GQ"/&gt;
     *               &lt;enumeration value="ER"/&gt;
     *               &lt;enumeration value="EE"/&gt;
     *               &lt;enumeration value="ET"/&gt;
     *               &lt;enumeration value="FK"/&gt;
     *               &lt;enumeration value="FO"/&gt;
     *               &lt;enumeration value="FJ"/&gt;
     *               &lt;enumeration value="FI"/&gt;
     *               &lt;enumeration value="FR"/&gt;
     *               &lt;enumeration value="GF"/&gt;
     *               &lt;enumeration value="PF"/&gt;
     *               &lt;enumeration value="TF"/&gt;
     *               &lt;enumeration value="GA"/&gt;
     *               &lt;enumeration value="GM"/&gt;
     *               &lt;enumeration value="GE"/&gt;
     *               &lt;enumeration value="DE"/&gt;
     *               &lt;enumeration value="GH"/&gt;
     *               &lt;enumeration value="GI"/&gt;
     *               &lt;enumeration value="GR"/&gt;
     *               &lt;enumeration value="GL"/&gt;
     *               &lt;enumeration value="GD"/&gt;
     *               &lt;enumeration value="GP"/&gt;
     *               &lt;enumeration value="GU"/&gt;
     *               &lt;enumeration value="GT"/&gt;
     *               &lt;enumeration value="GN"/&gt;
     *               &lt;enumeration value="GW"/&gt;
     *               &lt;enumeration value="GY"/&gt;
     *               &lt;enumeration value="HT"/&gt;
     *               &lt;enumeration value="HM"/&gt;
     *               &lt;enumeration value="VA"/&gt;
     *               &lt;enumeration value="HN"/&gt;
     *               &lt;enumeration value="HK"/&gt;
     *               &lt;enumeration value="HU"/&gt;
     *               &lt;enumeration value="IS"/&gt;
     *               &lt;enumeration value="IN"/&gt;
     *               &lt;enumeration value="ID"/&gt;
     *               &lt;enumeration value="IR"/&gt;
     *               &lt;enumeration value="IQ"/&gt;
     *               &lt;enumeration value="IE"/&gt;
     *               &lt;enumeration value="IL"/&gt;
     *               &lt;enumeration value="IT"/&gt;
     *               &lt;enumeration value="JM"/&gt;
     *               &lt;enumeration value="JP"/&gt;
     *               &lt;enumeration value="JO"/&gt;
     *               &lt;enumeration value="KZ"/&gt;
     *               &lt;enumeration value="KE"/&gt;
     *               &lt;enumeration value="KI"/&gt;
     *               &lt;enumeration value="KP"/&gt;
     *               &lt;enumeration value="KR"/&gt;
     *               &lt;enumeration value="KW"/&gt;
     *               &lt;enumeration value="KG"/&gt;
     *               &lt;enumeration value="LA"/&gt;
     *               &lt;enumeration value="LV"/&gt;
     *               &lt;enumeration value="LB"/&gt;
     *               &lt;enumeration value="LS"/&gt;
     *               &lt;enumeration value="LR"/&gt;
     *               &lt;enumeration value="LY"/&gt;
     *               &lt;enumeration value="LI"/&gt;
     *               &lt;enumeration value="LT"/&gt;
     *               &lt;enumeration value="LU"/&gt;
     *               &lt;enumeration value="MO"/&gt;
     *               &lt;enumeration value="MK"/&gt;
     *               &lt;enumeration value="MG"/&gt;
     *               &lt;enumeration value="MW"/&gt;
     *               &lt;enumeration value="MY"/&gt;
     *               &lt;enumeration value="MV"/&gt;
     *               &lt;enumeration value="ML"/&gt;
     *               &lt;enumeration value="MT"/&gt;
     *               &lt;enumeration value="MH"/&gt;
     *               &lt;enumeration value="MQ"/&gt;
     *               &lt;enumeration value="MR"/&gt;
     *               &lt;enumeration value="MU"/&gt;
     *               &lt;enumeration value="YT"/&gt;
     *               &lt;enumeration value="MX"/&gt;
     *               &lt;enumeration value="FM"/&gt;
     *               &lt;enumeration value="MD"/&gt;
     *               &lt;enumeration value="MC"/&gt;
     *               &lt;enumeration value="MN"/&gt;
     *               &lt;enumeration value="MS"/&gt;
     *               &lt;enumeration value="MA"/&gt;
     *               &lt;enumeration value="MZ"/&gt;
     *               &lt;enumeration value="MM"/&gt;
     *               &lt;enumeration value="NA"/&gt;
     *               &lt;enumeration value="NR"/&gt;
     *               &lt;enumeration value="NP"/&gt;
     *               &lt;enumeration value="NL"/&gt;
     *               &lt;enumeration value="AN"/&gt;
     *               &lt;enumeration value="NC"/&gt;
     *               &lt;enumeration value="NZ"/&gt;
     *               &lt;enumeration value="NI"/&gt;
     *               &lt;enumeration value="NE"/&gt;
     *               &lt;enumeration value="NG"/&gt;
     *               &lt;enumeration value="NU"/&gt;
     *               &lt;enumeration value="NF"/&gt;
     *               &lt;enumeration value="MP"/&gt;
     *               &lt;enumeration value="NO"/&gt;
     *               &lt;enumeration value="OM"/&gt;
     *               &lt;enumeration value="PK"/&gt;
     *               &lt;enumeration value="PW"/&gt;
     *               &lt;enumeration value="PS"/&gt;
     *               &lt;enumeration value="PA"/&gt;
     *               &lt;enumeration value="PG"/&gt;
     *               &lt;enumeration value="PY"/&gt;
     *               &lt;enumeration value="PE"/&gt;
     *               &lt;enumeration value="PH"/&gt;
     *               &lt;enumeration value="PN"/&gt;
     *               &lt;enumeration value="PL"/&gt;
     *               &lt;enumeration value="PT"/&gt;
     *               &lt;enumeration value="PR"/&gt;
     *               &lt;enumeration value="QA"/&gt;
     *               &lt;enumeration value="RE"/&gt;
     *               &lt;enumeration value="RO"/&gt;
     *               &lt;enumeration value="RU"/&gt;
     *               &lt;enumeration value="RW"/&gt;
     *               &lt;enumeration value="SH"/&gt;
     *               &lt;enumeration value="KN"/&gt;
     *               &lt;enumeration value="LC"/&gt;
     *               &lt;enumeration value="PM"/&gt;
     *               &lt;enumeration value="VC"/&gt;
     *               &lt;enumeration value="WS"/&gt;
     *               &lt;enumeration value="SM"/&gt;
     *               &lt;enumeration value="ST"/&gt;
     *               &lt;enumeration value="SA"/&gt;
     *               &lt;enumeration value="SN"/&gt;
     *               &lt;enumeration value="SC"/&gt;
     *               &lt;enumeration value="SL"/&gt;
     *               &lt;enumeration value="SG"/&gt;
     *               &lt;enumeration value="SK"/&gt;
     *               &lt;enumeration value="SI"/&gt;
     *               &lt;enumeration value="SB"/&gt;
     *               &lt;enumeration value="SO"/&gt;
     *               &lt;enumeration value="ZA"/&gt;
     *               &lt;enumeration value="GS"/&gt;
     *               &lt;enumeration value="ES"/&gt;
     *               &lt;enumeration value="LK"/&gt;
     *               &lt;enumeration value="SD"/&gt;
     *               &lt;enumeration value="SR"/&gt;
     *               &lt;enumeration value="SJ"/&gt;
     *               &lt;enumeration value="SZ"/&gt;
     *               &lt;enumeration value="SE"/&gt;
     *               &lt;enumeration value="CH"/&gt;
     *               &lt;enumeration value="SY"/&gt;
     *               &lt;enumeration value="TW"/&gt;
     *               &lt;enumeration value="TJ"/&gt;
     *               &lt;enumeration value="TZ"/&gt;
     *               &lt;enumeration value="TH"/&gt;
     *               &lt;enumeration value="TG"/&gt;
     *               &lt;enumeration value="TK"/&gt;
     *               &lt;enumeration value="TO"/&gt;
     *               &lt;enumeration value="TT"/&gt;
     *               &lt;enumeration value="TN"/&gt;
     *               &lt;enumeration value="TR"/&gt;
     *               &lt;enumeration value="TM"/&gt;
     *               &lt;enumeration value="TC"/&gt;
     *               &lt;enumeration value="TV"/&gt;
     *               &lt;enumeration value="UG"/&gt;
     *               &lt;enumeration value="UA"/&gt;
     *               &lt;enumeration value="AE"/&gt;
     *               &lt;enumeration value="GB"/&gt;
     *               &lt;enumeration value="US"/&gt;
     *               &lt;enumeration value="UM"/&gt;
     *               &lt;enumeration value="UY"/&gt;
     *               &lt;enumeration value="UZ"/&gt;
     *               &lt;enumeration value="VU"/&gt;
     *               &lt;enumeration value="VE"/&gt;
     *               &lt;enumeration value="VN"/&gt;
     *               &lt;enumeration value="VG"/&gt;
     *               &lt;enumeration value="VI"/&gt;
     *               &lt;enumeration value="WF"/&gt;
     *               &lt;enumeration value="EH"/&gt;
     *               &lt;enumeration value="YE"/&gt;
     *               &lt;enumeration value="YU"/&gt;
     *               &lt;enumeration value="ZM"/&gt;
     *               &lt;enumeration value="ZW"/&gt;
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
    @XmlType(name = "", propOrder = {
        "namePrivate",
        "nameCompany",
        "address",
        "zip",
        "city",
        "country"
    })
    public static class CustomerNameAddress {

        @XmlElement(name = "NamePrivate")
        protected NamePrivate namePrivate;
        @XmlElement(name = "NameCompany")
        protected NameCompany nameCompany;
        @XmlElement(name = "Address", required = true)
        protected String address;
        @XmlElement(name = "Zip", required = true)
        protected String zip;
        @XmlElement(name = "City", required = true)
        protected String city;
        @XmlElement(name = "Country", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String country;

        /**
         * 获取namePrivate属性的值。
         * 
         * @return
         *     possible object is
         *     {@link NamePrivate }
         *     
         */
        public NamePrivate getNamePrivate() {
            return namePrivate;
        }

        /**
         * 设置namePrivate属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link NamePrivate }
         *     
         */
        public void setNamePrivate(NamePrivate value) {
            this.namePrivate = value;
        }

        /**
         * 获取nameCompany属性的值。
         * 
         * @return
         *     possible object is
         *     {@link NameCompany }
         *     
         */
        public NameCompany getNameCompany() {
            return nameCompany;
        }

        /**
         * 设置nameCompany属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link NameCompany }
         *     
         */
        public void setNameCompany(NameCompany value) {
            this.nameCompany = value;
        }

        /**
         * 获取address属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAddress() {
            return address;
        }

        /**
         * 设置address属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAddress(String value) {
            this.address = value;
        }

        /**
         * 获取zip属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZip() {
            return zip;
        }

        /**
         * 设置zip属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZip(String value) {
            this.zip = value;
        }

        /**
         * 获取city属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCity() {
            return city;
        }

        /**
         * 设置city属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCity(String value) {
            this.city = value;
        }

        /**
         * 获取country属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCountry() {
            return country;
        }

        /**
         * 设置country属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCountry(String value) {
            this.country = value;
        }

    }

}
