package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.BranchRepository;
import com.jamil.shop.springboot.DAO.CustomerRepository;
import com.jamil.shop.springboot.DAO.CustomerTypeRepository;
import com.jamil.shop.springboot.Dto.CustomerDto;
import com.jamil.shop.springboot.model.Branch;
import com.jamil.shop.springboot.model.Customer;
import com.jamil.shop.springboot.model.CustomerType;
import com.jamil.shop.springboot.service.CustomerService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {

    private final static Logger logger = Logger.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerTypeRepository customerTypeRepository;


    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    private BranchRepository branchRepository;

    private ModelMapper modelMapper;

    public CustomerController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @RequestMapping("/customers")
    public List<CustomerDto> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @RequestMapping(value = "/customer/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomers(id);
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public ResponseEntity<CustomerDto> addCustomers(@RequestBody CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setCustomerType(customerTypeRepository.findByCustomerType(customerDto.getCustomerTypeId()));
        customer.setBranch(branchRepository.findByBranchName(customerDto.getBranch().getBranchName()));
        customer.setClosed(Boolean.FALSE);
        customer = customerRepository.save(customer);
        CustomerDto customerDto1 = modelMapper.map(customer, CustomerDto.class);
        logger.info("Customer saved successfully");
        return new ResponseEntity<CustomerDto>(customerDto1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customer/update", method = RequestMethod.POST)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        CustomerType customerType = customerTypeRepository.findByCustomerType(customer.getCustomerType().getId());
        Branch branchName = branchRepository.findByBranchName(customer.getBranch().getBranchName());
        customer.setBranch(branchName);
        customer.setCustomerType(customerType);
        Customer customerFound = customerService.getCustomers(customer.getId());
        modelMapper.map(customer, customerFound);
        customerFound.setClosed(Boolean.FALSE);
        customerService.addCustomers(customerFound);
        return new ResponseEntity<>(customerFound, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/customer/deleteCustomer", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteCustomer(@RequestBody CustomerDto customerDto) {

        Customer customer = customerService.getCustomers(customerDto.getId());
        customer.setClosed(Boolean.TRUE);
        customerService.addCustomers(customer);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/downloadCustomer")  //from html
    public HttpEntity<byte[]> downloadCustomer() throws IOException {

      /*  byte[] fileByte = downloadCustomer.getContent().getBytes();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("audio", "mpeg3"));
        header.set("Content-Disposition", "attachment; filename=" + downloadCustomer.getAuthor().getName() + " - " + downloadCustomer.getName() + ".mp3");
        header.setContentLength(fileByte.length);
*/
//        return new HttpEntity<byte[]>(fileByte, header);
        return null;
    }
/*
    @PostMapping("/upload")   //from html
    public void singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        bytes = file.getBytes();
        logger.info("Upload file and get bytes");

    }*/


    @RequestMapping(value = "/customerList/add/{id}", method = RequestMethod.POST)
    public ResponseEntity addCustomersCustomerlist(@PathVariable Long id) {
        try {
            customerService.addCustomersPlaylist(id);


        } catch (Exception e) {

            logger.error("Customer don't added to customerList.  Message: " + e.getMessage());

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.error("Customer added successfully");

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/customerList/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCustomersFromCustomerlist(@PathVariable int id) {
        customerService.deleteCustomersFromPlaylist(id);
    }

    @RequestMapping("api/customertypes")
    public List<CustomerType> findAllCustomerTypes() {
        return customerTypeRepository.findAll();
    }


}
