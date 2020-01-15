package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.Author;
import com.jamil.shop.springboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String role);
}
