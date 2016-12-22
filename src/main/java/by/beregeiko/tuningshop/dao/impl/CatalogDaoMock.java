package by.beregeiko.tuningshop.dao.impl;

import by.beregeiko.tuningshop.dao.CatalogDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Think on 14.12.2016.
 */
public class CatalogDaoMock implements CatalogDao {
    private final Map<Integer, Catalog> memory = new ConcurrentHashMap<>();

    public CatalogDaoMock() {
        this.memory.put(1, new Catalog(1, "Bamper"));
        this.memory.put(2, new Catalog(2, "Tuning Bamper"));
        this.memory.put(3, new Catalog(3, "Standart Bamper"));

        this.memory.put(4, new Catalog(4, "Spoiler"));
        this.memory.put(5, new Catalog(5, "Tuning Spoiler"));
        this.memory.put(6, new Catalog(6, "Standart Spoiler"));

    }

    @Override
    public Catalog selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Catalog for id == '" + id + "', only " + memory.keySet());
        }
        return memory.get(id);
    }

    @Override
    public List<Catalog> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}
