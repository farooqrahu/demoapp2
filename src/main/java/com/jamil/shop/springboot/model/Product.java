package com.jamil.shop.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Product extends BaseEntityAudit {
    private String name;
    private String releaseDate;
    private String display;
    private String dimension;
    private String weight;
    private String os;
    private String memory;
    private String cameras;
    private String battery;
    private String colors;
    private String osVersion;
    private String price;
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


    public Product() {
    }

    public Product(String name, String releaseDate, String display, String dimension, String weight, String os, String memory, String cameras, String battery, String colors, String osVersion, String price, ProductCategory productCategory, ProductCompany productCompany) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.display = display;
        this.dimension = dimension;
        this.weight = weight;
        this.os = os;
        this.memory = memory;
        this.cameras = cameras;
        this.battery = battery;
        this.colors = colors;
        this.osVersion = osVersion;
        this.price = price;
        this.productCategory = productCategory;
        this.productCompany = productCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }


    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCameras() {
        return cameras;
    }

    public void setCameras(String cameras) {
        this.cameras = cameras;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

   public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
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
