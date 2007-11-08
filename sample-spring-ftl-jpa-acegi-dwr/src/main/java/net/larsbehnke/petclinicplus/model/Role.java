package net.larsbehnke.petclinicplus.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.acegisecurity.GrantedAuthority;

/**
 * Represents a persistent user role capable of collaborating with Acegi
 * security.
 * <p>
 * Corresponding mapping:
 * </p>
 * 
 * <pre>
 *  &lt;entity class=&quot;Role&quot;&gt;
 *        &lt;table name=&quot;ROLES&quot; /&gt;
 *        &lt;attributes&gt;
 *            &lt;transient name=&quot;authority&quot; /&gt;
 *        &lt;/attributes&gt;
 *  &lt;/entity&gt;
 *    
 * </pre>
 * 
 * @author Lars Behnke
 */
@Entity
@Table(name = "ROLES")
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
    @Transient
    public String getAuthority() {
        return getName();
    }

}
