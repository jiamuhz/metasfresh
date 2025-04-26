//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:48 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_440.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>pendingType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="pendingType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.forum-datenaustausch.ch/invoice}statusType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="explanation" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_700" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.forum-datenaustausch.ch/invoice}notificationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pendingType", propOrder = {
    "explanation",
    "message"
})
public class PendingType
    extends StatusType
{

    protected String explanation;
    protected List<NotificationType> message;

    /**
     * 获取explanation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * 设置explanation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExplanation(String value) {
        this.explanation = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the message property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NotificationType }
     * 
     * 
     */
    public List<NotificationType> getMessage() {
        if (message == null) {
            message = new ArrayList<NotificationType>();
        }
        return this.message;
    }

}
