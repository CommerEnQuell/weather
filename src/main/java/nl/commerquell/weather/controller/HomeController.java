package nl.commerquell.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nl.commerquell.weather.utils.JspUtils;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String showHomePage(Model theModel) {
		JspUtils.addUsernameToModel(theModel);
		return "home";
	}
}
