package by.beregeiko.tuningshop.dao.impl.spring_jdbc;

import by.beregeiko.tuningshop.dao.CatalogDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Catalog;
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
//@Repository("catalogDao")
public class CatalogDaoSpringJdbcImpl implements CatalogDao, InitializingBean {
    private static final String SELECT_ALL_SQL = "SELECT * FROM catalogs";

    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Catalog selectById(int id) throws DaoSystemException, NoSuchEntityException {
        String sql = SELECT_ALL_SQL + " WHERE id = :catalogId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("catalogId", id);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new CatalogMapper());
    }

    @Override
    public List<Catalog> selectAll() throws DaoSystemException {
        return namedParameterJdbcTemplate.query(SELECT_ALL_SQL, new CatalogMapper());

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on CatalogDao");
        }

        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("Null NamedParameterJdbcTemplate on CatalogDao");
        }
    }

    private class CatalogMapper implements RowMapper<Catalog> {
        @Override
        public Catalog mapRow(ResultSet resultSet, int i) throws SQLException {
            Catalog catalog = new Catalog(resultSet.getInt("id"), resultSet.getString("name"));
            return catalog;
        }
    }
}
