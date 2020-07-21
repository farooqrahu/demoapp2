package com.jamil.shop.springboot.Dto.gl;

import java.io.Serializable;
import java.math.BigDecimal;

/*
  @auther by Farooq on 3/31/2017.
 */

public class GLReportDto implements Serializable {

    private String accountName;
    private BigDecimal creditAmount;
    private BigDecimal debitAmount;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
}
