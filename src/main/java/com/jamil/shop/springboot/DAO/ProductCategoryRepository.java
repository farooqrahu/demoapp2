package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Farooq
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    public ProductCategory findByProductCategory(String productCategory);

    @Query("select c from ProductCategory  c where c.id=?1 and c.closed=false")
    ProductCategory findActive(Long id);

    @Query("select p from ProductCategory p where p.closed=false ")
    List<ProductCategory> findAllByNotClosed();
/*
    @Query("SELECT c from CustomerType c where c.groupOfCompany.id=?1")
    List<CustomerType> findAllByGroupOfCompanyId(Long id);

    CustomerType findByTitleAndGroupOfCompany(String title, GroupOfCompany groupOfCompany);
*/
}
