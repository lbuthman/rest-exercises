package com.lbuthman.springexercises.services;


import com.lbuthman.springexercises.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(Long id);

    List<Product> getInStockProducts();

    List<Product> getOutOfStockProducts();

    List<Product> getSortedHighPriceProducts();

    List<Product> getSortedLowPriceProducts();

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
