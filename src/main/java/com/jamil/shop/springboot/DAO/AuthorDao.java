package com.jamil.shop.springboot.DAO;


import com.jamil.shop.springboot.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

}
