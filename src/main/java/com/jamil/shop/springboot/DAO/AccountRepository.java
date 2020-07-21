package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.gl.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by farooq on 3/30/2017.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account  a where a.branchId=?1")
    Account findByBranchId(Long branchId);
}
