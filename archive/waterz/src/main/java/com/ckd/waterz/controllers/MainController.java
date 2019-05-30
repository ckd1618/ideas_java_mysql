package com.ckd.waterz.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ckd.waterz.models.User;
import com.ckd.waterz.services.UserService;
import com.ckd.waterz.validator.UserValidator;

@Controller
public class MainController {
    private final UserService userService;
    private final UserValidator userValidator;



    public MainController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;

    }
    //to home
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = userService.findUserById(userId);
    	model.addAttribute("user",u);
    	
//    	List<Idea>home = ideaService.allIdea();
//    	model.addAttribute("home", home);
    	
    	return"home.jsp";
    }

	    
	
    
  
	
//===================================registration/login stuff    
    @RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
//    @RequestMapping("/login")
//    public String login() {
//        return "loginPage.jsp";
//    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
    	if(result.hasErrors()) {
    		return "registrationPage.jsp";
    	}else {
    		User u = userService.registerUser(user);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/home";
    	}
        
    }
    
    @RequestMapping(value="/l", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
    	boolean isAuthenticated = userService.authenticateUser(email,  password);
    	if(isAuthenticated) {
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/home";
    	} else {
    		model.addAttribute("user", new User()); //EB
    		model.addAttribute("error", "Invalid Credentials. Please try again.");
    		return "registrationPage.jsp"; //changed from login
    	}
        // else, add error messages and return the login page
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        // redirect to login page
    	session.invalidate();
    	return "redirect:/"; //changed from login
    }
}