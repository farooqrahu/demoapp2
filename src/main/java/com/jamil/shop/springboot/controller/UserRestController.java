package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.UserDao;
import com.jamil.shop.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> users() {
		return userDao.findAll();
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> userById(@PathVariable Long id) {
		User user = userDao.findOne(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		User user = userDao.findOne(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		} else if (user.getUsername().equalsIgnoreCase(loggedUsername)) {
			throw new RuntimeException("You cannot delete your account");
		} else {
			userDao.delete(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if (userDao.findOneByUsername(user.getUsername()) != null) {
			throw new RuntimeException("Username already exist");
		}
		return new ResponseEntity<User>(userDao.save(user), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		if (userDao.findOneByUsername(user.getUsername()) != null
				&& userDao.findOneByUsername(user.getUsername()).getId() != user.getId()) {
			throw new RuntimeException("Username already exist");
		}
		return userDao.save(user);
	}

}
