package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.Dto.ProductDto;
import com.jamil.shop.springboot.model.CustomerType;
import com.jamil.shop.springboot.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by farooq on 3/30/2017.
 */
@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
    @Query("select s from ProductStock  s where  s.branchId=?1 and s.productId=?2")
    ProductStock getStockBranchWise(Long branchId,Long productId);

/*
    @Query("SELECT c from CustomerType c where c.groupOfCompany.id=?1")
    List<CustomerType> findAllByGroupOfCompanyId(Long id);

    CustomerType findByTitleAndGroupOfCompany(String title, GroupOfCompany groupOfCompany);
*/
}
