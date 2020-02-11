package com.jamil.shop.springboot.model.gl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jamil.shop.springboot.model.BaseEntityAudit;

import javax.persistence.*;
import java.math.BigDecimal;

/*
  @auther by malik.imran on 3/31/2017.
 */
@Entity
public class GLEntryItem  extends BaseEntityAudit {

    private String description;
    private String flag;
    private BigDecimal creditAmount;
    private BigDecimal debitAmount;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GLENTRY_ID")
    @JsonIgnore
    private GLEntry glEntry;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public GLEntry getGlEntry() {
        return glEntry;
    }

    public void setGlEntry(GLEntry glEntry) {
        this.glEntry = glEntry;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
