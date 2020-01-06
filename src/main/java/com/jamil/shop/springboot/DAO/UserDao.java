package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	User findOneByUsername(String username);
}
