package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.ProductSale;
import com.jamil.shop.springboot.model.ProductSaleCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by farooq on 3/30/2017.
 */
@Repository
public interface ProductSaleCusRepository extends JpaRepository<ProductSaleCustomer, Long> {
    @Query("select s from ProductSaleCustomer  s where  s.branchId=?1 and s.productId=?2")
    ProductSaleCustomer getStockBranchWise(Long branchId, Long productId);

/*
    @Query("SELECT c from CustomerType c where c.groupOfCompany.id=?1")
    List<CustomerType> findAllByGroupOfCompanyId(Long id);

    CustomerType findByTitleAndGroupOfCompany(String title, GroupOfCompany groupOfCompany);
*/
}
