package com.jamil.shop.springboot.model.gl;

/*
  @auther by malik.imran on 3/31/2017.
 */

import com.jamil.shop.springboot.model.BaseEntityAudit;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Account extends BaseEntityAudit{

    private String code;
    private String title;
    private Boolean isActive;
    private Long branchId;
    private Date postingStartDate;
    private Date postingEndDate;

    public Account() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }



    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }


    public Date getPostingStartDate() {
        return postingStartDate;
    }

    public void setPostingStartDate(Date postingStartDate) {
        this.postingStartDate = postingStartDate;
    }

    public Date getPostingEndDate() {
        return postingEndDate;
    }

    public void setPostingEndDate(Date postingEndDate) {
        this.postingEndDate = postingEndDate;
    }
}
