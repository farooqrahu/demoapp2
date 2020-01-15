package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.BranchLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by bnv on 2/9/2017.
 */

@Repository
public interface BranchLogoRepository extends JpaRepository<BranchLogo,Long> {
    BranchLogo findByBranchId(Long branchId);
}
