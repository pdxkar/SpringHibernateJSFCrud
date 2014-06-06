package net.javabeat.spring.dao;
 
import java.io.Serializable;
import java.util.List;

import net.javabeat.spring.model.Customer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * customer DAO
 *
 * @author uday
 * @since 19 Nov 2013
 * @version 1.0.0
 *
 */
@Repository
public class CustomerDAO  {
    @Autowired
    private SessionFactory sessionFactory;
 
    /**
     * Get Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    /**
     * Set Hibernate Session Factory
     *
     * @param sessionFactory SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    /**
     * Add customer
     *
     * @param   customer   customer
     */
   
    public Integer addCustomer(Customer customer) {
    	Serializable ser = getSessionFactory().getCurrentSession().save(customer);
    	return 0; 
    }
 
    /**
     * Delete customer
     *
     * @param   customer  customer
     */
   
    public void deleteCustomer(Customer customer) {
        getSessionFactory().getCurrentSession().delete(customer);
    }
 
    /**
     * Update customer
     *
     * @param  customer customer 
     */
   
    public Customer updateCustomer(Customer customer) {
    	customer = (Customer) getSessionFactory().getCurrentSession().merge(customer);
        return customer;
    }
 
    /**
     * Get customer
     *
     * @param  id int  
     * @return customer
     */
   
    public Customer getCustomerById(int id) {
        @SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>) getSessionFactory().getCurrentSession()
                                            .createQuery("from net.javabeat.spring.model.Customer  where id=?")
                                            .setParameter(0, id).list();
        Customer customer = (Customer)list.get(0);
        return customer;
    }
 
    /**
     * Get customer List
     *
     * @return List - customer list
     */
   
    @SuppressWarnings("unchecked")
	public List<Customer> getCustomers() {
        List<Customer> list = (List<Customer>) getSessionFactory().getCurrentSession().createQuery("from net.javabeat.spring.model.Customer").list();
        return list;
    }
 
}