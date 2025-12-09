//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:23:44 PM CST 
//


package com.dpd.common.service.types.shipmentservice._3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>parcel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="parcel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="parcelLabelNumber" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="11"/&gt;
 *               &lt;maxLength value="14"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customerReferenceNumber1" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customerReferenceNumber2" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customerReferenceNumber3" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customerReferenceNumber4" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="swap" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="volume" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;maxInclusive value="999999999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="weight" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;maxInclusive value="99999999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="hazardousLimitedQuantities" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="higherInsurance" type="{http://dpd.com/common/service/types/ShipmentService/3.2}higherInsurance" minOccurs="0"/&gt;
 *         &lt;element name="content" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="50"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="addService" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="messageNumber" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;maxInclusive value="99999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="function" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="LOCKDZB"/&gt;
 *               &lt;enumeration value="LOCKASG"/&gt;
 *               &lt;enumeration value="LOCKEVM"/&gt;
 *               &lt;enumeration value="LOCKSHOP"/&gt;
 *               &lt;enumeration value="LOCKTV"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="parameter" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="300"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="cod" type="{http://dpd.com/common/service/types/ShipmentService/3.2}cod" minOccurs="0"/&gt;
 *         &lt;element name="international" type="{http://dpd.com/common/service/types/ShipmentService/3.2}international" minOccurs="0"/&gt;
 *         &lt;element name="hazardous" type="{http://dpd.com/common/service/types/ShipmentService/3.2}hazardous" maxOccurs="4" minOccurs="0"/&gt;
 *         &lt;element name="printInfo1OnParcelLabel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="info1" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="29"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="info2" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="returns" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parcel", propOrder = {
    "parcelLabelNumber",
    "customerReferenceNumber1",
    "customerReferenceNumber2",
    "customerReferenceNumber3",
    "customerReferenceNumber4",
    "swap",
    "volume",
    "weight",
    "hazardousLimitedQuantities",
    "higherInsurance",
    "content",
    "addService",
    "messageNumber",
    "function",
    "parameter",
    "cod",
    "international",
    "hazardous",
    "printInfo1OnParcelLabel",
    "info1",
    "info2",
    "returns"
})
public class Parcel {

    protected String parcelLabelNumber;
    protected String customerReferenceNumber1;
    protected String customerReferenceNumber2;
    protected String customerReferenceNumber3;
    protected String customerReferenceNumber4;
    protected Boolean swap;
    protected Integer volume;
    protected Integer weight;
    protected Boolean hazardousLimitedQuantities;
    protected HigherInsurance higherInsurance;
    protected String content;
    protected Integer addService;
    protected Integer messageNumber;
    protected String function;
    protected String parameter;
    protected Cod cod;
    protected International international;
    protected List<Hazardous> hazardous;
    protected Boolean printInfo1OnParcelLabel;
    protected String info1;
    protected String info2;
    protected Boolean returns;

    /**
     * 获取parcelLabelNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParcelLabelNumber() {
        return parcelLabelNumber;
    }

    /**
     * 设置parcelLabelNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParcelLabelNumber(String value) {
        this.parcelLabelNumber = value;
    }

    /**
     * 获取customerReferenceNumber1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerReferenceNumber1() {
        return customerReferenceNumber1;
    }

    /**
     * 设置customerReferenceNumber1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerReferenceNumber1(String value) {
        this.customerReferenceNumber1 = value;
    }

    /**
     * 获取customerReferenceNumber2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerReferenceNumber2() {
        return customerReferenceNumber2;
    }

    /**
     * 设置customerReferenceNumber2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerReferenceNumber2(String value) {
        this.customerReferenceNumber2 = value;
    }

    /**
     * 获取customerReferenceNumber3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerReferenceNumber3() {
        return customerReferenceNumber3;
    }

    /**
     * 设置customerReferenceNumber3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerReferenceNumber3(String value) {
        this.customerReferenceNumber3 = value;
    }

    /**
     * 获取customerReferenceNumber4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerReferenceNumber4() {
        return customerReferenceNumber4;
    }

    /**
     * 设置customerReferenceNumber4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerReferenceNumber4(String value) {
        this.customerReferenceNumber4 = value;
    }

    /**
     * 获取swap属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSwap() {
        return swap;
    }

    /**
     * 设置swap属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSwap(Boolean value) {
        this.swap = value;
    }

    /**
     * 获取volume属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVolume() {
        return volume;
    }

    /**
     * 设置volume属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVolume(Integer value) {
        this.volume = value;
    }

    /**
     * 获取weight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置weight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWeight(Integer value) {
        this.weight = value;
    }

    /**
     * 获取hazardousLimitedQuantities属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHazardousLimitedQuantities() {
        return hazardousLimitedQuantities;
    }

    /**
     * 设置hazardousLimitedQuantities属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHazardousLimitedQuantities(Boolean value) {
        this.hazardousLimitedQuantities = value;
    }

    /**
     * 获取higherInsurance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HigherInsurance }
     *     
     */
    public HigherInsurance getHigherInsurance() {
        return higherInsurance;
    }

    /**
     * 设置higherInsurance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HigherInsurance }
     *     
     */
    public void setHigherInsurance(HigherInsurance value) {
        this.higherInsurance = value;
    }

    /**
     * 获取content属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置content属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * 获取addService属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAddService() {
        return addService;
    }

    /**
     * 设置addService属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAddService(Integer value) {
        this.addService = value;
    }

    /**
     * 获取messageNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMessageNumber() {
        return messageNumber;
    }

    /**
     * 设置messageNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMessageNumber(Integer value) {
        this.messageNumber = value;
    }

    /**
     * 获取function属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunction() {
        return function;
    }

    /**
     * 设置function属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunction(String value) {
        this.function = value;
    }

    /**
     * 获取parameter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * 设置parameter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameter(String value) {
        this.parameter = value;
    }

    /**
     * 获取cod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Cod }
     *     
     */
    public Cod getCod() {
        return cod;
    }

    /**
     * 设置cod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Cod }
     *     
     */
    public void setCod(Cod value) {
        this.cod = value;
    }

    /**
     * 获取international属性的值。
     * 
     * @return
     *     possible object is
     *     {@link International }
     *     
     */
    public International getInternational() {
        return international;
    }

    /**
     * 设置international属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link International }
     *     
     */
    public void setInternational(International value) {
        this.international = value;
    }

    /**
     * Gets the value of the hazardous property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hazardous property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHazardous().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Hazardous }
     * 
     * 
     */
    public List<Hazardous> getHazardous() {
        if (hazardous == null) {
            hazardous = new ArrayList<Hazardous>();
        }
        return this.hazardous;
    }

    /**
     * 获取printInfo1OnParcelLabel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrintInfo1OnParcelLabel() {
        return printInfo1OnParcelLabel;
    }

    /**
     * 设置printInfo1OnParcelLabel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrintInfo1OnParcelLabel(Boolean value) {
        this.printInfo1OnParcelLabel = value;
    }

    /**
     * 获取info1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfo1() {
        return info1;
    }

    /**
     * 设置info1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfo1(String value) {
        this.info1 = value;
    }

    /**
     * 获取info2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfo2() {
        return info2;
    }

    /**
     * 设置info2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfo2(String value) {
        this.info2 = value;
    }

    /**
     * 获取returns属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturns() {
        return returns;
    }

    /**
     * 设置returns属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturns(Boolean value) {
        this.returns = value;
    }

}
