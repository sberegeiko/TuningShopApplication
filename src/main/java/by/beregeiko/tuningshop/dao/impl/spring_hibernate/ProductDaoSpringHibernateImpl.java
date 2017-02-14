package by.beregeiko.tuningshop.dao.impl.spring_hibernate;

import by.beregeiko.tuningshop.dao.ProductDao;
import by.beregeiko.tuningshop.entity.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Think on 09.02.2017.
 */
@Transactional
@Repository("productDao")
public class ProductDaoSpringHibernateImpl implements ProductDao {
    private static final Log LOG = LogFactory.getLog(ProductDaoSpringHibernateImpl.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public Product selectById(int id) {
        return (Product) sessionFactory.getCurrentSession()
                .getNamedQuery("Product.selectById")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> selectByCarId(int id) {
        String hql = "SELECT DISTINCT products " +
                "FROM Product products " +
                "LEFT JOIN FETCH products.cars cars " +
                "LEFT JOIN FETCH products.catalogs catalogs " +
                "WHERE cars.id = :carId";
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("carId", id).list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> selectByCatalogId(int id) {
        String hql = "SELECT DISTINCT products " +
                "FROM Product products " +
                "LEFT JOIN FETCH products.cars cars " +
                "LEFT JOIN FETCH products.catalogs catalogs " +
                "WHERE catalogs.id = :catalogId";
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("catalogId", id).list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> selectByCarAndCatalogId(int carId, int catalogId) {
        String hql = "SELECT DISTINCT products " +
                "FROM Product products " +
                "LEFT JOIN FETCH products.cars cars " +
                "LEFT JOIN FETCH products.catalogs catalogs " +
                "WHERE cars.id = :carId " +
                "AND catalogs.id = :catalogId";
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("carId", carId).setParameter("catalogId", catalogId).list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> selectAll() {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("Product.selectAll").list();
    }
}
