package com.jamil.shop.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sajjad on 3/30/2017.
 */

@Entity
@Table(name = "CUSTOMER_TYPE")
public class CustomerType extends BaseEntity {

    @Column(name = "CUSTOMER_TYPE", nullable = false)
    private String customerType;

    @Column(name = "DESCRIPTION")
    private String description;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
