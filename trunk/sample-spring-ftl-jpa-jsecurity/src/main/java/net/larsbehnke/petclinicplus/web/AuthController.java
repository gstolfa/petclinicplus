package net.larsbehnke.petclinicplus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AuthController extends MultiActionController {

	private String logoutView;
	private String loginView;
	private String loginErrorView;

	public String getLogoutView() {
		return logoutView;
	}

	public void setLogoutView(String logoutView) {
		this.logoutView = logoutView;
	}

	public String getLoginView() {
		return loginView;
	}

	public void setLoginView(String loginView) {
		this.loginView = loginView;
	}

	public String getLoginErrorView() {
		return loginErrorView;
	}

	public void setLoginErrorView(String loginErrorView) {
		this.loginErrorView = loginErrorView;
	}

	public ModelAndView logoutHandler(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession() != null)
			request.getSession().invalidate();
		return new ModelAndView(getLogoutView());
	}

	public ModelAndView loginHandler(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView(getLoginView());
	}

	public ModelAndView loginErrorHandler(HttpServletRequest request,
			HttpServletResponse response) {
	
		return new ModelAndView(getLoginErrorView());
	}
}
