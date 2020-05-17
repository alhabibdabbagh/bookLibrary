package com.spring.boot.application.web.ui.controller;
//

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// login controller
@Controller
public class LoginController {

	// login sayifasi
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {

		return "fancy-login";

	}

	// add request mapping for /access-denied
	// yanlis bilgiler girince
	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";

	}

}