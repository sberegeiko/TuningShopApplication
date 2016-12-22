package by.beregeiko.tuningshop.dao;

import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Car;

import java.util.List;

/**
 * Created by Think on 14.12.2016.
 */
public interface CarDao {
    public Car selectById(int id) throws DaoSystemException, NoSuchEntityException;
    public List<Car> selectAll() throws DaoSystemException;
}
