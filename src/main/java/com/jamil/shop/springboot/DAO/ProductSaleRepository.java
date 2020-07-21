package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.ProductSale;
import com.jamil.shop.springboot.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by farooq on 3/30/2017.
 */
@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
    @Query("select s from ProductSale  s where  s.branchId=?1 and s.productId=?2")
    ProductSale getStockBranchWise(Long branchId, Long productId);
    @Query("select s from ProductSale  s where  s.productId=?1")
    ProductSale getStockProductWise(Long productId);

    @Query("select s from ProductSale  s where  s.branchId=?1")
    List<ProductSale> getAllProductsBranchWise(Long branchId);

/*
    @Query("SELECT c from CustomerType c where c.groupOfCompany.id=?1")
    List<CustomerType> findAllByGroupOfCompanyId(Long id);

    CustomerType findByTitleAndGroupOfCompany(String title, GroupOfCompany groupOfCompany);
*/
}
