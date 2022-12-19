package com.shop.springboot.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.springboot.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Farooq Rahu On 2/10/2017.
 */
@Getter
@Setter
public class UserDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phoneNo;
    private String employeeId;
    private String designation;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Set<Role> roles = new HashSet<>();
    private String role;
    private Boolean resetPassword;
    private List<BranchDto> branchDto;
    private Boolean isActive;
    private String changPwd;
    private Boolean closed;

    private String branch;
    private String totalPurchase;
    private String totalBranchSale;
    private String totalCustomerSale;
    private String totalIncome;
    public Long getId() {
        return id;
    }

}
