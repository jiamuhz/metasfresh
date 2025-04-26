//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:39:48 PM CST 
//


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_440.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>bodyType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="bodyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prolog" type="{http://www.forum-datenaustausch.ch/invoice}prologType"/&gt;
 *         &lt;element name="biller" type="{http://www.forum-datenaustausch.ch/invoice}partyType"/&gt;
 *         &lt;element name="provider" type="{http://www.forum-datenaustausch.ch/invoice}partyType"/&gt;
 *         &lt;element name="insurance" type="{http://www.forum-datenaustausch.ch/invoice}partyType"/&gt;
 *         &lt;element name="patient" type="{http://www.forum-datenaustausch.ch/invoice}patientAddressType"/&gt;
 *         &lt;element name="treatment" type="{http://www.forum-datenaustausch.ch/invoice}treatmentType" minOccurs="0"/&gt;
 *         &lt;element name="contact" type="{http://www.forum-datenaustausch.ch/invoice}contactAddressType"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="pending" type="{http://www.forum-datenaustausch.ch/invoice}pendingType"/&gt;
 *           &lt;element name="accepted" type="{http://www.forum-datenaustausch.ch/invoice}acceptedType"/&gt;
 *           &lt;element name="rejected" type="{http://www.forum-datenaustausch.ch/invoice}rejectedType"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bodyType", propOrder = {
    "prolog",
    "biller",
    "provider",
    "insurance",
    "patient",
    "treatment",
    "contact",
    "pending",
    "accepted",
    "rejected"
})
public class BodyType {

    @XmlElement(required = true)
    protected PrologType prolog;
    @XmlElement(required = true)
    protected PartyType biller;
    @XmlElement(required = true)
    protected PartyType provider;
    @XmlElement(required = true)
    protected PartyType insurance;
    @XmlElement(required = true)
    protected PatientAddressType patient;
    protected TreatmentType treatment;
    @XmlElement(required = true)
    protected ContactAddressType contact;
    protected PendingType pending;
    protected AcceptedType accepted;
    protected RejectedType rejected;

    /**
     * 获取prolog属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PrologType }
     *     
     */
    public PrologType getProlog() {
        return prolog;
    }

    /**
     * 设置prolog属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PrologType }
     *     
     */
    public void setProlog(PrologType value) {
        this.prolog = value;
    }

    /**
     * 获取biller属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getBiller() {
        return biller;
    }

    /**
     * 设置biller属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setBiller(PartyType value) {
        this.biller = value;
    }

    /**
     * 获取provider属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getProvider() {
        return provider;
    }

    /**
     * 设置provider属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setProvider(PartyType value) {
        this.provider = value;
    }

    /**
     * 获取insurance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getInsurance() {
        return insurance;
    }

    /**
     * 设置insurance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setInsurance(PartyType value) {
        this.insurance = value;
    }

    /**
     * 获取patient属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PatientAddressType }
     *     
     */
    public PatientAddressType getPatient() {
        return patient;
    }

    /**
     * 设置patient属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PatientAddressType }
     *     
     */
    public void setPatient(PatientAddressType value) {
        this.patient = value;
    }

    /**
     * 获取treatment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TreatmentType }
     *     
     */
    public TreatmentType getTreatment() {
        return treatment;
    }

    /**
     * 设置treatment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TreatmentType }
     *     
     */
    public void setTreatment(TreatmentType value) {
        this.treatment = value;
    }

    /**
     * 获取contact属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ContactAddressType }
     *     
     */
    public ContactAddressType getContact() {
        return contact;
    }

    /**
     * 设置contact属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ContactAddressType }
     *     
     */
    public void setContact(ContactAddressType value) {
        this.contact = value;
    }

    /**
     * 获取pending属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PendingType }
     *     
     */
    public PendingType getPending() {
        return pending;
    }

    /**
     * 设置pending属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PendingType }
     *     
     */
    public void setPending(PendingType value) {
        this.pending = value;
    }

    /**
     * 获取accepted属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AcceptedType }
     *     
     */
    public AcceptedType getAccepted() {
        return accepted;
    }

    /**
     * 设置accepted属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AcceptedType }
     *     
     */
    public void setAccepted(AcceptedType value) {
        this.accepted = value;
    }

    /**
     * 获取rejected属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RejectedType }
     *     
     */
    public RejectedType getRejected() {
        return rejected;
    }

    /**
     * 设置rejected属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RejectedType }
     *     
     */
    public void setRejected(RejectedType value) {
        this.rejected = value;
    }

}
