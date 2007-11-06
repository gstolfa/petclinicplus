package net.larsbehnke.petclinicplus.util;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;

/**
 * Helper classes related to security.
 * 
 * @author Lars Behnke
 * 
 */
public final class SecurityUtils {

	/**
	 * Creates an Authentication instance and binds it to the security context.
	 * 
	 * @param user
	 *            The user.
	 * @param pwd
	 *            The password.
	 * @param authorities
	 *            The authorities.
	 */
	public static void createSecureContext(String user, String pwd,
			String[] authorities) {
		GrantedAuthority[] gas = new GrantedAuthorityImpl[authorities.length];
		for (int i = 0; i < gas.length; i++) {
			gas[i] = new GrantedAuthorityImpl(authorities[i]);
		}
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, pwd, gas);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	/**
	 * Clears the Authentication instance from the security context.
	 */
	public static void clearSecurityContext() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}


}
