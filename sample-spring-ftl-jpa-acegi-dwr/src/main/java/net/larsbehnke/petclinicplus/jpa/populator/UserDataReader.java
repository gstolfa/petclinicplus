package net.larsbehnke.petclinicplus.jpa.populator;

import java.util.Collection;

import net.larsbehnke.petclinicplus.User;

/**
 * Contract for user data readers.
 * @author Lars
 */
public interface UserDataReader {

    /**
     * Reads user data from an arbitrary source.
     * @return The user data.
     */
    Collection<User> read();
}
