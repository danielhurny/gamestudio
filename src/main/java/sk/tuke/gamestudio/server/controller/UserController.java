package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.server.entity.User;
import sk.tuke.gamestudio.server.service.serviceJPA.UserService;

//http://localhost:8080/user
@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class UserController {
	@Autowired
	private UserService userService;

	private User loggedUser;

	private String message = "";

	public String getMessage() {
		return message;
	}

	@RequestMapping("/user")
	public String user(Model model) {
		message = "";
		return "login";
	}

	@RequestMapping("/login")
	public String login(User user, Model model) {
		message = "";

		try {
			user = userService.login(user.getUsername(), user.getPasswd());
		} catch (Exception e) {
			message = "Wrong credentials, please try again or register";
			return "login";
		}
		loggedUser = user;
		return "forward:/index";
	}

	@RequestMapping("/register")
	public String register(User user, Model model) {
		message = "";
		System.out.println(user.getPasswd());
		System.out.println(user.getUsername());
		System.out.println("verified: " + user.getVerifiedPasswd());
		if (user.getPasswd().equals(user.getVerifiedPasswd())) {
			user = userService.register(user.getUsername(), user.getPasswd());
			loggedUser = user;
			return "forward:/index";
		} else {
			message = "Wrong credentials, check if passwords match or try other username";
			return "login";
		}
	}

	@RequestMapping("/logout")
	public String logout() {
		loggedUser = null;
		return "forward:/index";
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public boolean isLogged() {
		return loggedUser != null;
	}
}
