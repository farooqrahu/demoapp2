package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.*;
import com.jamil.shop.springboot.Dto.ProductCategoryDto;
import com.jamil.shop.springboot.Dto.ProductDto;
import com.jamil.shop.springboot.model.Product;
import com.jamil.shop.springboot.model.ProductCategory;
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
public class ProductCatController {

    private final static Logger logger = Logger.getLogger(ProductCatController.class);

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

    public ProductCatController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @RequestMapping(value = "/product/cat/add", method = RequestMethod.POST)
    public ResponseEntity<ProductCategoryDto> addProducts(@RequestBody ProductCategoryDto productDto) {
        ProductCategory product = modelMapper.map(productDto, ProductCategory.class);
        product.setClosed(Boolean.FALSE);
        product = productCategoryRepository.save(product);
        ProductCategoryDto productDto1 = modelMapper.map(product, ProductCategoryDto.class);
        logger.info("ProductCategoryDto saved successfully");
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/cat/update", method = RequestMethod.POST)
    public ResponseEntity<ProductCategory> updateProduct(@RequestBody ProductCategoryDto product) {
        ProductCategory productCategory = productCategoryRepository.findOne(product.getId());
        modelMapper.map(product, productCategory);
        productCategoryRepository.save(productCategory);
        return new ResponseEntity<>(productCategory, HttpStatus.CREATED);
    }

    @RequestMapping("api/cat/productcategories")
    public List<ProductCategory> findAllProductCategories() {
        return productCategoryRepository.findAllByNotClosed();
    }

    @RequestMapping(value = "/product/cat/deleteProduct", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteProduct(@RequestBody ProductCategoryDto productDto) {

        ProductCategory product = productCategoryRepository.findActive(productDto.getId());
        product.setClosed(Boolean.TRUE);
        productCategoryRepository.save(product);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}
