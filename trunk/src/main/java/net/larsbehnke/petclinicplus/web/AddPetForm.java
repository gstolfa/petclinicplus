package net.larsbehnke.petclinicplus.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.larsbehnke.petclinicplus.Owner;
import net.larsbehnke.petclinicplus.Pet;
import net.larsbehnke.petclinicplus.PetType;
import net.larsbehnke.petclinicplus.util.EntityUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * JavaBean form controller that is used to add a new <code>Pet</code> to the system.
 *
 * @author Ken Krebs
 */
public class AddPetForm extends AbstractClinicForm {

	/**
	 * Creates and initializes the form. The command name is set. This name must
	 * be used in the form (JSP, Freemarker page etc.) when binding the command
	 * object.
	 */
	public AddPetForm() {
		setCommandName("pet");
		
		/* need a session to hold the formBackingObject */
		setSessionForm(true);
	}

	protected Map referenceData(HttpServletRequest request) throws ServletException {
		Map refData = new HashMap();
		refData.put("types", getClinic().getPetTypes());
		return refData;
	}

	/**
	 * This method returns the command object that is populated with request parameters / input field values.
	 * An alternative (yet less flexible) way to register an command is calling <code>setCommandClass()</code> 
	 * from the controller's constructor.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		Owner owner = getClinic().loadOwner(ServletRequestUtils.getRequiredIntParameter(request, "ownerId"));
		Pet pet = new Pet();
		owner.addPet(pet);
		return pet;
	}

	protected void onBind(HttpServletRequest request, Object command) {
		Pet pet = (Pet) command;
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		pet.setType((PetType) EntityUtils.getById(getClinic().getPetTypes(), PetType.class, typeId));
	}

	/** Method inserts a new Pet */
	protected ModelAndView onSubmit(Object command) throws ServletException {
		Pet pet = (Pet) command;
		// delegate the insert to the Business layer
		getClinic().storePet(pet);
		return new ModelAndView(getSuccessView(), "ownerId", pet.getOwner().getId());
	}

	protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return disallowDuplicateFormSubmission(request, response);
	}

}
