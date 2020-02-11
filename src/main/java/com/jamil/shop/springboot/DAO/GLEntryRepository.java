package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.gl.GLEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by farooq on 3/30/2017.
 */
@Repository
public interface GLEntryRepository extends JpaRepository<GLEntry, Long> {
}
