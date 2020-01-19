package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by bnv on 2/9/2017.
 */

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    @Query("select b from Branch b where b.branchName=?1")
    Branch findByBranchName(String branch);

/*    @Query("SELECT b from Branch b  " +
            " WHERE b.id NOT IN (SELECT ur.id from UserBranch ur WHERE ur.users.id =?1 AND b.isActive=true)  AND b.company.id=?2")
    List<Branch> getBranchesNotHavingUser(Long userId, Long companyId);

    @Query("SELECT b from Branch b WHERE  b.company.id=?1")
    List<Branch> getAllBranchesByCompanyId(@Param("companyId") Long companyId);

    @Query("select count(b.id)>0 from Branch b where b.city.id =?1")
    Boolean findBranchByCity(Long cityId);

    @Query("select count(b.id)>0 from Branch b where b.company.id =?1")
    Boolean findBranchByCompanyId(Long id);

    @Query("select b from Branch b where UPPER(b.branchCode)=?1")
    Branch findByBranchCodeUpperCase(String branchCode);

    @Query("select b from Branch b where UPPER(b.branchCode) =?1 and b.id!=?2")
    Branch findByBranchCodeNotHavingOwnId(String code, Long id);

    @Query("select b from Branch b where b.id=?1")
    Branch findById(@Param("id") Long id);

    @Query("select b from Branch b where b.id in (?1)")
    List<Branch> findByIdIn(@Param("ids") List<Long> branchIdList);*/
}
