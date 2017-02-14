package by.beregeiko.tuningshop.dao.impl.spring_jdbc;

import by.beregeiko.tuningshop.dao.ProductDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.entity.Catalog;
import by.beregeiko.tuningshop.entity.Product;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Think on 09.02.2017.
 */
//@Repository("productDao")
public class ProductDaoSpringJdbcImpl implements ProductDao,InitializingBean {
    private static final String SELECT_ALL_SQL = "SELECT DISTINCT " +
            "products.id, products.name, products.producer, " +
            "products.productcode, products.producerproductcode, " +
            "cars.id as cars_id, cars.name as cars_name, " +
            "cars.model as cars_model, cars.yearfromto as cars_yearfromto, " +
            "catalogs.id as catalogs_id, catalogs.name as catalogs_name " +
            "FROM products " +
            "INNER JOIN product_cars " +
            "ON (product_cars.product_id = products.id) " +
            "INNER JOIN cars " +
            "ON (car_id = cars.id) " +
            "INNER JOIN product_catalogs " +
            "ON (product_catalogs.product_id = products.id) " +
            "INNER JOIN catalogs " +
            "ON (catalog_id = catalogs.id) ";

    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        String sql = SELECT_ALL_SQL + "WHERE products.id = " + id;
        Product result = namedParameterJdbcTemplate.query(sql, new ProductExtractor());
        return result;
    }

    @Override
    public List<Product> selectByCarId(int id) throws DaoSystemException, NoSuchEntityException {
        String sql = SELECT_ALL_SQL + "WHERE cars.id = " + id;
        return namedParameterJdbcTemplate.query(sql, new ProductListExtractor());
    }

    @Override
    public List<Product> selectByCatalogId(int id) throws DaoSystemException, NoSuchEntityException {
        String sql = SELECT_ALL_SQL + "WHERE catalogs.id = " + id;
        return namedParameterJdbcTemplate.query(sql, new ProductListExtractor());
    }

    @Override
    public List<Product> selectByCarAndCatalogId(int carId, int catalogId) throws DaoSystemException, NoSuchEntityException {
        String sql = SELECT_ALL_SQL + "WHERE cars.id = " + carId + "AND catalogs.id = " + catalogId;
        return namedParameterJdbcTemplate.query(sql, new ProductListExtractor());
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        String sql = SELECT_ALL_SQL;
        return namedParameterJdbcTemplate.query(sql, new ProductListExtractor());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on ProductDao");
        }

        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("Null NamedParameterJdbcTemplate on ProductDao");
        }
    }

    private static final class ProductListExtractor implements ResultSetExtractor<List<Product>> {
        @Override
        public List<Product> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Integer, Product> map = new HashMap<>();
            Product product = null;

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                product = map.get(id);
                if(product == null){
                    product = new Product();
                    product.setId(id);
                    product.setName(resultSet.getString("name"));
                    product.setProducer(resultSet.getString("producer"));
                    product.setProductCode(resultSet.getString("productcode"));
                    product.setProducerProductCode(resultSet.getString("producerproductcode"));
                    product.setCars(new HashSet<Car>());
                    product.setCatalogs(new HashSet<Catalog>());
                    map.put(id, product);
                }
                Integer carId = resultSet.getInt("cars_id");
                if(carId > 0) {
                    Car car = new Car();
                    car.setId(carId);
                    car.setBrand(resultSet.getString("cars_name"));
                    car.setModel(resultSet.getString("cars_model"));
                    car.setYearFromTo(resultSet.getString("cars_yearfromto"));
                    product.getCars().add(car);
                }

                Integer catalogId = resultSet.getInt("catalogs_id");
                if(catalogId > 0) {
                    Catalog catalog = new Catalog(catalogId, resultSet.getString("catalogs_name"));
                    product.getCatalogs().add(catalog);
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    private static final class ProductExtractor implements ResultSetExtractor<Product> {
        @Override
        public Product extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Product product = null;

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                if(product == null){
                    product = new Product();
                    product.setId(id);
                    product.setName(resultSet.getString("name"));
                    product.setProducer(resultSet.getString("producer"));
                    product.setProductCode(resultSet.getString("productcode"));
                    product.setProducerProductCode(resultSet.getString("producerproductcode"));
                    product.setCars(new HashSet<Car>());
                    product.setCatalogs(new HashSet<Catalog>());
                }
                Integer carId = resultSet.getInt("cars_id");
                if(carId > 0) {
                    Car car = new Car();
                    car.setId(carId);
                    car.setBrand(resultSet.getString("cars_name"));
                    car.setModel(resultSet.getString("cars_model"));
                    car.setYearFromTo(resultSet.getString("cars_yearfromto"));
                    product.getCars().add(car);
                }

                Integer catalogId = resultSet.getInt("catalogs_id");
                if(catalogId > 0) {
                    Catalog catalog = new Catalog(catalogId, resultSet.getString("catalogs_name"));
                    product.getCatalogs().add(catalog);
                }
            }
            return product;
        }
    }
}
