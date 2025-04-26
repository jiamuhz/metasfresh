//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:47 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_450.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
 *       &lt;choice maxOccurs="unbounded"&gt;
 *         &lt;element name="DataReference" type="{http://www.w3.org/2001/04/xmlenc#}ReferenceType"/&gt;
 *         &lt;element name="KeyReference" type="{http://www.w3.org/2001/04/xmlenc#}ReferenceType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dataReferenceOrKeyReference"
})
@XmlRootElement(name = "ReferenceList", namespace = "http://www.w3.org/2001/04/xmlenc#")
public class ReferenceList {

    @XmlElementRefs({
        @XmlElementRef(name = "DataReference", namespace = "http://www.w3.org/2001/04/xmlenc#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "KeyReference", namespace = "http://www.w3.org/2001/04/xmlenc#", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<ReferenceType2>> dataReferenceOrKeyReference;

    /**
     * Gets the value of the dataReferenceOrKeyReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataReferenceOrKeyReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataReferenceOrKeyReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ReferenceType2 }{@code >}
     * {@link JAXBElement }{@code <}{@link ReferenceType2 }{@code >}
     * 
     * 
     */
    public List<JAXBElement<ReferenceType2>> getDataReferenceOrKeyReference() {
        if (dataReferenceOrKeyReference == null) {
            dataReferenceOrKeyReference = new ArrayList<JAXBElement<ReferenceType2>>();
        }
        return this.dataReferenceOrKeyReference;
    }

}
