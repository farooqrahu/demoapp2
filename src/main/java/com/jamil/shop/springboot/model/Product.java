package com.jamil.shop.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Product extends BaseEntityAudit {
    private String name;
    private String model;
    private Boolean closed;
    private Boolean isActive;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_CAT_ID", nullable = false)
    private ProductCategory productCategory;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_COMPANY_ID", nullable = false)
    private ProductCompany productCompany;

    public Product(String name, String model, Boolean closed, Boolean isActive, ProductCategory productCategory, ProductCompany productCompany) {
        this.name = name;
        this.model = model;
        this.closed = closed;
        this.isActive = isActive;
        this.productCategory = productCategory;
        this.productCompany = productCompany;
    }

    public Product() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductCompany getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(ProductCompany productCompany) {
        this.productCompany = productCompany;
    }




}
