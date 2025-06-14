//
// 此文件是由 Eclipse Implementation of JAXB v2.3.7 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2025.04.25 时间 09:43:39 PM CST 
//


package de.metas.postfinance.customerregistration;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.metas.postfinance.customerregistration package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.metas.postfinance.customerregistration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomerRegistration }
     * 
     */
    public CustomerRegistration createCustomerRegistration() {
        return new CustomerRegistration();
    }

    /**
     * Create an instance of {@link CustomerRegistrationMessage }
     * 
     */
    public CustomerRegistrationMessage createCustomerRegistrationMessage() {
        return new CustomerRegistrationMessage();
    }

    /**
     * Create an instance of {@link CustomerRegistration.CustomerNameAddress }
     * 
     */
    public CustomerRegistration.CustomerNameAddress createCustomerRegistrationCustomerNameAddress() {
        return new CustomerRegistration.CustomerNameAddress();
    }

    /**
     * Create an instance of {@link CustomerSubscriptionFormField }
     * 
     */
    public CustomerSubscriptionFormField createCustomerSubscriptionFormField() {
        return new CustomerSubscriptionFormField();
    }

    /**
     * Create an instance of {@link NamePrivate }
     * 
     */
    public NamePrivate createNamePrivate() {
        return new NamePrivate();
    }

    /**
     * Create an instance of {@link NameCompany }
     * 
     */
    public NameCompany createNameCompany() {
        return new NameCompany();
    }

}
