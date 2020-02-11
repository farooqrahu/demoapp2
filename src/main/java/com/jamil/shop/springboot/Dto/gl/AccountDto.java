package com.jamil.shop.springboot.Dto.gl;

/*
  @auther by malik.imran on 3/31/2017.
 */

import java.io.Serializable;
import java.util.Date;

public class AccountDto implements Serializable {

    private String code;
    private String title;
    private Boolean isActive;
    private Long branchId;
    private Date postingStartDate;
    private Date postingEndDate;

    public AccountDto() {

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
