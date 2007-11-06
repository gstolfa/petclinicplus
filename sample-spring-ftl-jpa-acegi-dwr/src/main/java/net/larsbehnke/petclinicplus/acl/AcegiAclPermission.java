package net.larsbehnke.petclinicplus.acl;


/**
 * CREATE TABLE acl_permission ( id IDENTITY NOT NULL, acl_object_identity
 * INTEGER NOT NULL, recipient VARCHAR_IGNORECASE(100) NOT NULL, mask INTEGER
 * NOT NULL, CONSTRAINT unique_recipient UNIQUE(acl_object_identity, recipient),
 * FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity(id) );
 * 
 * @author Lars
 * 
 */
public class AcegiAclPermission {

	private AcegiAclObjectIdentity aclObjectIdentity;
	private String recipient;
	private Integer mask;
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public AcegiAclObjectIdentity getAclObjectIdentity() {
		return aclObjectIdentity;
	}

	public void setAclObjectIdentity(AcegiAclObjectIdentity aclObjectIdentity) {
		this.aclObjectIdentity = aclObjectIdentity;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Integer getMask() {
		return mask;
	}

	public void setMask(Integer mask) {
		this.mask = mask;
	}

}
