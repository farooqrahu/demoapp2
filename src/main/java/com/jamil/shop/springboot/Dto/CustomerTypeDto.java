package com.jamil.shop.springboot.Dto;

import javax.validation.constraints.NotNull;

/**
 * Created by sajjad on 3/30/2017.
 */
public class CustomerTypeDto {

    private Long id;

    @NotNull
    private String title;
    private String description;
    private Long branchId;
    private BranchDto branch;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public BranchDto getBranch() {
        return branch;
    }

    public void setBranch(BranchDto branch) {
        this.branch = branch;
    }
}
