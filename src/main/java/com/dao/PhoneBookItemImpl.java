package com.dao;

import com.entity.PhoneBookItem;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 15.04.2016.
 */

@Repository("PhoneBookItemDao")
public class PhoneBookItemImpl extends AbstractDao<Long, PhoneBookItem> implements PhoneBookItemDao {


    public void save(PhoneBookItem phoneBookItem) {
        persist(phoneBookItem);
    }

    public List<PhoneBookItem> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        return (List<PhoneBookItem>) criteria.list();
    }


    public PhoneBookItem findById(long id) {
        return getByKey(id);
    }


    public List<PhoneBookItem> findByName(String name, String login) {
        Criteria criteria = createEntityCriteria();

        criteria.createAlias("users", "users").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.and(  Restrictions.eq("name", name), Restrictions.eq("users.login", login)));
        return (List<PhoneBookItem>) criteria.list();
    }

    public List<PhoneBookItem> findBySurname(String surname,String login) {
        Criteria criteria = createEntityCriteria();

        criteria.createAlias("users", "users");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.and(  Restrictions.eq("surname", surname), Restrictions.eq("users.login", login)));
        return (List<PhoneBookItem>) criteria.list();
    }


    public List<PhoneBookItem> findByMobPhone(String mobPhone, String login) {


        Criteria criteria = createEntityCriteria();
        criteria.createAlias("users", "users");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.and(Restrictions.like("mobPhone", "%" + mobPhone + "%"), Restrictions.eq("users.login", login)));
//
        return (List<PhoneBookItem>) criteria.list();
    }


    public void delteById(long id) {
        PhoneBookItem phoneBookItem = getByKey(id);
        delete(phoneBookItem);
    }
}
