package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.ProductCompany;
import com.jamil.shop.springboot.model.ProductCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Farooq
 */
@Repository
public interface ProductCompanyRepository extends JpaRepository<ProductCompany, Long> {
    ProductCompany findByName(String productCompany);

    @Query("select c from ProductCompany  c where c.id=?1 and c.closed=false")
    ProductCompany findActive(Long id);

    @Query("select p from ProductCompany p where p.closed=false ")
    List<ProductCompany> findAllByNotClosed();
/*
    @Query("SELECT c from CustomerType c where c.groupOfCompany.id=?1")
    List<CustomerType> findAllByGroupOfCompanyId(Long id);

    CustomerType findByTitleAndGroupOfCompany(String title, GroupOfCompany groupOfCompany);
*/
}
