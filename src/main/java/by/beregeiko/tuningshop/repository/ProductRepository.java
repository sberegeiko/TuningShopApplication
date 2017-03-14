package by.beregeiko.tuningshop.repository;

import by.beregeiko.tuningshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Think on 24.02.2017.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.cars LEFT JOIN FETCH p.catalogs WHERE p.id = ?1")
    Product findById(int id);

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.cars c LEFT JOIN FETCH p.catalogs WHERE c.id = ?1")
    List<Product> findByCarId(int id);

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.cars LEFT JOIN FETCH p.catalogs c WHERE c.id = ?1")
    List<Product> findByCatalogId(int id);

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.cars c LEFT JOIN FETCH p.catalogs ct " +
            "WHERE c.id = ?1 AND ct.id = ?2")
    List<Product> findByCarAndCatalogId(int carId, int catalogId);
}
