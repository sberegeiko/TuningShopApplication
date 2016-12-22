package by.beregeiko.tuningshop.dao.impl;

import by.beregeiko.tuningshop.dao.CarDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Think on 14.12.2016.
 */
public class CarDaoMock implements CarDao {
    private final Map<Integer, Car> memory = new ConcurrentHashMap<>();

    public CarDaoMock() {
        memory.put(1, new Car(1, "Audi", "A6", "2005-2009"));
        memory.put(2, new Car(2, "Audi", "A7", "2005-2009"));
        memory.put(3, new Car(3, "BMW", "3", "2007-2010"));
    }

    @Override
    public Car selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if(!memory.containsKey(id)){
            throw new NoSuchEntityException("No CAR for id == '" + id + "', only " + memory.keySet());
        }
        return memory.get(id);
    }

    @Override
    public List<Car> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}
