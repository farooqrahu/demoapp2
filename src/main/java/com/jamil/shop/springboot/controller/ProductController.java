package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.*;
import com.jamil.shop.springboot.Dto.*;
import com.jamil.shop.springboot.model.*;
import com.jamil.shop.springboot.service.ProductService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    private final static Logger logger = Logger.getLogger(ProductController.class);

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
    private BranchRepository branchRepository;

    private ModelMapper modelMapper;

    public ProductController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @RequestMapping("/products")
    public List<ProductDto> getAllProduct() {
        List<Product> productList = productService.findAllByClosedNot();
        java.lang.reflect.Type targetListType = new TypeToken<List<ProductDto>>() {
        }.getType();
        return modelMapper.map(productList, targetListType);
    }

    @RequestMapping(value = "/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProducts(id);
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public ResponseEntity<ProductDto> addProducts(@RequestBody ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product.setProductCategory(productCategoryRepository.findByProductCategory(productDto.getProductCategory().getProductCategory()));
        product.setProductCompany(productCompanyRepository.findByName(productDto.getProductCompany().getName()));
        product.setClosed(Boolean.FALSE);
        product = productRepository.save(product);
        ProductDto productDto1 = modelMapper.map(product, ProductDto.class);
        logger.info("Product saved successfully");
        return new ResponseEntity<ProductDto>(productDto1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        ProductCategory productCategory = productCategoryRepository.findByProductCategory(product.getProductCategory().getProductCategory());
        ProductCompany productCompany = productCompanyRepository.findByName(product.getProductCompany().getName());
        product.setProductCompany(productCompany);
        product.setProductCategory(productCategory);
        Product productFound = productService.getProducts(product.getId());
        modelMapper.map(product, productFound);
        productFound.setClosed(Boolean.FALSE);
        productService.addProducts(productFound);
        return new ResponseEntity<>(productFound, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProducts(@PathVariable Long id) {
        try {
            productService.deleteProducts(id);

            logger.info("Product id - " + id + ", deleted successfully");

        } catch (Exception e) {
            logger.error("Error delete product.  Message: " + e.getMessage());

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/product/deleteProduct", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteProduct(@RequestBody ProductDto productDto) {

        Product product = productService.getProducts(productDto.getId());
        product.setClosed(Boolean.TRUE);
        productService.addProducts(product);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/downloadProduct")  //from html
    public HttpEntity<byte[]> downloadProduct() throws IOException {

      /*  byte[] fileByte = downloadProduct.getContent().getBytes();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("audio", "mpeg3"));
        header.set("Content-Disposition", "attachment; filename=" + downloadProduct.getAuthor().getName() + " - " + downloadProduct.getName() + ".mp3");
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

    @RequestMapping("/productList/{id}")
    public List<Product> loadProductlist(@PathVariable Long id) {
        return productService.productlist(id);
    }

    @RequestMapping(value = "/productList/add/{id}", method = RequestMethod.POST)
    public ResponseEntity addProductsProductlist(@PathVariable Long id) {
        try {
            productService.addProductsPlaylist(id);


        } catch (Exception e) {

            logger.error("Product don't added to productList.  Message: " + e.getMessage());

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.error("Product added successfully");

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/productList/delete/{id}", method = RequestMethod.DELETE)
    public void deleteProductsFromProductlist(@PathVariable int id) {
        productService.deleteProductsFromPlaylist(id);
    }

    @RequestMapping("/find/{name}")
    public List<Product> findProductByName(@PathVariable String name) {
        return productService.findProductByName(name);
    }

    @RequestMapping("api/productcategories")
    public List<ProductCategory> findAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @RequestMapping("api/productcompanies")
    public List<ProductCompany> findAllProductCompanies() {
        return productCompanyRepository.findAll();
    }


    @RequestMapping(value = "product/findstock", method = RequestMethod.POST)
    public ResponseEntity<ProductStockDto> findStock(@RequestBody ProductStockDto productStockDto) {
        Branch branch = branchRepository.findOne(productStockDto.getBranch());
        Product product = productRepository.findOne(productStockDto.getProduct());
        ProductStock productStock1 = productStockRepository.getStockBranchWise(branch.getId(), product.getId());
        if (productStock1 != null) {
            ProductStockDto stockDto = modelMapper.map(productStock1, ProductStockDto.class);
            return new ResponseEntity<>(stockDto, HttpStatus.CREATED);
        }
        ProductStockDto stockDto = new ProductStockDto();
        stockDto.setQuantity(0l);
        stockDto.setBranch(branch.getId());
        stockDto.setProduct(product.getId());
        return new ResponseEntity<>(stockDto, HttpStatus.CREATED);

    }

    @RequestMapping(value = "product/addstock", method = RequestMethod.POST)
    public ResponseEntity<ProductStockDto> addStock(@RequestBody ProductStockDto productStockDto) {
        if(productStockDto.getId()!=null){
            ProductStock productStock=productStockRepository.findOne(productStockDto.getId());
            productStock.setBranchId(productStockDto.getBranch());
            productStock.setProductId(productStockDto.getProduct());
            productStock.setQuantity(productStockDto.getQuantity());
            productStock=productStockRepository.save(productStock);
            return new ResponseEntity<>(modelMapper.map(productStock,ProductStockDto.class), HttpStatus.CREATED);
        }else{
            ProductStock productStock=new ProductStock();
            productStock.setBranchId(productStockDto.getBranch());
            productStock.setProductId(productStockDto.getProduct());
            productStock.setQuantity(productStockDto.getQuantity());
            productStock=productStockRepository.save(productStock);
            return new ResponseEntity<>(modelMapper.map(productStock,ProductStockDto.class), HttpStatus.CREATED);
        }
    }
}
