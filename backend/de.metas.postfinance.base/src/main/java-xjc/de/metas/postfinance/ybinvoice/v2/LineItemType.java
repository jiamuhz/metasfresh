//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:43:39 PM CST 
//


package de.metas.postfinance.ybinvoice.v2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * complextype LineItem
 * 
 * <p>LineItemType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LineItemType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LineItemType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="NORMAL"/&gt;
 *               &lt;enumeration value="LINEITEMALLOWANCEANDCHARGE"/&gt;
 *               &lt;enumeration value="GLOBALALLOWANCEANDCHARGE"/&gt;
 *               &lt;enumeration value="SHIPPINGANDHANDLING"/&gt;
 *               &lt;enumeration value="ROUNDING"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="LineItemID"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ProductGroup" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ProductSubGroup" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AchievementDate" type="{}AchievementDateType" minOccurs="0"/&gt;
 *         &lt;element name="ProductDescription"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ProductID" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EAN" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="\d{0,18}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Quantity"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="QuantityDescription"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PriceUnit"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PriceInclusiveTax" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="PriceExclusiveTax" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="Tax" type="{}TaxLineItemType" minOccurs="0"/&gt;
 *         &lt;element name="AmountInclusiveTax" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AmountExclusiveTax"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AccountAssignment" type="{}AccountAssignmentType" minOccurs="0"/&gt;
 *         &lt;element name="FixedReference" type="{}FixedReference" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="LineItemReference" type="{}Reference" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AllowanceAndCharge" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="BaseAmount" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Rate" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="AllowanceAndChargeCode" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="A"/&gt;
 *                         &lt;enumeration value="B"/&gt;
 *                         &lt;enumeration value="C"/&gt;
 *                         &lt;enumeration value="D"/&gt;
 *                         &lt;enumeration value="E"/&gt;
 *                         &lt;enumeration value="F"/&gt;
 *                         &lt;enumeration value="G"/&gt;
 *                         &lt;enumeration value="H"/&gt;
 *                         &lt;enumeration value="J"/&gt;
 *                         &lt;enumeration value="K"/&gt;
 *                         &lt;enumeration value="L"/&gt;
 *                         &lt;enumeration value="M"/&gt;
 *                         &lt;enumeration value="N"/&gt;
 *                         &lt;enumeration value="O"/&gt;
 *                         &lt;enumeration value="P"/&gt;
 *                         &lt;enumeration value="Q"/&gt;
 *                         &lt;enumeration value="R"/&gt;
 *                         &lt;enumeration value="S"/&gt;
 *                         &lt;enumeration value="T"/&gt;
 *                         &lt;enumeration value="U"/&gt;
 *                         &lt;enumeration value="V"/&gt;
 *                         &lt;enumeration value="W"/&gt;
 *                         &lt;enumeration value="X"/&gt;
 *                         &lt;enumeration value="Y"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FreeText" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
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
@XmlType(name = "LineItemType", propOrder = {
    "lineItemType",
    "lineItemID",
    "productGroup",
    "productSubGroup",
    "achievementDate",
    "productDescription",
    "productID",
    "ean",
    "quantity",
    "quantityDescription",
    "priceUnit",
    "priceInclusiveTax",
    "priceExclusiveTax",
    "tax",
    "amountInclusiveTax",
    "amountExclusiveTax",
    "accountAssignment",
    "fixedReference",
    "lineItemReference",
    "allowanceAndCharge",
    "freeText"
})
public class LineItemType {

    @XmlElement(name = "LineItemType", required = true)
    protected String lineItemType;
    @XmlElement(name = "LineItemID", required = true)
    protected String lineItemID;
    @XmlElement(name = "ProductGroup")
    protected String productGroup;
    @XmlElement(name = "ProductSubGroup")
    protected String productSubGroup;
    @XmlElement(name = "AchievementDate")
    protected AchievementDateType achievementDate;
    @XmlElement(name = "ProductDescription", required = true)
    protected String productDescription;
    @XmlElement(name = "ProductID")
    protected String productID;
    @XmlElement(name = "EAN")
    protected String ean;
    @XmlElement(name = "Quantity", required = true)
    protected BigDecimal quantity;
    @XmlElement(name = "QuantityDescription", required = true)
    protected String quantityDescription;
    @XmlElement(name = "PriceUnit", required = true)
    protected BigDecimal priceUnit;
    @XmlElement(name = "PriceInclusiveTax")
    protected BigDecimal priceInclusiveTax;
    @XmlElement(name = "PriceExclusiveTax")
    protected BigDecimal priceExclusiveTax;
    @XmlElement(name = "Tax")
    protected TaxLineItemType tax;
    @XmlElement(name = "AmountInclusiveTax")
    protected BigDecimal amountInclusiveTax;
    @XmlElement(name = "AmountExclusiveTax", required = true)
    protected BigDecimal amountExclusiveTax;
    @XmlElement(name = "AccountAssignment")
    protected AccountAssignmentType accountAssignment;
    @XmlElement(name = "FixedReference")
    protected List<FixedReference> fixedReference;
    @XmlElement(name = "LineItemReference")
    protected List<Reference> lineItemReference;
    @XmlElement(name = "AllowanceAndCharge")
    protected LineItemType.AllowanceAndCharge allowanceAndCharge;
    @XmlElement(name = "FreeText")
    protected List<String> freeText;

    /**
     * 获取lineItemType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineItemType() {
        return lineItemType;
    }

    /**
     * 设置lineItemType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineItemType(String value) {
        this.lineItemType = value;
    }

    /**
     * 获取lineItemID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineItemID() {
        return lineItemID;
    }

    /**
     * 设置lineItemID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineItemID(String value) {
        this.lineItemID = value;
    }

    /**
     * 获取productGroup属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductGroup() {
        return productGroup;
    }

    /**
     * 设置productGroup属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductGroup(String value) {
        this.productGroup = value;
    }

    /**
     * 获取productSubGroup属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductSubGroup() {
        return productSubGroup;
    }

    /**
     * 设置productSubGroup属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductSubGroup(String value) {
        this.productSubGroup = value;
    }

    /**
     * 获取achievementDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AchievementDateType }
     *     
     */
    public AchievementDateType getAchievementDate() {
        return achievementDate;
    }

    /**
     * 设置achievementDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AchievementDateType }
     *     
     */
    public void setAchievementDate(AchievementDateType value) {
        this.achievementDate = value;
    }

    /**
     * 获取productDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * 设置productDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductDescription(String value) {
        this.productDescription = value;
    }

    /**
     * 获取productID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductID() {
        return productID;
    }

    /**
     * 设置productID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductID(String value) {
        this.productID = value;
    }

    /**
     * 获取ean属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEAN() {
        return ean;
    }

    /**
     * 设置ean属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEAN(String value) {
        this.ean = value;
    }

    /**
     * 获取quantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 设置quantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQuantity(BigDecimal value) {
        this.quantity = value;
    }

    /**
     * 获取quantityDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantityDescription() {
        return quantityDescription;
    }

    /**
     * 设置quantityDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantityDescription(String value) {
        this.quantityDescription = value;
    }

    /**
     * 获取priceUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriceUnit() {
        return priceUnit;
    }

    /**
     * 设置priceUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriceUnit(BigDecimal value) {
        this.priceUnit = value;
    }

    /**
     * 获取priceInclusiveTax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriceInclusiveTax() {
        return priceInclusiveTax;
    }

    /**
     * 设置priceInclusiveTax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriceInclusiveTax(BigDecimal value) {
        this.priceInclusiveTax = value;
    }

    /**
     * 获取priceExclusiveTax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriceExclusiveTax() {
        return priceExclusiveTax;
    }

    /**
     * 设置priceExclusiveTax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriceExclusiveTax(BigDecimal value) {
        this.priceExclusiveTax = value;
    }

    /**
     * 获取tax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TaxLineItemType }
     *     
     */
    public TaxLineItemType getTax() {
        return tax;
    }

    /**
     * 设置tax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TaxLineItemType }
     *     
     */
    public void setTax(TaxLineItemType value) {
        this.tax = value;
    }

    /**
     * 获取amountInclusiveTax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountInclusiveTax() {
        return amountInclusiveTax;
    }

    /**
     * 设置amountInclusiveTax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountInclusiveTax(BigDecimal value) {
        this.amountInclusiveTax = value;
    }

    /**
     * 获取amountExclusiveTax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountExclusiveTax() {
        return amountExclusiveTax;
    }

    /**
     * 设置amountExclusiveTax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountExclusiveTax(BigDecimal value) {
        this.amountExclusiveTax = value;
    }

    /**
     * 获取accountAssignment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AccountAssignmentType }
     *     
     */
    public AccountAssignmentType getAccountAssignment() {
        return accountAssignment;
    }

    /**
     * 设置accountAssignment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AccountAssignmentType }
     *     
     */
    public void setAccountAssignment(AccountAssignmentType value) {
        this.accountAssignment = value;
    }

    /**
     * Gets the value of the fixedReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fixedReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFixedReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FixedReference }
     * 
     * 
     */
    public List<FixedReference> getFixedReference() {
        if (fixedReference == null) {
            fixedReference = new ArrayList<FixedReference>();
        }
        return this.fixedReference;
    }

    /**
     * Gets the value of the lineItemReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineItemReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineItemReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reference }
     * 
     * 
     */
    public List<Reference> getLineItemReference() {
        if (lineItemReference == null) {
            lineItemReference = new ArrayList<Reference>();
        }
        return this.lineItemReference;
    }

    /**
     * 获取allowanceAndCharge属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LineItemType.AllowanceAndCharge }
     *     
     */
    public LineItemType.AllowanceAndCharge getAllowanceAndCharge() {
        return allowanceAndCharge;
    }

    /**
     * 设置allowanceAndCharge属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LineItemType.AllowanceAndCharge }
     *     
     */
    public void setAllowanceAndCharge(LineItemType.AllowanceAndCharge value) {
        this.allowanceAndCharge = value;
    }

    /**
     * Gets the value of the freeText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the freeText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFreeText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFreeText() {
        if (freeText == null) {
            freeText = new ArrayList<String>();
        }
        return this.freeText;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="BaseAmount" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Rate" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="AllowanceAndChargeCode" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="A"/&gt;
     *               &lt;enumeration value="B"/&gt;
     *               &lt;enumeration value="C"/&gt;
     *               &lt;enumeration value="D"/&gt;
     *               &lt;enumeration value="E"/&gt;
     *               &lt;enumeration value="F"/&gt;
     *               &lt;enumeration value="G"/&gt;
     *               &lt;enumeration value="H"/&gt;
     *               &lt;enumeration value="J"/&gt;
     *               &lt;enumeration value="K"/&gt;
     *               &lt;enumeration value="L"/&gt;
     *               &lt;enumeration value="M"/&gt;
     *               &lt;enumeration value="N"/&gt;
     *               &lt;enumeration value="O"/&gt;
     *               &lt;enumeration value="P"/&gt;
     *               &lt;enumeration value="Q"/&gt;
     *               &lt;enumeration value="R"/&gt;
     *               &lt;enumeration value="S"/&gt;
     *               &lt;enumeration value="T"/&gt;
     *               &lt;enumeration value="U"/&gt;
     *               &lt;enumeration value="V"/&gt;
     *               &lt;enumeration value="W"/&gt;
     *               &lt;enumeration value="X"/&gt;
     *               &lt;enumeration value="Y"/&gt;
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
    @XmlType(name = "", propOrder = {
        "baseAmount",
        "rate",
        "allowanceAndChargeCode"
    })
    public static class AllowanceAndCharge {

        @XmlElement(name = "BaseAmount")
        protected BigDecimal baseAmount;
        @XmlElement(name = "Rate")
        protected BigDecimal rate;
        @XmlElement(name = "AllowanceAndChargeCode")
        protected String allowanceAndChargeCode;

        /**
         * 获取baseAmount属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getBaseAmount() {
            return baseAmount;
        }

        /**
         * 设置baseAmount属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setBaseAmount(BigDecimal value) {
            this.baseAmount = value;
        }

        /**
         * 获取rate属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getRate() {
            return rate;
        }

        /**
         * 设置rate属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setRate(BigDecimal value) {
            this.rate = value;
        }

        /**
         * 获取allowanceAndChargeCode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAllowanceAndChargeCode() {
            return allowanceAndChargeCode;
        }

        /**
         * 设置allowanceAndChargeCode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAllowanceAndChargeCode(String value) {
            this.allowanceAndChargeCode = value;
        }

    }

}
