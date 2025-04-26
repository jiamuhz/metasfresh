//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:43:39 PM CST 
//


package de.metas.postfinance.ybinvoice.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * complextype AchievementDate
 * 
 * <p>AchievementDateType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AchievementDateType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StartDateAchievement" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="EndDateAchievement" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AchievementDateType", propOrder = {
    "startDateAchievement",
    "endDateAchievement"
})
public class AchievementDateType {

    @XmlElement(name = "StartDateAchievement", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDateAchievement;
    @XmlElement(name = "EndDateAchievement", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDateAchievement;

    /**
     * 获取startDateAchievement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDateAchievement() {
        return startDateAchievement;
    }

    /**
     * 设置startDateAchievement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDateAchievement(XMLGregorianCalendar value) {
        this.startDateAchievement = value;
    }

    /**
     * 获取endDateAchievement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDateAchievement() {
        return endDateAchievement;
    }

    /**
     * 设置endDateAchievement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDateAchievement(XMLGregorianCalendar value) {
        this.endDateAchievement = value;
    }

}
