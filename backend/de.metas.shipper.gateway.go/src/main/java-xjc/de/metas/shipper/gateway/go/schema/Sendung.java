//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.12.09 时间 01:22:47 PM CST 
//


package de.metas.shipper.gateway.go.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Sendung complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Sendung"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SendungsnummerAX4" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]{1,15}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Frachtbriefnummer" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Versender"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]{1,30}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Benutzername"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="100"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Status"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *               &lt;enumeration value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Kundenreferenz" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="40"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Abholadresse"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Firmenname1"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="60"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Firmenname2" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="60"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Abteilung" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="40"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Strasse1"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="35"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Hausnummer" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Strasse2" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="25"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Land"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[A-Za-z]{1,3}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Postleitzahl"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="9"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Stadt"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="30"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Empfaenger"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Firmenname1"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="60"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Firmenname2" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="60"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Abteilung" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="40"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Strasse1"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="35"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Hausnummer" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Strasse2" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="35"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Land"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[A-Za-z]{1,3}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Postleitzahl"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="9"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Stadt"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="30"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Ansprechpartner" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Telefon"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="LaenderPrefix"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;pattern value="[0]{2}[1-9]{1,2}"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="Ortsvorwahl"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;pattern value="[0-9]{1,7}"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="Telefonnummer"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;pattern value="[0-9]{1,10}"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Service"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Abholdatum"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Datum"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).(19|20)[0-9]{2}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="UhrzeitVon" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="([01][0-9]|2[0-3]):[0-5][0-9]"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="UhrzeitBis" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="([01][0-9]|2[0-3]):[0-5][0-9]"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Zustelldatum" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Datum"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).(19|20)[0-9]{2}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="UhrzeitVon" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="([01][0-9]|2[0-3]):[0-5][0-9]"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="UhrzeitBis" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="([01][0-9]|2[0-3]):[0-5][0-9]"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="unfrei"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Selbstanlieferung"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Selbstabholung"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Warenwert" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Betrag"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[1-9][0-9]{0,4}\.[0-9]{2}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Waehrung"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Sonderversicherung" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Betrag"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[1-9][0-9]{0,4}\.[0-9]{2}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Waehrung"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Nachnahme" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Betrag"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[1-9][0-9]{0,4}\.[0-9]{2}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Waehrung"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Zahlungsart"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="1"/&gt;
 *                         &lt;enumeration value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Abholhinweise" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="128"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Zustellhinweise" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="128"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TelefonEmpfangsbestaetigung" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="25"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SendungsPosition"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="AnzahlPackstuecke"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[1-9][0-9]?"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Gewicht"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[1-9][0-9]{0,2}\.[0-9]{2}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Inhalt"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="40"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Abmessungen" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Laenge"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;pattern value="[0-9]{1,5}\.[0-9]{1}"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="Breite"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;pattern value="[0-9]{1,5}\.[0-9]{1}"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="Hoehe"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;pattern value="[0-9]{1,5}\.[0-9]{1}"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
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
@XmlType(name = "Sendung", propOrder = {
    "sendungsnummerAX4",
    "frachtbriefnummer",
    "versender",
    "benutzername",
    "status",
    "kundenreferenz",
    "abholadresse",
    "empfaenger",
    "service",
    "abholdatum",
    "zustelldatum",
    "unfrei",
    "selbstanlieferung",
    "selbstabholung",
    "warenwert",
    "sonderversicherung",
    "nachnahme",
    "abholhinweise",
    "zustellhinweise",
    "telefonEmpfangsbestaetigung",
    "sendungsPosition"
})
public class Sendung {

    @XmlElement(name = "SendungsnummerAX4")
    protected String sendungsnummerAX4;
    @XmlElement(name = "Frachtbriefnummer")
    protected String frachtbriefnummer;
    @XmlElement(name = "Versender", required = true)
    protected String versender;
    @XmlElement(name = "Benutzername", required = true)
    protected String benutzername;
    @XmlElement(name = "Status", required = true)
    protected String status;
    @XmlElement(name = "Kundenreferenz")
    protected String kundenreferenz;
    @XmlElement(name = "Abholadresse", required = true)
    protected Sendung.Abholadresse abholadresse;
    @XmlElement(name = "Empfaenger", required = true)
    protected Sendung.Empfaenger empfaenger;
    @XmlElement(name = "Service", required = true)
    protected String service;
    @XmlElement(name = "Abholdatum", required = true)
    protected Sendung.Abholdatum abholdatum;
    @XmlElement(name = "Zustelldatum")
    protected Sendung.Zustelldatum zustelldatum;
    @XmlElement(required = true)
    protected String unfrei;
    @XmlElement(name = "Selbstanlieferung", required = true)
    protected String selbstanlieferung;
    @XmlElement(name = "Selbstabholung", required = true)
    protected String selbstabholung;
    @XmlElement(name = "Warenwert")
    protected Sendung.Warenwert warenwert;
    @XmlElement(name = "Sonderversicherung")
    protected Sendung.Sonderversicherung sonderversicherung;
    @XmlElement(name = "Nachnahme")
    protected Sendung.Nachnahme nachnahme;
    @XmlElement(name = "Abholhinweise")
    protected String abholhinweise;
    @XmlElement(name = "Zustellhinweise")
    protected String zustellhinweise;
    @XmlElement(name = "TelefonEmpfangsbestaetigung")
    protected String telefonEmpfangsbestaetigung;
    @XmlElement(name = "SendungsPosition", required = true)
    protected Sendung.SendungsPosition sendungsPosition;

    /**
     * 获取sendungsnummerAX4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendungsnummerAX4() {
        return sendungsnummerAX4;
    }

    /**
     * 设置sendungsnummerAX4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendungsnummerAX4(String value) {
        this.sendungsnummerAX4 = value;
    }

    /**
     * 获取frachtbriefnummer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrachtbriefnummer() {
        return frachtbriefnummer;
    }

    /**
     * 设置frachtbriefnummer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrachtbriefnummer(String value) {
        this.frachtbriefnummer = value;
    }

    /**
     * 获取versender属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersender() {
        return versender;
    }

    /**
     * 设置versender属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersender(String value) {
        this.versender = value;
    }

    /**
     * 获取benutzername属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenutzername() {
        return benutzername;
    }

    /**
     * 设置benutzername属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenutzername(String value) {
        this.benutzername = value;
    }

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * 获取kundenreferenz属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKundenreferenz() {
        return kundenreferenz;
    }

    /**
     * 设置kundenreferenz属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKundenreferenz(String value) {
        this.kundenreferenz = value;
    }

    /**
     * 获取abholadresse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sendung.Abholadresse }
     *     
     */
    public Sendung.Abholadresse getAbholadresse() {
        return abholadresse;
    }

    /**
     * 设置abholadresse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sendung.Abholadresse }
     *     
     */
    public void setAbholadresse(Sendung.Abholadresse value) {
        this.abholadresse = value;
    }

    /**
     * 获取empfaenger属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sendung.Empfaenger }
     *     
     */
    public Sendung.Empfaenger getEmpfaenger() {
        return empfaenger;
    }

    /**
     * 设置empfaenger属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sendung.Empfaenger }
     *     
     */
    public void setEmpfaenger(Sendung.Empfaenger value) {
        this.empfaenger = value;
    }

    /**
     * 获取service属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * 设置service属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
    }

    /**
     * 获取abholdatum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sendung.Abholdatum }
     *     
     */
    public Sendung.Abholdatum getAbholdatum() {
        return abholdatum;
    }

    /**
     * 设置abholdatum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sendung.Abholdatum }
     *     
     */
    public void setAbholdatum(Sendung.Abholdatum value) {
        this.abholdatum = value;
    }

    /**
     * 获取zustelldatum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sendung.Zustelldatum }
     *     
     */
    public Sendung.Zustelldatum getZustelldatum() {
        return zustelldatum;
    }

    /**
     * 设置zustelldatum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sendung.Zustelldatum }
     *     
     */
    public void setZustelldatum(Sendung.Zustelldatum value) {
        this.zustelldatum = value;
    }

    /**
     * 获取unfrei属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnfrei() {
        return unfrei;
    }

    /**
     * 设置unfrei属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnfrei(String value) {
        this.unfrei = value;
    }

    /**
     * 获取selbstanlieferung属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelbstanlieferung() {
        return selbstanlieferung;
    }

    /**
     * 设置selbstanlieferung属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelbstanlieferung(String value) {
        this.selbstanlieferung = value;
    }

    /**
     * 获取selbstabholung属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelbstabholung() {
        return selbstabholung;
    }

    /**
     * 设置selbstabholung属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelbstabholung(String value) {
        this.selbstabholung = value;
    }

    /**
     * 获取warenwert属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sendung.Warenwert }
     *     
     */
    public Sendung.Warenwert getWarenwert() {
        return warenwert;
    }

    /**
     * 设置warenwert属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sendung.Warenwert }
     *     
     */
    public void setWarenwert(Sendung.Warenwert value) {
        this.warenwert = value;
    }

    /**
     * 获取sonderversicherung属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sendung.Sonderversicherung }
     *     
     */
    public Sendung.Sonderversicherung getSonderversicherung() {
        return sonderversicherung;
    }

    /**
     * 设置sonderversicherung属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sendung.Sonderversicherung }
     *     
     */
    public void setSonderversicherung(Sendung.Sonderversicherung value) {
        this.sonderversicherung = value;
    }

    /**
     * 获取nachnahme属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sendung.Nachnahme }
     *     
     */
    public Sendung.Nachnahme getNachnahme() {
        return nachnahme;
    }

    /**
     * 设置nachnahme属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sendung.Nachnahme }
     *     
     */
    public void setNachnahme(Sendung.Nachnahme value) {
        this.nachnahme = value;
    }

    /**
     * 获取abholhinweise属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbholhinweise() {
        return abholhinweise;
    }

    /**
     * 设置abholhinweise属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbholhinweise(String value) {
        this.abholhinweise = value;
    }

    /**
     * 获取zustellhinweise属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZustellhinweise() {
        return zustellhinweise;
    }

    /**
     * 设置zustellhinweise属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZustellhinweise(String value) {
        this.zustellhinweise = value;
    }

    /**
     * 获取telefonEmpfangsbestaetigung属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonEmpfangsbestaetigung() {
        return telefonEmpfangsbestaetigung;
    }

    /**
     * 设置telefonEmpfangsbestaetigung属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonEmpfangsbestaetigung(String value) {
        this.telefonEmpfangsbestaetigung = value;
    }

    /**
     * 获取sendungsPosition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sendung.SendungsPosition }
     *     
     */
    public Sendung.SendungsPosition getSendungsPosition() {
        return sendungsPosition;
    }

    /**
     * 设置sendungsPosition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sendung.SendungsPosition }
     *     
     */
    public void setSendungsPosition(Sendung.SendungsPosition value) {
        this.sendungsPosition = value;
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
     *         &lt;element name="Firmenname1"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="60"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Firmenname2" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="60"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Abteilung" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="40"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Strasse1"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="35"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Hausnummer" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Strasse2" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="25"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Land"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[A-Za-z]{1,3}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Postleitzahl"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="9"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Stadt"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="30"/&gt;
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
        "firmenname1",
        "firmenname2",
        "abteilung",
        "strasse1",
        "hausnummer",
        "strasse2",
        "land",
        "postleitzahl",
        "stadt"
    })
    public static class Abholadresse {

        @XmlElement(name = "Firmenname1", required = true)
        protected String firmenname1;
        @XmlElement(name = "Firmenname2")
        protected String firmenname2;
        @XmlElement(name = "Abteilung")
        protected String abteilung;
        @XmlElement(name = "Strasse1", required = true)
        protected String strasse1;
        @XmlElement(name = "Hausnummer")
        protected String hausnummer;
        @XmlElement(name = "Strasse2")
        protected String strasse2;
        @XmlElement(name = "Land", required = true)
        protected String land;
        @XmlElement(name = "Postleitzahl", required = true)
        protected String postleitzahl;
        @XmlElement(name = "Stadt", required = true)
        protected String stadt;

        /**
         * 获取firmenname1属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFirmenname1() {
            return firmenname1;
        }

        /**
         * 设置firmenname1属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFirmenname1(String value) {
            this.firmenname1 = value;
        }

        /**
         * 获取firmenname2属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFirmenname2() {
            return firmenname2;
        }

        /**
         * 设置firmenname2属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFirmenname2(String value) {
            this.firmenname2 = value;
        }

        /**
         * 获取abteilung属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAbteilung() {
            return abteilung;
        }

        /**
         * 设置abteilung属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAbteilung(String value) {
            this.abteilung = value;
        }

        /**
         * 获取strasse1属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStrasse1() {
            return strasse1;
        }

        /**
         * 设置strasse1属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStrasse1(String value) {
            this.strasse1 = value;
        }

        /**
         * 获取hausnummer属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHausnummer() {
            return hausnummer;
        }

        /**
         * 设置hausnummer属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHausnummer(String value) {
            this.hausnummer = value;
        }

        /**
         * 获取strasse2属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStrasse2() {
            return strasse2;
        }

        /**
         * 设置strasse2属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStrasse2(String value) {
            this.strasse2 = value;
        }

        /**
         * 获取land属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLand() {
            return land;
        }

        /**
         * 设置land属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLand(String value) {
            this.land = value;
        }

        /**
         * 获取postleitzahl属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPostleitzahl() {
            return postleitzahl;
        }

        /**
         * 设置postleitzahl属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPostleitzahl(String value) {
            this.postleitzahl = value;
        }

        /**
         * 获取stadt属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStadt() {
            return stadt;
        }

        /**
         * 设置stadt属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStadt(String value) {
            this.stadt = value;
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
     *         &lt;element name="Datum"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).(19|20)[0-9]{2}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="UhrzeitVon" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="([01][0-9]|2[0-3]):[0-5][0-9]"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="UhrzeitBis" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="([01][0-9]|2[0-3]):[0-5][0-9]"/&gt;
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
        "datum",
        "uhrzeitVon",
        "uhrzeitBis"
    })
    public static class Abholdatum {

        @XmlElement(name = "Datum", required = true)
        protected String datum;
        @XmlElement(name = "UhrzeitVon")
        protected String uhrzeitVon;
        @XmlElement(name = "UhrzeitBis")
        protected String uhrzeitBis;

        /**
         * 获取datum属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatum() {
            return datum;
        }

        /**
         * 设置datum属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatum(String value) {
            this.datum = value;
        }

        /**
         * 获取uhrzeitVon属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUhrzeitVon() {
            return uhrzeitVon;
        }

        /**
         * 设置uhrzeitVon属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUhrzeitVon(String value) {
            this.uhrzeitVon = value;
        }

        /**
         * 获取uhrzeitBis属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUhrzeitBis() {
            return uhrzeitBis;
        }

        /**
         * 设置uhrzeitBis属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUhrzeitBis(String value) {
            this.uhrzeitBis = value;
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
     *         &lt;element name="Firmenname1"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="60"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Firmenname2" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="60"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Abteilung" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="40"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Strasse1"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="35"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Hausnummer" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Strasse2" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="35"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Land"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[A-Za-z]{1,3}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Postleitzahl"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="9"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Stadt"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="30"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Ansprechpartner" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Telefon"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="LaenderPrefix"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;pattern value="[0]{2}[1-9]{1,2}"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="Ortsvorwahl"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;pattern value="[0-9]{1,7}"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="Telefonnummer"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;pattern value="[0-9]{1,10}"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
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
        "firmenname1",
        "firmenname2",
        "abteilung",
        "strasse1",
        "hausnummer",
        "strasse2",
        "land",
        "postleitzahl",
        "stadt",
        "ansprechpartner"
    })
    public static class Empfaenger {

        @XmlElement(name = "Firmenname1", required = true)
        protected String firmenname1;
        @XmlElement(name = "Firmenname2")
        protected String firmenname2;
        @XmlElement(name = "Abteilung")
        protected String abteilung;
        @XmlElement(name = "Strasse1", required = true)
        protected String strasse1;
        @XmlElement(name = "Hausnummer")
        protected String hausnummer;
        @XmlElement(name = "Strasse2")
        protected String strasse2;
        @XmlElement(name = "Land", required = true)
        protected String land;
        @XmlElement(name = "Postleitzahl", required = true)
        protected String postleitzahl;
        @XmlElement(name = "Stadt", required = true)
        protected String stadt;
        @XmlElement(name = "Ansprechpartner")
        protected Sendung.Empfaenger.Ansprechpartner ansprechpartner;

        /**
         * 获取firmenname1属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFirmenname1() {
            return firmenname1;
        }

        /**
         * 设置firmenname1属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFirmenname1(String value) {
            this.firmenname1 = value;
        }

        /**
         * 获取firmenname2属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFirmenname2() {
            return firmenname2;
        }

        /**
         * 设置firmenname2属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFirmenname2(String value) {
            this.firmenname2 = value;
        }

        /**
         * 获取abteilung属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAbteilung() {
            return abteilung;
        }

        /**
         * 设置abteilung属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAbteilung(String value) {
            this.abteilung = value;
        }

        /**
         * 获取strasse1属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStrasse1() {
            return strasse1;
        }

        /**
         * 设置strasse1属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStrasse1(String value) {
            this.strasse1 = value;
        }

        /**
         * 获取hausnummer属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHausnummer() {
            return hausnummer;
        }

        /**
         * 设置hausnummer属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHausnummer(String value) {
            this.hausnummer = value;
        }

        /**
         * 获取strasse2属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStrasse2() {
            return strasse2;
        }

        /**
         * 设置strasse2属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStrasse2(String value) {
            this.strasse2 = value;
        }

        /**
         * 获取land属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLand() {
            return land;
        }

        /**
         * 设置land属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLand(String value) {
            this.land = value;
        }

        /**
         * 获取postleitzahl属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPostleitzahl() {
            return postleitzahl;
        }

        /**
         * 设置postleitzahl属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPostleitzahl(String value) {
            this.postleitzahl = value;
        }

        /**
         * 获取stadt属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStadt() {
            return stadt;
        }

        /**
         * 设置stadt属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStadt(String value) {
            this.stadt = value;
        }

        /**
         * 获取ansprechpartner属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Sendung.Empfaenger.Ansprechpartner }
         *     
         */
        public Sendung.Empfaenger.Ansprechpartner getAnsprechpartner() {
            return ansprechpartner;
        }

        /**
         * 设置ansprechpartner属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Sendung.Empfaenger.Ansprechpartner }
         *     
         */
        public void setAnsprechpartner(Sendung.Empfaenger.Ansprechpartner value) {
            this.ansprechpartner = value;
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
         *         &lt;element name="Telefon"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="LaenderPrefix"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;pattern value="[0]{2}[1-9]{1,2}"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="Ortsvorwahl"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;pattern value="[0-9]{1,7}"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="Telefonnummer"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;pattern value="[0-9]{1,10}"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
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
            "telefon"
        })
        public static class Ansprechpartner {

            @XmlElement(name = "Telefon", required = true)
            protected Sendung.Empfaenger.Ansprechpartner.Telefon telefon;

            /**
             * 获取telefon属性的值。
             * 
             * @return
             *     possible object is
             *     {@link Sendung.Empfaenger.Ansprechpartner.Telefon }
             *     
             */
            public Sendung.Empfaenger.Ansprechpartner.Telefon getTelefon() {
                return telefon;
            }

            /**
             * 设置telefon属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link Sendung.Empfaenger.Ansprechpartner.Telefon }
             *     
             */
            public void setTelefon(Sendung.Empfaenger.Ansprechpartner.Telefon value) {
                this.telefon = value;
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
             *         &lt;element name="LaenderPrefix"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;pattern value="[0]{2}[1-9]{1,2}"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="Ortsvorwahl"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;pattern value="[0-9]{1,7}"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="Telefonnummer"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;pattern value="[0-9]{1,10}"/&gt;
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
                "laenderPrefix",
                "ortsvorwahl",
                "telefonnummer"
            })
            public static class Telefon {

                @XmlElement(name = "LaenderPrefix", required = true)
                protected String laenderPrefix;
                @XmlElement(name = "Ortsvorwahl", required = true)
                protected String ortsvorwahl;
                @XmlElement(name = "Telefonnummer", required = true)
                protected String telefonnummer;

                /**
                 * 获取laenderPrefix属性的值。
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getLaenderPrefix() {
                    return laenderPrefix;
                }

                /**
                 * 设置laenderPrefix属性的值。
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setLaenderPrefix(String value) {
                    this.laenderPrefix = value;
                }

                /**
                 * 获取ortsvorwahl属性的值。
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getOrtsvorwahl() {
                    return ortsvorwahl;
                }

                /**
                 * 设置ortsvorwahl属性的值。
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setOrtsvorwahl(String value) {
                    this.ortsvorwahl = value;
                }

                /**
                 * 获取telefonnummer属性的值。
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTelefonnummer() {
                    return telefonnummer;
                }

                /**
                 * 设置telefonnummer属性的值。
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTelefonnummer(String value) {
                    this.telefonnummer = value;
                }

            }

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
     *         &lt;element name="Betrag"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[1-9][0-9]{0,4}\.[0-9]{2}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Waehrung"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Zahlungsart"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="1"/&gt;
     *               &lt;enumeration value="2"/&gt;
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
        "betrag",
        "waehrung",
        "zahlungsart"
    })
    public static class Nachnahme {

        @XmlElement(name = "Betrag", required = true)
        protected String betrag;
        @XmlElement(name = "Waehrung", required = true)
        protected String waehrung;
        @XmlElement(name = "Zahlungsart", required = true)
        protected String zahlungsart;

        /**
         * 获取betrag属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBetrag() {
            return betrag;
        }

        /**
         * 设置betrag属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBetrag(String value) {
            this.betrag = value;
        }

        /**
         * 获取waehrung属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWaehrung() {
            return waehrung;
        }

        /**
         * 设置waehrung属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWaehrung(String value) {
            this.waehrung = value;
        }

        /**
         * 获取zahlungsart属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZahlungsart() {
            return zahlungsart;
        }

        /**
         * 设置zahlungsart属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZahlungsart(String value) {
            this.zahlungsart = value;
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
     *         &lt;element name="AnzahlPackstuecke"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[1-9][0-9]?"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Gewicht"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[1-9][0-9]{0,2}\.[0-9]{2}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Inhalt"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="40"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Abmessungen" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Laenge"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;pattern value="[0-9]{1,5}\.[0-9]{1}"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="Breite"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;pattern value="[0-9]{1,5}\.[0-9]{1}"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="Hoehe"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;pattern value="[0-9]{1,5}\.[0-9]{1}"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
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
        "anzahlPackstuecke",
        "gewicht",
        "inhalt",
        "abmessungen"
    })
    public static class SendungsPosition {

        @XmlElement(name = "AnzahlPackstuecke", required = true)
        protected String anzahlPackstuecke;
        @XmlElement(name = "Gewicht", required = true)
        protected String gewicht;
        @XmlElement(name = "Inhalt", required = true)
        protected String inhalt;
        @XmlElement(name = "Abmessungen")
        protected Sendung.SendungsPosition.Abmessungen abmessungen;

        /**
         * 获取anzahlPackstuecke属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAnzahlPackstuecke() {
            return anzahlPackstuecke;
        }

        /**
         * 设置anzahlPackstuecke属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAnzahlPackstuecke(String value) {
            this.anzahlPackstuecke = value;
        }

        /**
         * 获取gewicht属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGewicht() {
            return gewicht;
        }

        /**
         * 设置gewicht属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGewicht(String value) {
            this.gewicht = value;
        }

        /**
         * 获取inhalt属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInhalt() {
            return inhalt;
        }

        /**
         * 设置inhalt属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInhalt(String value) {
            this.inhalt = value;
        }

        /**
         * 获取abmessungen属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Sendung.SendungsPosition.Abmessungen }
         *     
         */
        public Sendung.SendungsPosition.Abmessungen getAbmessungen() {
            return abmessungen;
        }

        /**
         * 设置abmessungen属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Sendung.SendungsPosition.Abmessungen }
         *     
         */
        public void setAbmessungen(Sendung.SendungsPosition.Abmessungen value) {
            this.abmessungen = value;
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
         *         &lt;element name="Laenge"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;pattern value="[0-9]{1,5}\.[0-9]{1}"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="Breite"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;pattern value="[0-9]{1,5}\.[0-9]{1}"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="Hoehe"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;pattern value="[0-9]{1,5}\.[0-9]{1}"/&gt;
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
            "laenge",
            "breite",
            "hoehe"
        })
        public static class Abmessungen {

            @XmlElement(name = "Laenge", required = true)
            protected String laenge;
            @XmlElement(name = "Breite", required = true)
            protected String breite;
            @XmlElement(name = "Hoehe", required = true)
            protected String hoehe;

            /**
             * 获取laenge属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLaenge() {
                return laenge;
            }

            /**
             * 设置laenge属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLaenge(String value) {
                this.laenge = value;
            }

            /**
             * 获取breite属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBreite() {
                return breite;
            }

            /**
             * 设置breite属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBreite(String value) {
                this.breite = value;
            }

            /**
             * 获取hoehe属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHoehe() {
                return hoehe;
            }

            /**
             * 设置hoehe属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHoehe(String value) {
                this.hoehe = value;
            }

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
     *         &lt;element name="Betrag"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[1-9][0-9]{0,4}\.[0-9]{2}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Waehrung"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="3"/&gt;
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
        "betrag",
        "waehrung"
    })
    public static class Sonderversicherung {

        @XmlElement(name = "Betrag", required = true)
        protected String betrag;
        @XmlElement(name = "Waehrung", required = true)
        protected String waehrung;

        /**
         * 获取betrag属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBetrag() {
            return betrag;
        }

        /**
         * 设置betrag属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBetrag(String value) {
            this.betrag = value;
        }

        /**
         * 获取waehrung属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWaehrung() {
            return waehrung;
        }

        /**
         * 设置waehrung属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWaehrung(String value) {
            this.waehrung = value;
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
     *         &lt;element name="Betrag"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[1-9][0-9]{0,4}\.[0-9]{2}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Waehrung"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="3"/&gt;
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
        "betrag",
        "waehrung"
    })
    public static class Warenwert {

        @XmlElement(name = "Betrag", required = true)
        protected String betrag;
        @XmlElement(name = "Waehrung", required = true)
        protected String waehrung;

        /**
         * 获取betrag属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBetrag() {
            return betrag;
        }

        /**
         * 设置betrag属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBetrag(String value) {
            this.betrag = value;
        }

        /**
         * 获取waehrung属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWaehrung() {
            return waehrung;
        }

        /**
         * 设置waehrung属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWaehrung(String value) {
            this.waehrung = value;
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
     *         &lt;element name="Datum"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).(19|20)[0-9]{2}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="UhrzeitVon" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="([01][0-9]|2[0-3]):[0-5][0-9]"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="UhrzeitBis" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="([01][0-9]|2[0-3]):[0-5][0-9]"/&gt;
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
        "datum",
        "uhrzeitVon",
        "uhrzeitBis"
    })
    public static class Zustelldatum {

        @XmlElement(name = "Datum", required = true)
        protected String datum;
        @XmlElement(name = "UhrzeitVon")
        protected String uhrzeitVon;
        @XmlElement(name = "UhrzeitBis")
        protected String uhrzeitBis;

        /**
         * 获取datum属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatum() {
            return datum;
        }

        /**
         * 设置datum属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatum(String value) {
            this.datum = value;
        }

        /**
         * 获取uhrzeitVon属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUhrzeitVon() {
            return uhrzeitVon;
        }

        /**
         * 设置uhrzeitVon属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUhrzeitVon(String value) {
            this.uhrzeitVon = value;
        }

        /**
         * 获取uhrzeitBis属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUhrzeitBis() {
            return uhrzeitBis;
        }

        /**
         * 设置uhrzeitBis属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUhrzeitBis(String value) {
            this.uhrzeitBis = value;
        }

    }

}
