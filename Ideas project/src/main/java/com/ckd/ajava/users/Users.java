package com.ckd.ajava.users;

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

import com.ckd.ajava.models.Idea;
import com.ckd.ajava.models.User;
import com.ckd.ajava.services.IdeaService;
import com.ckd.ajava.services.UserService;
import com.ckd.ajava.validator.UserValidator;


@Controller
public class Users {
    private final UserService userService;
    private final UserValidator userValidator;
    private final IdeaService ideaService;


    public Users(UserService userService, UserValidator userValidator, IdeaService ideaService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.ideaService = ideaService;
    }

    @RequestMapping("/ideas")
    public String ideas(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = userService.findUserById(userId);
    	model.addAttribute("user",u);
    	
    	List<Idea>ideas = ideaService.allIdea();
    	model.addAttribute("ideas", ideas);
    	
    	return"ideasP2.jsp";
    }
    //likecount
    @GetMapping("/ideas/likecount/{id}")
    	public String likecount(HttpSession session, @PathVariable("id") Long id) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = userService.findUserById(userId);
    	Idea idea = ideaService.getIdea(id);

//    	if (likecount > 0) ; 
//    	else idea.setLikecount(0);
    	Integer likecount = idea.getLikecount();
    	idea.setLikecount(1+likecount);
    	ideaService.updateIdea(idea);
			return "redirect:/ideas";
    	
    }
    
    @RequestMapping("/ideas/new")
    public String goto3(@ModelAttribute("idea") Idea idea) {
      return "ideasnewP3.jsp";
    }
  
	@PostMapping("/ideas/new")
	public String goto3b(HttpSession session, @Valid @ModelAttribute("idea") Idea idea, BindingResult result) {
  	
		if(result.hasErrors()) {
//			errors.addFlashAttribute("errors", result.getAllErrors());
			return "ideasnewP3.jsp";
		}
		Idea idea2 = ideaService.createIdea(idea);
    	Long userId = (Long) session.getAttribute("userId");
    	User u = userService.findUserById(userId);
    	idea2.setUser(u);
    	idea2.setLikecount(0);
    	ideaService.updateIdea(idea2);
		return "redirect:/ideas";
	}
    
	@GetMapping("/ideas/{id}")
	public String goto4(Model model, @PathVariable("id") Long id) {

		Idea ideachosen = ideaService.getIdea(id);
		User user = ideachosen.getUser();
		
		model.addAttribute("idea", ideachosen);
		model.addAttribute("user", user);
		
		return "ideasnumP4.jsp";
	}
	
	//update page
	@GetMapping(value="/ideas/{id}/edit")
	public String goto5(HttpSession session, Model model, @PathVariable("id") Long id) {

		if (ideaService.getIdea(id).getUser().getId() != session.getAttribute("userId")) {
			return "redirect:/ideas";
		}
		
		session.setAttribute("likecount", ideaService.getIdea(id).getLikecount());
		model.addAttribute("idea", ideaService.getIdea(id));
	    return "ideasnumeditP5.jsp";
	}

	//update return
	@RequestMapping(value="/ideas/{id}/edit", method=RequestMethod.PUT)
	public String goto5b(HttpSession session, @PathVariable("id") Long id, @Valid @ModelAttribute("idea") Idea idea, BindingResult result, RedirectAttributes errors) {
	    if (result.hasErrors()) {
	    	errors.addFlashAttribute("errors", result.getAllErrors());
	        return "redirect:/ideas/"+ id +"/edit";
	    } else {
	    	Long userId = (Long) session.getAttribute("userId");
	    	User u = userService.findUserById(userId);
	    	idea.setUser(u);
	    	Integer likecount = idea.getLikecount();
	    	idea.setLikecount((Integer) session.getAttribute("likecount"));
	    	ideaService.updateIdea(idea);
	        return "redirect:/ideas/";
	    }
	}
	//delete
	@GetMapping("/delete/{id}")
	public String delete4b(Model model, @PathVariable("id") Long id) {
		ideaService.deleteIdea(id);
	    return "redirect:/ideas";
	}
	    
	
    
    
    //===============================================================================================================================================
//    //last part of login
//    @RequestMapping("/courses")
//    public String courses(HttpSession session, Model model) {
//        // get user from session, save them in the model and return the courses page
//    	Long userId = (Long) session.getAttribute("userId");
//    	User u = userService.findUserById(userId);
//    	model.addAttribute("user",u);
//    	
////    	model.addAttribute("allcourse", courseService.allCourse());
//    	
//    	ArrayList<Course> coursetoadd = new ArrayList<Course>();
//    	ArrayList<Course> coursealready = new ArrayList<Course>();
//    	ArrayList<Course> full = new ArrayList<Course>();
//    	
//    	for (Course c : courseService.allCourse()) {
//    		System.out.println("\n\n\n\n\n\n" +c);
//    		if (c.getUsers().size() >= c.getCapacity()) {
//    			full.add(c);
//    		} 
//    		else if (c.getUsers().contains(u)) {
//    			coursealready.add(c);
//    		}
//    		else coursetoadd.add(c);
//    	}
//    	session.setAttribute("full", full);
//    	model.addAttribute("full", full);
//    	model.addAttribute("coursetoadd", coursetoadd);
//    	model.addAttribute("coursealready", coursealready);
//    	
//    	//to display the contents of model using the standard jsp language use:
//    	//	model	<%= request.getAttribute("full") %>
//		// 	session	<%= session.getAttribute("full") %>
//    	
//    	return "courses.jsp";
//    }
//    
//    @RequestMapping("/q3coursesnew")
//    public String goto3(@ModelAttribute("course") Course course) {
//        return "q3coursesnew.jsp";
//    }
//    
//    @PostMapping("/q3coursesnew")
//    public String goto3b(@Valid @ModelAttribute("course") Course course, BindingResult result, RedirectAttributes errors) {
//    	
//		if(result.hasErrors()) {
////			errors.addFlashAttribute("errors", result.getAllErrors());
//			return "q3coursesnew.jsp";
//		}
//		Course course2 = courseService.createCourse(course);
//		return "redirect:/courses";
//    }
//    
//    //important part for 2 table adding
//    @GetMapping("/coursesb/{id}")
//	public String goto2b(Model model, @PathVariable("id") Long id, HttpSession session) {
//    	
//    	Long userId = (Long) session.getAttribute("userId");
//    	User u = userService.findUserById(userId);
//    	
//    	List<User> usersincourse = courseService.getCourseC(id);
//    	if (usersincourse.contains(u)) {
//    		System.out.println("\n\n\n\n\n found!!!!!!!!");
//    	}
//    	
//    	CourseUser cu = new CourseUser();
//    	Course c = courseService.getCourse(id);
//
//    	cu.setCourse(c);
//    	cu.setUser(u);
//    	courseUserService.createCourseUser(cu);
//    	return "redirect:/courses";
//    }
//    	
//    
//    
//    //important part for 2 table adding
////	@GetMapping("/coursesb/{id}")
////	public String goto2b(Model model, @PathVariable("id") Long id, HttpSession session) {
////    	Long userId = (Long) session.getAttribute("userId");
////		Course coursechosen = courseService.getCourse(id);
////		List<User> x = coursechosen.getUsers();
////		
////		x.add(userService.findUserById(userId));
////		System.out.println("\n\n\n\n\n\n\n\n\n\n" + x);
////		courseService.updateCourse(coursechosen);
////		return "redirect:/courses";
////	}
//	
//	@GetMapping("/courses/{id}")
//	public String goto4(Model model, @PathVariable("id") Long id, HttpSession session) {
//		//get course name
//		Course coursechosen = courseService.getCourse(id);
//		List<User> x = coursechosen.getUsers();
//		
//
//		model.addAttribute("course", coursechosen);
//		model.addAttribute("alluser", x);
//		model.addAttribute("numofstudents", x.size());
//		
//		List<CourseUser> xlist = courseUserService.middleByCourse(coursechosen);
//		for(CourseUser cu : xlist) {
//			System.out.println(cu.getUser().getName());
//		}
//		model.addAttribute("xlist", xlist);
////		model.addAttribute("courses", courseUserService.findallCUbyC(id));
//		//get all users in the class with getter
//		return "q4coursesnum.jsp";
//	}
//    
//    @GetMapping("/delete/{id}")
//    public String delete4b(Model model, @PathVariable("id") Long id) {
//    	courseService.deleteCourse(id);
//        return "redirect:/courses";
//    }
//    
//    //update page
//    @GetMapping(value="/courses/{id}/edit")
//    public String goto5(Model model, @PathVariable("id") Long id) {
//
//    	model.addAttribute("course", courseService.getCourse(id));
//        return "q5coursesnumedit.jsp";
//    }
//    
//    //update return
//    @RequestMapping(value="/courses/{id}/edit", method=RequestMethod.PUT)
//    public String goto5b(@PathVariable("id") Long id, @Valid @ModelAttribute("course") Course course, BindingResult result) {
//        if (result.hasErrors()) {
//            return "q5coursesnumedit.jsp";
//        } else {
//            courseService.updateCourse(course);
//            return "redirect:/courses";
//        }
//    }
	
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
    		return "redirect:/ideas";
    	}
        
    }
    
    @RequestMapping(value="/l", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
    	boolean isAuthenticated = userService.authenticateUser(email,  password);
    	if(isAuthenticated) {
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/ideas";
    	} else {
    		model.addAttribute("user", new User());
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
