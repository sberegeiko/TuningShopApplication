package by.beregeiko.tuningshop.dao.impl.spring_hibernate;

import by.beregeiko.tuningshop.dao.CarDao;
import by.beregeiko.tuningshop.entity.Car;
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
@Repository("carDao")
public class CarDaoSpringHibernateImpl implements CarDao {
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
    public Car selectById(int id) {
        String hql = "SELECT DISTINCT cars " +
                "FROM Car cars " +
                "WHERE cars.id = :carId";
        return (Car) sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("carId", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> selectAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Car c").list();
    }
}
