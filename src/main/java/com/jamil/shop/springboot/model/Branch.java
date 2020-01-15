package com.jamil.shop.springboot.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BRANCH")
public class Branch extends BaseEntity {


    @Column(name = "BRANCH_CODE", nullable = false, unique = true, length = 3)
    private String branchCode;

    @Column(name = "BRANCH_NAME", nullable = false)
    private String branchName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private Set<UserBranch> userBranch;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CITY_ID")
    private City city;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<UserBranch> getUserBranch() {
        return userBranch;
    }

    public void setUserBranch(Set<UserBranch> userBranch) {
        this.userBranch = userBranch;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
