package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.*;
import com.jamil.shop.springboot.Dto.ProductCompanyDto;
import com.jamil.shop.springboot.model.ProductCompany;
import com.jamil.shop.springboot.service.ProductService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductComController {

    private final static Logger logger = Logger.getLogger(ProductComController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductCompanyRepository productCompanyRepository;

    @Autowired
    ProductDao productRepository;

    @Autowired
    ProductStockRepository productStockRepository;

    @Autowired
    ProductSaleRepository productSaleRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private GLEntryRepository glEntryRepository;
    private ModelMapper modelMapper;

    public ProductComController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @RequestMapping(value = "/product/com/add", method = RequestMethod.POST)
    public ResponseEntity<ProductCompanyDto> addProducts(@RequestBody ProductCompanyDto productDto) {
        ProductCompany product = modelMapper.map(productDto, ProductCompany.class);
        product.setClosed(Boolean.FALSE);
        product = productCompanyRepository.save(product);
        ProductCompanyDto productDto1 = modelMapper.map(product, ProductCompanyDto.class);
        logger.info("ProductCompanyDto saved successfully");
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/com/update", method = RequestMethod.POST)
    public ResponseEntity<ProductCompany> updateProduct(@RequestBody ProductCompanyDto product) {
        ProductCompany productCategory = productCompanyRepository.findOne(product.getId());
        modelMapper.map(product, productCategory);
        productCompanyRepository.save(productCategory);
        return new ResponseEntity<>(productCategory, HttpStatus.CREATED);
    }

    @RequestMapping("api/com/productcompanies")
    public List<ProductCompany> findAllProductCompanies() {
        return productCompanyRepository.findAllByNotClosed();
    }

    @RequestMapping(value = "/product/com/deleteProduct", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteProduct(@RequestBody ProductCompanyDto productDto) {

        ProductCompany product = productCompanyRepository.findActive(productDto.getId());
        product.setClosed(Boolean.TRUE);
        productCompanyRepository.save(product);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}
