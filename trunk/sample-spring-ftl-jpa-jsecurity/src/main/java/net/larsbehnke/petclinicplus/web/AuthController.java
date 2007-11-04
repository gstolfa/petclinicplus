package net.larsbehnke.petclinicplus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AuthController extends MultiActionController {

	public ModelAndView logoutHandler(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession() != null)
			request.getSession().invalidate();
		return new ModelAndView("welcome");
	}

	public ModelAndView loginHandler(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("login");
	}

	public ModelAndView loginErrorHandler(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("loginerror");
	}
}
