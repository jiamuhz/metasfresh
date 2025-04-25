//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.24 时间 11:44:25 AM CST 
//


package de.metas.payment.camt054_001_06;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CardholderAuthentication2 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CardholderAuthentication2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AuthntcnMtd" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}AuthenticationMethod1Code"/&gt;
 *         &lt;element name="AuthntcnNtty" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}AuthenticationEntity1Code"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardholderAuthentication2", propOrder = {
    "authntcnMtd",
    "authntcnNtty"
})
public class CardholderAuthentication2 {

    @XmlElement(name = "AuthntcnMtd", required = true)
    @XmlSchemaType(name = "string")
    protected AuthenticationMethod1Code authntcnMtd;
    @XmlElement(name = "AuthntcnNtty", required = true)
    @XmlSchemaType(name = "string")
    protected AuthenticationEntity1Code authntcnNtty;

    /**
     * 获取authntcnMtd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationMethod1Code }
     *     
     */
    public AuthenticationMethod1Code getAuthntcnMtd() {
        return authntcnMtd;
    }

    /**
     * 设置authntcnMtd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationMethod1Code }
     *     
     */
    public void setAuthntcnMtd(AuthenticationMethod1Code value) {
        this.authntcnMtd = value;
    }

    /**
     * 获取authntcnNtty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationEntity1Code }
     *     
     */
    public AuthenticationEntity1Code getAuthntcnNtty() {
        return authntcnNtty;
    }

    /**
     * 设置authntcnNtty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationEntity1Code }
     *     
     */
    public void setAuthntcnNtty(AuthenticationEntity1Code value) {
        this.authntcnNtty = value;
    }

}
