package net.larsbehnke.petclinicplus.validation;

import net.larsbehnke.petclinicplus.Clinic;
import net.larsbehnke.petclinicplus.Vet;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <code>Validator</code> for <code>Vet</code> forms.
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Lars Behnke
 */
public class VetValidator implements Validator {

	private Clinic clinic;

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return Vet.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		Vet vet = (Vet) obj;

		if (!StringUtils.hasLength(vet.getFirstName())) {
			errors.rejectValue("firstName", "required", "required");
		}

		if (!StringUtils.hasLength(vet.getLastName())) {
			errors.rejectValue("lastName", "required", "required");
		}

		if (!StringUtils.hasLength(vet.getLoginName())) {
			errors.rejectValue("loginName", "required", "required");
		} else if (vet.isNew()) {
			if (getClinic().loadVetByLoginName(vet.getLoginName()) != null) {
				errors.rejectValue("loginName", "duplicate", "duplicate");
			}
		}

		if (!StringUtils.hasLength(vet.getPassword())) {
			errors.rejectValue("password", "required", "required");
		}

	}

}
