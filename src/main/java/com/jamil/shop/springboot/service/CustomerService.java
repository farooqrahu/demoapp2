package com.jamil.shop.springboot.service;

import com.jamil.shop.springboot.DAO.CustomerRepository;
import com.jamil.shop.springboot.DAO.UserDao;
import com.jamil.shop.springboot.Dto.CustomerDto;
import com.jamil.shop.springboot.Dto.ProductDto;
import com.jamil.shop.springboot.model.Customer;
import com.jamil.shop.springboot.model.Customer;
import com.jamil.shop.springboot.model.User;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final static Logger logger = Logger.getLogger(CustomerService.class);


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserDao userDao;


    private List<Customer> customer;
    private User user;
    private ModelMapper modelMapper;

    public CustomerService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<Customer> listCustomers() {
        logger.info("Load all Customer service method");

        return customerRepository.findAll();
    }

    public Customer getCustomers(Long id) {
        return customerRepository.findOne(id);
    }

    public Customer addCustomers(Customer Customer) {
        return customerRepository.save(Customer);
    }

    public void deleteCustomers(Long id) {

        Customer Customer = customerRepository.findOne(id);

        customerRepository.delete(id);

        logger.info("Delete Customer service method");




    }

    public List<Customer> customerlist(Long userId) {

        User user = userDao.findOne(userId);

        return null;//user.getCustomerList(customer);

    }

    public void addCustomersPlaylist(Long id) throws Exception {
        authentication();

        for (Customer Customer: customer) {
            if (Customer.getId().longValue()==id) {

                throw new RuntimeException("This Customer already in a customerlist");
            }
        }
        customer.add(customerRepository.findOne(id));

        logger.info("Add Customer  user " + user.getName() +" customerlist");

        saveUser();
    }

    public void deleteCustomersFromPlaylist(int id) {
        authentication();

        customer.removeIf(s -> s.getId() == id);

        logger.info("Delete Customer from user " + user.getName() +" customerlist");

        saveUser();
    }


    private void authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        user = userDao.findOneByUsername(loggedUsername);
        customer = new ArrayList<>();
//        customer.addAll(user.getCustomerList(customer));

        logger.info("Get current " + user.getName() +" customerlist");
    }

    private void saveUser() {
  //      user.getCustomerList(customer);
        userDao.save(user);
        logger.info("Save new " + user.getName() +" customerlist");
    }


    public List<CustomerDto> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAllByNotClosed();
        java.lang.reflect.Type targetListType = new TypeToken<List<ProductDto>>() {
        }.getType();
        return modelMapper.map(customerList, targetListType);

    }
}
