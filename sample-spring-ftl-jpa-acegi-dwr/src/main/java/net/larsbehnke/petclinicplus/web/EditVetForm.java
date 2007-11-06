package net.larsbehnke.petclinicplus.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.larsbehnke.petclinicplus.Specialty;
import net.larsbehnke.petclinicplus.Vet;
import net.larsbehnke.petclinicplus.util.EntityUtils;
import net.larsbehnke.petclinicplus.web.editors.SpecialtyEditor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * JavaBean Form controller that is used to edit an existing <code>Vet</code>.
 * 
 * @author Ken Krebs
 */
public class EditVetForm extends AbstractClinicForm {

	private static Log log = LogFactory.getLog(EditVetForm.class);
	
	/**
	 * Creates and initializes the form controller. The command name is set.
	 * This name must be used in the form (JSP, Freemarker page etc.) when
	 * binding the command object. The command is created in the
	 * <code>formBackingObject()</code> method.
	 */
	public EditVetForm() {
		setCommandName("vet");

		/* need a session to hold the formBackingObject */
		setSessionForm(true);

		/* initialize the form from the formBackingObject */
		setBindOnNewForm(true);
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Specialty.class, new SpecialtyEditor(
				getClinic(), false));

	}

	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request, Object command, Errors errors)
			throws ServletException {
		Map refData = new HashMap();
		refData.put("specialties", EntityUtils.createMap(getClinic().getSpecialties()));
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
		return getClinic().loadVet(
				ServletRequestUtils.getRequiredIntParameter(request, "vetId"));
	}

	protected void onBind(HttpServletRequest request, Object command)
			throws ServletException {
		Vet vet = (Vet) command;
		log.debug("Binding vet: " + vet.getLastName());
	}

	/** Method updates an existing Vet */
	protected ModelAndView onSubmit(Object command) throws ServletException {
		Vet vet = (Vet) command;
		getClinic().storeVet(vet);
		return new ModelAndView(getSuccessView());
	}

}
