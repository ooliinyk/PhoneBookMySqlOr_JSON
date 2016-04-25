package com.service.forJSON;


import com.entity.PhoneBookItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AbstractPhoneBookItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Service to work with phoneBookItem.json file
 */

@PropertySource(value = {"classpath:application.properties"})
@Service("PhoneBookItemDAO_JSON")

public class PhoneBookItemService implements AbstractPhoneBookItemService {


    //    private final static String baseFile = "temp/phoneBookItem.json" ;
    @Value("${my.PhoneBookItemPath}")
    private  String baseFile;


    public  void toJSON(List<PhoneBookItem> phoneBookItems) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(baseFile), phoneBookItems);

        System.out.println("json created!");
    }

    public  List<PhoneBookItem> toJavaObject() throws IOException {

        try {


            ObjectMapper mapper = new ObjectMapper();
            List<PhoneBookItem> phoneBookItems = mapper.readValue(new File(baseFile), new TypeReference<List<PhoneBookItem>>() {
            });

            return phoneBookItems;
        } catch (IOException e) {
            List<PhoneBookItem> phoneBookItems = new ArrayList<PhoneBookItem>();
            return phoneBookItems;
        }
    }


    public void saveDocument(PhoneBookItem phoneBookItem) {

        try {
            List<PhoneBookItem> phoneBookItems = toJavaObject();

            phoneBookItem.setId((long) (phoneBookItems.size()));
            phoneBookItems.add(phoneBookItem);

            toJSON(phoneBookItems);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<PhoneBookItem> findAll() {

        try {
            return toJavaObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PhoneBookItem findById(long id) {

        try {
            List<PhoneBookItem> phoneBookItems = toJavaObject();

            for (PhoneBookItem phoneBookItem : phoneBookItems) {

                if (phoneBookItem.getId() == id) {
                    return phoneBookItem;
                }

            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<PhoneBookItem> findALlforUser(String login) {

        try {

            List<PhoneBookItem> phoneBookItems = toJavaObject();
            if (phoneBookItems.size() == 0) {
                return null;
            }
            List<PhoneBookItem> phoneBookItemsFind = new ArrayList<PhoneBookItem>();

            for (PhoneBookItem phoneBookItem : phoneBookItems) {

                if (phoneBookItem.getUsers().get(0).getLogin().equals(login)) {
                    phoneBookItemsFind.add(phoneBookItem);
                }

            }
            return phoneBookItemsFind;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void deleteById(long id) {

        try {
            List<PhoneBookItem> phoneBookItems = toJavaObject();

            for (int i = 0; i < phoneBookItems.size(); i++) {
                if (phoneBookItems.get(i).getId() == id) {
                    phoneBookItems.remove(i);
                }
            }
            toJSON(phoneBookItems);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<PhoneBookItem> findByName(String name, String login) {

        try {
            List<PhoneBookItem> phoneBookItems = toJavaObject();

            List<PhoneBookItem> phoneBookItemsFind = new ArrayList<PhoneBookItem>();
            for (PhoneBookItem phoneBookItem : phoneBookItems) {

                if (phoneBookItem.getUsers().get(0).getLogin().equals(login) && phoneBookItem.getName().equals(name)) {
                    phoneBookItemsFind.add(phoneBookItem);
                }

            }
            return phoneBookItemsFind;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public List<PhoneBookItem> findBySurname(String surname, String login) {

        try {

            List<PhoneBookItem> phoneBookItems = toJavaObject();

            List<PhoneBookItem> phoneBookItemsFind = new ArrayList<PhoneBookItem>();
            for (PhoneBookItem phoneBookItem : phoneBookItems) {

                if (phoneBookItem.getUsers().get(0).getLogin().equals(login) && (phoneBookItem.getSurname().equals(surname))) {
                    phoneBookItemsFind.add(phoneBookItem);
                }

            }
            return phoneBookItemsFind;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<PhoneBookItem> findByMobPhone(String mobPhone, String login) {

        try {
            List<PhoneBookItem> phoneBookItems = toJavaObject();

            List<PhoneBookItem> phoneBookItemsFind = new ArrayList<PhoneBookItem>();
            for (PhoneBookItem phoneBookItem : phoneBookItems) {

                if (phoneBookItem.getUsers().get(0).getLogin().equals(login) && phoneBookItem.getMobPhone().indexOf(mobPhone) != -1) {
                    phoneBookItemsFind.add(phoneBookItem);
                }


            }
            return phoneBookItemsFind;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    public void updatePhoneBookItem(PhoneBookItem phoneBookItem) {


        try {
            List<PhoneBookItem> phoneBookItems = findAll();
            for (PhoneBookItem entity : phoneBookItems) {

                if (phoneBookItem.getId() == entity.getId()) {
                    entity.setName(phoneBookItem.getName());
                    entity.setSurname(phoneBookItem.getSurname());
                    entity.setPatronymic(phoneBookItem.getPatronymic());
                    entity.setEmail(phoneBookItem.getEmail());
                    entity.setAddress(phoneBookItem.getAddress());
                    entity.setMobPhone(phoneBookItem.getMobPhone());
                    entity.setHomePhone(phoneBookItem.getHomePhone());
                }


            }
            toJSON(phoneBookItems);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
