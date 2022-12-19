package com.shop.springboot.Dto;

import java.io.Serializable;
import java.math.BigDecimal;

/*
  @auther by Farooq on 3/31/2017.
 */

public class PurchaseReportDto implements Serializable {

    private String product;
    private BigDecimal quantity;
    private BigDecimal totalAmount;
    private String branch;
    private String unitPrice;
    private String purchaseDate;
    private String glEntryId;
    private BigDecimal newQuantity;
private String transactionType;

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

    public String getGlEntryId() {
        return glEntryId;
    }

    public void setGlEntryId(String glEntryId) {
        this.glEntryId = glEntryId;
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
}
