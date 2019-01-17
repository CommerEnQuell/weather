package nl.commerquell.weather.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

public class JspUtils {
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
