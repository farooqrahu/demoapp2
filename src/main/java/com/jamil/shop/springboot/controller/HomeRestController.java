package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.UserDao;
import com.jamil.shop.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@RestController
@RequestMapping(value = "/api")
public class HomeRestController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userDao.findOneByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<User>(userDao.save(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ResponseEntity<User> editUser(@RequestBody User user) {
        return new ResponseEntity<User>(userDao.save(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteUser(@RequestBody User user) {
        userDao.delete(user);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfo (HttpSession session) {
        User nUser = (User)session.getAttribute("user");
        return nUser;
    }
}
