package net.larsbehnke.petclinicplus.hibernate;

import net.larsbehnke.petclinicplus.AbstractClinicTests;

/**
 * Live unit tests for HibernateClinic implementation.
 * "applicationContext-hibernate.xml" determines the actual beans to test.
 *
 * @author Juergen Hoeller
 */
public class HibernateClinicTests extends AbstractClinicTests {

	protected String getConfigPath() { 
		return "applicationContext-hibernate.xml";
	}

}
