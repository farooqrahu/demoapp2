package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.service.ProductService;
import com.jamil.shop.springboot.model.Content;
import com.jamil.shop.springboot.model.Product;
import com.jamil.shop.springboot.service.ContentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    private final static Logger logger = Logger.getLogger(ProductController.class);

    private byte[] bytes;

    private Product downloadProduct;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public List<Product> getAllProduct() {
        return productService.listProducts();
    }

    @RequestMapping(value = "/product/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProducts(id);
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public void addProducts(@RequestBody Product product) {

        Content content = new Content(bytes);
        contentService.saveContent(content);


        productService.addProducts(product);

        logger.info("Product saved successfully");
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public void updateProduct(@RequestBody Product product) {

        productService.addProducts(product);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProducts(@PathVariable int id) {
        try {
            productService.deleteProducts(id);

            logger.info("Product id - " + id + ", deleted successfully");

        }catch (Exception e) {
            logger.error("Error delete product.  Message: " + e.getMessage());

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/download/product/{id}", method = RequestMethod.POST) //from angularJS
    public void findProductForDownload(@PathVariable int id) throws IOException {

        downloadProduct = productService.getProducts(id);
    }

    @RequestMapping(value = "/downloadProduct")  //from html
    public HttpEntity<byte[]> downloadProduct() throws IOException {

      /*  byte[] fileByte = downloadProduct.getContent().getBytes();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("audio", "mpeg3"));
        header.set("Content-Disposition", "attachment; filename=" + downloadProduct.getAuthor().getName() + " - " + downloadProduct.getName() + ".mp3");
        header.setContentLength(fileByte.length);
*/
//        return new HttpEntity<byte[]>(fileByte, header);
return null;
    }

    @PostMapping("/upload")   //from html
    public void singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        bytes = file.getBytes();
        logger.info("Upload file and get bytes");

    }

    @RequestMapping("/productList/{id}")
    public List<Product> loadProductlist(@PathVariable Long id) {
        return productService.productlist(id);
    }

    @RequestMapping(value = "/productList/add/{id}", method = RequestMethod.POST)
    public ResponseEntity addProductsProductlist(@PathVariable int id) {
        try {
            productService.addProductsPlaylist(id);


        } catch (Exception e) {

            logger.error("Product don't added to productList.  Message: " + e.getMessage());

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.error("Product added successfully");

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/productList/delete/{id}", method = RequestMethod.DELETE)
    public void deleteProductsFromProductlist(@PathVariable int id) {
        productService.deleteProductsFromPlaylist(id);
    }

    @RequestMapping("/find/{name}")
    public List<Product> findProductByName(@PathVariable String name) {
        return productService.findProductByName(name);
    }



//    @RequestMapping("/play")
//    public String playProduct() {
//        Product product = productsService.getProduct(3);
//        byte[] fileByte = product.getContent();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("data:audio/mp3;base64,");
//        sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(fileByte, false)));
//
//        return sb.toString();
//    }
}
