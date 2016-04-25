package com.service;

import com.entity.PhoneBookItem;

import java.util.List;

/**
 * Created by user on 24.04.2016.
 */

/**
 * interface to work with PhoneBookItem entity
 */

//@Service("AbstractPhoneBookItemService")
public interface  AbstractPhoneBookItemService {

    /**
     * this method:search PhoneBookItem by ID
     */
    public PhoneBookItem findById(long id);

    /**
     * this method: search  for all PhoneBookItems
     */
    public   List<PhoneBookItem> findAll();

    /**
     * this method: search  for all PhoneBookItems with special parameters
     */
    public    List<PhoneBookItem> findByName(String name, String login);

    public   List<PhoneBookItem> findBySurname(String name, String login);

    public   List<PhoneBookItem> findByMobPhone(String mobPhone, String login);

    /**
     * this method: save PhoneBookItem to DB
     */
    public   void saveDocument(PhoneBookItem phoneBookItem);
    /**
     * this method: delete PhoneBookItem from DB
     */
    public   void deleteById(long id);
    /**
     * this method: update PhoneBookItem in DB
     */
    public   void updatePhoneBookItem(PhoneBookItem phoneBookItem);
    /**
     * this method: find all PhoneBookItems from DB for user with this login
     */
    public List<PhoneBookItem> findALlforUser(String login);


}
