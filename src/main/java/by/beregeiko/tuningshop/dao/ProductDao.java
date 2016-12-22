package by.beregeiko.tuningshop.dao;

import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Product;

import java.util.List;
import java.util.Set;

/**
 * Created by Think on 09.12.2016.
 */
public interface ProductDao {
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException;
    public List<Product> selectByCarId(int id) throws DaoSystemException, NoSuchEntityException;
    public List<Product> selectByCatalogId(int id) throws DaoSystemException, NoSuchEntityException;
    public Set<Product> selectByCarAndCatalogId(int carId, int catalogId) throws DaoSystemException, NoSuchEntityException;
    public List<Product> selectAll() throws DaoSystemException;
}
