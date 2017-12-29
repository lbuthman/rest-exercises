package com.lbuthman.springexercises.services;

import com.lbuthman.springexercises.domain.Product;
import com.lbuthman.springexercises.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> getInStockProducts() {
        return null;
    }

    @Override
    public List<Product> getOutOfStockProducts() {
        return null;
    }

    @Override
    public List<Product> getSortedHighPriceProducts() {
        return null;
    }

    @Override
    public List<Product> getSortedLowPriceProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
