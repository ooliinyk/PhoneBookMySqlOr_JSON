package com.service;

import com.entity.User;

/**
 * Created by user on 24.04.2016.
 */

public interface AbstractUserService {

    void save(User user);

    User findById(long id);

    User findByLogin(String login);

    boolean isUserLoginUnique(Long id, String login);
}
