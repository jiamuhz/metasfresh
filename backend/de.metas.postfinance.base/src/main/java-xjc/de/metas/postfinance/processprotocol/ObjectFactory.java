//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:43:39 PM CST 
//


package de.metas.postfinance.processprotocol;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.metas.postfinance.processprotocol package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.metas.postfinance.processprotocol
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TransactionDetailType }
     * 
     */
    public TransactionDetailType createTransactionDetailType() {
        return new TransactionDetailType();
    }

    /**
     * Create an instance of {@link Envelope }
     * 
     */
    public Envelope createEnvelope() {
        return new Envelope();
    }

    /**
     * Create an instance of {@link PhysicalAddress }
     * 
     */
    public PhysicalAddress createPhysicalAddress() {
        return new PhysicalAddress();
    }

    /**
     * Create an instance of {@link POBoxAddress }
     * 
     */
    public POBoxAddress createPOBoxAddress() {
        return new POBoxAddress();
    }

    /**
     * Create an instance of {@link TransactionDetailType.RelocationAddress }
     * 
     */
    public TransactionDetailType.RelocationAddress createTransactionDetailTypeRelocationAddress() {
        return new TransactionDetailType.RelocationAddress();
    }

}
