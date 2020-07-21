package com.jamil.shop.springboot.Dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BranchDto {

    private Long id;

    private String branchCode;

    private String branchName;

    private String address;

    private String ntnNumber;

    private String phone;

    private String fax;

    private BranchLogoDto branchLogoDto;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNtnNumber() {
        return ntnNumber;
    }

    public void setNtnNumber(String ntnNumber) {
        this.ntnNumber = ntnNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public BranchLogoDto getBranchLogoDto() {
        return branchLogoDto;
    }

    public void setBranchLogoDto(BranchLogoDto branchLogoDto) {
        this.branchLogoDto = branchLogoDto;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
