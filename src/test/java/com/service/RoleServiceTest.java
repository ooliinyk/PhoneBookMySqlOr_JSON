package com.service;

import com.AbstractTest;
import com.entity.Role;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 25.04.2016.
 */
@Transactional
public class RoleServiceTest extends AbstractTest {

    @Autowired
    private ServiceManager service;

    @Test
    public void testFindByName() {

        String name = "USER";


        Role role = service.getRoleService().findByName(name);


        Assert.assertNotNull("failure - expected not null", role);
    }

    public void testFindByID() {

        Long id = new Long(1);


        Role role = service.getRoleService().findById(id);


        Assert.assertNotNull("failure - expected not null", role);
    }
    @Test
    public void testFindByIdNotFound() {

        Long id = Long.MAX_VALUE;

        Role entity = service.getRoleService().findById(id);

        Assert.assertNull("failure - expected null", entity);

    }

    @Test
    public void testFindAll() {

        Collection<Role> list = service.getRoleService().findAll();

        Assert.assertNotNull("failure - expected not null", list);
        assertEquals("failure - expected list size", 1, list.size());

    }
}
