package com.jamil.shop.springboot.DAO;

import com.jamil.shop.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	User findOneByUsername(String username);
	@Query("select u from User u where u.closed=false")
	List<User> findAllUsers();
}
