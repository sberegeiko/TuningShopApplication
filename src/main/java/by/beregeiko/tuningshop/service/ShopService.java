package by.beregeiko.tuningshop.service;

import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.entity.Catalog;
import by.beregeiko.tuningshop.entity.Product;

import java.util.List;

/**
 * Created by Think on 23.02.2017.
 */
public interface ShopService {
    Car findCarById(int id);
    List<Car> findCarByBrand(String brand);
    List<String> findAllCarBrands();
    List<Car> findAllCars();

    Catalog findCatalogById(int id);
    List<Catalog> findAllCatalogs();

    Product findProductById(int id);
    List<Product> findProductByCarId(int carId);
    List<Product> findProductByCatalogId(int catalogId);
    List<Product> findProductByCarAndCatalogId(int carId, int catalogId);
    List<Product> findAllProducts();

}
