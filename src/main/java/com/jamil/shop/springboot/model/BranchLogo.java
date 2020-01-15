package com.jamil.shop.springboot.model;

import javax.persistence.*;


@Entity
@Table(name = "BRANCH_LOGO")
public class BranchLogo extends BaseEntity {

    @Column(name = "IMAGE_PATH")
    private String imagePath;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
