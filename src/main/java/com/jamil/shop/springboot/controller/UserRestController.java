package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.RoleRepository;
import com.jamil.shop.springboot.DAO.UserDao;
import com.jamil.shop.springboot.Dto.UserDto;
import com.jamil.shop.springboot.model.Role;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class UserRestController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleRepository roleRepository;

    private ModelMapper modelMapper;

    public UserRestController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDto> users() {
        java.lang.reflect.Type targetListType = new TypeToken<List<UserDto>>() {}.getType();
        List<UserDto> userList=modelMapper.map(userDao.findAllUsers(),targetListType);
        return userList;
    }


    @RequestMapping(value = "/findallroles", method = RequestMethod.GET)
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
