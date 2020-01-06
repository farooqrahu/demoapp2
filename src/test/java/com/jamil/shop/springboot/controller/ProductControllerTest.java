package com.jamil.shop.springboot.controller;

import com.google.common.collect.ImmutableList;
import com.jamil.shop.springboot.model.Author;
import com.jamil.shop.springboot.model.Content;
import com.jamil.shop.springboot.model.Product;
import com.jamil.shop.springboot.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private List<Product> products = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Content content = new Content();
        Author author = new Author();

        products.add(new Product("name", author, "duration", "date", "album", content));
        products.add(new Product("name1", author, "duration1", "date1", "album1", content));
        products.add(new Product("name2", author, "duration2", "date2", "album2", content));
    }


    @Test
    public void getAllProductVerify() throws Exception {

        when(productService.listProducts()).thenReturn(ImmutableList.of());

        List<Product> allProducts = productController.getAllProduct();

        verify(productService).listProducts();

    }

    @Test
    public void getAllProductTest() throws Exception {

        when(productService.listProducts()).thenReturn(products);

        List<Product> allProducts = productController.getAllProduct();

        Assert.assertEquals(3, allProducts.size());
    }

    @Test
    public void getProductByIdTest() throws Exception {

        int id = 1;

        when(productService.getProducts(id)).thenReturn(products.get(id));

        Product product = productController.getProduct(id);

        Assert.assertEquals("name1", product.getName());

    }









}
