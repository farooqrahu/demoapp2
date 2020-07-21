package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.*;
import com.jamil.shop.springboot.Dto.UserDto;
import com.jamil.shop.springboot.Dto.gl.GLReportDto;
import com.jamil.shop.springboot.model.Branch;
import com.jamil.shop.springboot.model.Role;
import com.jamil.shop.springboot.model.User;
import com.jamil.shop.springboot.model.gl.Account;
import com.jamil.shop.springboot.model.gl.GLEntryItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GLEntryItemRepository glEntryItemRepository;

    public UserRestController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDto> users() {
        java.lang.reflect.Type targetListType = new TypeToken<List<UserDto>>() {
        }.getType();
        return modelMapper.map(userDao.findAllUsers(), targetListType);
    }


    @RequestMapping(value = "/findallroles", method = RequestMethod.GET)
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @RequestMapping(value = "/findallbranches", method = RequestMethod.GET)
    public List<Branch> findAllBranches() {
        return branchRepository.findAll();
    }

    @RequestMapping(value = "/findallcustomerbranches", method = RequestMethod.GET)
    public List<Branch> findAllCustomerBranches() {
        return branchRepository.findAllCustomerBranches();
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        if (userDao.findOneByUsername(userDto.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        User userMapper = modelMapper.map(userDto, User.class);
        Role role = roleRepository.findByName(userDto.getRole());
        userMapper.setPassword(passwordEncoder.encode(userDto.getPassKey()));
        HashSet roleSet = new HashSet<>();
        roleSet.add(role);
        userMapper.setRoles(roleSet);

        Branch branch = branchRepository.findByBranchName(userDto.getBranch());
        userMapper.setPassword(passwordEncoder.encode(userDto.getPassKey()));
        HashSet branchSet = new HashSet<>();
        branchSet.add(branch);
        userMapper.setBranches(branchSet);

        userMapper.setClosed(Boolean.FALSE);
        userMapper.setIsActive(Boolean.TRUE);
        userMapper = userDao.save(userMapper);
        UserDto userMapperDto = modelMapper.map(userMapper, UserDto.class);

        return new ResponseEntity<UserDto>(userMapperDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto) {
        User user = userDao.findOne(userDto.getId());

        user.setName(userDto.getName());
        user.setDesignation(userDto.getDesignation());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setEmail(userDto.getEmail());
        user.setIsActive(userDto.getIsActive());

        Role role = roleRepository.findByName(userDto.getRole());
        HashSet roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);

        Branch branch = branchRepository.findByBranchName(userDto.getBranch());
        HashSet branchSet = new HashSet<>();
        branchSet.add(branch);
        user.setBranches(branchSet);

        User userMapper = userDao.save(user);
        UserDto userMapperDto = modelMapper.map(userMapper, UserDto.class);

        return new ResponseEntity<UserDto>(userMapperDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteUser(@RequestBody UserDto userDto) {

        User user = userDao.findOne(userDto.getId());
        user.setClosed(Boolean.TRUE);
        userDao.save(user);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @RequestMapping("/user")
    public UserDto user(Principal principal) {
        UserDto userDto = modelMapper.map(userDao.findOneByUsername(principal.getName()), UserDto.class);
        Account purchaseAccount = accountRepository.findOne(1l);
        Account branchAccount = accountRepository.findOne(5l);
        Account customerAccount = accountRepository.findOne(3l);
        Account incomeAccount = accountRepository.findOne(4l);


        userDto.setTotalBranchSale("PKR "+getTotalAmount("C",branchAccount.getId())+".00");
        userDto.setTotalCustomerSale("PKR "+getTotalAmount("C",customerAccount.getId())+".00");
        userDto.setTotalPurchase("PKR " + getTotalAmount("D",purchaseAccount.getId()) + ".00");
        userDto.setTotalIncome("PKR "+ getTotalAmount("C",incomeAccount.getId()) +".00");
        return userDto;
    }

    private long getTotalAmount(String flagType,long accountId){
        List<GLEntryItem> glEntryItem = glEntryItemRepository.findByAccountId(accountId);
        long amount = 0l;
        for (GLEntryItem glEntryItem1 : glEntryItem) {
            if (flagType.equals("D")) {
                amount += glEntryItem1.getDebitAmount() != null ? glEntryItem1.getDebitAmount().longValue() : 0l;
            }else{
                amount += glEntryItem1.getCreditAmount() != null ? glEntryItem1.getCreditAmount().longValue() : 0l;
            }
        }
        return amount;
    }

}
