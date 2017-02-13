package by.beregeiko.tuningshop.dao.impl.jdbc;

import by.beregeiko.tuningshop.dao.CarDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 23.12.2016.
 */
public class CarDaoJbdcImpl implements CarDao {
    private static final String JDBC_URL =
            "jdbc:postgresql://127.0.0.1:5432/tuningshop?user=postgres&password=";
    private static final String SELECT_CAR_BY_ID_SQL = "SELECT * FROM cars WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM cars";

    @Override
    public Car selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_CAR_BY_ID_SQL);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(!resultSet.next()){
                throw new NoSuchEntityException("No product for id = " + id);
            }
            Car result = new Car(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("model"), resultSet.getString("yearfromto"));
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
    public List<Car> selectAll() throws DaoSystemException {
        Connection connection = null;
        Statement statement= null;
        ResultSet resultSet = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SQL);
            ArrayList<Car> result = new ArrayList<>();
            while (resultSet.next()){
                result.add(new Car(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("model"), resultSet.getString("yearfromto")));
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
