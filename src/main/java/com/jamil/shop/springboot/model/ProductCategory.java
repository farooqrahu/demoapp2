package com.jamil.shop.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;


@Entity
public class ProductCategory extends BaseEntity {

    @Column(name = "PRODUCT_CATEGORY", nullable = false)
    private String productCategory;

    @Column(name = "DESCRIPTION")
    private String description;

    private Boolean closed;
    private Boolean isActive;

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
