package com.jamil.shop.springboot.Dto.gl;

import java.io.Serializable;
import java.math.BigDecimal;

/*
  @auther by malik.imran on 3/31/2017.
 */

public class GLEntryItemDto implements Serializable {
    private Long id;
    private String description;
    private String flag;
    private BigDecimal creditAmount;
    private BigDecimal debitAmount;
    private AccountDto accountDto;

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

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public AccountDto getAccountDto() {
        return accountDto;
    }

    public void setAccountDto(AccountDto accountDto) {
        this.accountDto = accountDto;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
