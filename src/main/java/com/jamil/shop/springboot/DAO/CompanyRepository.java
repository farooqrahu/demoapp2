package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.ProductCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by bnv on 2/9/2017.
 */
@Repository
public interface CompanyRepository extends JpaRepository<ProductCompany, Long> {

    @Query("select c from ProductCompany c where UPPER(c.name) =?1")
    ProductCompany findCompanyByName(String name);

    @Query("select c from ProductCompany c where UPPER(c.name) =?1 and c.id!=?2")
    ProductCompany findByCompanyNameNotHavingOwnId(String name, Long id);
}
