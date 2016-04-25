package com.dao;

import com.entity.User;

import java.util.List;

/**
 * Created by user on 15.04.2016.
 */
public interface UserDao {


    void save(User user);

    List<User> findAll();

    User findById(long id);

    User findByLogin(String login);

    void delteById(long id);
}
