package com.jamil.shop.springboot.model.gl;


import com.jamil.shop.springboot.model.BaseEntityAudit;

import javax.persistence.Entity;

/**
 * Created by malik.imran on 3/31/2017.
 */
@Entity
public class AccountType extends BaseEntityAudit {

    private String code;
    private String name;
    private String description;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
