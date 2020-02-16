package com.jamil.shop.springboot.Dto;

public class ProductSaleCustomerDto {
    private Long id;
    private Long quantity;
    private Long newQuantity;
    private Long product;
    private Long branch;
    private Long totalSaleAmount;
    private Long newTotalSaleAmount;
    private String salePrice;
    private String customerName;

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

    public Long getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(Long newQuantity) {
        this.newQuantity = newQuantity;
    }

    public Long getNewTotalSaleAmount() {
        return newTotalSaleAmount;
    }

    public void setNewTotalSaleAmount(Long newTotalSaleAmount) {
        this.newTotalSaleAmount = newTotalSaleAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
