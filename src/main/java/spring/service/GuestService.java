package spring.service;
 
import java.sql.SQLException;
import java.util.List;

import spring.dao.GuestDAO;
import spring.model.Guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Guest Service
 *
 * @author uday
 * @since 19 Nov 2013
 * @version 1.0.0
 *
 *
 */
@Service("GuestService")
@Transactional(readOnly = true)
public class GuestService {
 
    // GuestDAO is injected...
    @Autowired
    GuestDAO guestDAO;
 
    /**
     * Add Guest
     *
     * @param  guest Guest
     */
    @Transactional(readOnly = false)
    public void addGuest(Guest guest) {
    	getGuestDAO().addGuest(guest);
    }
 
    /**
     * Delete Guest
     *
     * @param   guest  Guest
     */
    @Transactional(readOnly = false)
    public void deleteGuest(Guest guest) {
    	System.out.println("delete in service: " + guest.getName());
        getGuestDAO().deleteGuest(guest);
    }
 
    /**
     * Update Guest
     *
     * @param guest  Guest
     */
    @Transactional(readOnly = false)
    public Guest updateGuest(Guest guest) {
    	
    	try{
    	
    		guest = getGuestDAO().updateGuest(guest);
    	}
    	
    	catch(SQLException e){
    		
    		guest = null;
    	}
    	
    	return guest;
    }
 
    /**
     * Get Guest
     *
     * @param  id int Guest Id
     */

    public Guest getGuestById(int id) {
        return getGuestDAO().getGuestById(id);
    }
 
    /**
     * Get Guest List
     *
     */

    public List<Guest> getGuests() {
        return getGuestDAO().getGuests();
    }
 
    /**
     * Get Guest DAO
     *
     * @return guestDAO - Guest DAO
     */
    public GuestDAO getGuestDAO() {
        return guestDAO;
    }
 
    /**
     * Set Guest DAO
     *
     * @param  guestDAO - GuestDAO
     */
    public void setGuestDAO(GuestDAO guestDAO) {
        this.guestDAO = guestDAO;
    }

}