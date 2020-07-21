package com.jamil.shop.springboot.Dto.gl;


import org.joda.time.LocalDate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/*
  @auther by malik.imran on 3/31/2017.
 */
public class GLEntryDto implements Serializable {

    private Long id;
    private String description;
    private Set<GLEntryItemDto> glEntryItem;
    private BigDecimal totalAmount;
    private LocalDate transactionDate;
    private Long userId;
    private Long branchId;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GLEntryItemDto> getGlEntryItem() {
        return glEntryItem;
    }

    public void setGlEntryItem(Set<GLEntryItemDto> glEntryItem) {
        this.glEntryItem = glEntryItem;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
