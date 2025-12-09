//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:23:44 PM CST 
//


package com.dpd.common.service.types.shipmentservice._3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>storeOrdersResponseType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="storeOrdersResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="parcellabelsPDF" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="shipmentResponses" type="{http://dpd.com/common/service/types/ShipmentService/3.2}shipmentResponse" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeOrdersResponseType", propOrder = {
    "parcellabelsPDF",
    "shipmentResponses"
})
public class StoreOrdersResponseType {

    protected byte[] parcellabelsPDF;
    protected List<ShipmentResponse> shipmentResponses;

    /**
     * 获取parcellabelsPDF属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getParcellabelsPDF() {
        return parcellabelsPDF;
    }

    /**
     * 设置parcellabelsPDF属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setParcellabelsPDF(byte[] value) {
        this.parcellabelsPDF = value;
    }

    /**
     * Gets the value of the shipmentResponses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentResponses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentResponses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentResponse }
     * 
     * 
     */
    public List<ShipmentResponse> getShipmentResponses() {
        if (shipmentResponses == null) {
            shipmentResponses = new ArrayList<ShipmentResponse>();
        }
        return this.shipmentResponses;
    }

}
