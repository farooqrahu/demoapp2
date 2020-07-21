package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by farooq on 3/30/2017.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("select a from Invoice  a where a.invoiceNumberBranch=?1")
    Invoice findByInVoice(Long invoiceNumber);
    @Query("select max(a.invoiceNumberBranch) from Invoice  a")
    Long findMaxInvoiceNumberBranch();
    @Query("select max(a.invoiceNumberCustomer) from Invoice  a")
    Long findMaxInvoiceNumberCustomer();

}
