package com.jamil.shop.springboot.DAO;


import com.jamil.shop.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query("SELECT s FROM Product s WHERE   s.name LIKE CONCAT('%',:name,'%')")
    List<Product> findProductsByName(@Param("name") String name);



}
