//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:43:39 PM CST 
//


package de.metas.postfinance.ybinvoice.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * complextype BillHeader
 * 
 * <p>BillHeaderType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BillHeaderType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocumentType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="BILL"/&gt;
 *               &lt;enumeration value="REMINDER"/&gt;
 *               &lt;enumeration value="CREDITADVICE"/&gt;
 *               &lt;enumeration value=""/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DocumentSubType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="final"/&gt;
 *               &lt;enumeration value="partial"/&gt;
 *               &lt;enumeration value="cancellation"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DocumentID"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="70"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DocumentReference" type="{}Reference" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DocumentDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="SenderParty"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Network" type="{}NetworkType" minOccurs="0"/&gt;
 *                   &lt;element name="TaxLiability" form="qualified"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="FRE"/&gt;
 *                         &lt;enumeration value="VAT"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="PartyType" type="{}PartyType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ReceiverParty"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Network" type="{}NetworkType" minOccurs="0"/&gt;
 *                   &lt;element name="PartyType" type="{}PartyType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="InvoiceReceivingParty" type="{}PartyType" minOccurs="0"/&gt;
 *         &lt;element name="DeliveryPlace" type="{}PartyType" minOccurs="0"/&gt;
 *         &lt;element name="AchievementDate" type="{}AchievementDateType" minOccurs="0"/&gt;
 *         &lt;element name="Currency"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="3"/&gt;
 *               &lt;pattern value="[A-Z]{3}"/&gt;
 *               &lt;enumeration value="CHF"/&gt;
 *               &lt;enumeration value="EUR"/&gt;
 *               &lt;enumeration value="AUD"/&gt;
 *               &lt;enumeration value="CAD"/&gt;
 *               &lt;enumeration value="DKK"/&gt;
 *               &lt;enumeration value="GBP"/&gt;
 *               &lt;enumeration value="JPY"/&gt;
 *               &lt;enumeration value="NOK"/&gt;
 *               &lt;enumeration value="SEK"/&gt;
 *               &lt;enumeration value="USD"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AccountAssignment" type="{}AccountAssignmentType" minOccurs="0"/&gt;
 *         &lt;element name="FixedReference" type="{}FixedReference" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Language"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="de"/&gt;
 *               &lt;enumeration value="fr"/&gt;
 *               &lt;enumeration value="it"/&gt;
 *               &lt;enumeration value="en"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PaymentInformation"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="PaymentDueDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="PaymentType"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="ESR"/&gt;
 *                         &lt;enumeration value="DD"/&gt;
 *                         &lt;enumeration value="CREDIT"/&gt;
 *                         &lt;enumeration value="OTHER"/&gt;
 *                         &lt;enumeration value="IBAN"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="fixAmount"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="Yes"/&gt;
 *                         &lt;enumeration value="No"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ESR" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="ESRCustomerNumber"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="11"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="ESRReferenceNumber"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="27"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="IBAN" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="BIC" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="11"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="BankName" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="50"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="IBAN"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="34"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="CreditorName" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="70"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="CreditorReference" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="140"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="ChargingOption" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;enumeration value="BEN"/&gt;
 *                                   &lt;enumeration value="OUR"/&gt;
 *                                   &lt;enumeration value="SHA"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Instalments" maxOccurs="99" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;extension base="{}Instalments"&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
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
@XmlType(name = "BillHeaderType", propOrder = {
    "documentType",
    "documentSubType",
    "documentID",
    "documentReference",
    "documentDate",
    "senderParty",
    "receiverParty",
    "invoiceReceivingParty",
    "deliveryPlace",
    "achievementDate",
    "currency",
    "accountAssignment",
    "fixedReference",
    "language",
    "paymentInformation",
    "freeText"
})
@XmlSeeAlso({
    de.metas.postfinance.ybinvoice.v2.BillType.Header.class
})
public class BillHeaderType {

    @XmlElement(name = "DocumentType", required = true)
    protected String documentType;
    @XmlElement(name = "DocumentSubType")
    protected String documentSubType;
    @XmlElement(name = "DocumentID", required = true)
    protected String documentID;
    @XmlElement(name = "DocumentReference")
    protected List<Reference> documentReference;
    @XmlElement(name = "DocumentDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar documentDate;
    @XmlElement(name = "SenderParty", required = true)
    protected BillHeaderType.SenderParty senderParty;
    @XmlElement(name = "ReceiverParty", required = true)
    protected BillHeaderType.ReceiverParty receiverParty;
    @XmlElement(name = "InvoiceReceivingParty")
    protected PartyType invoiceReceivingParty;
    @XmlElement(name = "DeliveryPlace")
    protected PartyType deliveryPlace;
    @XmlElement(name = "AchievementDate")
    protected AchievementDateType achievementDate;
    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @XmlElement(name = "AccountAssignment")
    protected AccountAssignmentType accountAssignment;
    @XmlElement(name = "FixedReference")
    protected List<FixedReference> fixedReference;
    @XmlElement(name = "Language", required = true)
    protected String language;
    @XmlElement(name = "PaymentInformation", required = true)
    protected BillHeaderType.PaymentInformation paymentInformation;
    @XmlElement(name = "FreeText")
    protected List<String> freeText;

    /**
     * 获取documentType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * 设置documentType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentType(String value) {
        this.documentType = value;
    }

    /**
     * 获取documentSubType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentSubType() {
        return documentSubType;
    }

    /**
     * 设置documentSubType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentSubType(String value) {
        this.documentSubType = value;
    }

    /**
     * 获取documentID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentID() {
        return documentID;
    }

    /**
     * 设置documentID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentID(String value) {
        this.documentID = value;
    }

    /**
     * Gets the value of the documentReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reference }
     * 
     * 
     */
    public List<Reference> getDocumentReference() {
        if (documentReference == null) {
            documentReference = new ArrayList<Reference>();
        }
        return this.documentReference;
    }

    /**
     * 获取documentDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDocumentDate() {
        return documentDate;
    }

    /**
     * 设置documentDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDocumentDate(XMLGregorianCalendar value) {
        this.documentDate = value;
    }

    /**
     * 获取senderParty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BillHeaderType.SenderParty }
     *     
     */
    public BillHeaderType.SenderParty getSenderParty() {
        return senderParty;
    }

    /**
     * 设置senderParty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BillHeaderType.SenderParty }
     *     
     */
    public void setSenderParty(BillHeaderType.SenderParty value) {
        this.senderParty = value;
    }

    /**
     * 获取receiverParty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BillHeaderType.ReceiverParty }
     *     
     */
    public BillHeaderType.ReceiverParty getReceiverParty() {
        return receiverParty;
    }

    /**
     * 设置receiverParty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BillHeaderType.ReceiverParty }
     *     
     */
    public void setReceiverParty(BillHeaderType.ReceiverParty value) {
        this.receiverParty = value;
    }

    /**
     * 获取invoiceReceivingParty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getInvoiceReceivingParty() {
        return invoiceReceivingParty;
    }

    /**
     * 设置invoiceReceivingParty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setInvoiceReceivingParty(PartyType value) {
        this.invoiceReceivingParty = value;
    }

    /**
     * 获取deliveryPlace属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getDeliveryPlace() {
        return deliveryPlace;
    }

    /**
     * 设置deliveryPlace属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setDeliveryPlace(PartyType value) {
        this.deliveryPlace = value;
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
     * 获取currency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置currency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
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
     * 获取language属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置language属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * 获取paymentInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BillHeaderType.PaymentInformation }
     *     
     */
    public BillHeaderType.PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * 设置paymentInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BillHeaderType.PaymentInformation }
     *     
     */
    public void setPaymentInformation(BillHeaderType.PaymentInformation value) {
        this.paymentInformation = value;
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
     *         &lt;element name="PaymentDueDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="PaymentType"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="ESR"/&gt;
     *               &lt;enumeration value="DD"/&gt;
     *               &lt;enumeration value="CREDIT"/&gt;
     *               &lt;enumeration value="OTHER"/&gt;
     *               &lt;enumeration value="IBAN"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="fixAmount"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="Yes"/&gt;
     *               &lt;enumeration value="No"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ESR" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="ESRCustomerNumber"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="11"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="ESRReferenceNumber"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="27"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="IBAN" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="BIC" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="11"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="BankName" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="50"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="IBAN"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="34"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="CreditorName" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="70"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="CreditorReference" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="140"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="ChargingOption" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;enumeration value="BEN"/&gt;
     *                         &lt;enumeration value="OUR"/&gt;
     *                         &lt;enumeration value="SHA"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Instalments" maxOccurs="99" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;extension base="{}Instalments"&gt;
     *               &lt;/extension&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
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
        "paymentDueDate",
        "paymentType",
        "fixAmount",
        "esr",
        "iban",
        "instalments"
    })
    public static class PaymentInformation {

        @XmlElement(name = "PaymentDueDate", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar paymentDueDate;
        @XmlElement(name = "PaymentType", required = true)
        protected String paymentType;
        @XmlElement(required = true)
        protected String fixAmount;
        @XmlElement(name = "ESR")
        protected BillHeaderType.PaymentInformation.ESR esr;
        @XmlElement(name = "IBAN")
        protected BillHeaderType.PaymentInformation.IBAN iban;
        @XmlElement(name = "Instalments")
        protected List<BillHeaderType.PaymentInformation.Instalments> instalments;

        /**
         * 获取paymentDueDate属性的值。
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getPaymentDueDate() {
            return paymentDueDate;
        }

        /**
         * 设置paymentDueDate属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setPaymentDueDate(XMLGregorianCalendar value) {
            this.paymentDueDate = value;
        }

        /**
         * 获取paymentType属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPaymentType() {
            return paymentType;
        }

        /**
         * 设置paymentType属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPaymentType(String value) {
            this.paymentType = value;
        }

        /**
         * 获取fixAmount属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFixAmount() {
            return fixAmount;
        }

        /**
         * 设置fixAmount属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFixAmount(String value) {
            this.fixAmount = value;
        }

        /**
         * 获取esr属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BillHeaderType.PaymentInformation.ESR }
         *     
         */
        public BillHeaderType.PaymentInformation.ESR getESR() {
            return esr;
        }

        /**
         * 设置esr属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BillHeaderType.PaymentInformation.ESR }
         *     
         */
        public void setESR(BillHeaderType.PaymentInformation.ESR value) {
            this.esr = value;
        }

        /**
         * 获取iban属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BillHeaderType.PaymentInformation.IBAN }
         *     
         */
        public BillHeaderType.PaymentInformation.IBAN getIBAN() {
            return iban;
        }

        /**
         * 设置iban属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BillHeaderType.PaymentInformation.IBAN }
         *     
         */
        public void setIBAN(BillHeaderType.PaymentInformation.IBAN value) {
            this.iban = value;
        }

        /**
         * Gets the value of the instalments property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the instalments property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInstalments().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BillHeaderType.PaymentInformation.Instalments }
         * 
         * 
         */
        public List<BillHeaderType.PaymentInformation.Instalments> getInstalments() {
            if (instalments == null) {
                instalments = new ArrayList<BillHeaderType.PaymentInformation.Instalments>();
            }
            return this.instalments;
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
         *         &lt;element name="ESRCustomerNumber"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="11"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="ESRReferenceNumber"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="27"/&gt;
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
            "esrCustomerNumber",
            "esrReferenceNumber"
        })
        public static class ESR {

            @XmlElement(name = "ESRCustomerNumber", required = true)
            protected String esrCustomerNumber;
            @XmlElement(name = "ESRReferenceNumber", required = true)
            protected String esrReferenceNumber;

            /**
             * 获取esrCustomerNumber属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getESRCustomerNumber() {
                return esrCustomerNumber;
            }

            /**
             * 设置esrCustomerNumber属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setESRCustomerNumber(String value) {
                this.esrCustomerNumber = value;
            }

            /**
             * 获取esrReferenceNumber属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getESRReferenceNumber() {
                return esrReferenceNumber;
            }

            /**
             * 设置esrReferenceNumber属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setESRReferenceNumber(String value) {
                this.esrReferenceNumber = value;
            }

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
         *         &lt;element name="BIC" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="11"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="BankName" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="50"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="IBAN"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="34"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="CreditorName" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="70"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="CreditorReference" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="140"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="ChargingOption" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;enumeration value="BEN"/&gt;
         *               &lt;enumeration value="OUR"/&gt;
         *               &lt;enumeration value="SHA"/&gt;
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
            "bic",
            "bankName",
            "iban",
            "creditorName",
            "creditorReference",
            "chargingOption"
        })
        public static class IBAN {

            @XmlElement(name = "BIC")
            protected String bic;
            @XmlElement(name = "BankName")
            protected String bankName;
            @XmlElement(name = "IBAN", required = true)
            protected String iban;
            @XmlElement(name = "CreditorName")
            protected String creditorName;
            @XmlElement(name = "CreditorReference")
            protected String creditorReference;
            @XmlElement(name = "ChargingOption")
            protected String chargingOption;

            /**
             * 获取bic属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBIC() {
                return bic;
            }

            /**
             * 设置bic属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBIC(String value) {
                this.bic = value;
            }

            /**
             * 获取bankName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBankName() {
                return bankName;
            }

            /**
             * 设置bankName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBankName(String value) {
                this.bankName = value;
            }

            /**
             * 获取iban属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIBAN() {
                return iban;
            }

            /**
             * 设置iban属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIBAN(String value) {
                this.iban = value;
            }

            /**
             * 获取creditorName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCreditorName() {
                return creditorName;
            }

            /**
             * 设置creditorName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCreditorName(String value) {
                this.creditorName = value;
            }

            /**
             * 获取creditorReference属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCreditorReference() {
                return creditorReference;
            }

            /**
             * 设置creditorReference属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCreditorReference(String value) {
                this.creditorReference = value;
            }

            /**
             * 获取chargingOption属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getChargingOption() {
                return chargingOption;
            }

            /**
             * 设置chargingOption属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setChargingOption(String value) {
                this.chargingOption = value;
            }

        }


        /**
         * <p>anonymous complex type的 Java 类。
         * 
         * <p>以下模式片段指定包含在此类中的预期内容。
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;extension base="{}Instalments"&gt;
         *     &lt;/extension&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Instalments
            extends de.metas.postfinance.ybinvoice.v2.Instalments
        {


        }

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
     *         &lt;element name="Network" type="{}NetworkType" minOccurs="0"/&gt;
     *         &lt;element name="PartyType" type="{}PartyType"/&gt;
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
        "network",
        "partyType"
    })
    public static class ReceiverParty {

        @XmlElement(name = "Network")
        protected NetworkType network;
        @XmlElement(name = "PartyType", required = true)
        protected PartyType partyType;

        /**
         * 获取network属性的值。
         * 
         * @return
         *     possible object is
         *     {@link NetworkType }
         *     
         */
        public NetworkType getNetwork() {
            return network;
        }

        /**
         * 设置network属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link NetworkType }
         *     
         */
        public void setNetwork(NetworkType value) {
            this.network = value;
        }

        /**
         * 获取partyType属性的值。
         * 
         * @return
         *     possible object is
         *     {@link PartyType }
         *     
         */
        public PartyType getPartyType() {
            return partyType;
        }

        /**
         * 设置partyType属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link PartyType }
         *     
         */
        public void setPartyType(PartyType value) {
            this.partyType = value;
        }

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
     *         &lt;element name="Network" type="{}NetworkType" minOccurs="0"/&gt;
     *         &lt;element name="TaxLiability" form="qualified"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="FRE"/&gt;
     *               &lt;enumeration value="VAT"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="PartyType" type="{}PartyType"/&gt;
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
        "network",
        "taxLiability",
        "partyType"
    })
    public static class SenderParty {

        @XmlElement(name = "Network")
        protected NetworkType network;
        @XmlElement(name = "TaxLiability", required = true)
        protected String taxLiability;
        @XmlElement(name = "PartyType", required = true)
        protected PartyType partyType;

        /**
         * 获取network属性的值。
         * 
         * @return
         *     possible object is
         *     {@link NetworkType }
         *     
         */
        public NetworkType getNetwork() {
            return network;
        }

        /**
         * 设置network属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link NetworkType }
         *     
         */
        public void setNetwork(NetworkType value) {
            this.network = value;
        }

        /**
         * 获取taxLiability属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTaxLiability() {
            return taxLiability;
        }

        /**
         * 设置taxLiability属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTaxLiability(String value) {
            this.taxLiability = value;
        }

        /**
         * 获取partyType属性的值。
         * 
         * @return
         *     possible object is
         *     {@link PartyType }
         *     
         */
        public PartyType getPartyType() {
            return partyType;
        }

        /**
         * 设置partyType属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link PartyType }
         *     
         */
        public void setPartyType(PartyType value) {
            this.partyType = value;
        }

    }

}
