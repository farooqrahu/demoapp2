package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.DAO.*;
import com.jamil.shop.springboot.Dto.BranchSaleReportDto;
import com.jamil.shop.springboot.Dto.ProductDto;
import com.jamil.shop.springboot.Dto.PurchaseReportDto;
import com.jamil.shop.springboot.Dto.gl.AccountDto;
import com.jamil.shop.springboot.Dto.gl.GLEntryDto;
import com.jamil.shop.springboot.Dto.gl.GLEntryItemDto;
import com.jamil.shop.springboot.Dto.gl.GLReportDto;
import com.jamil.shop.springboot.model.Product;
import com.jamil.shop.springboot.model.ProductSale;
import com.jamil.shop.springboot.model.ProductStock;
import com.jamil.shop.springboot.model.gl.Account;
import com.jamil.shop.springboot.model.gl.GLEntry;
import com.jamil.shop.springboot.model.gl.GLEntryItem;
import com.jamil.shop.springboot.service.ProductService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.stream.Collectors;

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
    private GLEntryRepository glEntryRepository;

    @Autowired
    private GLEntryItemRepository glEntryItemRepository;

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
            purchaseReportDto.setTotalAmount(new BigDecimal(productStock.getTotalPurchaseAmount()));
            purchaseReportDto.setBranch(branchRepository.findOne(productStock.getBranchId()).getBranchName());
            purchaseReportDto.setUnitPrice(productStock.getPurchasePrice());
            purchaseReportDto.setPurchaseDate(productStock.getUpdatedAt()!=null?String.valueOf(productStock.getUpdatedAt()):String.valueOf(productStock.getCreatedAt()));
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
            branchSaleReportDto.setTotalAmount(new BigDecimal(productStock.getTotalSaleAmount()));
            branchSaleReportDto.setBranch(branchRepository.findOne(productStock.getBranchId()).getBranchName());
            branchSaleReportDto.setUnitPrice(productStock.getSalePrice());
            branchSaleReportDto.setSaleDate(String.valueOf(productStock.getCreatedAt()));
            purchaseReportDtos.add(branchSaleReportDto);
        }
        return purchaseReportDtos;
    }
}
