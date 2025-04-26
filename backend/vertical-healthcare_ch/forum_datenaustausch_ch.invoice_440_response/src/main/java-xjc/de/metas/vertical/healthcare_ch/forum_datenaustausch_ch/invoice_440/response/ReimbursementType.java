//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:48 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_440.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>reimbursementType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="reimbursementType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="debitor" type="{http://www.forum-datenaustausch.ch/invoice}esrAddressType"/&gt;
 *         &lt;element name="creditor" type="{http://www.forum-datenaustausch.ch/invoice}esrAddressType"/&gt;
 *         &lt;element name="balance" type="{http://www.forum-datenaustausch.ch/invoice}balanceType"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="esr5" type="{http://www.forum-datenaustausch.ch/invoice}esr5Type"/&gt;
 *           &lt;element name="esr9" type="{http://www.forum-datenaustausch.ch/invoice}esr9Type"/&gt;
 *           &lt;element name="esrRed" type="{http://www.forum-datenaustausch.ch/invoice}esrRedType"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reimbursementType", propOrder = {
    "debitor",
    "creditor",
    "balance",
    "esr5",
    "esr9",
    "esrRed"
})
public class ReimbursementType {

    @XmlElement(required = true)
    protected EsrAddressType debitor;
    @XmlElement(required = true)
    protected EsrAddressType creditor;
    @XmlElement(required = true)
    protected BalanceType balance;
    protected Esr5Type esr5;
    protected Esr9Type esr9;
    protected EsrRedType esrRed;

    /**
     * 获取debitor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EsrAddressType }
     *     
     */
    public EsrAddressType getDebitor() {
        return debitor;
    }

    /**
     * 设置debitor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EsrAddressType }
     *     
     */
    public void setDebitor(EsrAddressType value) {
        this.debitor = value;
    }

    /**
     * 获取creditor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EsrAddressType }
     *     
     */
    public EsrAddressType getCreditor() {
        return creditor;
    }

    /**
     * 设置creditor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EsrAddressType }
     *     
     */
    public void setCreditor(EsrAddressType value) {
        this.creditor = value;
    }

    /**
     * 获取balance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BalanceType }
     *     
     */
    public BalanceType getBalance() {
        return balance;
    }

    /**
     * 设置balance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BalanceType }
     *     
     */
    public void setBalance(BalanceType value) {
        this.balance = value;
    }

    /**
     * 获取esr5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Esr5Type }
     *     
     */
    public Esr5Type getEsr5() {
        return esr5;
    }

    /**
     * 设置esr5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Esr5Type }
     *     
     */
    public void setEsr5(Esr5Type value) {
        this.esr5 = value;
    }

    /**
     * 获取esr9属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Esr9Type }
     *     
     */
    public Esr9Type getEsr9() {
        return esr9;
    }

    /**
     * 设置esr9属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Esr9Type }
     *     
     */
    public void setEsr9(Esr9Type value) {
        this.esr9 = value;
    }

    /**
     * 获取esrRed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EsrRedType }
     *     
     */
    public EsrRedType getEsrRed() {
        return esrRed;
    }

    /**
     * 设置esrRed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EsrRedType }
     *     
     */
    public void setEsrRed(EsrRedType value) {
        this.esrRed = value;
    }

}
