package com.service.forJSON;


import com.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AbstractUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to work with user.json file
 */

@PropertySource(value = {"classpath:application.properties"})
@Service("UserServiceJSON")
public class UserService implements AbstractUserService {

    @Value("${my.UserPath}")
    private   String baseFile;

//    private  static String baseFile = "C:/Users/user/Desktop/PhoneBookMySqlOr_JSON/target/classes/static/temp/user.json";

    public  void toJSON(List<User> users) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(baseFile), users);

        System.out.println("json created!");
    }

    public  List<User> toJavaObject() throws IOException {

        try {


            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue(new File(baseFile), new TypeReference<List<User>>() {
            });
            return users;
        } catch (IOException e) {
            List<User> users = new ArrayList<User>();
            return users;
        }

    }


    public void save(User user) {

        try {
            List<User> users = toJavaObject();

            user.setId(1+users.size());
            users.add(user);
            toJSON(users);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<User> findAll() throws IOException {

        try {

            return toJavaObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public User findById(long id) {

        try {
            List<User> users = toJavaObject();
            for (User user : users) {
                if (user.getId() == id) {
                    return user;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findByLogin(String login) {

        try {

            List<User> users = toJavaObject();

            for (User user : users) {

                if (user.getLogin().equals(login)) {
                    return user;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delteById(long id) {

        try {

            List<User> users = toJavaObject();

            for (int i = 0; i < users.size(); i++) {

                if (users.get(i).getId() == id) {
                    users.remove(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public boolean isUserLoginUnique(Long id, String login) {

        User user = findByLogin(login);
        return (user == null || ((id != null) && (user.getId() == id)));

    }


}
