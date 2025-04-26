
package de.metas.postfinance.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="GetInvoicePayerResult" type="{http://swisspost_ch.ebs.ebill.b2bservice}DownloadFile" minOccurs="0"/&gt;
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
    "getInvoicePayerResult"
})
@XmlRootElement(name = "GetInvoicePayerResponse")
public class GetInvoicePayerResponse {

    @XmlElementRef(name = "GetInvoicePayerResult", namespace = "http://ch.swisspost.ebill.b2bservice", type = JAXBElement.class, required = false)
    protected JAXBElement<DownloadFile> getInvoicePayerResult;

    /**
     * 获取getInvoicePayerResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DownloadFile }{@code >}
     *     
     */
    public JAXBElement<DownloadFile> getGetInvoicePayerResult() {
        return getInvoicePayerResult;
    }

    /**
     * 设置getInvoicePayerResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DownloadFile }{@code >}
     *     
     */
    public void setGetInvoicePayerResult(JAXBElement<DownloadFile> value) {
        this.getInvoicePayerResult = value;
    }

}
