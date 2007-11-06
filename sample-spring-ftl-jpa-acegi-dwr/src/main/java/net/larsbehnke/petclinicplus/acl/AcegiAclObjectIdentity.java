package net.larsbehnke.petclinicplus.acl;

/**
 * CREATE TABLE acl_object_identity ( id IDENTITY NOT NULL, object_identity
 * VARCHAR_IGNORECASE(250) NOT NULL, parent_object INTEGER, acl_class
 * VARCHAR_IGNORECASE(250) NOT NULL, CONSTRAINT unique_object_identity
 * UNIQUE(object_identity), FOREIGN KEY (parent_object) REFERENCES
 * acl_object_identity(id) );
 * 
 * @author Lars
 * 
 */
public class AcegiAclObjectIdentity {

	private String objectIdentity;
	private AcegiAclObjectIdentity parentObject;
	private String aclClass;
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getObjectIdentity() {
		return objectIdentity;
	}

	public void setObjectIdentity(String objectIdentity) {
		this.objectIdentity = objectIdentity;
	}

	public AcegiAclObjectIdentity getParentObject() {
		return parentObject;
	}

	public void setParentObject(AcegiAclObjectIdentity parentObject) {
		this.parentObject = parentObject;
	}

	public String getAclClass() {
		return aclClass;
	}

	public void setAclClass(String aclClass) {
		this.aclClass = aclClass;
	}

}
