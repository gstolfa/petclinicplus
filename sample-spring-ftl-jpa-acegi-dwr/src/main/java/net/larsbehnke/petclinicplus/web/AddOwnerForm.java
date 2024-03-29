package net.larsbehnke.petclinicplus.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.larsbehnke.petclinicplus.model.Owner;

import org.springframework.web.servlet.ModelAndView;

/**
 * JavaBean form controller that is used to add a new <code>Owner</code> to the system.
 *
 * @author Ken Krebs
 */
public class AddOwnerForm extends AbstractClinicForm {

	/**
	 * Creates and initializes the form. The command name is set. This name must
	 * be used in the form (JSP, Freemarker page etc.) when binding the command
	 * object.
	 */
	public AddOwnerForm() {
		setCommandName("owner");
		
		/* OK to start with a blank command object. */
		setCommandClass(Owner.class);
		
		/* activate session form mode to allow for detection of duplicate submissions */
		setSessionForm(true);
	}

	/** Method inserts a new <code>Owner</code>. */
	protected ModelAndView onSubmit(Object command) throws ServletException {
		Owner owner = (Owner) command;
		
		/* delegate the insert to the Business layer */
		owner = getClinic().storeOwner(owner);
		return new ModelAndView(getSuccessView(), "ownerId", owner.getId());
	}

	protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return disallowDuplicateFormSubmission(request, response);
	}

}
