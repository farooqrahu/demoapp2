package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.gl.GLEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by farooq on 3/30/2017.
 */
@Repository
public interface GLEntryRepository extends JpaRepository<GLEntry, Long> {
    @Query("select e from GLEntry e where e.isActive=true")
    List<GLEntry> findAllActive();

    @Query("select e from GLEntry e where e.isActive=false and e.transactionReturn=true")
    List<GLEntry> findAllInActive();

}
