package by.beregeiko.tuningshop.repository;

import by.beregeiko.tuningshop.entity.Car;

import java.util.List;

/**
 * Created by Think on 23.02.2017.
 */
public interface CarRepository {
    Car findById(int id);
    List<Car> findByBrand(String brand);
    List<String> findAllBrands();
    List<Car> findAll();
}
