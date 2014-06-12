package spring.dao;
 
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import spring.model.Guest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * guest DAO
 *
 * @author uday
 * @since 19 Nov 2013
 * @version 1.0.0
 *
 */
@Repository
public class GuestDAO  {
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
     * Add guest
     *
     * @param   guest   guest
     */
   
    public Integer addGuest(Guest guest) {
    	Serializable ser = getSessionFactory().getCurrentSession().save(guest);
    	return 0; 
    }
 
    /**
     * Delete guest
     *
     * @param   guest  guest
     */
   
    public void deleteGuest(Guest guest) {
    	System.out.println("delete in DAO: " + guest.getName());
        getSessionFactory().getCurrentSession().delete(guest);
    }
 
    /**
     * Update guest
     *
     * @param  guest guest 
     */
   
    public Guest updateGuest(Guest guest) throws SQLException {
    	
    	guest = (Guest) getSessionFactory().getCurrentSession().merge(guest);
    	
        return guest;
    }
 
    /**
     * Get guest
     *
     * @param  id int  
     * @return guest
     */
   
    public Guest getGuestById(int id) {
        @SuppressWarnings("unchecked")
		List<Guest> list = (List<Guest>) getSessionFactory().getCurrentSession()
                                            .createQuery("from spring.model.Guest  where id=?")
                                            .setParameter(0, id).list();
        Guest guest = (Guest)list.get(0);
        return guest;
    }
 
    /**
     * Get guest List
     *
     * @return List - guest list
     */
   
    @SuppressWarnings("unchecked")
	public List<Guest> getGuests() {
        List<Guest> list = (List<Guest>) getSessionFactory().getCurrentSession().createQuery("from spring.model.Guest").list();
        return list;
    }
 
}