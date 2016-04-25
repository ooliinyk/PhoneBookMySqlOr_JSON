package com.service.forMySql;


import com.dao.RoleDao;
import com.entity.Role;
import com.service.AbstractRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 17.03.2016.
 */

@Service("RoleService")
@Transactional
public class RoleServiceImpl implements AbstractRoleService,RoleService {

    @Autowired
    RoleDao dao;

    public List<Role> findAll() {
        return dao.findAll();
    }

    public Role findByName(String type){
        return dao.findByRoleName(type);
    }

    public Role findById(long id) {
        return dao.findById(id);
    }
}
