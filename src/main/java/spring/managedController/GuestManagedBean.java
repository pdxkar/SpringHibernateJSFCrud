package spring.managedController;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Transient;

import spring.model.Guest;
import spring.service.GuestService;

import org.hibernate.id.uuid.CustomVersionOneStrategy;
import org.springframework.dao.DataAccessException;

/**
 *
 * Guest Managed Bean
 *
 * @author uday
 * @since 19 Nov 2013
 * @version 1.0.0
 *
 */

@ManagedBean(name="guestMB")
@SessionScoped
public class GuestManagedBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "index";
    private static final String ERROR   = "error";
 
    //Spring Guest Service is injected...
    @ManagedProperty(value="#{GuestService}")
    GuestService guestService;
 
    private int id;
    private String name;
    private String surname;
    private int version;
 
    /**
     * Get this Guest
     * 
     * @return Guest
     * 
     * when the guest update form is posted, the managed bean fields are populated
     * getUpdatedGuest instantiates a Guest bean and populates it with the values
     * that have been set on the managed bean
     */
    public Guest getUpdatedGuest() {
    	Guest guest = new Guest();
    	guest.setId(getId());
        guest.setName(getName());
        guest.setSurname(getSurname());
        guest.setVersion(getVersion());
        return guest;
    }
    
    /**
     * Make new Guest
     * 
     * @return Guest
     * 
     * when the guest create form is posted, the managed bean fields are populated
     * makeNewGuest instantiates a Guest bean and populates it with the values
     * that have been set on the managed bean
     */
    public Guest makeNewGuest() {
    	
    	Guest guest = new Guest();
        guest.setName(getName());
        guest.setSurname(getSurname());

        return guest;
    }
    
    /**
     * Add Guest 
     *
     * @return String - Response Message
     * 
     * the response message is the name of a view - if SUCCESS is "success", the page
     * named "success" will be loaded; if success is "index", the index page will
     * be loaded
     */
    public String addGuest() {
        try {
        	System.out.println("adding guest...");
            getGuestService().addGuest(makeNewGuest());
            clearGuestManagedBean();
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }   
 
        return ERROR;
    }
    
    /**
     * @param guest
     * 
     * the GuestManagedBean is the backing bean for the views, so it needs to be
     * updated with the current values for a guest; when the guest update
     * form is posted, it contains a version  - after the guest is updated,
     * the version will have changed, so the GuestManagedBean must be updated
     * before it is returned with the view
     */
    private void updateGuestManagedBean(Guest guest) {
		setId(guest.getId());
		setName(guest.getName());
		setSurname(guest.getSurname());
		setVersion(guest.getVersion());
	}



	/**
     * Update Guest 
     *
     * @return String - Response Message
     * 
     * update guest updates a guest, and updates GuestManagedBean
     */
    public String updateGuest() {
        try {
			Guest guest = getGuestService().updateGuest(getUpdatedGuest());
			updateGuestManagedBean(guest);

			if (guest == null){

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
     * Delete Guest 
     *
     * @return String - Response Message
     */
    public String deleteGuest(int id) {

        try {

            Guest guest = getGuestById(id);
            getGuestService().deleteGuest(guest);
            clearGuestManagedBean();

            return SUCCESS;

        } catch (DataAccessException e) {
            e.printStackTrace();
        }   
 
        return ERROR;
    }

    /**
     * the GuestManagedBean will by default be populated with the last guest
     * that was modified; after a user is deleted, the bean must be cleared so an
     * empty form will be returned
     */
    private void clearGuestManagedBean() {
		setId(0);
		setName(null);
		setSurname(null);
		setVersion(0);
	}

    /**
     * Get Guest List
     *
     * @return List - Guest List
     */
    public ArrayList<Guest> getGuestList() {

        return (ArrayList<Guest>) getGuestService().getGuests();
    }

    /**
     * Get Guest Service
     *
     * @return IGuestService - Guest Service
     */
    public GuestService getGuestService() {
        return guestService;
    }

    /**
     * @param id
     * @return Guest
     * 
     * getGuestById returns the guest associated with the
     * id passed in
     */
    public Guest getGuestById(int id){

	    return getGuestService().getGuestById(id);
    }

    /**
     * @param id
     * 
     * loadGuestForEdit loads the guest associated with the id
     * passed in, and sets that guest on the GuestManagedBean to
     * be edited
     */
    public void loadGuestForEdit(int id){

    	Guest guest = getGuestService().getGuestById(id);
    	updateGuestManagedBean(guest);
    }

    /**
     * Set Guest Service
     *
     * @param guestService IGuestService - Guest Service
     */
    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
    }

	/**
     * Get Guest Id
     *
     * @return int - Guest Id
     */
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