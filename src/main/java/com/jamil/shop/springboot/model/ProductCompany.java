package com.jamil.shop.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by bnv on 2/8/2017.
 */

@Entity
@Table(name = "PRODUCT_COMPANY")
public class ProductCompany extends BaseEntity {

    private static final long serialVersionUID = 2127130896324939098L;

    @Column(name = "COMPANY_NAME", nullable = false, unique = true,length=50)
    private String name;

    @Column(name = "COMPANY_DESC", length = 250)
    private String companyDesc;

    @Column(name = "COMPANY_ADDRESS",length=50)
    private String companyAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

}
