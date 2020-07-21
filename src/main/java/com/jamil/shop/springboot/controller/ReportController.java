package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.*;
import com.jamil.shop.springboot.Dto.*;
import com.jamil.shop.springboot.Dto.gl.GLReportDto;
import com.jamil.shop.springboot.config.SecurityUser;
import com.jamil.shop.springboot.model.*;
import com.jamil.shop.springboot.model.gl.Account;
import com.jamil.shop.springboot.model.gl.GLEntry;
import com.jamil.shop.springboot.model.gl.GLEntryItem;
import com.jamil.shop.springboot.util.CustomConstants;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController {

    private final static Logger logger = Logger.getLogger(ReportController.class);


    @Autowired
    ProductDao productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductStockRepository productStockRepository;

    @Autowired
    ProductSaleRepository productSaleRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductSaleCusRepository productSaleCusRepository;

    @Autowired
    private GLEntryItemRepository glEntryItemRepository;

    @Autowired
    private GLEntryRepository glEntryRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Autowired
    private UserDao userDao;

    private ModelMapper modelMapper;

    public ReportController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    @RequestMapping("/findgldata")
    public List<GLReportDto> findGLdata() {
        List<GLReportDto> glReportDtos = new ArrayList<>();
        List<Account> accounts = accountRepository.findAll();
        GLReportDto glReportDto = new GLReportDto();

        for (Account account : accounts) {
            glReportDto = new GLReportDto();
            glReportDto.setAccountName(account.getTitle());
            List<GLEntryItem> glEntryItem = glEntryItemRepository.findByAccountId(account.getId());
            long creditAmount = 0l;
            long debitAmount = 0l;
            for (GLEntryItem glEntryItem1 : glEntryItem) {
                if (glEntryItem1.getFlag().equals("D")) {
                    debitAmount += glEntryItem1.getDebitAmount() != null ? glEntryItem1.getDebitAmount().longValue() : 0l;
                    glReportDto.setDebitAmount(glEntryItem1.getDebitAmount());
                } else if (glEntryItem1.getFlag().equals("C")) {
                    creditAmount += glEntryItem1.getCreditAmount() != null ? glEntryItem1.getCreditAmount().longValue() : 0l;
                }
            }
            glReportDto.setCreditAmount(new BigDecimal(creditAmount));
            glReportDto.setDebitAmount(new BigDecimal(debitAmount));
            glReportDtos.add(glReportDto);
        }
        return glReportDtos;
    }

    @Transactional
    @RequestMapping("/getpurchasedata")
    public List<PurchaseReportDto> getPurchaseData() {
        List<PurchaseReportDto> purchaseReportDtos = new ArrayList<>();
        PurchaseReportDto purchaseReportDto = new PurchaseReportDto();
        List<ProductStock> stockList = productStockRepository.findAll();

        for (ProductStock productStock : stockList) {
            purchaseReportDto = new PurchaseReportDto();
            purchaseReportDto.setProduct(productRepository.findOne(productStock.getProductId()).getName());
            purchaseReportDto.setQuantity(new BigDecimal(productStock.getQuantity()));
            purchaseReportDto.setTotalAmount(new BigDecimal(productStock.getQuantity() * Long.parseLong(productStock.getPurchasePrice())));
            purchaseReportDto.setBranch(branchRepository.findOne(productStock.getBranchId()).getBranchName());
            purchaseReportDto.setUnitPrice(productStock.getPurchasePrice());
            purchaseReportDto.setPurchaseDate(productStock.getUpdatedAt() != null ? String.valueOf(productStock.getUpdatedAt()) : String.valueOf(productStock.getCreatedAt()));
            purchaseReportDtos.add(purchaseReportDto);
        }
        return purchaseReportDtos;
    }

    @Transactional
    @RequestMapping("/getbranchsaledata")
    public List<BranchSaleReportDto> getBranchSaleData() {
        List<BranchSaleReportDto> purchaseReportDtos = new ArrayList<>();
        BranchSaleReportDto branchSaleReportDto = new BranchSaleReportDto();
        List<ProductSale> stockList = productSaleRepository.findAll();

        for (ProductSale productStock : stockList) {
            branchSaleReportDto = new BranchSaleReportDto();
            branchSaleReportDto.setProduct(productRepository.findOne(productStock.getProductId()).getName());
            branchSaleReportDto.setQuantity(new BigDecimal(productStock.getQuantity()));
            branchSaleReportDto.setModel(productRepository.findOne(productStock.getProductId()).getModel());
            branchSaleReportDto.setTotalAmount(new BigDecimal(productStock.getTotalSaleAmount()));
            Branch branch = branchRepository.findOne(productStock.getBranchId());
            branchSaleReportDto.setBranch(branch.getBranchName() + ", " + branch.getAddress());

            branchSaleReportDto.setUnitPrice(productStock.getSalePrice());
            branchSaleReportDto.setSaleDate(String.valueOf(productStock.getCreatedAt()));
            purchaseReportDtos.add(branchSaleReportDto);
        }
        return purchaseReportDtos;
    }

    @Transactional
    @RequestMapping("/getcustomersaledata")
    public List<ProductSaleCustomerDto> getCustomerSaleData() {
        List<ProductSaleCustomerDto> purchaseReportDtos = new ArrayList<>();
        ProductSaleCustomerDto branchSaleReportDto = new ProductSaleCustomerDto();
        List<ProductSaleCustomer> stockList = productSaleCusRepository.findAll();
        for (ProductSaleCustomer productSaleCustomer : stockList) {
            branchSaleReportDto = new ProductSaleCustomerDto();
            branchSaleReportDto.setProduct(productRepository.findOne(productSaleCustomer.getProductId()).getId());
            branchSaleReportDto.setProductName(productRepository.findOne(productSaleCustomer.getProductId()).getName());
            branchSaleReportDto.setModel(productRepository.findOne(productSaleCustomer.getProductId()).getModel());
            branchSaleReportDto.setQuantity(productSaleCustomer.getQuantity());
            branchSaleReportDto.setTotalSaleAmount(productSaleCustomer.getTotalSaleAmount());
            if (productCategoryRepository.findActive(productSaleCustomer.getProductId()) != null)
                branchSaleReportDto.setProductCategory(productCategoryRepository.findActive(productSaleCustomer.getProductId()).getProductCategory());

            //            branchSaleReportDto.setBranchName(branchRepository.findOne(productSaleCustomer.getBranchId()).getBranchName());
            branchSaleReportDto.setSalePrice(productSaleCustomer.getSalePrice());
            branchSaleReportDto.setSaleDate(String.valueOf(productSaleCustomer.getCreatedAt()));
            purchaseReportDtos.add(branchSaleReportDto);

        }
        return purchaseReportDtos;
    }


    @Transactional
    @RequestMapping("/getpurchaseproduct")
    public List<PurchaseReportDto> getPurchaseProduct() {
        List<PurchaseReportDto> purchaseReportDtos = new ArrayList<>();
        PurchaseReportDto purchaseReportDto;

        List<GLEntry> entryRepositoryAll = glEntryRepository.findAllActive();
        for (GLEntry glEntry : entryRepositoryAll) {

            Product product = productRepository.findOne(glEntry.getProductId());

            ProductStock productStock = productStockRepository.getStockProductWise(glEntry.getProductId());
            if (glEntry.getTransactionType().equals(CustomConstants.PURCHASE_BRANCH)) {
                purchaseReportDto = new PurchaseReportDto();
                purchaseReportDto.setTransactionType(glEntry.getTransactionType());
                purchaseReportDto.setProduct(product.getName());
                purchaseReportDto.setPurchaseDate(glEntry.getTransactionDate().toString());
                purchaseReportDto.setUnitPrice(productStock.getPurchasePrice());
                purchaseReportDto.setQuantity(new BigDecimal(glEntry.getQuantity()));
                purchaseReportDto.setTotalAmount(glEntry.getTotalAmount());
                purchaseReportDto.setGlEntryId(glEntry.getId().toString());
                purchaseReportDtos.add(purchaseReportDto);
            } else if (glEntry.getTransactionType().equals(CustomConstants.SALE_BRANCH)) {
                purchaseReportDto = new PurchaseReportDto();
                purchaseReportDto.setTransactionType(glEntry.getTransactionType());
                purchaseReportDto.setProduct(product.getName());
                purchaseReportDto.setPurchaseDate(glEntry.getTransactionDate().toString());
                purchaseReportDto.setUnitPrice(productStock.getSalePrice());
                purchaseReportDto.setQuantity(new BigDecimal(glEntry.getQuantity()));
                purchaseReportDto.setTotalAmount(glEntry.getTotalAmount());
                purchaseReportDto.setGlEntryId(glEntry.getId().toString());
                purchaseReportDtos.add(purchaseReportDto);
            } else if (glEntry.getTransactionType().equals(CustomConstants.SALE_CUSTOMER)) {
                purchaseReportDto = new PurchaseReportDto();
                purchaseReportDto.setTransactionType(glEntry.getTransactionType());
                purchaseReportDto.setProduct(product.getName());
                purchaseReportDto.setPurchaseDate(glEntry.getTransactionDate().toString());
                purchaseReportDto.setUnitPrice(productStock.getSalePrice());
                purchaseReportDto.setQuantity(new BigDecimal(glEntry.getQuantity()));
                purchaseReportDto.setTotalAmount(glEntry.getTotalAmount());
                purchaseReportDto.setGlEntryId(glEntry.getId().toString());
                purchaseReportDtos.add(purchaseReportDto);
            }


        }
        return purchaseReportDtos;
    }

    @RequestMapping(value = "addproductreturn", method = RequestMethod.POST)
    public ResponseEntity<PurchaseReportDto> addProductReturn(@RequestBody PurchaseReportDto returnProduct) {

        GLEntry glEntry = glEntryRepository.findOne(Long.parseLong(returnProduct.getGlEntryId()));
        if (returnProduct.getQuantity().longValue() == new BigDecimal(glEntry.getQuantity()).longValue()) {
            glEntry.setActive(Boolean.FALSE);
            glEntry.setTransactionReturn(Boolean.TRUE);
            for (GLEntryItem glEntryItem : glEntry.getGlEntryItem()) {
                glEntryItem.setActive(Boolean.FALSE);
            }
            glEntryRepository.save(glEntry);
//            ProductStock productStock = productStockRepository.getStockByTransactionRefId(glEntry.getTransactionRefId());
            ProductStock productStock = productStockRepository.findOne(glEntry.getProductId());
            productStock.setQuantity(productStock.getQuantity() + returnProduct.getQuantity().longValue());
            productStockRepository.save(productStock);
        } else {

        }
        return new ResponseEntity<>(modelMapper.map(returnProduct, PurchaseReportDto.class), HttpStatus.CREATED);

    }

    @Transactional
    @RequestMapping("/getreturnedproducts")
    public List<PurchaseReportDto> getReturnedProducts() {
        List<PurchaseReportDto> purchaseReportDtos = new ArrayList<>();
        PurchaseReportDto purchaseReportDto;

        List<GLEntry> entryRepositoryAll = glEntryRepository.findAllInActive();
        for (GLEntry glEntry : entryRepositoryAll) {

            Product product = productRepository.findOne(glEntry.getProductId());

            ProductStock productStock = productStockRepository.getStockProductWise(glEntry.getProductId());
            if (glEntry.getTransactionType().equals(CustomConstants.PURCHASE_BRANCH)) {
                purchaseReportDto = new PurchaseReportDto();
                purchaseReportDto.setTransactionType(glEntry.getTransactionType());
                purchaseReportDto.setProduct(product.getName());
                purchaseReportDto.setPurchaseDate(glEntry.getTransactionDate().toString());
                purchaseReportDto.setUnitPrice(productStock.getPurchasePrice());
                purchaseReportDto.setQuantity(new BigDecimal(glEntry.getQuantity()));
                purchaseReportDto.setTotalAmount(glEntry.getTotalAmount());
                purchaseReportDto.setGlEntryId(glEntry.getId().toString());
                purchaseReportDtos.add(purchaseReportDto);
            } else if (glEntry.getTransactionType().equals(CustomConstants.SALE_BRANCH)) {
                purchaseReportDto = new PurchaseReportDto();
                purchaseReportDto.setTransactionType(glEntry.getTransactionType());
                purchaseReportDto.setProduct(product.getName());
                purchaseReportDto.setPurchaseDate(glEntry.getTransactionDate().toString());
                purchaseReportDto.setUnitPrice(productStock.getSalePrice());
                purchaseReportDto.setQuantity(new BigDecimal(glEntry.getQuantity()));
                purchaseReportDto.setTotalAmount(glEntry.getTotalAmount());
                purchaseReportDto.setGlEntryId(glEntry.getId().toString());
                purchaseReportDtos.add(purchaseReportDto);
            } else if (glEntry.getTransactionType().equals(CustomConstants.SALE_CUSTOMER)) {
                purchaseReportDto = new PurchaseReportDto();
                purchaseReportDto.setTransactionType(glEntry.getTransactionType());
                purchaseReportDto.setProduct(product.getName());
                purchaseReportDto.setPurchaseDate(glEntry.getTransactionDate().toString());
                purchaseReportDto.setUnitPrice(productStock.getSalePrice());
                purchaseReportDto.setQuantity(new BigDecimal(glEntry.getQuantity()));
                purchaseReportDto.setTotalAmount(glEntry.getTotalAmount());
                purchaseReportDto.setGlEntryId(glEntry.getId().toString());
                purchaseReportDtos.add(purchaseReportDto);
            }


        }
        return purchaseReportDtos;
    }

    @RequestMapping(value = "generateInvoice", method = RequestMethod.GET)
    public ResponseEntity<Long> generateInvoice(@RequestParam("type") String type) {
        Long invoiceNo = 0L;
        CustomerType customerType;
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((SecurityUser) principal).getUsername();
            user = userDao.findOneByUsername(username);
        }
        if (type.equals("Customer")) {
            customerType = customerTypeRepository.findByCustomerType(1l);
            Long invoiceNumber = invoiceRepository.findMaxInvoiceNumberCustomer();
            Invoice invoice = new Invoice();
            if (invoiceNumber != null) {
                invoice.setInvoiceNumberCustomer(invoiceNumber + 1);
                invoice.setCustomerType(customerType);
                invoice.setUser(user);
                invoice = invoiceRepository.save(invoice);
            } else {
                invoice = new Invoice();
                invoice.setId(0l);
                invoice.setUser(user);
                invoice.setCustomerType(customerType);
                invoice.setInvoiceNumberCustomer(1l);
                invoice = invoiceRepository.save(invoice);
            }
            invoiceNo = invoice.getInvoiceNumberCustomer();
        } else if (type.equals("Branch")) {
            Long invoiceNumber = invoiceRepository.findMaxInvoiceNumberBranch();
            Invoice invoice = new Invoice();
            customerType = customerTypeRepository.findByCustomerType(2l);
            if (invoiceNumber != null) {
                invoice.setUser(user);
                invoice.setInvoiceNumberBranch(invoiceNumber + 1);
                invoice.setCustomerType(customerType);
                invoice = invoiceRepository.save(invoice);
            } else {
                invoice = new Invoice();
                invoice.setId(0l);
                invoice.setUser(user);
                invoice.setCustomerType(customerType);
                invoice.setInvoiceNumberBranch(1l);
                invoice = invoiceRepository.save(invoice);
            }
            invoiceNo = invoice.getInvoiceNumberBranch();
        }
        return new ResponseEntity<>(invoiceNo, HttpStatus.OK);
    }

}
