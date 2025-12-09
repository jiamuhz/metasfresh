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
 * <p>productAndServiceData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="productAndServiceData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="orderType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="consignment"/&gt;
 *               &lt;enumeration value="collection request order"/&gt;
 *               &lt;enumeration value="pickup information"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="saturdayDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="exWorksDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="guarantee" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="tyres" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="personalDelivery" type="{http://dpd.com/common/service/types/ShipmentService/3.2}personalDelivery" minOccurs="0"/&gt;
 *         &lt;element name="pickup" type="{http://dpd.com/common/service/types/ShipmentService/3.2}pickup" minOccurs="0"/&gt;
 *         &lt;element name="parcelShopDelivery" type="{http://dpd.com/common/service/types/ShipmentService/3.2}parcelShopDelivery" minOccurs="0"/&gt;
 *         &lt;element name="predict" type="{http://dpd.com/common/service/types/ShipmentService/3.2}notification" minOccurs="0"/&gt;
 *         &lt;element name="personalDeliveryNotification" type="{http://dpd.com/common/service/types/ShipmentService/3.2}notification" minOccurs="0"/&gt;
 *         &lt;element name="proactiveNotification" type="{http://dpd.com/common/service/types/ShipmentService/3.2}proactiveNotification" maxOccurs="5" minOccurs="0"/&gt;
 *         &lt;element name="delivery" type="{http://dpd.com/common/service/types/ShipmentService/3.2}delivery" minOccurs="0"/&gt;
 *         &lt;element name="invoiceAddress" type="{http://dpd.com/common/service/types/ShipmentService/3.2}address" minOccurs="0"/&gt;
 *         &lt;element name="countrySpecificService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productAndServiceData", propOrder = {
    "orderType",
    "saturdayDelivery",
    "exWorksDelivery",
    "guarantee",
    "tyres",
    "personalDelivery",
    "pickup",
    "parcelShopDelivery",
    "predict",
    "personalDeliveryNotification",
    "proactiveNotification",
    "delivery",
    "invoiceAddress",
    "countrySpecificService"
})
public class ProductAndServiceData {

    @XmlElement(required = true)
    protected String orderType;
    protected Boolean saturdayDelivery;
    protected Boolean exWorksDelivery;
    protected Boolean guarantee;
    protected Boolean tyres;
    protected PersonalDelivery personalDelivery;
    protected Pickup pickup;
    protected ParcelShopDelivery parcelShopDelivery;
    protected Notification predict;
    protected Notification personalDeliveryNotification;
    protected List<ProactiveNotification> proactiveNotification;
    protected Delivery delivery;
    protected Address invoiceAddress;
    protected String countrySpecificService;

    /**
     * 获取orderType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置orderType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * 获取saturdayDelivery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSaturdayDelivery() {
        return saturdayDelivery;
    }

    /**
     * 设置saturdayDelivery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSaturdayDelivery(Boolean value) {
        this.saturdayDelivery = value;
    }

    /**
     * 获取exWorksDelivery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExWorksDelivery() {
        return exWorksDelivery;
    }

    /**
     * 设置exWorksDelivery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExWorksDelivery(Boolean value) {
        this.exWorksDelivery = value;
    }

    /**
     * 获取guarantee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGuarantee() {
        return guarantee;
    }

    /**
     * 设置guarantee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGuarantee(Boolean value) {
        this.guarantee = value;
    }

    /**
     * 获取tyres属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTyres() {
        return tyres;
    }

    /**
     * 设置tyres属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTyres(Boolean value) {
        this.tyres = value;
    }

    /**
     * 获取personalDelivery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PersonalDelivery }
     *     
     */
    public PersonalDelivery getPersonalDelivery() {
        return personalDelivery;
    }

    /**
     * 设置personalDelivery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalDelivery }
     *     
     */
    public void setPersonalDelivery(PersonalDelivery value) {
        this.personalDelivery = value;
    }

    /**
     * 获取pickup属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Pickup }
     *     
     */
    public Pickup getPickup() {
        return pickup;
    }

    /**
     * 设置pickup属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Pickup }
     *     
     */
    public void setPickup(Pickup value) {
        this.pickup = value;
    }

    /**
     * 获取parcelShopDelivery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ParcelShopDelivery }
     *     
     */
    public ParcelShopDelivery getParcelShopDelivery() {
        return parcelShopDelivery;
    }

    /**
     * 设置parcelShopDelivery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ParcelShopDelivery }
     *     
     */
    public void setParcelShopDelivery(ParcelShopDelivery value) {
        this.parcelShopDelivery = value;
    }

    /**
     * 获取predict属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Notification }
     *     
     */
    public Notification getPredict() {
        return predict;
    }

    /**
     * 设置predict属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Notification }
     *     
     */
    public void setPredict(Notification value) {
        this.predict = value;
    }

    /**
     * 获取personalDeliveryNotification属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Notification }
     *     
     */
    public Notification getPersonalDeliveryNotification() {
        return personalDeliveryNotification;
    }

    /**
     * 设置personalDeliveryNotification属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Notification }
     *     
     */
    public void setPersonalDeliveryNotification(Notification value) {
        this.personalDeliveryNotification = value;
    }

    /**
     * Gets the value of the proactiveNotification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proactiveNotification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProactiveNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProactiveNotification }
     * 
     * 
     */
    public List<ProactiveNotification> getProactiveNotification() {
        if (proactiveNotification == null) {
            proactiveNotification = new ArrayList<ProactiveNotification>();
        }
        return this.proactiveNotification;
    }

    /**
     * 获取delivery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Delivery }
     *     
     */
    public Delivery getDelivery() {
        return delivery;
    }

    /**
     * 设置delivery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Delivery }
     *     
     */
    public void setDelivery(Delivery value) {
        this.delivery = value;
    }

    /**
     * 获取invoiceAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getInvoiceAddress() {
        return invoiceAddress;
    }

    /**
     * 设置invoiceAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setInvoiceAddress(Address value) {
        this.invoiceAddress = value;
    }

    /**
     * 获取countrySpecificService属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountrySpecificService() {
        return countrySpecificService;
    }

    /**
     * 设置countrySpecificService属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountrySpecificService(String value) {
        this.countrySpecificService = value;
    }

}
