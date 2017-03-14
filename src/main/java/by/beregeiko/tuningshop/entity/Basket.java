package by.beregeiko.tuningshop.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple JavaBean object that represents a Basket of {@link Product's}.
 * Created by Think on 06.03.2017.
 */
public class Basket {
    private Map<Product, Integer> products;

    public Basket() {
        this.products = new HashMap<>();
    }

    public Basket(Map<Product, Integer> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        if(!products.containsKey(product)) {
            products.put(product, 1);
        } else {
            products.put(product,  products.get(product) + 1);
        }
    }

    public void subtractProduct(Product product){
        if(products != null) {
            if(products.get(product) != 1) {
                products.put(product,  products.get(product) - 1);
            } else {
                products.remove(product);
            }
        }
    }

    public void removeProduct(Product product){
        if(products != null) {
            products.remove(product);
        }
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "products=" + products +
                '}';
    }
}
