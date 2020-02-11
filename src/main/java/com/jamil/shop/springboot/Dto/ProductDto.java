package com.jamil.shop.springboot.Dto;

public class ProductDto {
    private Long id;
    private String name;
    private String model;

    private ProductCategoryDto productCategory;
    private ProductCompanyDto productCompany;

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
