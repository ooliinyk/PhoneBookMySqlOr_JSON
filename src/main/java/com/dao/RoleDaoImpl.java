package com.dao;


import com.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 17.03.2016.
 */
@Repository("RoleDao")
public class RoleDaoImpl extends AbstractDao<Long, Role> implements RoleDao {


    public List<Role> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("roleName"));
        return (List<Role>)criteria.list();
    }

    public Role findByRoleName(String roleName) {
        Criteria criteria = createEntityCriteria();
        criteria.add((Restrictions.eq("roleName", roleName)));
    return (Role) criteria.uniqueResult();
    }

    public Role findById(long id) {
       return getByKey(id);
    }
}
