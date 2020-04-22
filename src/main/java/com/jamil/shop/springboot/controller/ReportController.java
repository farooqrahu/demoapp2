package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.*;
import com.jamil.shop.springboot.Dto.*;
import com.jamil.shop.springboot.Dto.gl.GLReportDto;
import com.jamil.shop.springboot.model.Product;
import com.jamil.shop.springboot.model.ProductSale;
import com.jamil.shop.springboot.model.ProductSaleCustomer;
import com.jamil.shop.springboot.model.ProductStock;
import com.jamil.shop.springboot.model.gl.Account;
import com.jamil.shop.springboot.model.gl.GLEntry;
import com.jamil.shop.springboot.model.gl.GLEntryItem;
import com.jamil.shop.springboot.util.CustomConstants;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            branchSaleReportDto.setBranch(branchRepository.findOne(productStock.getBranchId()).getBranchName());
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
            ProductStock productStock = productStockRepository.getStockByTransactionRefId(glEntry.getTransactionRefId());
            productStock.setQuantity(productStock.getQuantity() + returnProduct.getQuantity().longValue());
            productStockRepository.save(productStock);
        }else{

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
}
