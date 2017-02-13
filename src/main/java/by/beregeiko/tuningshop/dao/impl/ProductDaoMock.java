package by.beregeiko.tuningshop.dao.impl;

import by.beregeiko.tuningshop.dao.ProductDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.entity.Catalog;
import by.beregeiko.tuningshop.entity.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.asList;

/**
 * Created by Think on 09.12.2016.
 */
public class ProductDaoMock implements ProductDao {
    private final Map<Integer, Product> memory = new ConcurrentHashMap<>();

    public ProductDaoMock() {
        this.memory.put(1, new Product(
                        1, "Bamper", "DEPO", "000001", "DEPO000001",
                        new HashSet<Catalog>(asList(new Catalog(1, "Bamper"), new Catalog(2, "Tuning Bamper"))),
                        new HashSet<Car>(asList(new Car(1, "Audi", "A6", "2005-2009"))),
                        asList("1", "2")
                )
        );

        this.memory.put(2, new Product(
                        2, "Bamper", "DEPO", "000002", "DEPO000002",
                        new HashSet<Catalog>(asList(new Catalog(1, "Bamper"), new Catalog(3, "Standart Bamper"))),
                        new HashSet<Car>(asList(new Car(1, "Audi", "A6", "2005-2009"), new Car(2, "Audi", "A7", "2005-2009"))),
                        asList("3", "4")
                )
        );

        this.memory.put(3, new Product(
                        3, "Bamper", "DEPO", "000003", "DEPO000003",
                        new HashSet<Catalog>(asList(new Catalog(1, "Bamper"), new Catalog(2, "Tuning Bamper"))),
                        new HashSet<Car>(asList(new Car(3, "BMW", "3", "2007-2010"))),
                        asList("5", "6")
                )
        );

        this.memory.put(4, new Product(
                        4, "Spoiler", "DEPO", "000004", "DEPO000005",
                        new HashSet<Catalog>(asList(new Catalog(4, "Spoiler"), new Catalog(5, "Tuning Spoiler"))),
                        new HashSet<Car>(asList(new Car(3, "BMW", "3", "2007-2010"))),
                        asList("1", "2")
                )
        );
    }

    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Product for id == '" + id + "', only " + memory.keySet());
        }
        return memory.get(id);
    }

    @Override
    public List<Product> selectByCarId(int carId) throws DaoSystemException, NoSuchEntityException {
        List<Product> productList = new ArrayList<>();
        for (Product product : memory.values()) {
            for (Car car : product.getCars()) {
                if (car.getId() == carId) {
                    productList.add(product);
                }
            }
        }
        return productList;
    }

    @Override
    public List<Product> selectByCatalogId(int catalogId) throws DaoSystemException, NoSuchEntityException {
        List<Product> productList = new ArrayList<>();
        for (Product product : memory.values()) {
            for (Catalog catalog : product.getCatalogs()) {
                if (catalog.getId() == catalogId) {
                    productList.add(product);
                }
            }
        }
        return productList;
    }

    @Override
    public List<Product> selectByCarAndCatalogId(int carId, int catalogId) throws DaoSystemException, NoSuchEntityException {
        Set<Product> productSet = new HashSet<>();
        Set<Product> productSetByCatalog = new HashSet<>();

        for (Product product : memory.values()) {
            for (Car car : product.getCars()) {
                if (car.getId() == carId) {
                    productSet.add(product);
                }
            }
            for (Catalog catalog : product.getCatalogs()) {
                if (catalog.getId() == catalogId) {
                    productSetByCatalog.add(product);
                }
            }
        }
        productSet.retainAll(productSetByCatalog);
        return new ArrayList<>(productSet);
    }

    public List<Product> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}
