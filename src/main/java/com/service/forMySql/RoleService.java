package com.service.forMySql;



import com.entity.Role;

import java.util.List;

/**
 * Created by user on 17.03.2016.
 */
public interface RoleService {

    List<Role> findAll();

    Role findByName(String type);

    Role findById(long id);
}
