package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by sajjad on 30/03/2016.
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


   /* @Query("SELECT c FROM Customer c WHERE c.branch.id =?1")
    List<Customer> findCustomerByBranchId(Long id);

    @Query("SELECT c FROM Customer c WHERE c.branch.id =?1")
    List<Customer> findByBranchIdRestRequest(@Param("branchId") Long id);


    @Query("SELECT c FROM Customer c WHERE c.branch.id =?1 or c.visibleToAll=true ")
    List<Customer> findCustomerByBranchIdAndVisibleToAll(@Param("branchId") Long id);

    Customer findByCode(String code);

    Long countByAccountId(Long accountId);

    @Query("SELECT c FROM Customer c WHERE UPPER(c.code)=?1 and c.branch.id=?2")
    Customer findByCodeAndBranchUpperCase(String code, Long branchId);

    @Query("SELECT c FROM Customer c WHERE UPPER(c.code)=?1 and c.branch.id=?2 and c.id!=?3")
    Customer findByCodeAndBranchUpperCaseNotHavingOwnId(String code, Long branchId, Long id);


    @Query("SELECT c FROM Customer c WHERE c.accountId=?1 and c.id=?2")
    Customer findByAccountId(Long accountId, Long customerId);

    Customer findByTitle(String title);*/

}
