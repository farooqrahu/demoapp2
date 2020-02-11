package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.gl.GLEntryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by farooq on 3/30/2017.
 */
@Repository
public interface GLEntryItemRepository extends JpaRepository<GLEntryItem, Long> {
    @Query("select gli from GLEntryItem  gli where gli.account.id=?1")
    List<GLEntryItem> findByAccountId(Long id);
}
