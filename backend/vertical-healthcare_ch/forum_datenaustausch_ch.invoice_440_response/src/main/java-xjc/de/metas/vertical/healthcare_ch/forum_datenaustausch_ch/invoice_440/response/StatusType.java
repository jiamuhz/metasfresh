//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:48 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_440.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>statusType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="statusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="status_in" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *             &lt;enumeration value="unknown"/&gt;
 *             &lt;enumeration value="ambiguous"/&gt;
 *             &lt;enumeration value="received"/&gt;
 *             &lt;enumeration value="frozen"/&gt;
 *             &lt;enumeration value="processed"/&gt;
 *             &lt;enumeration value="granted"/&gt;
 *             &lt;enumeration value="canceled"/&gt;
 *             &lt;enumeration value="claimed"/&gt;
 *             &lt;enumeration value="reimbursed"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="status_out" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *             &lt;enumeration value="unknown"/&gt;
 *             &lt;enumeration value="ambiguous"/&gt;
 *             &lt;enumeration value="received"/&gt;
 *             &lt;enumeration value="frozen"/&gt;
 *             &lt;enumeration value="processed"/&gt;
 *             &lt;enumeration value="granted"/&gt;
 *             &lt;enumeration value="canceled"/&gt;
 *             &lt;enumeration value="claimed"/&gt;
 *             &lt;enumeration value="reimbursed"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusType")
@XmlSeeAlso({
    PendingType.class,
    AcceptedType.class,
    RejectedType.class
})
public class StatusType {

    @XmlAttribute(name = "status_in", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String statusIn;
    @XmlAttribute(name = "status_out", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String statusOut;

    /**
     * 获取statusIn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusIn() {
        return statusIn;
    }

    /**
     * 设置statusIn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusIn(String value) {
        this.statusIn = value;
    }

    /**
     * 获取statusOut属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusOut() {
        return statusOut;
    }

    /**
     * 设置statusOut属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusOut(String value) {
        this.statusOut = value;
    }

}
