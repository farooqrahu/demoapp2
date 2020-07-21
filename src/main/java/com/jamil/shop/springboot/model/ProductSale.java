package com.jamil.shop.springboot.model;

import javax.persistence.Entity;

@Entity
public class ProductSale extends BaseEntityAudit {

    private Long quantity;
    private Long productId;
    private Long branchId;
    private Long totalSaleAmount;
    private String salePrice;

    public ProductSale(Long quantity, Long productId, Long branchId, Long totalSaleAmount, String salePrice) {
        this.quantity = quantity;
        this.productId = productId;
        this.branchId = branchId;
        this.totalSaleAmount = totalSaleAmount;
        this.salePrice = salePrice;
    }

    public ProductSale() {
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


    public Long getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public void setTotalSaleAmount(Long totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }
}
