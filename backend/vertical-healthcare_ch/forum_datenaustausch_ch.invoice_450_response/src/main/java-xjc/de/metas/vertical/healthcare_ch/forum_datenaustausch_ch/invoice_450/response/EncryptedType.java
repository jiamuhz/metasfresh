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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>EncryptedType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EncryptedType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EncryptionMethod" type="{http://www.w3.org/2001/04/xmlenc#}EncryptionMethodType" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}KeyInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}CipherData"/&gt;
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}EncryptionProperties" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Encoding" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptedType", namespace = "http://www.w3.org/2001/04/xmlenc#", propOrder = {
    "encryptionMethod",
    "keyInfo",
    "cipherData",
    "encryptionProperties"
})
@XmlSeeAlso({
    EncryptedDataType.class,
    EncryptedKeyType.class
})
public abstract class EncryptedType {

    @XmlElement(name = "EncryptionMethod")
    protected EncryptionMethodType encryptionMethod;
    @XmlElement(name = "KeyInfo", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected KeyInfoType keyInfo;
    @XmlElement(name = "CipherData", required = true)
    protected CipherDataType cipherData;
    @XmlElement(name = "EncryptionProperties")
    protected EncryptionPropertiesType encryptionProperties;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "Type")
    @XmlSchemaType(name = "anyURI")
    protected String type;
    @XmlAttribute(name = "MimeType")
    protected String mimeType;
    @XmlAttribute(name = "Encoding")
    @XmlSchemaType(name = "anyURI")
    protected String encoding;

    /**
     * 获取encryptionMethod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EncryptionMethodType }
     *     
     */
    public EncryptionMethodType getEncryptionMethod() {
        return encryptionMethod;
    }

    /**
     * 设置encryptionMethod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionMethodType }
     *     
     */
    public void setEncryptionMethod(EncryptionMethodType value) {
        this.encryptionMethod = value;
    }

    /**
     * 获取keyInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link KeyInfoType }
     *     
     */
    public KeyInfoType getKeyInfo() {
        return keyInfo;
    }

    /**
     * 设置keyInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link KeyInfoType }
     *     
     */
    public void setKeyInfo(KeyInfoType value) {
        this.keyInfo = value;
    }

    /**
     * 获取cipherData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CipherDataType }
     *     
     */
    public CipherDataType getCipherData() {
        return cipherData;
    }

    /**
     * 设置cipherData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CipherDataType }
     *     
     */
    public void setCipherData(CipherDataType value) {
        this.cipherData = value;
    }

    /**
     * 获取encryptionProperties属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EncryptionPropertiesType }
     *     
     */
    public EncryptionPropertiesType getEncryptionProperties() {
        return encryptionProperties;
    }

    /**
     * 设置encryptionProperties属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionPropertiesType }
     *     
     */
    public void setEncryptionProperties(EncryptionPropertiesType value) {
        this.encryptionProperties = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * 获取mimeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * 设置mimeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * 获取encoding属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * 设置encoding属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

}
