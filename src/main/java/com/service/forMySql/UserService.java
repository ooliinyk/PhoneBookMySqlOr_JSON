package com.service.forMySql;

import com.entity.User;

/**
 * Created by user on 16.04.2016.
 */
public interface UserService {
    void save(User user);

    User findById(long id);

    User findByLogin(String login);

    boolean isUserLoginUnique(Long id, String login) ;

}
