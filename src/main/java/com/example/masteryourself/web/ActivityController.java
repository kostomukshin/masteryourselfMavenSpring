package com.example.masteryourself.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.masteryourself.model.ActivityModel;
import com.example.masteryourself.model.ActivityRepository;
import com.example.masteryourself.model.CategoryRepository;
import com.example.masteryourself.model.SignUpForm;
import com.example.masteryourself.model.User;
import com.example.masteryourself.model.UserRepository;
import com.example.masteryourself.service.ActivityService;

@Controller
public class ActivityController {
	@Autowired
	private ActivityRepository arepository;

	@Autowired
	private CategoryRepository crepository;

	@Autowired
	ActivityService service;

	@Autowired
	UserRepository urepository;

	@GetMapping(value = "/login")
	public String viewLoginPage() {
		return "login";
	}

	@RequestMapping(value = "signup")
	public String addUser(Model model) {
		model.addAttribute("SignUpForm", new SignUpForm());
		return "signup";
	}

	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute("SignUpForm") SignUpForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // validation errors
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRole("USER");
				newUser.setEmail(signupForm.getEmail());
				if (urepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
					urepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "signup_success";
	}

	@RequestMapping(value = { "/", "/activitylist" })
	public String activityList(Model model) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User userNow = urepository.findByUsername(username);
		System.out.println("USEnR FIND START");
		System.out.println(userNow);
		System.out.println("USER FIND END");
		model.addAttribute("a", arepository.findByUser(userNow));
		System.out.println("ACTIVITIES");
		System.out.println(arepository.findByUser(userNow));
		listByPage(model, 1);
		return "activitylist";

	}

	@RequestMapping(value = "/activities", method = RequestMethod.GET)
	public @ResponseBody List<ActivityModel> actListRest() {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User userNow = urepository.findByUsername(username);
		System.out.println("BY USER");
		System.out.println((List<ActivityModel>) arepository.findByUser(userNow));
		return (List<ActivityModel>) arepository.findByUser(userNow);
	}

	@RequestMapping(value = "user{id}/activity/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<ActivityModel> findActRest(@PathVariable("id") Long activityId) {
		return arepository.findById(activityId);
	}

	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {
		Page<ActivityModel> page = service.listAll(currentPage);

		int totalPages = page.getTotalPages();
		long totalActivities = page.getTotalElements();

		List<ActivityModel> activities = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("activities", activities);
		model.addAttribute("totalActivities", totalActivities);
		model.addAttribute("totalPages", totalPages);
		return "activitylist";
	}

	@RequestMapping(value = "/add")
	public String addActivity(Model model) {
		model.addAttribute("activity", new ActivityModel());
		model.addAttribute("categories", crepository.findAll());
		return "addactivity";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ActivityModel activity) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User userNow = urepository.findByUsername(username);
		activity.setUser(userNow);
		arepository.save(activity);
		System.out.println("SAVE START");
		System.out.println(activity);
		return "redirect:activitylist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteActivity(@PathVariable("id") Long activityId, Model model) {
		arepository.deleteById(activityId);
		return "redirect:../activitylist";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editActivity(@PathVariable("id") Long activityId, Model model) {
		model.addAttribute("activity", arepository.findById(activityId));
		model.addAttribute("categories", crepository.findAll());

		return "editactivity";
	}
}
