package com.shop.springboot.model;

import javax.persistence.*;

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




}
