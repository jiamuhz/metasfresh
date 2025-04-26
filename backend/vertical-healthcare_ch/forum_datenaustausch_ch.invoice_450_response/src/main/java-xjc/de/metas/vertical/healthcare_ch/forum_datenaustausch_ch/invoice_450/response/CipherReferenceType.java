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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CipherReferenceType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CipherReferenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="Transforms" type="{http://www.w3.org/2001/04/xmlenc#}TransformsType" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CipherReferenceType", namespace = "http://www.w3.org/2001/04/xmlenc#", propOrder = {
    "transforms"
})
public class CipherReferenceType {

    @XmlElement(name = "Transforms")
    protected TransformsType2 transforms;
    @XmlAttribute(name = "URI", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String uri;

    /**
     * 获取transforms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TransformsType2 }
     *     
     */
    public TransformsType2 getTransforms() {
        return transforms;
    }

    /**
     * 设置transforms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TransformsType2 }
     *     
     */
    public void setTransforms(TransformsType2 value) {
        this.transforms = value;
    }

    /**
     * 获取uri属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURI() {
        return uri;
    }

    /**
     * 设置uri属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURI(String value) {
        this.uri = value;
    }

}
