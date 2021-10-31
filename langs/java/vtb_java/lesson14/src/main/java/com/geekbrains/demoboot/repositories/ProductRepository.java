package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> getAllProductsByStringContains(String str) {
        List<Product> tmp = new ArrayList<>();
        for (Product product : products){
            if(product.getTitle().contains(str)){
                tmp.add(product);
            }
        }
        return tmp;
    }

    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
    }

    public void deleteById(Long id) {
        products.remove(findById(id));
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        products.add(product);
    }
}
