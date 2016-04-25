package com.service;

import com.entity.Role;

import java.util.List;

/**
 * Created by user on 24.04.2016.
 */


public interface AbstractRoleService {

    List<Role> findAll();

    Role findByName(String type);

    Role findById(long id);
}
