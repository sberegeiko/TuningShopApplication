package by.beregeiko.tuningshop.service;

import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.entity.Catalog;
import by.beregeiko.tuningshop.entity.Product;
import by.beregeiko.tuningshop.repository.CarRepository;
import by.beregeiko.tuningshop.repository.CatalogRepositiry;
import by.beregeiko.tuningshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Think on 23.02.2017.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CatalogRepositiry catalogRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Car findCarById(int id) {
        return carRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findCarByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllCarBrands() {
        return carRepository.findAllBrands();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Catalog findCatalogById(int id) {
        return catalogRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Catalog> findAllCatalogs() {
        List<Catalog> catalogs = catalogRepository.findAll();
        Collections.sort(catalogs, new Comparator<Catalog>() {
            @Override
            public int compare(Catalog o1, Catalog o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return catalogs;
        /*return catalogRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Object::toString))
                .collect(Collectors.toList());*/
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductByCarId(int carId) {
        return productRepository.findByCarId(carId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductByCatalogId(int catalogId) {
        return productRepository.findByCatalogId(catalogId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductByCarAndCatalogId(int carId, int catalogId) {
        return productRepository.findByCarAndCatalogId(carId, catalogId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

}
