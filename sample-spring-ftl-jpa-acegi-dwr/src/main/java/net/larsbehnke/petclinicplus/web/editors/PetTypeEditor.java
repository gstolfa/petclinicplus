package net.larsbehnke.petclinicplus.web.editors;

import java.beans.PropertyEditorSupport;

import net.larsbehnke.petclinicplus.model.Clinic;
import net.larsbehnke.petclinicplus.model.PetType;
import net.larsbehnke.petclinicplus.util.EntityUtils;

import org.springframework.util.StringUtils;

/**
 * Converts PetType objects into their corresponding ids and vice versa.
 * 
 * @author Lars Behnke
 * 
 */
public class PetTypeEditor extends PropertyEditorSupport {

	private Clinic clinic;

	private boolean allowEmpty;

	public PetTypeEditor(Clinic clinic, boolean allowEmpty) {
		this.clinic = clinic;
		this.allowEmpty = allowEmpty;
	}

	@Override
	public String getAsText() {
		PetType pt = (PetType) getValue();
		return pt == null ? "" : pt.getId() + "";
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (allowEmpty && !StringUtils.hasText(text)) {
			setValue(null);
			return;
		}
		Integer id;
		try {
			id = new Integer(text);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid pet type selection");
		}
		setValue(EntityUtils.getById(clinic.getPetTypes(), PetType.class, id));
	}
}
