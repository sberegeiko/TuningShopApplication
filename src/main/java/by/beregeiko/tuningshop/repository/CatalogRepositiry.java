package by.beregeiko.tuningshop.repository;

import by.beregeiko.tuningshop.entity.Catalog;

import java.util.List;

/**
 * Created by Think on 23.02.2017.
 */
public interface CatalogRepositiry {
    Catalog findById(int id);
    List<Catalog> findAll();
}
