package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.CustomerType;
import com.jamil.shop.springboot.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by sajjad on 3/30/2017.
 */
@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

/*
    @Query("SELECT c from CustomerType c where c.groupOfCompany.id=?1")
    List<CustomerType> findAllByGroupOfCompanyId(Long id);

    CustomerType findByTitleAndGroupOfCompany(String title, GroupOfCompany groupOfCompany);
*/
}
