package net.larsbehnke.petclinicplus.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.larsbehnke.petclinicplus.Pet;
import net.larsbehnke.petclinicplus.PetType;
import net.larsbehnke.petclinicplus.util.EntityUtils;
import net.larsbehnke.petclinicplus.web.editors.PetTypeEditor;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * JavaBean Form controller that is used to edit an existing <code>Pet</code>.
 * 
 * @author Ken Krebs
 */
public class EditPetForm extends AbstractClinicForm {

	/**
	 * Creates and initializes the form controller. The command name is set.
	 * This name must be used in the form (JSP, Freemarker page etc.) when
	 * binding the command object. The command is created in the
	 * <code>formBackingObject()</code> method.
	 */
	public EditPetForm() {
		setCommandName("pet");

		/* need a session to hold the formBackingObject */
		setSessionForm(true);

		/* initialize the form from the formBackingObject */
		setBindOnNewForm(true);
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
		binder.registerCustomEditor(PetType.class, new PetTypeEditor(
				getClinic(), false));

	}

	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request)
			throws ServletException {
		Map refData = new HashMap();
		refData.put("types", EntityUtils.createMap(getClinic().getPetTypes()));
		return refData;
	}

	/**
	 * This method returns the command object that is populated with request
	 * parameters / input field values. An alternative (yet less flexible) way
	 * to register an command is calling <code>setCommandClass()</code> from
	 * the controller's constructor.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {
		// get the Pet referred to by id in the request
		return getClinic().loadPet(
				ServletRequestUtils.getRequiredIntParameter(request, "petId"));
	}

	protected void onBind(HttpServletRequest request, Object command)
			throws ServletException {
		// Pet pet = (Pet) command;
		// int typeId = ServletRequestUtils.getRequiredIntParameter(request,
		// "typeId");
		// pet.setType((PetType) EntityUtils.getById(getClinic().getPetTypes(),
		// PetType.class, typeId));
	}

	/** Method updates an existing Pet */
	protected ModelAndView onSubmit(Object command) throws ServletException {
		Pet pet = (Pet) command;
		// delegate the update to the business layer
		getClinic().storePet(pet);
		return new ModelAndView(getSuccessView(), "ownerId", pet.getOwner()
				.getId());
	}

}
