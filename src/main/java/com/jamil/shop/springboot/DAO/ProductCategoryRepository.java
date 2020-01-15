package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Farooq
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

/*
    @Query("SELECT c from CustomerType c where c.groupOfCompany.id=?1")
    List<CustomerType> findAllByGroupOfCompanyId(Long id);

    CustomerType findByTitleAndGroupOfCompany(String title, GroupOfCompany groupOfCompany);
*/
}
