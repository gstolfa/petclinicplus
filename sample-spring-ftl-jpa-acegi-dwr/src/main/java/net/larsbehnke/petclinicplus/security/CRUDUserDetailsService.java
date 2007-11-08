package net.larsbehnke.petclinicplus.security;

import net.larsbehnke.petclinicplus.model.User;

import org.acegisecurity.userdetails.UserDetailsService;

/**
 * Adds capability to add, update and delete data to the UserDetailsService.
 * @author Lars
 */
public interface CRUDUserDetailsService extends UserDetailsService {

    /**
     * Clears all users from the data store.
     */
    void clearUsers();

    /**
     * Counts all users in the data store.
     */
    Long countUsers();
    
    /**
     * Inserts or update user data.
     * @param user The user to store.
     * @return The user (with ID).
     */
    User storeUser(User user);

    /**
     * Deletes a user from the data store.
     * @param user The user to delete.
     */
    void deleteUser(User user);

}
