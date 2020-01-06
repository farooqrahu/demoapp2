package com.jamil.shop.springboot.DAO;


import com.jamil.shop.springboot.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDao extends JpaRepository<Content, Integer>{
}
