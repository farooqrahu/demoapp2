package com.jamil.shop.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;


@Entity
public class Invoice extends BaseEntityAudit {

    private Long invoiceNumberCustomer;
    private Long invoiceNumberBranch;
    private String product;
    private BigDecimal quantity;
    private BigDecimal totalAmount;
    private String branch;
    private String unitPrice;
    private String purchaseDate;
    private BigDecimal newQuantity;
    private String transactionType;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_TYPE", nullable = false)
    private CustomerType customerType;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER", nullable = false)
    private User user;

    public Invoice() {
    }



    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(BigDecimal newQuantity) {
        this.newQuantity = newQuantity;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getInvoiceNumberCustomer() {
        return invoiceNumberCustomer;
    }

    public void setInvoiceNumberCustomer(Long invoiceNumberCustomer) {
        this.invoiceNumberCustomer = invoiceNumberCustomer;
    }

    public Long getInvoiceNumberBranch() {
        return invoiceNumberBranch;
    }

    public void setInvoiceNumberBranch(Long invoiceNumberBranch) {
        this.invoiceNumberBranch = invoiceNumberBranch;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
