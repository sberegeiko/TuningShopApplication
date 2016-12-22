package by.beregeiko.tuningshop.dao;

import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Catalog;

import java.util.List;

/**
 * Created by Think on 14.12.2016.
 */
public interface CatalogDao {
    public Catalog selectById(int id) throws DaoSystemException, NoSuchEntityException;
    public List<Catalog> selectAll() throws DaoSystemException;
}
