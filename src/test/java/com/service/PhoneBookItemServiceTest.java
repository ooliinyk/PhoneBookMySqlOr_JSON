package com.service;

import com.AbstractTest;
import com.entity.PhoneBookItem;

import com.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test methods for the GreetingService and GreetingServiceBean.
 *
 * @author Matt Warman
 */
@Transactional
public class PhoneBookItemServiceTest extends AbstractTest {

    @Autowired
    private ServiceManager service;


    @Test
    public void testFindAll() {

        Collection<PhoneBookItem> list = service.getPhoneBookItemService().findAll();

        Assert.assertNotNull("failure - expected not null", list);
        assertEquals("failure - expected list size", 2, list.size());

    }

    @Test
    public void testFindById() {

        Long id = new Long(1);

        PhoneBookItem entity = service.getPhoneBookItemService().findById(id);

        Assert.assertNotNull("failure - expected not null", entity);
        assertEquals("failure= expected phoneBookItem", (Long) id, (Long) entity.getId());
    }

    @Test
    public void testFindByIdNotFound() {

        Long id = Long.MAX_VALUE;

        PhoneBookItem entity = service.getPhoneBookItemService().findById(id);

        Assert.assertNull("failure - expected null", entity);

    }

    @Test
    public void testFindByName() {

        String name="user1";
        String login="user1";

        List<PhoneBookItem> phoneBookItemLIst = service.getPhoneBookItemService().findByName(name, login);


        Assert.assertNotNull("failure - expected not null", phoneBookItemLIst);
        assertEquals("failure - expected list size", 1, phoneBookItemLIst.size());
    }

    @Test
    public void testFindBySurname() {

        String surname="user1";
        String login="user1";

        List<PhoneBookItem> phoneBookItemLIst = service.getPhoneBookItemService().findBySurname(surname, login);


        Assert.assertNotNull("failure - expected not null", phoneBookItemLIst);
        assertEquals("failure - expected list size", 1, phoneBookItemLIst.size());
    }

    @Test
    public void testFindByMobPhone() {

        String mob="93";
        String login="user1";

        List<PhoneBookItem> phoneBookItemLIst = service.getPhoneBookItemService().findByMobPhone(mob,login);


        Assert.assertNotNull("failure - expected not null", phoneBookItemLIst);
        assertEquals("failure - expected list size", 1, phoneBookItemLIst.size());
    }



    @Test
    public void testFindByNameExpectedNull() {

        String name="user1";
        String login="noUserLogin;";

        List<PhoneBookItem> phoneBookItemLIst = service.getPhoneBookItemService().findByName(name, login);


        Assert.assertNotNull("failure - expected not null", phoneBookItemLIst);
        assertEquals("failure - expected list size", 0, phoneBookItemLIst.size());
    }

    @Test
    public void testFindBySurnameExpectedNull() {

        String surname="user1";
        String login="noUserLogin";

        List<PhoneBookItem> phoneBookItemLIst = service.getPhoneBookItemService().findBySurname(surname, login);


        Assert.assertNotNull("failure - expected not null", phoneBookItemLIst);
        assertEquals("failure - expected list size", 0, phoneBookItemLIst.size());
    }

    @Test
    public void testFindByMobPhoneExpectedNull() {

        String mob="1";
        String login="noUserLogin";

        List<PhoneBookItem> phoneBookItemLIst = service.getPhoneBookItemService().findByMobPhone(mob, login);


        Assert.assertNotNull("failure - expected not null", phoneBookItemLIst);
        assertEquals("failure - expected list size", 0, phoneBookItemLIst.size());
    }


    @Test
    public void testCreate() {

        PhoneBookItem entity = new PhoneBookItem();
        entity.setName("ALEXX");
        entity.setEmail("my@email");
        entity.setSurname("asdasd");
        entity.setMobPhone("1234567890");
        entity.setPatronymic("asdasdasdasd");
        List<User> list=new ArrayList<>();
        list.add(service.getUserService().findByLogin("user1"));
        entity.setUsers(list);



       service.getPhoneBookItemService().saveDocument(entity);
       List<PhoneBookItem> list1 = service.getPhoneBookItemService().findByName("ALEXX","user1");


    }


    @Test
    public void testUpdate() {


        PhoneBookItem entity = new PhoneBookItem();
        entity.setId(1);
        entity.setName("ALEXX");
        entity.setEmail("my@email");
        entity.setSurname("asdasd");
        entity.setMobPhone("1234567890");
        entity.setPatronymic("asdasdasdasd");

        service.getPhoneBookItemService().updatePhoneBookItem(entity);

    }

    @Test
    public void testDelete() {

        Long id = new Long(1);

   service.getPhoneBookItemService().deleteById(id);

    }

}
