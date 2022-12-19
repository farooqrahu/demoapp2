package com.shop.springboot.DAO;

import com.shop.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UseRepository extends JpaRepository<User, Long> {

public Optional<User> findByUsername(String name);
}
