package com.jamil.shop.springboot.Dto;

public class ProductStockDto {
    private Long id;
    private Long quantity;
    private Long newQuantity;
    private Long product;
    private Long branch;
    private Long totalPurchaseAmount;
    private Long newTotalPurchaseAmount;
    private Long newTotalSaleAmount;
    private Long totalSaleAmount;
    private String purchasePrice;
    private String salePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public Long getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(Long totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public Long getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public void setTotalSaleAmount(Long totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public Long getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(Long newQuantity) {
        this.newQuantity = newQuantity;
    }

    public Long getNewTotalPurchaseAmount() {
        return newTotalPurchaseAmount;
    }

    public void setNewTotalPurchaseAmount(Long newTotalPurchaseAmount) {
        this.newTotalPurchaseAmount = newTotalPurchaseAmount;
    }

    public Long getNewTotalSaleAmount() {
        return newTotalSaleAmount;
    }

    public void setNewTotalSaleAmount(Long newTotalSaleAmount) {
        this.newTotalSaleAmount = newTotalSaleAmount;
    }
}
