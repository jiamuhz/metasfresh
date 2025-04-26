
package de.metas.postfinance.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SearchInvoicesResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SearchInvoicesResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InvoiceCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="TotalInvoiceCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="InvoiceList" type="{http://swisspost_ch.ebs.ebill.b2bservice}ArrayOfSearchInvoice" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInvoicesResponse", namespace = "http://swisspost_ch.ebs.ebill.b2bservice", propOrder = {
    "invoiceCount",
    "totalInvoiceCount",
    "invoiceList"
})
public class SearchInvoicesResponse2 {

    @XmlElement(name = "InvoiceCount")
    protected Integer invoiceCount;
    @XmlElement(name = "TotalInvoiceCount")
    protected Integer totalInvoiceCount;
    @XmlElementRef(name = "InvoiceList", namespace = "http://swisspost_ch.ebs.ebill.b2bservice", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSearchInvoice> invoiceList;

    /**
     * 获取invoiceCount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInvoiceCount() {
        return invoiceCount;
    }

    /**
     * 设置invoiceCount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInvoiceCount(Integer value) {
        this.invoiceCount = value;
    }

    /**
     * 获取totalInvoiceCount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalInvoiceCount() {
        return totalInvoiceCount;
    }

    /**
     * 设置totalInvoiceCount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalInvoiceCount(Integer value) {
        this.totalInvoiceCount = value;
    }

    /**
     * 获取invoiceList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSearchInvoice }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSearchInvoice> getInvoiceList() {
        return invoiceList;
    }

    /**
     * 设置invoiceList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSearchInvoice }{@code >}
     *     
     */
    public void setInvoiceList(JAXBElement<ArrayOfSearchInvoice> value) {
        this.invoiceList = value;
    }

}
