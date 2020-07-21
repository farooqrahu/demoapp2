package com.jamil.shop.springboot.model.gl;


import com.jamil.shop.springboot.model.BaseEntityAudit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/*
  @auther by malik.imran on 3/31/2017.
 */
@Entity
public class GLEntry extends BaseEntityAudit {

    private String description;
    @OneToMany(mappedBy = "glEntry", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Set<GLEntryItem> glEntryItem;
    private BigDecimal totalAmount;
    @NotNull(message = "Transaction Date must be provide")
    private Date transactionDate;
    private Long branchId;
    private Long productId;
    private Long quantity;
    private Boolean isActive;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "transaction_ref_id")
    private Long transactionRefId;
    @Column(name = "transaction_return",nullable = false)
    private Boolean transactionReturn=false;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GLEntryItem> getGlEntryItem() {
        return glEntryItem;
    }

    public void setGlEntryItem(Set<GLEntryItem> glEntryItem) {
        this.glEntryItem = glEntryItem;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String purchaseType) {
        this.transactionType = purchaseType;
    }

    public Long getTransactionRefId() {
        return transactionRefId;
    }

    public void setTransactionRefId(Long transactionRefId) {
        this.transactionRefId = transactionRefId;
    }

    public Boolean getTransactionReturn() {
        return transactionReturn;
    }

    public void setTransactionReturn(Boolean transactionReturn) {
        this.transactionReturn = transactionReturn;
    }
}
