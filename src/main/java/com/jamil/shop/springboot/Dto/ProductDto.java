package com.jamil.shop.springboot.Dto;

public class ProductDto {
    private Long id;
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
    private ProductCategoryDto productCategory;
    private ProductCompanyDto productCompany;

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

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProductCategoryDto getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryDto productCategory) {
        this.productCategory = productCategory;
    }

    public ProductCompanyDto getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(ProductCompanyDto productCompany) {
        this.productCompany = productCompany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
