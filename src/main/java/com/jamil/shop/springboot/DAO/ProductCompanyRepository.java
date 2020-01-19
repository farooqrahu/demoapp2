package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.ProductCategory;
import com.jamil.shop.springboot.model.ProductCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Farooq
 */
@Repository
public interface ProductCompanyRepository extends JpaRepository<ProductCompany, Long> {
    ProductCompany findByName(String productCompany);

/*
    @Query("SELECT c from CustomerType c where c.groupOfCompany.id=?1")
    List<CustomerType> findAllByGroupOfCompanyId(Long id);

    CustomerType findByTitleAndGroupOfCompany(String title, GroupOfCompany groupOfCompany);
*/
}
