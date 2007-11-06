package net.larsbehnke.petclinicplus;

import java.io.Serializable;

/**
 * Simple JavaBean domain object representing an person.
 *
 * @author Ken Krebs
 */
public class Person extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 4939379552457575644L;

	private String firstName;

	private String lastName;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
