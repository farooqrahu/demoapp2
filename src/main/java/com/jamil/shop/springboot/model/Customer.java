package com.jamil.shop.springboot.model;

import javax.persistence.*;

/**
 * Created by sajjad on 3/30/2017.
 */

@Entity
@Table(name = "CUSTOMER", uniqueConstraints =
@UniqueConstraint(columnNames = {"CODE", "BRANCH_ID"}))
public class Customer extends BaseEntity {


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private Branch branch;

    @Column(name = "CUSTOMER_ADDRESS")
    private String CustomerAddress;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "SHORT_NAME", length = 255)
    private String shortName;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

   /* @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_TYPE_ID", nullable = false)
    private CustomerType customerType;*/

    @Column(name = "NTN_NUMBER")
    private String ntnNumber;

    @Column(name = "SALE_TAX_NUMBER")
    private String saleTaxNumber;

    @Column(name = "VISIBLE_TO_ALL_BRANCH")
    private Boolean visibleToAll;

    private Boolean closed;

    private Boolean isActive;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  /*  public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
*/
    public String getNtnNumber() {
        return ntnNumber;
    }

    public void setNtnNumber(String ntnNumber) {
        this.ntnNumber = ntnNumber;
    }

    public String getSaleTaxNumber() {
        return saleTaxNumber;
    }

    public void setSaleTaxNumber(String saleTaxNumber) {
        this.saleTaxNumber = saleTaxNumber;
    }

    public Boolean getVisibleToAll() {
        return visibleToAll;
    }

    public void setVisibleToAll(Boolean visibleToAll) {
        this.visibleToAll = visibleToAll;
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
