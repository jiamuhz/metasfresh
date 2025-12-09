//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:22:47 PM CST 
//


package de.metas.shipper.gateway.go.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Sendungsnummern complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Sendungsnummern"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Seitengroesse"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="A4"/&gt;
 *               &lt;enumeration value="A5"/&gt;
 *               &lt;enumeration value="A6"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SendungsnummerAX4" maxOccurs="unbounded"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *               &lt;maxInclusive value="15"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sendungsnummern", propOrder = {
    "seitengroesse",
    "sendungsnummerAX4"
})
public class Sendungsnummern {

    @XmlElement(name = "Seitengroesse", required = true)
    protected String seitengroesse;
    @XmlElement(name = "SendungsnummerAX4", type = Integer.class)
    protected List<Integer> sendungsnummerAX4;

    /**
     * 获取seitengroesse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeitengroesse() {
        return seitengroesse;
    }

    /**
     * 设置seitengroesse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeitengroesse(String value) {
        this.seitengroesse = value;
    }

    /**
     * Gets the value of the sendungsnummerAX4 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sendungsnummerAX4 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSendungsnummerAX4().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getSendungsnummerAX4() {
        if (sendungsnummerAX4 == null) {
            sendungsnummerAX4 = new ArrayList<Integer>();
        }
        return this.sendungsnummerAX4;
    }

}
