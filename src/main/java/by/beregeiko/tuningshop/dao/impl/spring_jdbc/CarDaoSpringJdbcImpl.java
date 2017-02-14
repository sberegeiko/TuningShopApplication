package by.beregeiko.tuningshop.dao.impl.spring_jdbc;

import by.beregeiko.tuningshop.dao.CarDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Car;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Think on 13.02.2017.
 */
//@Repository("carDao")
public class CarDaoSpringJdbcImpl implements CarDao, InitializingBean {
    private static final String SELECT_ALL_SQL = "SELECT * FROM cars";

    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Car selectById(int id) throws DaoSystemException, NoSuchEntityException {
        String sql = SELECT_ALL_SQL + " WHERE id = :carId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("carId", id);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new CarMapper());
    }

    @Override
    public List<Car> selectAll() throws DaoSystemException {
        return namedParameterJdbcTemplate.query(SELECT_ALL_SQL, new CarMapper());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on CarDao");
        }

        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("Null NamedParameterJdbcTemplate on CarDao");
        }
    }

    private class CarMapper implements RowMapper<Car> {
        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setId(resultSet.getInt("id"));
            car.setBrand(resultSet.getString("name"));
            car.setModel(resultSet.getString("model"));
            car.setYearFromTo(resultSet.getString("yearfromto"));
            return car;
        }
    }
}
