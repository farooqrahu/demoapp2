package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.*;
import com.jamil.shop.springboot.Dto.*;
import com.jamil.shop.springboot.model.*;
import com.jamil.shop.springboot.model.gl.Account;
import com.jamil.shop.springboot.model.gl.GLEntry;
import com.jamil.shop.springboot.model.gl.GLEntryItem;
import com.jamil.shop.springboot.service.ProductService;
import com.jamil.shop.springboot.util.CustomConstants;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
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
    ProductSaleRepository productSaleRepository;

    @Autowired
    ProductSaleCusRepository productSaleCusRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private GLEntryRepository glEntryRepository;
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
    @RequestMapping("/saletocustomer")
    public List<ProductSaleDto> saleToCustomer() {
        List<ProductSale> productList = productService.saleToCustomer();
        java.lang.reflect.Type targetListType = new TypeToken<List<ProductSaleDto>>() {
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
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
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




   /* @RequestMapping(value = "product/findstock", method = RequestMethod.POST)
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

    }*/

    @RequestMapping(value = "product/findstock", method = RequestMethod.POST)
    public ResponseEntity<ProductStockDto> findStock(@RequestBody ProductStockDto productStockDto) {
        Branch branch = branchRepository.findByBranchName("ADMIN");
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
        ProductStock productStock;
        if (productStockDto.getId() != null) {
            productStock = productStockRepository.findOne(productStockDto.getId());
            productStock.setBranchId(branchRepository.findByBranchName("ADMIN").getId());
            productStock.setProductId(productStockDto.getProduct());
            productStock.setQuantity(productStockDto.getQuantity()+productStockDto.getNewQuantity());
           productStock.setSalePrice(productStockDto.getSalePrice());
            productStock.setPurchasePrice(productStockDto.getPurchasePrice());
            productStock.setTotalPurchaseAmount(productStockDto.getTotalPurchaseAmount()+productStockDto.getNewTotalPurchaseAmount());
           productStock.setTotalSaleAmount(productStockDto.getTotalSaleAmount()+productStockDto.getNewTotalSaleAmount());
            productStock = productStockRepository.save(productStock);
        } else {
            productStock = new ProductStock();
            productStock.setBranchId(branchRepository.findByBranchName("ADMIN").getId());
            productStock.setSalePrice(productStockDto.getSalePrice());
            productStock.setPurchasePrice(productStockDto.getPurchasePrice());
            productStock.setTotalPurchaseAmount(productStockDto.getNewTotalPurchaseAmount());
            productStock.setTotalSaleAmount(productStockDto.getNewTotalSaleAmount());
            productStock.setProductId(productStockDto.getProduct());
            productStock.setQuantity(productStockDto.getNewQuantity());
            productStock = productStockRepository.save(productStock);
        }
        Account accountPurchase = accountRepository.findOne(1l);
        Account accountVendor = accountRepository.findOne(2l);

        GLEntry glEntry = new GLEntry();
        glEntry.setBranchId(branchRepository.findByBranchName("ADMIN").getId());
        glEntry.setProductId(productStockDto.getProduct());
        glEntry.setTransactionDate(new Date());
        glEntry.setTotalAmount(BigDecimal.valueOf(productStockDto.getNewTotalPurchaseAmount()));
        glEntry.setQuantity(productStockDto.getNewQuantity());
        glEntry.setActive(Boolean.TRUE);
        glEntry.setTransactionType(CustomConstants.PURCHASE_BRANCH);
        glEntry.setTransactionRefId(productStock.getId());
        //Debit Entry for Purchase
        GLEntryItem glEntryDebit = new GLEntryItem();

        glEntryDebit.setId(0l);
        glEntryDebit.setGlEntry(glEntry);
        glEntryDebit.setDescription("Debit Entry for Purchase");
        glEntryDebit.setFlag("D");
        glEntryDebit.setAccount(accountPurchase);
        glEntryDebit.setDebitAmount(BigDecimal.valueOf(productStockDto.getNewTotalPurchaseAmount()));
        glEntryDebit.setActive(Boolean.TRUE);
        //Credit Entry for Purchase
        GLEntryItem glEntryCredit = new GLEntryItem();

        glEntryCredit.setId(1l);
        glEntryCredit.setGlEntry(glEntry);
        glEntryCredit.setDescription("Credit Entry for Purchase");
        glEntryCredit.setFlag("C");
        glEntryCredit.setAccount(accountVendor);
        glEntryCredit.setCreditAmount(BigDecimal.valueOf(productStockDto.getNewTotalPurchaseAmount()));
        glEntryCredit.setActive(Boolean.TRUE);
        HashSet glEntryItemSet = new HashSet<>();
        glEntryItemSet.add(glEntryCredit);
        glEntryItemSet.add(glEntryDebit);
        glEntry.setGlEntryItem(glEntryItemSet);

        glEntryRepository.save(glEntry);

        return new ResponseEntity<>(modelMapper.map(productStock, ProductStockDto.class), HttpStatus.CREATED);

    }

    @RequestMapping(value = "product/salestocktobranch", method = RequestMethod.POST)
    public ResponseEntity<ProductSaleDto> saleStockToBranch(@RequestBody ProductSaleDto productSaleDto) {
        ProductStock productStock;
        ProductSale productSale=new ProductSale();
        long income=0l;
        if (productSaleDto.getId() != null) {
            productSale.setBranchId(productSaleDto.getBranch());
            productSale.setProductId(productSaleDto.getProduct());
            productSale.setQuantity(productSaleDto.getNewQuantity());
            productSale.setSalePrice(productSaleDto.getSalePrice());
            productSale.setTotalSaleAmount(productSaleDto.getNewTotalSaleAmount());
            productStock = productStockRepository.findOne(productSaleDto.getId());
            if(productStock.getQuantity()>=productSaleDto.getNewQuantity()){
                productStock.setQuantity(productStock.getQuantity()-productSaleDto.getNewQuantity());
                long totalPurchaseAmount=productSaleDto.getNewQuantity()*Long.parseLong(productStock.getPurchasePrice());
                income=productSaleDto.getNewTotalSaleAmount()-totalPurchaseAmount;
                productStockRepository.save(productStock);
                productSale = productSaleRepository.save(productSale);
            }else{
                return new ResponseEntity<>(productSaleDto, HttpStatus.NOT_FOUND);
            }

        }


        Account accountBranchSale = accountRepository.findOne(5l);
        Account accountBranch = accountRepository.findByBranchId(productSale.getBranchId());
        Account accountIncome = accountRepository.findOne(4l);

        GLEntry glEntry = new GLEntry();
        glEntry.setBranchId(productSaleDto.getBranch());
        glEntry.setProductId(productSaleDto.getProduct());
        glEntry.setTransactionDate(new Date());
        glEntry.setTotalAmount(BigDecimal.valueOf(productSaleDto.getNewTotalSaleAmount()));
        glEntry.setQuantity(productSaleDto.getNewQuantity());
        glEntry.setActive(Boolean.TRUE);
        glEntry.setTransactionType(CustomConstants.SALE_BRANCH);
        glEntry.setTransactionRefId(productSale.getId());
        //Debit Entry for Purchase
        GLEntryItem glEntryDebit = new GLEntryItem();

        glEntryDebit.setId(0l);
        glEntryDebit.setGlEntry(glEntry);
        glEntryDebit.setDescription("Debit Entry for Sale");
        glEntryDebit.setFlag("D");
        glEntryDebit.setAccount(accountBranch);
        glEntryDebit.setDebitAmount(BigDecimal.valueOf(productSaleDto.getNewTotalSaleAmount()));
        glEntryDebit.setActive(Boolean.TRUE);
        //Credit Entry for Sale
        GLEntryItem glEntryCredit = new GLEntryItem();

        glEntryCredit.setId(1l);
        glEntryCredit.setGlEntry(glEntry);
        glEntryCredit.setDescription("Credit Entry for Sale");
        glEntryCredit.setFlag("C");
        glEntryCredit.setAccount(accountBranchSale);
        glEntryCredit.setCreditAmount(BigDecimal.valueOf(productSaleDto.getNewTotalSaleAmount()));
        glEntryCredit.setActive(Boolean.TRUE);

       /* GLEntry glEntryIncom = new GLEntry();
        glEntryIncom.setBranchId(productSaleDto.getBranch());
        glEntryIncom.setProductId(productSaleDto.getProduct());
        glEntryIncom.setTransactionDate(new Date());
        glEntryIncom.setTotalAmount(BigDecimal.valueOf(income));
        glEntryIncom.setQuantity(productSaleDto.getNewQuantity());*/
        //Credit Entry for Incom
        GLEntryItem incomCredit = new GLEntryItem();

        incomCredit.setId(2l);
        incomCredit.setGlEntry(glEntry);
        incomCredit.setDescription("Credit Entry for Income");
        incomCredit.setFlag("C");
        incomCredit.setAccount(accountIncome);
        incomCredit.setCreditAmount(BigDecimal.valueOf(income));
        incomCredit.setActive(Boolean.TRUE);

        HashSet glEntryItemSet = new HashSet<>();
        glEntryItemSet.add(glEntryCredit);
        glEntryItemSet.add(glEntryDebit);
        glEntryItemSet.add(incomCredit);
        glEntry.setGlEntryItem(glEntryItemSet);

        glEntryRepository.save(glEntry);

        return new ResponseEntity<>(modelMapper.map(productSale, ProductSaleDto.class), HttpStatus.CREATED);

    }



    @RequestMapping(value = "product/salestocktocustomer", method = RequestMethod.POST)
    public ResponseEntity<ProductSaleCustomerDto> saleStockToCus(@RequestBody ProductSaleCustomerDto productSaleDto) {
        ProductStock productStock;
        ProductSaleCustomer productSale=new ProductSaleCustomer();
        long income=0l;
        if (productSaleDto.getId() != null) {
            productSale.setBranchId(productSaleDto.getBranch());
            productSale.setProductId(productSaleDto.getProduct());
            productSale.setQuantity(productSaleDto.getNewQuantity());
            productSale.setSalePrice(productSaleDto.getSalePrice());
            productSale.setTotalSaleAmount(productSaleDto.getNewTotalSaleAmount());
            productStock = productStockRepository.findOne(productSaleDto.getId());
            if(productStock.getQuantity()>=productSaleDto.getNewQuantity()){
                productStock.setQuantity(productStock.getQuantity()-productSaleDto.getNewQuantity());
                long totalPurchaseAmount=productSaleDto.getNewQuantity()*Long.parseLong(productStock.getPurchasePrice());
                income=productSaleDto.getNewTotalSaleAmount()-totalPurchaseAmount;
                productStockRepository.save(productStock);
                productSale = productSaleCusRepository.save(productSale);
            }else{
                return new ResponseEntity<>(productSaleDto, HttpStatus.NOT_FOUND);
            }

        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account accountCusSale = accountRepository.findOne(3l);
//        Account accountBranch = accountRepository.findByBranchId(productSale.getBranchId());
        Account accountIncome = accountRepository.findOne(4l);

        GLEntry glEntry = new GLEntry();
        glEntry.setBranchId(productSaleDto.getBranch());
        glEntry.setProductId(productSaleDto.getProduct());
        glEntry.setTransactionDate(new Date());
        glEntry.setTotalAmount(BigDecimal.valueOf(productSaleDto.getNewTotalSaleAmount()));
        glEntry.setQuantity(productSaleDto.getNewQuantity());
        glEntry.setActive(Boolean.TRUE);
        glEntry.setBranchId(accountCusSale.getBranchId());
        glEntry.setTransactionType(CustomConstants.SALE_CUSTOMER);
        glEntry.setTransactionRefId(productSale.getId());
        //Debit Entry for Purchase
      /*  GLEntryItem glEntryDebit = new GLEntryItem();

        glEntryDebit.setId(0l);
        glEntryDebit.setGlEntry(glEntry);
        glEntryDebit.setDescription("Debit Entry for Sale");
        glEntryDebit.setFlag("D");
        glEntryDebit.setAccount(accountBranch);
        glEntryDebit.setDebitAmount(BigDecimal.valueOf(productSaleDto.getNewTotalSaleAmount()));*/
        //Credit Entry for Sale
        GLEntryItem glEntryCredit = new GLEntryItem();

        glEntryCredit.setId(1l);
        glEntryCredit.setGlEntry(glEntry);
        glEntryCredit.setDescription("Credit Entry for Sale");
        glEntryCredit.setFlag("C");
        glEntryCredit.setAccount(accountCusSale);
        glEntryCredit.setCreditAmount(BigDecimal.valueOf(productSaleDto.getNewTotalSaleAmount()));
        glEntryCredit.setActive(Boolean.TRUE);

       /* GLEntry glEntryIncom = new GLEntry();
        glEntryIncom.setBranchId(productSaleDto.getBranch());
        glEntryIncom.setProductId(productSaleDto.getProduct());
        glEntryIncom.setTransactionDate(new Date());
        glEntryIncom.setTotalAmount(BigDecimal.valueOf(income));
        glEntryIncom.setQuantity(productSaleDto.getNewQuantity());*/
        //Credit Entry for Incom
        GLEntryItem incomCredit = new GLEntryItem();

        incomCredit.setId(2l);
        incomCredit.setGlEntry(glEntry);
        incomCredit.setDescription("Credit Entry for Income");
        incomCredit.setFlag("C");
        incomCredit.setAccount(accountIncome);
        incomCredit.setCreditAmount(BigDecimal.valueOf(income));
        incomCredit.setActive(Boolean.TRUE);
        HashSet glEntryItemSet = new HashSet<>();
        glEntryItemSet.add(glEntryCredit);
//        glEntryItemSet.add(glEntryDebit);
        glEntryItemSet.add(incomCredit);
        glEntry.setGlEntryItem(glEntryItemSet);

        glEntryRepository.save(glEntry);

        return new ResponseEntity<>(modelMapper.map(productSale, ProductSaleCustomerDto.class), HttpStatus.CREATED);

    }
}
