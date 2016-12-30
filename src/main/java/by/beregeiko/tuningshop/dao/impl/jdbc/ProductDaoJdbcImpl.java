package by.beregeiko.tuningshop.dao.impl.jdbc;

import by.beregeiko.tuningshop.dao.ProductDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.entity.Catalog;
import by.beregeiko.tuningshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Think on 23.12.2016.
 */
public class ProductDaoJdbcImpl implements ProductDao {
    private static final String JDBC_URL =
            "jdbc:postgresql://127.0.0.1:5432/tuningshop?user=postgres&password=postgres";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM products WHERE id = ?";
    private static final String SELECT_CARS_BY_PRODUCT_ID_SQL = "SELECT cars.id, cars.name, cars.model, cars.yearfromto FROM product_cars INNER JOIN cars ON (car_id = cars.id) WHERE product_cars.product_id = ?";
    private static final String SELECT_CATALOGS_BY_PRODUCT_ID_SQL = "SELECT catalogs.id, catalogs.name FROM product_catalogs INNER JOIN catalogs ON (catalog_id = catalogs.id) WHERE product_catalogs.product_id = ?";
    private static final String SELECT_PRODUCT_BY_CAR_ID_SQL = "SELECT products.id, products.name, products.producer, products.productcode, products.producerproductcode FROM product_cars INNER JOIN products ON (product_id = products.id) WHERE product_cars.car_id = ?";
    private static final String SELECT_PRODUCT_BY_CATALOG_ID_SQL = "SELECT products.id, products.name, products.producer, products.productcode, products.producerproductcode FROM product_catalogs INNER JOIN products ON (product_id = products.id) WHERE product_catalogs.catalog_id = ?";
    private static final String SELECT_PRODUCT_BY_CAR_AND_CATALOG_ID_SQL = "SELECT products.id, products.name, products.producer, products.productcode, products.producerproductcode FROM products\n INNER JOIN product_catalogs ON (product_id = products.id) INNER JOIN product_cars ON (product_catalogs.product_id = product_cars.product_id) WHERE car_id = ? AND catalog_id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM products";

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new NoSuchEntityException("No product for id = " + id);
            }
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setProducer(resultSet.getString("producer"));
            product.setProductCode(resultSet.getString("productcode"));
            product.setProducerProductCode(resultSet.getString("producerproductcode"));
            product.setCars(selectCarsByProduct(connection, product));
            product.setCatalogs(selectCatalogsByProduct(connection, product));

            connection.commit();
            return product;
        } catch (SQLException | ClassNotFoundException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet, statement, connection);
        }
    }

    @Override
    public List<Product> selectByCarId(int id) throws DaoSystemException, NoSuchEntityException {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_PRODUCT_BY_CAR_ID_SQL);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setProducer(resultSet.getString("producer"));
                product.setProductCode(resultSet.getString("productcode"));
                product.setProducerProductCode(resultSet.getString("producerproductcode"));
                product.setCars(selectCarsByProduct(connection, product));
                product.setCatalogs(selectCatalogsByProduct(connection, product));
                productList.add(product);
            }

            connection.commit();
            return productList;
        } catch (SQLException | ClassNotFoundException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet, statement, connection);
        }
    }

    @Override
    public List<Product> selectByCatalogId(int id) throws DaoSystemException, NoSuchEntityException {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_PRODUCT_BY_CATALOG_ID_SQL);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setProducer(resultSet.getString("producer"));
                product.setProductCode(resultSet.getString("productcode"));
                product.setProducerProductCode(resultSet.getString("producerproductcode"));
                product.setCars(selectCarsByProduct(connection, product));
                product.setCatalogs(selectCatalogsByProduct(connection, product));
                productList.add(product);
            }

            connection.commit();
            return productList;
        } catch (SQLException | ClassNotFoundException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet, statement, connection);
        }
    }

    @Override
    public Set<Product> selectByCarAndCatalogId(int carId, int catalogId) throws DaoSystemException, NoSuchEntityException {
        Set<Product> productList = new HashSet<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_PRODUCT_BY_CAR_AND_CATALOG_ID_SQL);
            statement.setInt(1, carId);
            statement.setInt(2, catalogId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setProducer(resultSet.getString("producer"));
                product.setProductCode(resultSet.getString("productcode"));
                product.setProducerProductCode(resultSet.getString("producerproductcode"));
                product.setCars(selectCarsByProduct(connection, product));
                product.setCatalogs(selectCatalogsByProduct(connection, product));
                productList.add(product);
            }

            connection.commit();
            return productList;
        } catch (SQLException | ClassNotFoundException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet, statement, connection);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_ALL_SQL);
            resultSet = statement.executeQuery();
            ArrayList<Product> result = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setProducer(resultSet.getString("producer"));
                product.setProductCode(resultSet.getString("productcode"));
                product.setProducerProductCode(resultSet.getString("producerproductcode"));
                product.setCars(selectCarsByProduct(connection, product));
                product.setCatalogs(selectCatalogsByProduct(connection, product));
                result.add(product);
            }

            connection.commit();
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet, statement, connection);
        }
    }

    private List<Car> selectCarsByProduct(Connection connection, Product product) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CARS_BY_PRODUCT_ID_SQL)){
            statement.setInt(1, product.getId());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("model"), resultSet.getString("yearfromto")));
            }
            return cars;
        }
    }

    private List<Catalog> selectCatalogsByProduct (Connection connection, Product product) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(SELECT_CATALOGS_BY_PRODUCT_ID_SQL)) {

            statement.setInt(1, product.getId());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Catalog> catalogs = new ArrayList<>();
            while (resultSet.next()) {
                catalogs.add(new Catalog(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return catalogs;
        }
    }

}
