package com.service.forMySql;

import com.dao.UserDao;
import com.entity.User;
import com.service.AbstractUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by user on 16.04.2016.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements AbstractUserService, UserService {

    @Autowired
    UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    public User findById(long id) {
        return dao.findById(id);
    }

    public User findByLogin(String login) {
        return dao.findByLogin(login);
    }

    public boolean isUserLoginUnique(Long id, String login) {
        User user = findByLogin(login);

        return (user == null || ((id != null) && (user.getId() == id)));

    }
}
