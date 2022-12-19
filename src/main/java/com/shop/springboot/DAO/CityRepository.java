package com.shop.springboot.DAO;

import com.shop.springboot.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Ikrama on 3/17/2017.
 */

@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    City findByCityName(String name);
}
