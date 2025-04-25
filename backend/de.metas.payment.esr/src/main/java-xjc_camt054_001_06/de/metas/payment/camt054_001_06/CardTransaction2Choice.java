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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CardTransaction2Choice complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CardTransaction2Choice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="Aggtd" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}CardAggregated1"/&gt;
 *         &lt;element name="Indv" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}CardIndividualTransaction2"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardTransaction2Choice", propOrder = {
    "aggtd",
    "indv"
})
public class CardTransaction2Choice {

    @XmlElement(name = "Aggtd")
    protected CardAggregated1 aggtd;
    @XmlElement(name = "Indv")
    protected CardIndividualTransaction2 indv;

    /**
     * 获取aggtd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CardAggregated1 }
     *     
     */
    public CardAggregated1 getAggtd() {
        return aggtd;
    }

    /**
     * 设置aggtd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CardAggregated1 }
     *     
     */
    public void setAggtd(CardAggregated1 value) {
        this.aggtd = value;
    }

    /**
     * 获取indv属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CardIndividualTransaction2 }
     *     
     */
    public CardIndividualTransaction2 getIndv() {
        return indv;
    }

    /**
     * 设置indv属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CardIndividualTransaction2 }
     *     
     */
    public void setIndv(CardIndividualTransaction2 value) {
        this.indv = value;
    }

}
