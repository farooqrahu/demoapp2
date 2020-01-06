package com.jamil.shop.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Productlist {
    @Id
    private Long products_id;
    private Long user_id;


    public Productlist() {
    }

    public Productlist(Long user_id, Long products_id) {
        this.user_id = user_id;
        this.products_id = products_id;
    }

    public Long getProduct_id() {
        return products_id;
    }

    public void setProduct_id(Long products_id) {
        this.products_id = products_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
