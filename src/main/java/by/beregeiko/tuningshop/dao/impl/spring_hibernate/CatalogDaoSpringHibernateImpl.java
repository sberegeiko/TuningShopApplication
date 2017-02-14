package by.beregeiko.tuningshop.dao.impl.spring_hibernate;

import by.beregeiko.tuningshop.dao.CatalogDao;
import by.beregeiko.tuningshop.entity.Catalog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Think on 13.02.2017.
 */
@Transactional
@Repository("catalogDao")
public class CatalogDaoSpringHibernateImpl implements CatalogDao {
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
    public Catalog selectById(int id) {
        String hql = "SELECT DISTINCT catalogs " +
                "FROM Catalog catalogs " +
                "WHERE catalogs.id = :catalogId";
        return (Catalog) sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("catalogId", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Catalog> selectAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Catalog c").list();
    }
}
