package com.example.lostindublin.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.servlet.view.RedirectView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lostindublin.dao.UserDAO;
import com.example.lostindublin.model.User;


@RestController
@RequestMapping("/UserService")
public class UserService {

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	@PostMapping("/register")
	public RedirectView register(
			 @RequestParam String userEmail,
		     @RequestParam String userPassword,
		     @RequestParam String userPhoneNumber) throws URISyntaxException {
		
		//URI uriReg = new URI("http://localhost:8080/FYP/register.jsp");
		//URI uriLog = new URI("http://localhost:8080/FYP/login.jsp");
		
		UserDAO dao = new UserDAO();
		
		List<User> users = dao.getAllUsers();
		
		for(int i = 0; i<users.size();i++) {
			
			if(users.get(i).getUserEmail().equals(userEmail)) {
				return new RedirectView("/register.jsp");	
			}
			
		}
		
		
		User user = new User(userEmail, userPassword, userPhoneNumber);
		
		
		
		dao.persist(user);
		return new RedirectView("/login.jsp");					
	}
	
	
	@PostMapping("/login")
	public RedirectView login(
			@RequestParam("userEmail")String userEmail,
			@RequestParam("userPassword")String userPassword) throws URISyntaxException {
		
		//URI uriFail = new URI("http://localhost:8080/FYP/login.jsp");
		//URI uriHome = new URI("http://localhost:8080/FYP/home.jsp");
		
		UserDAO dao = new UserDAO();
		
		List<User> users = dao.getAllUsers();		
		for(int i = 0; i<users.size();i++) {
			if(users.get(i).getUserEmail().equals(userEmail)&& users.get(i).getUserPassword().equals(userPassword)) {
				return new RedirectView("/home.jsp");	
			}
		}
			return new RedirectView("/login.jsp");	
		
	}
	
	
	
	
	@GetMapping("/user/{userId}")
	public User getUserById(@PathVariable int userId) {
		UserDAO dao = new UserDAO();
		return dao.getUserById(userId);
	}
	
	
	
	
	
	
}
