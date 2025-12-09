//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:18:04 PM CST 
//


package de.metas.printing.esb.base.jaxb.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PRT_PrinterHW_CalibrationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PRT_PrinterHW_CalibrationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AD_PrinterHW_ID" type="{}PRT_AD_PrinterHWType" minOccurs="0"/&gt;
 *         &lt;element name="AD_PrinterHW_MediaSize_ID" type="{}PRT_AD_PrinterHW_MediaSizeType" minOccurs="0"/&gt;
 *         &lt;element name="AD_PrinterHW_MediaTray_ID" type="{}PRT_AD_PrinterHW_MediaTrayType" minOccurs="0"/&gt;
 *         &lt;element name="CalX" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="CalY" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute ref="{}AD_Client_Value"/&gt;
 *       &lt;attribute ref="{}ReplicationEvent"/&gt;
 *       &lt;attribute ref="{}ReplicationMode"/&gt;
 *       &lt;attribute ref="{}ReplicationType"/&gt;
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" fixed="*" /&gt;
 *       &lt;attribute ref="{}SequenceNo"/&gt;
 *       &lt;attribute ref="{}TrxName"/&gt;
 *       &lt;attribute ref="{}AD_Session_ID"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PRT_PrinterHW_CalibrationType", propOrder = {
    "adPrinterHWID",
    "adPrinterHWMediaSizeID",
    "adPrinterHWMediaTrayID",
    "calX",
    "calY"
})
public class PRTPrinterHWCalibrationType {

    @XmlElement(name = "AD_PrinterHW_ID")
    protected PRTADPrinterHWType adPrinterHWID;
    @XmlElement(name = "AD_PrinterHW_MediaSize_ID")
    protected PRTADPrinterHWMediaSizeType adPrinterHWMediaSizeID;
    @XmlElement(name = "AD_PrinterHW_MediaTray_ID")
    protected PRTADPrinterHWMediaTrayType adPrinterHWMediaTrayID;
    @XmlElement(name = "CalX", required = true)
    protected BigInteger calX;
    @XmlElement(name = "CalY", required = true)
    protected BigInteger calY;
    @XmlAttribute(name = "AD_Client_Value")
    protected String adClientValueAttr;
    @XmlAttribute(name = "ReplicationEvent")
    protected ReplicationEventEnum replicationEventAttr;
    @XmlAttribute(name = "ReplicationMode")
    protected ReplicationModeEnum replicationModeAttr;
    @XmlAttribute(name = "ReplicationType")
    protected ReplicationTypeEnum replicationTypeAttr;
    @XmlAttribute(name = "Version")
    protected String versionAttr;
    @XmlAttribute(name = "SequenceNo")
    protected BigInteger sequenceNoAttr;
    @XmlAttribute(name = "TrxName")
    protected String trxNameAttr;
    @XmlAttribute(name = "AD_Session_ID")
    protected BigInteger adSessionIDAttr;

    /**
     * 获取adPrinterHWID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PRTADPrinterHWType }
     *     
     */
    public PRTADPrinterHWType getADPrinterHWID() {
        return adPrinterHWID;
    }

    /**
     * 设置adPrinterHWID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PRTADPrinterHWType }
     *     
     */
    public void setADPrinterHWID(PRTADPrinterHWType value) {
        this.adPrinterHWID = value;
    }

    /**
     * 获取adPrinterHWMediaSizeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PRTADPrinterHWMediaSizeType }
     *     
     */
    public PRTADPrinterHWMediaSizeType getADPrinterHWMediaSizeID() {
        return adPrinterHWMediaSizeID;
    }

    /**
     * 设置adPrinterHWMediaSizeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PRTADPrinterHWMediaSizeType }
     *     
     */
    public void setADPrinterHWMediaSizeID(PRTADPrinterHWMediaSizeType value) {
        this.adPrinterHWMediaSizeID = value;
    }

    /**
     * 获取adPrinterHWMediaTrayID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PRTADPrinterHWMediaTrayType }
     *     
     */
    public PRTADPrinterHWMediaTrayType getADPrinterHWMediaTrayID() {
        return adPrinterHWMediaTrayID;
    }

    /**
     * 设置adPrinterHWMediaTrayID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PRTADPrinterHWMediaTrayType }
     *     
     */
    public void setADPrinterHWMediaTrayID(PRTADPrinterHWMediaTrayType value) {
        this.adPrinterHWMediaTrayID = value;
    }

    /**
     * 获取calX属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCalX() {
        return calX;
    }

    /**
     * 设置calX属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCalX(BigInteger value) {
        this.calX = value;
    }

    /**
     * 获取calY属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCalY() {
        return calY;
    }

    /**
     * 设置calY属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCalY(BigInteger value) {
        this.calY = value;
    }

    /**
     * 获取adClientValueAttr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADClientValueAttr() {
        return adClientValueAttr;
    }

    /**
     * 设置adClientValueAttr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADClientValueAttr(String value) {
        this.adClientValueAttr = value;
    }

    /**
     * 获取replicationEventAttr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReplicationEventEnum }
     *     
     */
    public ReplicationEventEnum getReplicationEventAttr() {
        return replicationEventAttr;
    }

    /**
     * 设置replicationEventAttr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReplicationEventEnum }
     *     
     */
    public void setReplicationEventAttr(ReplicationEventEnum value) {
        this.replicationEventAttr = value;
    }

    /**
     * 获取replicationModeAttr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReplicationModeEnum }
     *     
     */
    public ReplicationModeEnum getReplicationModeAttr() {
        return replicationModeAttr;
    }

    /**
     * 设置replicationModeAttr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReplicationModeEnum }
     *     
     */
    public void setReplicationModeAttr(ReplicationModeEnum value) {
        this.replicationModeAttr = value;
    }

    /**
     * 获取replicationTypeAttr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReplicationTypeEnum }
     *     
     */
    public ReplicationTypeEnum getReplicationTypeAttr() {
        return replicationTypeAttr;
    }

    /**
     * 设置replicationTypeAttr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReplicationTypeEnum }
     *     
     */
    public void setReplicationTypeAttr(ReplicationTypeEnum value) {
        this.replicationTypeAttr = value;
    }

    /**
     * 获取versionAttr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionAttr() {
        if (versionAttr == null) {
            return "*";
        } else {
            return versionAttr;
        }
    }

    /**
     * 设置versionAttr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionAttr(String value) {
        this.versionAttr = value;
    }

    /**
     * 获取sequenceNoAttr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSequenceNoAttr() {
        return sequenceNoAttr;
    }

    /**
     * 设置sequenceNoAttr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSequenceNoAttr(BigInteger value) {
        this.sequenceNoAttr = value;
    }

    /**
     * 获取trxNameAttr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrxNameAttr() {
        return trxNameAttr;
    }

    /**
     * 设置trxNameAttr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrxNameAttr(String value) {
        this.trxNameAttr = value;
    }

    /**
     * 获取adSessionIDAttr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getADSessionIDAttr() {
        return adSessionIDAttr;
    }

    /**
     * 设置adSessionIDAttr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setADSessionIDAttr(BigInteger value) {
        this.adSessionIDAttr = value;
    }

}
