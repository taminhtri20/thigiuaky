package org.example.demo5.service;

import org.example.demo5.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    void save(Product product);
    List<Product> findAll();
    void remove(int id);
    void edit(Product product);
    Optional<Product> findById(int id);
    List<Product> search(String nameSearch);
}
