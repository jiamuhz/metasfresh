//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:48 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_440.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>prologType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="prologType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="package" type="{http://www.forum-datenaustausch.ch/invoice}softwareType" minOccurs="0"/&gt;
 *         &lt;element name="generator" type="{http://www.forum-datenaustausch.ch/invoice}softwareType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prologType", propOrder = {
    "_package",
    "generator"
})
public class PrologType {

    @XmlElement(name = "package")
    protected SoftwareType _package;
    @XmlElement(required = true)
    protected SoftwareType generator;

    /**
     * 获取package属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SoftwareType }
     *     
     */
    public SoftwareType getPackage() {
        return _package;
    }

    /**
     * 设置package属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SoftwareType }
     *     
     */
    public void setPackage(SoftwareType value) {
        this._package = value;
    }

    /**
     * 获取generator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SoftwareType }
     *     
     */
    public SoftwareType getGenerator() {
        return generator;
    }

    /**
     * 设置generator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SoftwareType }
     *     
     */
    public void setGenerator(SoftwareType value) {
        this.generator = value;
    }

}
