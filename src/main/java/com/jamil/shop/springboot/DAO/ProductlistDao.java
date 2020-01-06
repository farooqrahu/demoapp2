package com.jamil.shop.springboot.DAO;


import com.jamil.shop.springboot.model.Productlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductlistDao extends JpaRepository<Productlist, Integer> {

}
