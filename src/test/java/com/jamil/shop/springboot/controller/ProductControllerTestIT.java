package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= DEFINED_PORT)
public class ProductControllerTestIT {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void listProductTest() throws Exception {

        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Product>>() {
        });

        List<Product> actualList = responseEntity.getBody();

        assertThat(actualList.size(), is(7));
        List<Long> actualListId = actualList.stream()
                .map(Product::getId)
                .collect(toList());
        assertThat(actualListId, containsInAnyOrder(1, 2,3,4,5,6,7));
    }

    @Test
    public void getProductTest() throws Exception {

        ResponseEntity<Product> responseEntity = restTemplate.getForEntity("http://localhost:8080/product/{id}", Product.class,
                2);

        Product actualProduct = responseEntity.getBody();
        assertEquals("Clint Eastwood", actualProduct.getName());
    }

    @Test
    public void productlistLoadTest() throws Exception {
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange("http://localhost:8080/productList/{id}", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Product>>() {}, 2);

        List<Product> actualList = responseEntity.getBody();

        assertThat(actualList.size(), is(3));

    }


}
