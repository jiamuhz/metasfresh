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
import javax.xml.bind.annotation.XmlType;


/**
 * Bundles general shipment data.
 * 
 * <p>generalShipmentData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="generalShipmentData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mpsId" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="25"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="cUser" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="mpsCustomerReferenceNumber1" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="50"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="mpsCustomerReferenceNumber2" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="mpsCustomerReferenceNumber3" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="mpsCustomerReferenceNumber4" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="identificationNumber" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="sendingDepot"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="product"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="CL"/&gt;
 *               &lt;enumeration value="E830"/&gt;
 *               &lt;enumeration value="E10"/&gt;
 *               &lt;enumeration value="E12"/&gt;
 *               &lt;enumeration value="E18"/&gt;
 *               &lt;enumeration value="IE2"/&gt;
 *               &lt;enumeration value="PL"/&gt;
 *               &lt;enumeration value="PL+"/&gt;
 *               &lt;enumeration value="MAIL"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="mpsCompleteDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="mpsCompleteDeliveryLabel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="mpsVolume" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *               &lt;maxInclusive value="999999999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="mpsWeight" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *               &lt;maxInclusive value="99999999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="mpsExpectedSendingDate" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[2][0-9]{3}([0][0-9]|[1][0-2])(0[1-9]|[12][0-9]|3[01])"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="mpsExpectedSendingTime" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="(([1][0-9]|[2][0-3]|[0][0-9])([0-5][0-9])([0-5][0-9]))|([0-9]([0-5][0-9])([0-5][0-9]))"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="sender" type="{http://dpd.com/common/service/types/ShipmentService/3.2}address"/&gt;
 *         &lt;element name="recipient" type="{http://dpd.com/common/service/types/ShipmentService/3.2}address"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generalShipmentData", propOrder = {
    "mpsId",
    "cUser",
    "mpsCustomerReferenceNumber1",
    "mpsCustomerReferenceNumber2",
    "mpsCustomerReferenceNumber3",
    "mpsCustomerReferenceNumber4",
    "identificationNumber",
    "sendingDepot",
    "product",
    "mpsCompleteDelivery",
    "mpsCompleteDeliveryLabel",
    "mpsVolume",
    "mpsWeight",
    "mpsExpectedSendingDate",
    "mpsExpectedSendingTime",
    "sender",
    "recipient"
})
public class GeneralShipmentData {

    protected String mpsId;
    protected String cUser;
    protected String mpsCustomerReferenceNumber1;
    protected String mpsCustomerReferenceNumber2;
    protected String mpsCustomerReferenceNumber3;
    protected String mpsCustomerReferenceNumber4;
    protected String identificationNumber;
    @XmlElement(required = true)
    protected String sendingDepot;
    @XmlElement(required = true)
    protected String product;
    protected Boolean mpsCompleteDelivery;
    protected Boolean mpsCompleteDeliveryLabel;
    protected Long mpsVolume;
    protected Long mpsWeight;
    protected String mpsExpectedSendingDate;
    protected String mpsExpectedSendingTime;
    @XmlElement(required = true)
    protected Address sender;
    @XmlElement(required = true)
    protected Address recipient;

    /**
     * 获取mpsId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMpsId() {
        return mpsId;
    }

    /**
     * 设置mpsId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMpsId(String value) {
        this.mpsId = value;
    }

    /**
     * 获取cUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUser() {
        return cUser;
    }

    /**
     * 设置cUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUser(String value) {
        this.cUser = value;
    }

    /**
     * 获取mpsCustomerReferenceNumber1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMpsCustomerReferenceNumber1() {
        return mpsCustomerReferenceNumber1;
    }

    /**
     * 设置mpsCustomerReferenceNumber1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMpsCustomerReferenceNumber1(String value) {
        this.mpsCustomerReferenceNumber1 = value;
    }

    /**
     * 获取mpsCustomerReferenceNumber2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMpsCustomerReferenceNumber2() {
        return mpsCustomerReferenceNumber2;
    }

    /**
     * 设置mpsCustomerReferenceNumber2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMpsCustomerReferenceNumber2(String value) {
        this.mpsCustomerReferenceNumber2 = value;
    }

    /**
     * 获取mpsCustomerReferenceNumber3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMpsCustomerReferenceNumber3() {
        return mpsCustomerReferenceNumber3;
    }

    /**
     * 设置mpsCustomerReferenceNumber3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMpsCustomerReferenceNumber3(String value) {
        this.mpsCustomerReferenceNumber3 = value;
    }

    /**
     * 获取mpsCustomerReferenceNumber4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMpsCustomerReferenceNumber4() {
        return mpsCustomerReferenceNumber4;
    }

    /**
     * 设置mpsCustomerReferenceNumber4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMpsCustomerReferenceNumber4(String value) {
        this.mpsCustomerReferenceNumber4 = value;
    }

    /**
     * 获取identificationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * 设置identificationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationNumber(String value) {
        this.identificationNumber = value;
    }

    /**
     * 获取sendingDepot属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendingDepot() {
        return sendingDepot;
    }

    /**
     * 设置sendingDepot属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendingDepot(String value) {
        this.sendingDepot = value;
    }

    /**
     * 获取product属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置product属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
    }

    /**
     * 获取mpsCompleteDelivery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMpsCompleteDelivery() {
        return mpsCompleteDelivery;
    }

    /**
     * 设置mpsCompleteDelivery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMpsCompleteDelivery(Boolean value) {
        this.mpsCompleteDelivery = value;
    }

    /**
     * 获取mpsCompleteDeliveryLabel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMpsCompleteDeliveryLabel() {
        return mpsCompleteDeliveryLabel;
    }

    /**
     * 设置mpsCompleteDeliveryLabel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMpsCompleteDeliveryLabel(Boolean value) {
        this.mpsCompleteDeliveryLabel = value;
    }

    /**
     * 获取mpsVolume属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMpsVolume() {
        return mpsVolume;
    }

    /**
     * 设置mpsVolume属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMpsVolume(Long value) {
        this.mpsVolume = value;
    }

    /**
     * 获取mpsWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMpsWeight() {
        return mpsWeight;
    }

    /**
     * 设置mpsWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMpsWeight(Long value) {
        this.mpsWeight = value;
    }

    /**
     * 获取mpsExpectedSendingDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMpsExpectedSendingDate() {
        return mpsExpectedSendingDate;
    }

    /**
     * 设置mpsExpectedSendingDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMpsExpectedSendingDate(String value) {
        this.mpsExpectedSendingDate = value;
    }

    /**
     * 获取mpsExpectedSendingTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMpsExpectedSendingTime() {
        return mpsExpectedSendingTime;
    }

    /**
     * 设置mpsExpectedSendingTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMpsExpectedSendingTime(String value) {
        this.mpsExpectedSendingTime = value;
    }

    /**
     * 获取sender属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getSender() {
        return sender;
    }

    /**
     * 设置sender属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setSender(Address value) {
        this.sender = value;
    }

    /**
     * 获取recipient属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getRecipient() {
        return recipient;
    }

    /**
     * 设置recipient属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setRecipient(Address value) {
        this.recipient = value;
    }

}
