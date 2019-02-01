package nl.commerquell.weather.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

/**
 * Lorem ipsum dolor sit amet, consectetur adipisicing elit,
 * sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
 * Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
 * ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit
 * in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
 * Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
 * deserunt mollit anim id est laborum.
 */

public final class JspUtils {
	
	private JspUtils() {}
	
	public static void addUsernameToModel(Model theModel) {
		String userName = null;
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if (a != null) {
			if (a.getPrincipal() instanceof UserDetails) {
				userName = ((UserDetails) a.getPrincipal()).getUsername();
			}
			theModel.addAttribute("user", userName);
		}
	}

}
