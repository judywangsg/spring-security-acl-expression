/**
 * 
 */
package org.krams.tutorial.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles authentication related requests
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController {
        
	protected static Logger logger = Logger.getLogger("controller");

	/**
	 * Retrieves the login page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error, 
			ModelMap model) {
		logger.debug("Received request to show login page");

		// Add an error message to the model if login is unsuccessful
		if (error == true) {
			// Has errors
			model.put("error", "You have entered an invalid username or password!");
		} else {
			// No errors
			model.put("error", "");
		}
		
		// This will resolve to /WEB-INF/jsp/loginpage.jsp
		return "loginpage";
	}
	
	/**
	 * Retrieves the denied page. 
	 * <p>
	 * This is shown whenever a user has
	 * no access in a protected resource
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
 	public String getDeniedPage() {
		logger.debug("Received request to show denied page");
		
		// This will resolve to /WEB-INF/jsp/deniedpage.jsp
		return "deniedpage";
	}

}