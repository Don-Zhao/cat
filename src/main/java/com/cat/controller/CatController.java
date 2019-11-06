package com.cat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.model.User;
import com.cat.service.UserService;

@RestController
public class CatController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello,world";
	}
	
	@RequestMapping("/find/{id}")
	public User find(@PathVariable int id, HttpServletRequest request) {
		User user = userService.findById(id);
		user.setUrl(request.getRequestURL().toString());
		
		return user;
	}
	
	@RequestMapping(value="/user/create", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody User user) {
		System.out.println(user);
		return "success, user id:" + user.getId();
	}
	
	@RequestMapping(value="/user/createXml", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_XML_VALUE,
			produces=MediaType.APPLICATION_XML_VALUE)
	public String createXmlPerson(@RequestBody User user) {
		System.out.println(user.getId() + " " + user.getName() + " " + user.getUrl());
		return "<result><message>success</message></result>";
	}
}
