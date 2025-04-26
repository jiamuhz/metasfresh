//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:47 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_450.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>transportType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="transportType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="via" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="via" use="required" type="{http://www.forum-datenaustausch.ch/invoice}eanPartyType" /&gt;
 *                 &lt;attribute name="sequence_id" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="from" use="required" type="{http://www.forum-datenaustausch.ch/invoice}eanPartyType" /&gt;
 *       &lt;attribute name="to" use="required" type="{http://www.forum-datenaustausch.ch/invoice}eanPartyType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transportType", propOrder = {
    "via"
})
public class TransportType {

    @XmlElement(required = true)
    protected List<TransportType.Via> via;
    @XmlAttribute(name = "from", required = true)
    protected String from;
    @XmlAttribute(name = "to", required = true)
    protected String to;

    /**
     * Gets the value of the via property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the via property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransportType.Via }
     * 
     * 
     */
    public List<TransportType.Via> getVia() {
        if (via == null) {
            via = new ArrayList<TransportType.Via>();
        }
        return this.via;
    }

    /**
     * 获取from属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * 设置from属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * 获取to属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * 设置to属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="via" use="required" type="{http://www.forum-datenaustausch.ch/invoice}eanPartyType" /&gt;
     *       &lt;attribute name="sequence_id" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Via {

        @XmlAttribute(name = "via", required = true)
        protected String via;
        @XmlAttribute(name = "sequence_id", required = true)
        @XmlSchemaType(name = "unsignedShort")
        protected int sequenceId;

        /**
         * 获取via属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVia() {
            return via;
        }

        /**
         * 设置via属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVia(String value) {
            this.via = value;
        }

        /**
         * 获取sequenceId属性的值。
         * 
         */
        public int getSequenceId() {
            return sequenceId;
        }

        /**
         * 设置sequenceId属性的值。
         * 
         */
        public void setSequenceId(int value) {
            this.sequenceId = value;
        }

    }

}
