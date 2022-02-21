package service;

import model.Product;

import java.util.ArrayList;

public class ProductServiceImpl implements service.impl.IProductService {
    private static final ArrayList<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product(1, "A", 10000, "a"));
        products.add(new Product(2, "B", 20000, "b"));
        products.add(new Product(3, "C", 30000, "c"));
    }

    @Override
    public ArrayList<Product> getAll() {
        return products;
    }

    @Override
    public void createProduct(Product product) {
        products.add(product);
    }

    @Override
    public void updateProduct(Product product, int index) {
        products.set(index, product);
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }

    @Override
    public Product getProduct(int id) {
        for(Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}