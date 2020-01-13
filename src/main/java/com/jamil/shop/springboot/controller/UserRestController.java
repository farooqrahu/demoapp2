package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.RoleRepository;
import com.jamil.shop.springboot.DAO.UserDao;
import com.jamil.shop.springboot.model.Role;
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
	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> users() {
		return userDao.findAll();
	}
	@RequestMapping(value = "/findallroles", method = RequestMethod.GET)
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}
}
