package org.utopia.internationale.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class SecurityController {
	
	/*@RequestMapping("/loginAdmin")
	public String Admin() {
		return "login";
	}*/
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/index")
	public String home() {
		//return "redirect:/index";
		return "index";
	}
	
	@RequestMapping(value="/403")
	public String accessDenied(){
		return "403";
	}
}
