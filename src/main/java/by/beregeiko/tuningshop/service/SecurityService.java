package by.beregeiko.tuningshop.service;

/**
 * Created by Think on 06.04.2017.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}