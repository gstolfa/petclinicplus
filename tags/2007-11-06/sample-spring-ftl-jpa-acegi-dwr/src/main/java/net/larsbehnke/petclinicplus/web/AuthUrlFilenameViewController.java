package net.larsbehnke.petclinicplus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.AbstractProcessingFilter;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.util.WebUtils;

public class AuthUrlFilenameViewController extends UrlFilenameViewController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView result = super.handleRequestInternal(request, response);

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null)
			result.addObject("auth", auth);

		bindSessionAttribute(request, "acegiException",
				AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY,
				result);

		bindSessionAttribute(
				request,
				"acegiLastUser",
				AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY,
				result);

		return result;
	}

	private void bindSessionAttribute(HttpServletRequest request,
			String modelKey, String sessionKey, ModelAndView result) {
		Object obj = WebUtils.getSessionAttribute(request, sessionKey);
		if (obj != null) {
			result.addObject(modelKey, obj);
		}
	}

}
