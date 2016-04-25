package com.dao;

import com.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 15.04.2016.
 */

@Repository("UserDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    public void save(User user) {
        persist(user);
    }

    public List<User> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        return (List<User>)criteria.list();
    }

    public User findById(long id) {
        return getByKey(id);
    }

    public void delteById(long id) {
        User user = getByKey(id);
        delete(user);
    }

    public User findByLogin(String login) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("login", login));
        return (User) crit.uniqueResult();
    }

//    public User findMobPhone(String login, String mobPhone) {
//        Criteria crit = createEntityCriteria();
//        crit.add(Restrictions.eq("login", login));
//        return (User) crit.uniqueResult();
//    }
}
