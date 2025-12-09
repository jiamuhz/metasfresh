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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Bundles shipment service data.
 * 
 * <p>shipmentServiceData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="shipmentServiceData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="generalShipmentData" type="{http://dpd.com/common/service/types/ShipmentService/3.2}generalShipmentData"/&gt;
 *         &lt;element name="parcels" type="{http://dpd.com/common/service/types/ShipmentService/3.2}parcel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="productAndServiceData" type="{http://dpd.com/common/service/types/ShipmentService/3.2}productAndServiceData"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shipmentServiceData", propOrder = {
    "generalShipmentData",
    "parcels",
    "productAndServiceData"
})
public class ShipmentServiceData {

    @XmlElement(required = true)
    protected GeneralShipmentData generalShipmentData;
    protected List<Parcel> parcels;
    @XmlElement(required = true)
    protected ProductAndServiceData productAndServiceData;

    /**
     * 获取generalShipmentData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GeneralShipmentData }
     *     
     */
    public GeneralShipmentData getGeneralShipmentData() {
        return generalShipmentData;
    }

    /**
     * 设置generalShipmentData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralShipmentData }
     *     
     */
    public void setGeneralShipmentData(GeneralShipmentData value) {
        this.generalShipmentData = value;
    }

    /**
     * Gets the value of the parcels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parcels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParcels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Parcel }
     * 
     * 
     */
    public List<Parcel> getParcels() {
        if (parcels == null) {
            parcels = new ArrayList<Parcel>();
        }
        return this.parcels;
    }

    /**
     * 获取productAndServiceData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ProductAndServiceData }
     *     
     */
    public ProductAndServiceData getProductAndServiceData() {
        return productAndServiceData;
    }

    /**
     * 设置productAndServiceData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ProductAndServiceData }
     *     
     */
    public void setProductAndServiceData(ProductAndServiceData value) {
        this.productAndServiceData = value;
    }

}
