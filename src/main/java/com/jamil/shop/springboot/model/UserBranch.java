package com.jamil.shop.springboot.model;

import javax.persistence.*;

/**
 * Created by Farooq Rahu on 4/25/2017.
 */
@Entity
@Table(name = "USER_BRANCH")
/*@SQLDelete(sql = "UPDATE USER_BRANCH SET IS_DELETED = true  WHERE id= ? and -1 != ?")*/
//@Where(clause = " IS_DELETED = 'false' ")
public class UserBranch extends BaseEntity {
    private static final long serialVersionUID = 150412029859606392L;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User users;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
