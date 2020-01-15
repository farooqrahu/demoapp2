package com.jamil.shop.springboot.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Ikrama on 3/16/2017.
 */

@Entity
@Table(name = "CITY")
public class City extends BaseEntity {

    @Column(name = "CITY_CODE")
    private String cityCode;

    @Column(name = "CITY_NAME")
    private String cityName;


    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    private Set<Branch> branchList;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public Set<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(Set<Branch> branchList) {
        this.branchList = branchList;
    }

}
