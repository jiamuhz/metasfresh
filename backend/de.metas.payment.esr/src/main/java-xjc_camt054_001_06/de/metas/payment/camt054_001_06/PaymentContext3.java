//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.24 时间 11:44:25 AM CST 
//


package de.metas.payment.camt054_001_06;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PaymentContext3 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PaymentContext3"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CardPres" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}TrueFalseIndicator" minOccurs="0"/&gt;
 *         &lt;element name="CrdhldrPres" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}TrueFalseIndicator" minOccurs="0"/&gt;
 *         &lt;element name="OnLineCntxt" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}TrueFalseIndicator" minOccurs="0"/&gt;
 *         &lt;element name="AttndncCntxt" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}AttendanceContext1Code" minOccurs="0"/&gt;
 *         &lt;element name="TxEnvt" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}TransactionEnvironment1Code" minOccurs="0"/&gt;
 *         &lt;element name="TxChanl" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}TransactionChannel1Code" minOccurs="0"/&gt;
 *         &lt;element name="AttndntMsgCpbl" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}TrueFalseIndicator" minOccurs="0"/&gt;
 *         &lt;element name="AttndntLang" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}ISO2ALanguageCode" minOccurs="0"/&gt;
 *         &lt;element name="CardDataNtryMd" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}CardDataReading1Code"/&gt;
 *         &lt;element name="FllbckInd" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}TrueFalseIndicator" minOccurs="0"/&gt;
 *         &lt;element name="AuthntcnMtd" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.06}CardholderAuthentication2" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentContext3", propOrder = {
    "cardPres",
    "crdhldrPres",
    "onLineCntxt",
    "attndncCntxt",
    "txEnvt",
    "txChanl",
    "attndntMsgCpbl",
    "attndntLang",
    "cardDataNtryMd",
    "fllbckInd",
    "authntcnMtd"
})
public class PaymentContext3 {

    @XmlElement(name = "CardPres")
    protected Boolean cardPres;
    @XmlElement(name = "CrdhldrPres")
    protected Boolean crdhldrPres;
    @XmlElement(name = "OnLineCntxt")
    protected Boolean onLineCntxt;
    @XmlElement(name = "AttndncCntxt")
    @XmlSchemaType(name = "string")
    protected AttendanceContext1Code attndncCntxt;
    @XmlElement(name = "TxEnvt")
    @XmlSchemaType(name = "string")
    protected TransactionEnvironment1Code txEnvt;
    @XmlElement(name = "TxChanl")
    @XmlSchemaType(name = "string")
    protected TransactionChannel1Code txChanl;
    @XmlElement(name = "AttndntMsgCpbl")
    protected Boolean attndntMsgCpbl;
    @XmlElement(name = "AttndntLang")
    protected String attndntLang;
    @XmlElement(name = "CardDataNtryMd", required = true)
    @XmlSchemaType(name = "string")
    protected CardDataReading1Code cardDataNtryMd;
    @XmlElement(name = "FllbckInd")
    protected Boolean fllbckInd;
    @XmlElement(name = "AuthntcnMtd")
    protected CardholderAuthentication2 authntcnMtd;

    /**
     * 获取cardPres属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCardPres() {
        return cardPres;
    }

    /**
     * 设置cardPres属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCardPres(Boolean value) {
        this.cardPres = value;
    }

    /**
     * 获取crdhldrPres属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCrdhldrPres() {
        return crdhldrPres;
    }

    /**
     * 设置crdhldrPres属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCrdhldrPres(Boolean value) {
        this.crdhldrPres = value;
    }

    /**
     * 获取onLineCntxt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnLineCntxt() {
        return onLineCntxt;
    }

    /**
     * 设置onLineCntxt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnLineCntxt(Boolean value) {
        this.onLineCntxt = value;
    }

    /**
     * 获取attndncCntxt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AttendanceContext1Code }
     *     
     */
    public AttendanceContext1Code getAttndncCntxt() {
        return attndncCntxt;
    }

    /**
     * 设置attndncCntxt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AttendanceContext1Code }
     *     
     */
    public void setAttndncCntxt(AttendanceContext1Code value) {
        this.attndncCntxt = value;
    }

    /**
     * 获取txEnvt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TransactionEnvironment1Code }
     *     
     */
    public TransactionEnvironment1Code getTxEnvt() {
        return txEnvt;
    }

    /**
     * 设置txEnvt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionEnvironment1Code }
     *     
     */
    public void setTxEnvt(TransactionEnvironment1Code value) {
        this.txEnvt = value;
    }

    /**
     * 获取txChanl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TransactionChannel1Code }
     *     
     */
    public TransactionChannel1Code getTxChanl() {
        return txChanl;
    }

    /**
     * 设置txChanl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionChannel1Code }
     *     
     */
    public void setTxChanl(TransactionChannel1Code value) {
        this.txChanl = value;
    }

    /**
     * 获取attndntMsgCpbl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAttndntMsgCpbl() {
        return attndntMsgCpbl;
    }

    /**
     * 设置attndntMsgCpbl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAttndntMsgCpbl(Boolean value) {
        this.attndntMsgCpbl = value;
    }

    /**
     * 获取attndntLang属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttndntLang() {
        return attndntLang;
    }

    /**
     * 设置attndntLang属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttndntLang(String value) {
        this.attndntLang = value;
    }

    /**
     * 获取cardDataNtryMd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CardDataReading1Code }
     *     
     */
    public CardDataReading1Code getCardDataNtryMd() {
        return cardDataNtryMd;
    }

    /**
     * 设置cardDataNtryMd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CardDataReading1Code }
     *     
     */
    public void setCardDataNtryMd(CardDataReading1Code value) {
        this.cardDataNtryMd = value;
    }

    /**
     * 获取fllbckInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFllbckInd() {
        return fllbckInd;
    }

    /**
     * 设置fllbckInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFllbckInd(Boolean value) {
        this.fllbckInd = value;
    }

    /**
     * 获取authntcnMtd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CardholderAuthentication2 }
     *     
     */
    public CardholderAuthentication2 getAuthntcnMtd() {
        return authntcnMtd;
    }

    /**
     * 设置authntcnMtd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CardholderAuthentication2 }
     *     
     */
    public void setAuthntcnMtd(CardholderAuthentication2 value) {
        this.authntcnMtd = value;
    }

}
