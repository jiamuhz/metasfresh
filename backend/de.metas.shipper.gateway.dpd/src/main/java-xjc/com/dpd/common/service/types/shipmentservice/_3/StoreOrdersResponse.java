//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:23:44 PM CST 
//


package com.dpd.common.service.types.shipmentservice._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>storeOrdersResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="storeOrdersResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="orderResult" type="{http://dpd.com/common/service/types/ShipmentService/3.2}storeOrdersResponseType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeOrdersResponse", propOrder = {
    "orderResult"
})
public class StoreOrdersResponse {

    @XmlElement(required = true)
    protected StoreOrdersResponseType orderResult;

    /**
     * 获取orderResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StoreOrdersResponseType }
     *     
     */
    public StoreOrdersResponseType getOrderResult() {
        return orderResult;
    }

    /**
     * 设置orderResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StoreOrdersResponseType }
     *     
     */
    public void setOrderResult(StoreOrdersResponseType value) {
        this.orderResult = value;
    }

}
