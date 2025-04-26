//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:43:39 PM CST 
//


package de.metas.postfinance.ybinvoice.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * complextype Bill
 * 
 * <p>BillType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BillType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Header"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{}BillHeaderType"&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="LineItems"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="LineItem" type="{}LineItemType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Summary" type="{}SummaryType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillType", propOrder = {
    "header",
    "lineItems",
    "summary"
})
public class BillType {

    @XmlElement(name = "Header", required = true)
    protected BillType.Header header;
    @XmlElement(name = "LineItems", required = true)
    protected BillType.LineItems lineItems;
    @XmlElement(name = "Summary", required = true)
    protected SummaryType summary;

    /**
     * 获取header属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BillType.Header }
     *     
     */
    public BillType.Header getHeader() {
        return header;
    }

    /**
     * 设置header属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BillType.Header }
     *     
     */
    public void setHeader(BillType.Header value) {
        this.header = value;
    }

    /**
     * 获取lineItems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BillType.LineItems }
     *     
     */
    public BillType.LineItems getLineItems() {
        return lineItems;
    }

    /**
     * 设置lineItems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BillType.LineItems }
     *     
     */
    public void setLineItems(BillType.LineItems value) {
        this.lineItems = value;
    }

    /**
     * 获取summary属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SummaryType }
     *     
     */
    public SummaryType getSummary() {
        return summary;
    }

    /**
     * 设置summary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SummaryType }
     *     
     */
    public void setSummary(SummaryType value) {
        this.summary = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{}BillHeaderType"&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Header
        extends BillHeaderType
    {


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
     *       &lt;sequence&gt;
     *         &lt;element name="LineItem" type="{}LineItemType" maxOccurs="unbounded"/&gt;
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
        "lineItem"
    })
    public static class LineItems {

        @XmlElement(name = "LineItem", required = true)
        protected List<LineItemType> lineItem;

        /**
         * Gets the value of the lineItem property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the lineItem property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLineItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LineItemType }
         * 
         * 
         */
        public List<LineItemType> getLineItem() {
            if (lineItem == null) {
                lineItem = new ArrayList<LineItemType>();
            }
            return this.lineItem;
        }

    }

}
