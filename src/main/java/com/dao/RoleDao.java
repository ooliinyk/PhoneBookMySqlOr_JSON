package com.dao;



import com.entity.Role;

import java.util.List;

/**
 * Created by user on 17.03.2016.
 */
public interface RoleDao {

    List<Role> findAll();

    Role findByRoleName(String roleName);

    Role findById(long id);
}
