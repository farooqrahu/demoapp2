package com.jamil.shop.springboot.service;

import com.jamil.shop.springboot.DAO.ContentDao;
import com.jamil.shop.springboot.DAO.ProductDao;
import com.jamil.shop.springboot.DAO.ProductlistDao;
import com.jamil.shop.springboot.DAO.UserDao;
import com.jamil.shop.springboot.model.User;
import com.jamil.shop.springboot.model.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final static Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductlistDao productlistDao;

    private List<Product> products;
    private User user;

    public List<Product> listProducts() {
        logger.info("Load all Product service method");

        return productDao.findAll();
    }

    public Product getProducts(int id) {
        return productDao.findOne(id);
    }

    public void addProducts(Product Product) {
        productDao.save(Product);
    }

    public void deleteProducts(int id) {
        if (productlistDao.findOne(id) != null) {

            productlistDao.delete(id);

            logger.info("Delete Product from productlist service method");
        }
        Product Product = productDao.findOne(id);

        productDao.delete(id);

        logger.info("Delete Product service method");




    }

    public List<Product> productlist(Long userId) {

        User user = userDao.findOne(userId);

        return user.getProductList(products);

    }

    public void addProductsPlaylist(int id) throws Exception {
        authentication();

        for (Product Product: products) {
            if (Product.getId() == id) {

                throw new RuntimeException("This Product already in a productlist");
            }
        }
        products.add(productDao.findOne(id));

        logger.info("Add Product  user " + user.getName() +" productlist");

        saveUser();
    }

    public void deleteProductsFromPlaylist(int id) {
        authentication();

        products.removeIf(s -> s.getId() == id);

        logger.info("Delete Product from user " + user.getName() +" productlist");

        saveUser();
    }

    public List<Product> findProductByName(String name) {
        return productDao.findProductsByName(name);
    }

    private void authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        user = userDao.findOneByUsername(loggedUsername);
        products = new ArrayList<>();
        products.addAll(user.getProductList(products));

        logger.info("Get current " + user.getName() +" productlist");
    }

    private void saveUser() {
        user.getProductList(products);
        userDao.save(user);
        logger.info("Save new " + user.getName() +" productlist");
    }

}
