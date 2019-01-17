package nl.commerquell.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nl.commerquell.weather.utils.JspUtils;

@Controller
public class LoginController {

	@GetMapping("/showLoginPage")
	public String showLoginPage() {
		return "login-form";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied(Model theModel) {
		JspUtils.addUsernameToModel(theModel);
		return "access-denied";
	}
}
