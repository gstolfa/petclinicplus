package net.larsbehnke.petclinicplus.jpa;

import net.larsbehnke.petclinicplus.util.namedvocabulary.NamedVocabularyManager;

public abstract class AbstractDbPopulator implements DbPopulator {

	public static final String MODE_CLEAN_INSERT = "CLEAN_INSERT";
	public static final String MODE_INSERT_IF_EMPTY = "INSERT_IF_EMPTY";

	private String mode;


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

	public void populate() {
		if (MODE_CLEAN_INSERT.equalsIgnoreCase(getMode())) {
			doInsertClean();
		} else if (MODE_INSERT_IF_EMPTY.equalsIgnoreCase(getMode())) {
			doInsertIfEmpty();
		} else {
			throw new RuntimeException("Invalid database population mode: "
					+ getMode());
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
