package net.larsbehnke.petclinicplus.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.larsbehnke.petclinicplus.Pet;
import net.larsbehnke.petclinicplus.PetType;
import net.larsbehnke.petclinicplus.util.EntityUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * JavaBean Form controller that is used to edit an existing <code>Pet</code>.
 *
 * @author Ken Krebs
 */
public class EditPetForm extends AbstractClinicForm {

	public EditPetForm() {
		setCommandName("pet");
		// need a session to hold the formBackingObject
		setSessionForm(true);
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);
	}

	protected Map referenceData(HttpServletRequest request) throws ServletException {
		Map refData = new HashMap();
		refData.put("types", getClinic().getPetTypes());
		return refData;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Pet referred to by id in the request
		return getClinic().loadPet(ServletRequestUtils.getRequiredIntParameter(request, "petId"));
	}

	protected void onBind(HttpServletRequest request, Object command) throws ServletException {
		Pet pet = (Pet) command;
		int typeId = ServletRequestUtils.getRequiredIntParameter(request, "typeId");
		pet.setType((PetType) EntityUtils.getById(getClinic().getPetTypes(), PetType.class, typeId));
	}

	/** Method updates an existing Pet */
	protected ModelAndView onSubmit(Object command) throws ServletException {
		Pet pet = (Pet) command;
		// delegate the update to the business layer
		getClinic().storePet(pet);
		return new ModelAndView(getSuccessView(), "ownerId", pet.getOwner().getId());
	}

}
