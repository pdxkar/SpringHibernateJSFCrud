package net.javabeat.managedController;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.Transient;

import net.javabeat.spring.model.Customer;
import net.javabeat.spring.service.CustomerService;

import org.hibernate.id.uuid.CustomVersionOneStrategy;
import org.springframework.dao.DataAccessException;

/**
 *
 * Customer Managed Bean
 *
 * @author uday
 * @since 19 Nov 2013
 * @version 1.0.0
 *
 */
@ManagedBean(name="customerMB")
@RequestScoped
public class CustomerManagedBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "index";
    private static final String ERROR   = "error";
 
    //Spring Customer Service is injected...
    @ManagedProperty(value="#{CustomerService}")
    CustomerService customerService;
 
    List<Customer> customerList;
 
    private int id;
    private String name;
    private String surname;
    private int version;
 
    /**
     * Get this Customer
     * 
     * @return Customer
     * 
     * when the customer update form is posted, the managed bean fields are populated
     * getUpdatedCustomer instantiates a Customer bean and populates it with the values
     * that have been set on the managed bean
     */
    public Customer getUpdatedCustomer() {
    	Customer customer = new Customer();
    	customer.setId(getId());
        customer.setName(getName());
        customer.setSurname(getSurname());
        customer.setVersion(getVersion());
        return customer;
    }
    
    /**
     * Add Customer 
     *
     * @return String - Response Message
     * 
     * the response message is the name of a view - if SUCCESS is "success", the page
     * named "success" will be loaded; if success is "index", the index page will
     * be loaded
     */
    public String addCustomer() {
        try {
            getCustomerService().addCustomer(getUpdatedCustomer());
            clearCustomerManagedBean();
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }   
 
        return ERROR;
    }
    
    /**
     * @param customer
     * 
     * the CustomerManagedBean is the backing bean for the views, so it needs to be
     * updated with the current values for a customer; when the customer update
     * form is posted, it contains a version  - after the customer is updated,
     * the version will have changed, so the CustomerManagedBean must be updated
     * before it is returned with the view
     */
    private void updateCustomerManagedBean(Customer customer) {
		setId(customer.getId());
		setName(customer.getName());
		setSurname(customer.getSurname());
		setVersion(customer.getVersion());
	}



	/**
     * Update Customer 
     *
     * @return String - Response Message
     * 
     * update customer updates a customer, and updates CustomerManagedBean
     */
    public String updateCustomer() {
        try {
			Customer customer = getCustomerService().updateCustomer(getUpdatedCustomer());
			updateCustomerManagedBean(customer);

			if (customer == null){

				return ERROR;
			}
			
			else{

				return SUCCESS;
			}

        } catch (DataAccessException e) {
            e.printStackTrace();
        }   
 
        return ERROR;
    }
    
    /**
     * Delete Customer 
     *
     * @return String - Response Message
     */
    public String deleteCustomer() {
        try {
            Customer customer = getUpdatedCustomer();
            getCustomerService().deleteCustomer(customer);
            clearCustomerManagedBean();
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }   
 
        return ERROR;
    }
 
    /**
     * the CustomerManagedBean will by default be populated with the last customer
     * that was modified; after a user is deleted, the bean must be cleared so an
     * empty form will be returned
     */
    private void clearCustomerManagedBean() {
		setId(0);
		setName(null);
		setSurname(null);
		setVersion(0);
	}

    /**
     * Get Customer List
     *
     * @return List - Customer List
     */
    public List<Customer> getCustomerList() {
        customerList = new ArrayList<Customer>();
        customerList.addAll(getCustomerService().getCustomers());
        return customerList;
    }
 
    /**
     * Get Customer Service
     *
     * @return ICustomerService - Customer Service
     */
    public CustomerService getCustomerService() {
        return customerService;
    }

    /**
     * @param id
     * @return Customer
     * 
     * getCustomerById returns the customer associated with the
     * id passed in
     */
    public Customer getCustomerById(int id){
    	Customer customer = getCustomerService().getCustomerById(id);
    	updateCustomerManagedBean(customer);
    	return customer;
    }
    
    /**
     * @param id
     * 
     * loadCustomerForEdit loads the customer associated with the id
     * passed in, and sets that customer on the CustomerManagedBean to
     * be edited
     */
    public void loadCustomerForEdit(int id){
    	Customer customer = getCustomerService().getCustomerById(id);
    	updateCustomerManagedBean(customer);
    }
    /**
     * Set Customer Service
     *
     * @param customerService ICustomerService - Customer Service
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
 
    /**
     * Set Customer List
     *
     * @param customerList List - Customer List
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
 
    /**
     * Get Customer Id
     *
     * @return int - Customer Id
     */
    public int getId() {
        return id;
    }
 
    /**
     * Set Customer Id
     *
     * @param id int - Customer Id
     */
    public void setId(int id) {
        this.id = id;
    }
 
    /**
     * Get Customer Name
     *
     * @return String - Customer Name
     */
    public String getName() {
        return name;
    }
 
    /**
     * Set Customer Name
     *
     * @param name String - Customer Name
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /**
     * Get Customer Surname
     *
     * @return String - Customer Surname
     */
    public String getSurname() {
        return surname;
    }
 
    /**
     * Set Customer Surname
     *
     * @param surname String - Customer Surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
 
}