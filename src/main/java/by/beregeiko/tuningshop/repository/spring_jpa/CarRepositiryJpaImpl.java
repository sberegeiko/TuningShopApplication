package by.beregeiko.tuningshop.repository.spring_jpa;

import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.repository.CarRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Think on 13.02.2017.
 */
@Repository
public class CarRepositiryJpaImpl implements CarRepository {
    private static final Log LOG = LogFactory.getLog(CarRepositiryJpaImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Car findById(int id) {
        Query query = entityManager.createQuery(
                "SELECT car FROM Car car left join fetch car.products WHERE car.id =:id");
        query.setParameter("id", id);
        return (Car) query.getSingleResult();
    }

    @Override
    public List<Car> findByBrand(String brand) {
        Query query = entityManager.createQuery(
                "SELECT DISTINCT car FROM Car car WHERE car.brand LIKE :brand");
        query.setParameter("brand", brand + "%");
        return query.getResultList();
    }

    @Override
    public List<String> findAllBrands() {
        Query query = entityManager.createQuery(
                "SELECT DISTINCT car.brand FROM Car car ORDER BY car.brand");
        return query.getResultList();
    }

    @Override
    public List<Car> findAll() {
        Query query = entityManager.createQuery(
                "SELECT DISTINCT car FROM Car car");
        return query.getResultList();
    }
}
