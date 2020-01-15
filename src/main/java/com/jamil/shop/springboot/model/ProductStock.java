package com.jamil.shop.springboot.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
public class ProductStock extends BaseEntity {

    private String quantity;

    @JoinColumn(name = "product_id")
    private Product productId;

    @JoinColumn(name = "branch_id")
    private Branch branchId;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }
}
