package net.larsbehnke.petclinicplus;

import java.io.Serializable;

import org.acegisecurity.GrantedAuthority;

/**
 * Represents a persistent user role capable of collaborating with Acegi
 * security.
 * @author Lars Behnke
 */
public class Role extends NamedEntity implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = -3016631432001089541L;

    public Role() {
        this(null);
    }
    
    public Role(String name) {
        super();
        setName(name);
    }
    
    /*
     * {@inheritDoc}
     */
    public String getAuthority() {
        return getName();
    }

    
}
