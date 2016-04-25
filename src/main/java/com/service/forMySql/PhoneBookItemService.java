package com.service.forMySql;

import com.entity.PhoneBookItem;

import java.util.List;

/**
 * Created by user on 15.04.2016.
 */
public interface PhoneBookItemService {

    PhoneBookItem findById(long id);

    List<PhoneBookItem> findAll();

    List<PhoneBookItem> findByName(String name, String login);

    List<PhoneBookItem> findBySurname(String name, String login);

    List<PhoneBookItem> findByMobPhone(String mobPhone, String login);

    void saveDocument(PhoneBookItem phoneBookItem);

    void deleteById(long id);

    void updatePhoneBookItem(PhoneBookItem phoneBookItem);
}
