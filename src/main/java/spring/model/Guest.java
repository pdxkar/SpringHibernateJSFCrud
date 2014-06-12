package spring.model;
 
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
 
/**
 *
 * Guest Entity
 *
 * @author uday
 * @since 19 Nov 2013
 * @version 1.0.0
 *
 */
@Entity
@Table(name="CUSTOMERS")
public class Guest {
 
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
     * Get Guest Id
     *
     * @return int - Guest Id
     */
    @Id
    @Column(name="CUSTOMER_ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }
 
    /**
     * Set Guest Id
     *
     * @param id int - Guest Id
     */
    public void setId(int id) {
        this.id = id;
    }
 
    /**
     * Get Guest Name
     *
     * @return String - Guest Name
     */
    @Column(name="CUST_FIRST_NAME", unique = true, nullable = false)
    public String getName() {
        return name;
    }
 
    /**
     * Set Guest Name
     *
     * @param name String - Guest Name
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /**
     * Get Guest Surname
     *
     * @return String - Guest Surname
     */
    @Column(name="CUST_LAST_NAME", unique = true, nullable = false)
    public String getSurname() {
        return surname;
    }
 
    /**
     * Set Guest Surname
     *
     * @param surname String - Guest Surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public Guest()
    {
    	
    }
    
    public Guest(String name, String surname)
    {
    	this.name = name;
    	this.surname = surname;
    	
    }
    
    public Guest(int id,String name,String surname,int version)
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