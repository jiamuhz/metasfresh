
package de.metas.postfinance.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="invoices" type="{http://swisspost_ch.ebs.ebill.b2bservice}ArrayOfInvoice"/&gt;
 *         &lt;element name="BillerID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "invoices",
    "billerID"
})
@XmlRootElement(name = "UploadFilesReport")
public class UploadFilesReport {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfInvoice invoices;
    @XmlElement(name = "BillerID", required = true, nillable = true)
    protected String billerID;

    /**
     * 获取invoices属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInvoice }
     *     
     */
    public ArrayOfInvoice getInvoices() {
        return invoices;
    }

    /**
     * 设置invoices属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInvoice }
     *     
     */
    public void setInvoices(ArrayOfInvoice value) {
        this.invoices = value;
    }

    /**
     * 获取billerID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillerID() {
        return billerID;
    }

    /**
     * 设置billerID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillerID(String value) {
        this.billerID = value;
    }

}
