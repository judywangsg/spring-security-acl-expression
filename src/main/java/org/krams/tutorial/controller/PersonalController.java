package org.krams.tutorial.controller;

import org.apache.log4j.Logger;
import org.krams.tutorial.domain.PersonalPost;
import org.krams.tutorial.service.PersonalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;

/**
 * Handles Personal-related requests
 */
@Controller
@RequestMapping("/personal")
public class PersonalController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="personalService")
	private PersonalService personalService;
    
    /**
     * Retrieves the Edit page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditPage(Model model) {
    	logger.debug("Received request to view edit page");
    
    	// Call service. If true, we have appropriate authority
    	if (personalService.edit(new PersonalPost()) == true) {
        	// Add result to model
        	model.addAttribute("result", "Entry has been edited successfully!");
    	} else {
        	// Add result to model
        	model.addAttribute("result", "You're not allowed to perform that action!");
    	}

    	// Add source to model to help us determine the source of the JSP page
    	model.addAttribute("source", "Personal >> Edit");
    	
    	// Add our current role and username
    	model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    	model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
    	
    	// This will resolve to /WEB-INF/jsp/resultpage.jsp
    	return "resultpage";
	}
    
    /**
     * Retrieves the Add page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddPage(Model model) {
    	logger.debug("Received request to view add page");
    
    	// Call service. If true, we have appropriate authority
    	if (personalService.add(new PersonalPost()) == true) {
        	// Add result to model
        	model.addAttribute("result", "Entry has been added successfully!");
    	} else {
        	// Add result to model
        	model.addAttribute("result", "You're not allowed to perform that action!");
    	}
    	
    	// Add source to model to help us determine the source of the JSP page
    	model.addAttribute("source", "Personal >> Add");
    	
    	// Add our current role and username
    	model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    	model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
    	
    	// This will resolve to /WEB-INF/jsp/resultpage.jsp
    	return "resultpage";
	}
    
    /**
     * Retrieves the Delete page
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDeletePage(Model model) {
    	logger.debug("Received request to view delete page");
    
    	// Call service. If true, we have appropriate authority
    	if (personalService.delete(new PersonalPost()) == true) {
        	// Add result to model
        	model.addAttribute("result", "Entry has been deleted successfully!");
    	} else {
        	// Add result to model
        	model.addAttribute("result", "You're not allowed to perform that action!");
    	}
    	
    	// Add source to model to help us determine the source of the JSP page
    	model.addAttribute("source", "Personal >> Delete");
    	
    	// Add our current role and username
    	model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    	model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
    	
    	// This will resolve to /WEB-INF/jsp/resultpage.jsp
    	return "resultpage";
	}
}
