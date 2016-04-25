package com.service;

import com.AbstractTest;
import com.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class UserServiceTest extends AbstractTest {

    @Autowired
    private ServiceManager service;

    @Test
    public  void saveTest(){
        User user= new User();
        user.setName("Alexx");
        user.setSurname("lasdasd");
        user.setPatronymic("dasdasd");
        user.setLogin("asdasd");
        user.setPassword("dsadasd");
        service.getUserService().save(user);

    }

    @Test
    public  void findByIdTest(){
        Long id = new Long(1);
        User user=service.getUserService().findById(id);

        Assert.assertNotNull("failure - expected not null", user);

    }
    @Test
    public  void findByLoginTest(){
        String login="user1";

        User user=service.getUserService().findByLogin(login);
        Assert.assertNotNull("failure - expected not null", user);
    }
    @Test
    public  void isUserLoginUniqueTest(){
        Long id = new Long(5);
        String login="user11";
        boolean check =service.getUserService().isUserLoginUnique(id, login);
        Assert.assertTrue("failure - expected not true", check);


    }

    @Test
    public  void FalseisUserLoginUniqueTest(){
        Long id = new Long(2);
        String login="user1";
        boolean check =service.getUserService().isUserLoginUnique(id,login);
        Assert.assertFalse("failure - expected not true", check);


    }

}
