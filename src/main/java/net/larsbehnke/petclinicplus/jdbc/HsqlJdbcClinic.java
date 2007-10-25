/*
 * HsqlJdbcClinic.java
 *
 */

package net.larsbehnke.petclinicplus.jdbc;

/**
 * HSQL JDBC implementation of the Clinic interface.
 * Defines the identity query for HSQL.
 *
 * @author Juergen Hoeller
 */
public class HsqlJdbcClinic extends AbstractJdbcClinic {

	protected String getIdentityQuery() {
		return "call identity()";
	}

}
