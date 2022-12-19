package com.shop.springboot.controller;

import com.shop.springboot.DAO.RoleRepository;
import com.shop.springboot.DAO.UseRepository;
import com.shop.springboot.Dto.UserDto;
import com.shop.springboot.model.Role;
import com.shop.springboot.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

    private final UseRepository useRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserRestController(UseRepository useRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.useRepository = useRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    @RequestMapping(value = "/findallroles", method = RequestMethod.GET)
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        if (useRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exist");
        }
        User userMapper = modelMapper.map(userDto, User.class);
        Role role = roleRepository.findByName(userDto.getRole());
        userMapper.setPassword(passwordEncoder.encode(userDto.getPassword()));
        HashSet roleSet = new HashSet<>();
        roleSet.add(role);
        userMapper.setRoles(roleSet);
        userMapper.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userMapper = useRepository.save(userMapper);
        UserDto userMapperDto = modelMapper.map(userMapper, UserDto.class);
        return new ResponseEntity<>(userMapperDto, HttpStatus.CREATED);
    }
//
//    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
//    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto) {
//        User user = useRepository.findOne(userDto.getId());
//
//        user.setName(userDto.getName());
//        user.setDesignation(userDto.getDesignation());
//        user.setPhoneNo(userDto.getPhoneNo());
//        user.setEmail(userDto.getEmail());
//        user.setIsActive(userDto.getIsActive());
//
//        Role role = roleRepository.findByName(userDto.getRole());
//        HashSet roleSet = new HashSet<>();
//        roleSet.add(role);
//        user.setRoles(roleSet);
//
//        Branch branch = branchRepository.findByBranchName(userDto.getBranch());
//        HashSet branchSet = new HashSet<>();
//        branchSet.add(branch);
//        user.setBranches(branchSet);
//
//        User userMapper = useRepository.save(user);
//        UserDto userMapperDto = modelMapper.map(userMapper, UserDto.class);
//
//        return new ResponseEntity<UserDto>(userMapperDto, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
//    public ResponseEntity<Boolean> deleteUser(@RequestBody UserDto userDto) {
//
//        User user = useRepository.findOne(userDto.getId());
//        user.setClosed(Boolean.TRUE);
//        useRepository.save(user);
//        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
//    }
//
    @RequestMapping("/auth")
    public UserDto user(Principal principal) {
        Optional<User> user=useRepository.findByUsername(principal.getName());
        UserDto userMapperDto = null;
        if(user.isPresent()){
            userMapperDto=new UserDto();
            userMapperDto.setName(user.get().getName());
        }
        return userMapperDto;
    }


}
