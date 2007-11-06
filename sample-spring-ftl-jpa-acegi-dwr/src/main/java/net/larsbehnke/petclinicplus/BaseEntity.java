package net.larsbehnke.petclinicplus;

import org.acegisecurity.acl.basic.AclObjectIdentity;
import org.acegisecurity.acl.basic.AclObjectIdentityAware;
import org.acegisecurity.acl.basic.NamedEntityObjectIdentity;

/**
 * Simple JavaBean domain object with an id property.
 * Used as a base class for objects needing this property.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * 
 */
public class BaseEntity implements AclObjectIdentityAware {

	private Integer id;
	

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public boolean isNew() {
		return (this.id == null);
	}

	public AclObjectIdentity getAclObjectIdentity() {
	    return new NamedEntityObjectIdentity(this.getClass().getName(), getId() + "");
	}

}
