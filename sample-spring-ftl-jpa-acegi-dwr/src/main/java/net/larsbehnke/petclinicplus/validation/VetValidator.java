package net.larsbehnke.petclinicplus.validation;

import net.larsbehnke.petclinicplus.model.Clinic;
import net.larsbehnke.petclinicplus.model.Vet;

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

		if (!StringUtils.hasLength(vet.getUserData().getFirstName())) {
			errors.rejectValue("userData.firstName", "required", "required");
		}

		if (!StringUtils.hasLength(vet.getUserData().getLastName())) {
			errors.rejectValue("userData.lastName", "required", "required");
		}

		if (!StringUtils.hasLength(vet.getUserData().getUsername())) {
			errors.rejectValue("userData.username", "required", "required");
		} 
		if (!StringUtils.hasLength(vet.getUserData().getPassword())) {
			errors.rejectValue("userData.password", "required", "required");
		}

	}

}
