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
 * <p>international complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="international"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="parcelType" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="customsAmount"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *               &lt;maxInclusive value="999999999999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customsCurrency"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customsTerms"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="01"/&gt;
 *               &lt;enumeration value="02"/&gt;
 *               &lt;enumeration value="03"/&gt;
 *               &lt;enumeration value="05"/&gt;
 *               &lt;enumeration value="06"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customsContent"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customsTarif" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="8"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customsPaper" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customsEnclosure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="customsInvoice" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customsInvoiceDate" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;maxInclusive value="99999999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customsAmountParcel" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *               &lt;maxInclusive value="999999999999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customsOrigin" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="linehaul" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="AI"/&gt;
 *               &lt;enumeration value="RO"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="shipMrn" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="collectiveCustomsClearance" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="invoicePosition" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="6"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="comment1" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="70"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="comment2" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="70"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="commercialInvoiceConsigneeVatNumber" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="commercialInvoiceConsignee" type="{http://dpd.com/common/service/types/ShipmentService/3.2}address"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "international", propOrder = {
    "parcelType",
    "customsAmount",
    "customsCurrency",
    "customsTerms",
    "customsContent",
    "customsTarif",
    "customsPaper",
    "customsEnclosure",
    "customsInvoice",
    "customsInvoiceDate",
    "customsAmountParcel",
    "customsOrigin",
    "linehaul",
    "shipMrn",
    "collectiveCustomsClearance",
    "invoicePosition",
    "comment1",
    "comment2",
    "commercialInvoiceConsigneeVatNumber",
    "commercialInvoiceConsignee"
})
public class International {

    protected boolean parcelType;
    protected long customsAmount;
    @XmlElement(required = true)
    protected String customsCurrency;
    @XmlElement(required = true)
    protected String customsTerms;
    @XmlElement(required = true)
    protected String customsContent;
    protected String customsTarif;
    protected String customsPaper;
    protected Boolean customsEnclosure;
    protected String customsInvoice;
    protected Integer customsInvoiceDate;
    protected Long customsAmountParcel;
    protected String customsOrigin;
    protected String linehaul;
    protected String shipMrn;
    protected Boolean collectiveCustomsClearance;
    protected String invoicePosition;
    protected String comment1;
    protected String comment2;
    protected String commercialInvoiceConsigneeVatNumber;
    @XmlElement(required = true)
    protected Address commercialInvoiceConsignee;

    /**
     * 获取parcelType属性的值。
     * 
     */
    public boolean isParcelType() {
        return parcelType;
    }

    /**
     * 设置parcelType属性的值。
     * 
     */
    public void setParcelType(boolean value) {
        this.parcelType = value;
    }

    /**
     * 获取customsAmount属性的值。
     * 
     */
    public long getCustomsAmount() {
        return customsAmount;
    }

    /**
     * 设置customsAmount属性的值。
     * 
     */
    public void setCustomsAmount(long value) {
        this.customsAmount = value;
    }

    /**
     * 获取customsCurrency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomsCurrency() {
        return customsCurrency;
    }

    /**
     * 设置customsCurrency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomsCurrency(String value) {
        this.customsCurrency = value;
    }

    /**
     * 获取customsTerms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomsTerms() {
        return customsTerms;
    }

    /**
     * 设置customsTerms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomsTerms(String value) {
        this.customsTerms = value;
    }

    /**
     * 获取customsContent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomsContent() {
        return customsContent;
    }

    /**
     * 设置customsContent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomsContent(String value) {
        this.customsContent = value;
    }

    /**
     * 获取customsTarif属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomsTarif() {
        return customsTarif;
    }

    /**
     * 设置customsTarif属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomsTarif(String value) {
        this.customsTarif = value;
    }

    /**
     * 获取customsPaper属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomsPaper() {
        return customsPaper;
    }

    /**
     * 设置customsPaper属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomsPaper(String value) {
        this.customsPaper = value;
    }

    /**
     * 获取customsEnclosure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCustomsEnclosure() {
        return customsEnclosure;
    }

    /**
     * 设置customsEnclosure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustomsEnclosure(Boolean value) {
        this.customsEnclosure = value;
    }

    /**
     * 获取customsInvoice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomsInvoice() {
        return customsInvoice;
    }

    /**
     * 设置customsInvoice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomsInvoice(String value) {
        this.customsInvoice = value;
    }

    /**
     * 获取customsInvoiceDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustomsInvoiceDate() {
        return customsInvoiceDate;
    }

    /**
     * 设置customsInvoiceDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustomsInvoiceDate(Integer value) {
        this.customsInvoiceDate = value;
    }

    /**
     * 获取customsAmountParcel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCustomsAmountParcel() {
        return customsAmountParcel;
    }

    /**
     * 设置customsAmountParcel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCustomsAmountParcel(Long value) {
        this.customsAmountParcel = value;
    }

    /**
     * 获取customsOrigin属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomsOrigin() {
        return customsOrigin;
    }

    /**
     * 设置customsOrigin属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomsOrigin(String value) {
        this.customsOrigin = value;
    }

    /**
     * 获取linehaul属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinehaul() {
        return linehaul;
    }

    /**
     * 设置linehaul属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinehaul(String value) {
        this.linehaul = value;
    }

    /**
     * 获取shipMrn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipMrn() {
        return shipMrn;
    }

    /**
     * 设置shipMrn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipMrn(String value) {
        this.shipMrn = value;
    }

    /**
     * 获取collectiveCustomsClearance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCollectiveCustomsClearance() {
        return collectiveCustomsClearance;
    }

    /**
     * 设置collectiveCustomsClearance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCollectiveCustomsClearance(Boolean value) {
        this.collectiveCustomsClearance = value;
    }

    /**
     * 获取invoicePosition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoicePosition() {
        return invoicePosition;
    }

    /**
     * 设置invoicePosition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoicePosition(String value) {
        this.invoicePosition = value;
    }

    /**
     * 获取comment1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment1() {
        return comment1;
    }

    /**
     * 设置comment1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment1(String value) {
        this.comment1 = value;
    }

    /**
     * 获取comment2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment2() {
        return comment2;
    }

    /**
     * 设置comment2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment2(String value) {
        this.comment2 = value;
    }

    /**
     * 获取commercialInvoiceConsigneeVatNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommercialInvoiceConsigneeVatNumber() {
        return commercialInvoiceConsigneeVatNumber;
    }

    /**
     * 设置commercialInvoiceConsigneeVatNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommercialInvoiceConsigneeVatNumber(String value) {
        this.commercialInvoiceConsigneeVatNumber = value;
    }

    /**
     * 获取commercialInvoiceConsignee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getCommercialInvoiceConsignee() {
        return commercialInvoiceConsignee;
    }

    /**
     * 设置commercialInvoiceConsignee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setCommercialInvoiceConsignee(Address value) {
        this.commercialInvoiceConsignee = value;
    }

}
