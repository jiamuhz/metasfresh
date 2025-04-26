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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>zipType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="zipType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.forum-datenaustausch.ch/invoice&gt;stringType1_9"&gt;
 *       &lt;attribute name="statecode" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_9" /&gt;
 *       &lt;attribute name="countrycode" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_3" default="CH" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zipType", propOrder = {
    "value"
})
public class ZipType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "statecode")
    protected String statecode;
    @XmlAttribute(name = "countrycode")
    protected String countrycode;

    /**
     * 获取value属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取statecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatecode() {
        return statecode;
    }

    /**
     * 设置statecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatecode(String value) {
        this.statecode = value;
    }

    /**
     * 获取countrycode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountrycode() {
        if (countrycode == null) {
            return "CH";
        } else {
            return countrycode;
        }
    }

    /**
     * 设置countrycode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountrycode(String value) {
        this.countrycode = value;
    }

}
