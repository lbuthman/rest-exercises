package com.lbuthman.springexercises.web.rest;

import com.lbuthman.springexercises.domain.Product;
import com.lbuthman.springexercises.services.ProductService;
import com.lbuthman.springexercises.web.rest.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private static final String ENTITY_NAME = "product";

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping
    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getOneProduct(@PathVariable Long id) {
        Product product = service.getProduct(id);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found ID: " + id, ENTITY_NAME);
        }
        return product;
    }
}
