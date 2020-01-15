package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.RoleRepository;
import com.jamil.shop.springboot.DAO.UserDao;
import com.jamil.shop.springboot.Dto.UserDto;
import com.jamil.shop.springboot.model.Role;
import com.jamil.shop.springboot.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;


@RestController
@RequestMapping(value = "/api")
public class HomeRestController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public HomeRestController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        if (userDao.findOneByUsername(userDto.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        User userMapper = modelMapper.map(userDto, User.class);
        Role role = roleRepository.findByName(userDto.getRole());
        userMapper.setPassword(passwordEncoder.encode(userDto.getPassword()));
        HashSet roleSet = new HashSet<>();
        roleSet.add(role);
        userMapper.setRoles(roleSet);
        userMapper.setClosed(Boolean.FALSE);
        userMapper.setIsActive(Boolean.TRUE);
        userMapper = userDao.save(userMapper);
        UserDto userMapperDto = modelMapper.map(userMapper, UserDto.class);

        return new ResponseEntity<UserDto>(userMapperDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto) {
        User user = userDao.findOne(userDto.getId());
        modelMapper.map(userDto, user);
        User userMapper = userDao.save(user);
        UserDto userMapperDto = modelMapper.map(userMapper, UserDto.class);

        return new ResponseEntity<UserDto>(userMapperDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteUser(@RequestBody UserDto userDto) {

        User user = userDao.findOne(userDto.getId());
        user.setClosed(Boolean.FALSE);
        userDao.save(user);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
