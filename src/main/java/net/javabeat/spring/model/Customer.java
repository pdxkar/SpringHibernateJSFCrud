	package net.javabeat.spring.model;
 
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
 
/**
 *
 * Customer Entity
 *
 * @author uday
 * @since 19 Nov 2013
 * @version 1.0.0
 *
 */
@Entity
@Table(name="CUSTOMER")
public class Customer {
 
    private int id;
    private String name;
    private String surname;
    private Integer version;
    
    @Version
    @Column(name="VERSION")
    public Integer getVersion() {

		return version;
	}

    public void setVersion(Integer version){ this.version = version;}
  

    
 
    
    
    /**
     * Get Customer Id
     *
     * @return int - Customer Id
     */
    @Id
    @Column(name="ID", unique = true, nullable = false)
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
    @Column(name="NAME", unique = true, nullable = false)
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
    @Column(name="SURNAME", unique = true, nullable = false)
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
    
    public Customer()
    {
    	
    }
    
    public Customer(String name, String surname)
    {
    	this.name = name;
    	this.surname = surname;
    	
    }
    
    public Customer(int id,String name,String surname,int version)
    {
    	this.id = id;
    	this.name = name;
    	this.surname = surname;
    	this.version = version;
    }
 
	@Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", name : ").append(getName());
        strBuff.append(", surname : ").append(getSurname());
        return strBuff.toString();
    }
}