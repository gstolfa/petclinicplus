package net.larsbehnke.petclinicplus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.larsbehnke.petclinicplus.Clinic;
import net.larsbehnke.petclinicplus.Owner;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * <code>MultiActionController</code> that handles all non-form URL's.
 *
 * @author Ken Krebs
 * @author Rob Harrop
 */
public class ClinicController extends MultiActionController implements InitializingBean {

	private Clinic clinic;


	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public void afterPropertiesSet() {
		if (this.clinic == null) {
			throw new IllegalArgumentException("Property 'clinic' is required");
		}
	}


	/**
	 * Custom handler for the welcome view.
	 * <p>Note that this handler returns an empty ModelAndView object.
	 * It relies on the RequestToViewNameTranslator to come up with
	 * a view name based on the request URL: "/welcome.html" -> "welcome",
	 * plus configured "View" suffix -> "welcomeView".
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @return a ModelAndView to render the response
	 */
	public ModelAndView welcomeHandler(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView();
	}

	/**
	 * Custom handler for vets display.
	 * 
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @return a ModelAndView to render the response
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView vetsHandler(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vets", new ModelMap("vets", this.clinic.getVets()));
	}

	/**
	 * Custom handler for owner display.
	 * <p>Note that this handler usually returns a ModelAndView object
	 * without view, also leveraging convention-based model attribute names.
	 * It relies on the RequestToViewNameTranslator to come up with
	 * a view name based on the request URL: "/owner.html" -> "owner",
	 * plus configured "View" suffix -> "ownerView".
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @return a ModelAndView to render the response
	 */
	public ModelAndView ownerHandler(HttpServletRequest request, HttpServletResponse response) {
	    int ownerId = ServletRequestUtils.getIntParameter(request, "ownerId", 0);
		Owner owner = this.clinic.loadOwner(ownerId);
		if (owner == null) {
			return new ModelAndView("redirect:findOwners.htm");
		}
		return new ModelAndView().addObject(owner);
	}

}
