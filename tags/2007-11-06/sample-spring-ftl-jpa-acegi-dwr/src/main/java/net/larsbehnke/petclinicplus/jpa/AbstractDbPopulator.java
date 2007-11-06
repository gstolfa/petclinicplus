package net.larsbehnke.petclinicplus.jpa;

import java.util.Set;

import net.larsbehnke.petclinicplus.util.SecurityUtils;
import net.larsbehnke.petclinicplus.util.namedvocabulary.NamedVocabularyManager;

public abstract class AbstractDbPopulator implements DbPopulator {

	public static final String MODE_CLEAN_INSERT = "CLEAN_INSERT";
	public static final String MODE_INSERT_IF_EMPTY = "INSERT_IF_EMPTY";

	private String mode;
	private String adminUser;
	private String adminPassword;
	private Set<String> adminAuthorities;


	private NamedVocabularyManager namedVocabularyManager;



	public NamedVocabularyManager getNamedVocabularyManager() {
		return namedVocabularyManager;
	}

	public void setNamedVocabularyManager(
			NamedVocabularyManager namedVocabularyManager) {
		this.namedVocabularyManager = namedVocabularyManager;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Set<String> getAdminAuthorities() {
		return adminAuthorities;
	}

	public void setAdminAuthorities(Set<String> adminAuthorities) {
		this.adminAuthorities = adminAuthorities;
	}

	public String getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}
	
	public void populate() {
		
        String[] authArray = getAdminAuthorities().toArray(new String[]{});
		SecurityUtils.createSecureContext(getAdminUser(), getAdminPassword(), authArray);
        try {
    		if (MODE_CLEAN_INSERT.equalsIgnoreCase(getMode())) {
    			doInsertClean();
    		} else if (MODE_INSERT_IF_EMPTY.equalsIgnoreCase(getMode())) {
    			doInsertIfEmpty();
    		} else {
    			throw new RuntimeException("Invalid database population mode: "
    					+ getMode());
    		}
        } finally {
        	SecurityUtils.clearSecurityContext();
        }


	}

	/**
	 * Template method to be implemented by subclass.
	 */
	abstract protected void doInsertIfEmpty();

	/**
	 * Template method to be implemented by subclass.
	 */
	abstract protected void doInsertClean();



}
