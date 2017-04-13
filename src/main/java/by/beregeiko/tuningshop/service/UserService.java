package by.beregeiko.tuningshop.service;

import by.beregeiko.tuningshop.entity.User;

import java.util.List;

/**
 * Created by Think on 05.04.2017.
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);

    List<User> findAllUsers();
    User findUserById(int id);

    void delete(User user);
}
