package by.beregeiko.tuningshop.dao;

import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.User;

/**
 * Created by Think on 09.12.2016.
 */
public interface UserDao {
    public User selectByLogin(String login) throws DaoSystemException, NoSuchEntityException;
}
