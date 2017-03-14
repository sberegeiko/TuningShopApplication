package by.beregeiko.tuningshop.repository.spring_jpa;

import by.beregeiko.tuningshop.entity.Catalog;
import by.beregeiko.tuningshop.repository.CatalogRepositiry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Think on 23.02.2017.
 */
@Repository
public class CatalogRepositiryJpaImpl implements CatalogRepositiry {
    private static final Log LOG = LogFactory.getLog(CarRepositiryJpaImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Catalog findById(int id) {
        Query query = entityManager.createQuery(
                "SELECT catalog FROM Catalog catalog left join fetch catalog.products WHERE catalog.id =:id");
        query.setParameter("id", id);
        return (Catalog) query.getSingleResult();
    }

    @Override
    public List<Catalog> findAll() {
        Query query = entityManager.createQuery(
                "SELECT DISTINCT catalog FROM Catalog catalog");
        return query.getResultList();
    }
}
