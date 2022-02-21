package service;

import model.Product;

import java.util.ArrayList;

public interface IProductService {
    ArrayList<Product> getAll();


    ArrayList<Product> findByName(String name);

    Product findById(int id);

    void createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int id);

    public boolean checkProduct(int id);
}
