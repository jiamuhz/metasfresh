//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:23:44 PM CST 
//


package com.dpd.common.service.types.shipmentservice._3;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Bundles hazardous materials data.
 * 
 * <p>hazardous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="hazardous"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="identificationUnNo"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="identificationClass"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="6"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="classificationCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="5"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="packingGroup" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="5"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="packingCode"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="0A"/&gt;
 *               &lt;enumeration value="0A1"/&gt;
 *               &lt;enumeration value="0A2"/&gt;
 *               &lt;enumeration value="1A"/&gt;
 *               &lt;enumeration value="1A1"/&gt;
 *               &lt;enumeration value="1A2"/&gt;
 *               &lt;enumeration value="1B"/&gt;
 *               &lt;enumeration value="1B1"/&gt;
 *               &lt;enumeration value="1B2"/&gt;
 *               &lt;enumeration value="1H"/&gt;
 *               &lt;enumeration value="1H1"/&gt;
 *               &lt;enumeration value="1H2"/&gt;
 *               &lt;enumeration value="3A"/&gt;
 *               &lt;enumeration value="3A1"/&gt;
 *               &lt;enumeration value="3A2"/&gt;
 *               &lt;enumeration value="3B"/&gt;
 *               &lt;enumeration value="3B1"/&gt;
 *               &lt;enumeration value="3B2"/&gt;
 *               &lt;enumeration value="3H"/&gt;
 *               &lt;enumeration value="3H1"/&gt;
 *               &lt;enumeration value="3H2"/&gt;
 *               &lt;enumeration value="4A"/&gt;
 *               &lt;enumeration value="4B"/&gt;
 *               &lt;enumeration value="4D"/&gt;
 *               &lt;enumeration value="4G"/&gt;
 *               &lt;enumeration value="4H"/&gt;
 *               &lt;enumeration value="4H1"/&gt;
 *               &lt;enumeration value="4H2"/&gt;
 *               &lt;enumeration value="5H"/&gt;
 *               &lt;enumeration value="5M"/&gt;
 *               &lt;enumeration value="6H"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="description"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="160"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="subsidiaryRisk" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="tunnelRestrictionCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="A"/&gt;
 *               &lt;enumeration value="B"/&gt;
 *               &lt;enumeration value="C"/&gt;
 *               &lt;enumeration value="D"/&gt;
 *               &lt;enumeration value="E"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="hazardousWeight"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *               &lt;fractionDigits value="2"/&gt;
 *               &lt;totalDigits value="6"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="netWeight" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *               &lt;fractionDigits value="2"/&gt;
 *               &lt;totalDigits value="6"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="factor"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;maxInclusive value="999"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="notOtherwiseSpecified" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="150"/&gt;
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
@XmlType(name = "hazardous", propOrder = {
    "identificationUnNo",
    "identificationClass",
    "classificationCode",
    "packingGroup",
    "packingCode",
    "description",
    "subsidiaryRisk",
    "tunnelRestrictionCode",
    "hazardousWeight",
    "netWeight",
    "factor",
    "notOtherwiseSpecified"
})
public class Hazardous {

    @XmlElement(required = true)
    protected String identificationUnNo;
    @XmlElement(required = true)
    protected String identificationClass;
    protected String classificationCode;
    protected String packingGroup;
    @XmlElement(required = true)
    protected String packingCode;
    @XmlElement(required = true)
    protected String description;
    protected String subsidiaryRisk;
    protected String tunnelRestrictionCode;
    @XmlElement(required = true)
    protected BigDecimal hazardousWeight;
    protected BigDecimal netWeight;
    protected int factor;
    protected String notOtherwiseSpecified;

    /**
     * 获取identificationUnNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationUnNo() {
        return identificationUnNo;
    }

    /**
     * 设置identificationUnNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationUnNo(String value) {
        this.identificationUnNo = value;
    }

    /**
     * 获取identificationClass属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationClass() {
        return identificationClass;
    }

    /**
     * 设置identificationClass属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationClass(String value) {
        this.identificationClass = value;
    }

    /**
     * 获取classificationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificationCode() {
        return classificationCode;
    }

    /**
     * 设置classificationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificationCode(String value) {
        this.classificationCode = value;
    }

    /**
     * 获取packingGroup属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackingGroup() {
        return packingGroup;
    }

    /**
     * 设置packingGroup属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackingGroup(String value) {
        this.packingGroup = value;
    }

    /**
     * 获取packingCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackingCode() {
        return packingCode;
    }

    /**
     * 设置packingCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackingCode(String value) {
        this.packingCode = value;
    }

    /**
     * 获取description属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置description属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * 获取subsidiaryRisk属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidiaryRisk() {
        return subsidiaryRisk;
    }

    /**
     * 设置subsidiaryRisk属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidiaryRisk(String value) {
        this.subsidiaryRisk = value;
    }

    /**
     * 获取tunnelRestrictionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTunnelRestrictionCode() {
        return tunnelRestrictionCode;
    }

    /**
     * 设置tunnelRestrictionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTunnelRestrictionCode(String value) {
        this.tunnelRestrictionCode = value;
    }

    /**
     * 获取hazardousWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHazardousWeight() {
        return hazardousWeight;
    }

    /**
     * 设置hazardousWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHazardousWeight(BigDecimal value) {
        this.hazardousWeight = value;
    }

    /**
     * 获取netWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetWeight() {
        return netWeight;
    }

    /**
     * 设置netWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetWeight(BigDecimal value) {
        this.netWeight = value;
    }

    /**
     * 获取factor属性的值。
     * 
     */
    public int getFactor() {
        return factor;
    }

    /**
     * 设置factor属性的值。
     * 
     */
    public void setFactor(int value) {
        this.factor = value;
    }

    /**
     * 获取notOtherwiseSpecified属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotOtherwiseSpecified() {
        return notOtherwiseSpecified;
    }

    /**
     * 设置notOtherwiseSpecified属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotOtherwiseSpecified(String value) {
        this.notOtherwiseSpecified = value;
    }

}
