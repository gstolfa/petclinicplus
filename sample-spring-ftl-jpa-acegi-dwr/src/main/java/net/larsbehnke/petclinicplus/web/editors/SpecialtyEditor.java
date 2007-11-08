package net.larsbehnke.petclinicplus.web.editors;

import java.beans.PropertyEditorSupport;

import net.larsbehnke.petclinicplus.model.Clinic;
import net.larsbehnke.petclinicplus.model.Specialty;
import net.larsbehnke.petclinicplus.util.EntityUtils;

import org.springframework.util.StringUtils;

/**
 * Converts Specialty objects into their corresponding ids and vice versa.
 * 
 * @author Lars Behnke
 * 
 */
public class SpecialtyEditor extends PropertyEditorSupport {

	private Clinic clinic;

	private boolean allowEmpty;

	public SpecialtyEditor(Clinic clinic, boolean allowEmpty) {
		this.clinic = clinic;
		this.allowEmpty = allowEmpty;
	}

	@Override
	public String getAsText() {
		Specialty spec = (Specialty) getValue();
		return spec == null ? "" : spec.getId() + "";
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
		setValue(EntityUtils.getById(clinic.getSpecialties(), Specialty.class, id));
	}
}
