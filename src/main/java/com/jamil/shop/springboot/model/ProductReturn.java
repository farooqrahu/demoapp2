package com.jamil.shop.springboot.model;

import javax.persistence.Entity;

@Entity
public class ProductReturn extends BaseEntityAudit {

    private Long quantity;
    private Long productId;
    private Long branchId;
    private String amount;


    public ProductReturn() {
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
