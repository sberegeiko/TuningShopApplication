package by.beregeiko.tuningshop.dao.impl.jdbc;

import by.beregeiko.tuningshop.dao.CatalogDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Catalog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 23.12.2016.
 */
public class CatalogDaoJdbcImpl implements CatalogDao {
    private static final String JDBC_URL =
            "jdbc:postgresql://127.0.0.1:5432/tuningshop?user=postgres&password=postgres";
    private static final String SELECT_CATALOG_BY_ID_SQL = "SELECT * FROM catalogs WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM catalogs";

    @Override
    public Catalog selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_CATALOG_BY_ID_SQL);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(!resultSet.next()){
                throw new NoSuchEntityException("No product for id = " + id);
            }
            Catalog result = new Catalog(resultSet.getInt("id"), resultSet.getString("name"));
            connection.commit();
            return result;
        } catch(SQLException | ClassNotFoundException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet, statement, connection);
        }
    }

    @Override
    public List<Catalog> selectAll() throws DaoSystemException {
        Connection connection = null;
        Statement statement= null;
        ResultSet resultSet = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SQL);
            ArrayList<Catalog> result = new ArrayList<>();
            while (resultSet.next()){
                result.add(new Catalog(resultSet.getInt("id"), resultSet.getString("name")));
            }
            connection.commit();
            return result;
        } catch(SQLException | ClassNotFoundException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet, statement, connection);
        }
    }
}
