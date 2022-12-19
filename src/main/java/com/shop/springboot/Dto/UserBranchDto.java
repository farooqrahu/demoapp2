package com.shop.springboot.Dto;

/**
 * Created by Farooq Rahu on 4/26/2017.
 */
public class UserBranchDto {

    private BranchDto branch;


    protected Boolean canPost;

    protected Boolean bDefault;

    protected Boolean reportingActive;

    private UserDto users;

    public BranchDto getBranch() {
        return branch;
    }

    public void setBranch(BranchDto branch) {
        this.branch = branch;
    }


    public Boolean getCanPost() {
        return canPost;
    }

    public void setCanPost(Boolean canPost) {
        this.canPost = canPost;
    }

    public Boolean getbDefault() {
        return bDefault;
    }

    public void setbDefault(Boolean bDefault) {
        this.bDefault = bDefault;
    }

    public Boolean getReportingActive() {
        return reportingActive;
    }

    public void setReportingActive(Boolean reportingActive) {
        this.reportingActive = reportingActive;
    }

    public UserDto getUsers() {
        return users;
    }

    public void setUsers(UserDto users) {
        this.users = users;
    }
}
