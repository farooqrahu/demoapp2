package com.jamil.shop.springboot.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jamil.shop.springboot.model.Branch;
import com.jamil.shop.springboot.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bnv on 2/10/2017.
 */
public class UserDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phoneNo;
    private String employeeId;
    private String designation;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passKey;
    private Set<Role> roles = new HashSet<>();
    private String role;
    private Boolean resetPassword;
    private List<BranchDto> branchDto;
    private Boolean isActive;
    private String changPwd;
    private Boolean closed;
    private Set<Branch> branches;
    private String branch;
    private String totalPurchase;
    private String totalBranchSale;
    private String totalCustomerSale;
    private String totalIncome;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassKey() {
        return passKey;
    }

    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(Boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public List<BranchDto> getBranchDto() {
        return branchDto;
    }

    public void setBranchDto(List<BranchDto> branchDto) {
        this.branchDto = branchDto;
    }


    public String getChangPwd() {
        return changPwd;
    }

    public void setChangPwd(String changPwd) {
        this.changPwd = changPwd;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(String totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    public String getTotalBranchSale() {
        return totalBranchSale;
    }

    public void setTotalBranchSale(String totalBranchSale) {
        this.totalBranchSale = totalBranchSale;
    }

    public String getTotalCustomerSale() {
        return totalCustomerSale;
    }

    public void setTotalCustomerSale(String totalCustomerSale) {
        this.totalCustomerSale = totalCustomerSale;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
}
