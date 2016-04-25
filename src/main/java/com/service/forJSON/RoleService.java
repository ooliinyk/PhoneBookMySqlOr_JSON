package com.service.forJSON;


import com.entity.Role;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AbstractRoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Service to work with role.json file
 */

@PropertySource(value = {"classpath:application.properties"})
@Service("RoleServiceJSON")
public class RoleService implements AbstractRoleService {

    @Value("${my.RolePath}")
    private   String baseFile ;

    public void toJSON(List<Role> roles) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(baseFile), roles);

        System.out.println("json created!");
    }

    public List<Role> toJavaObject() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Role> roles = mapper.readValue(new File(baseFile), new TypeReference<List<Role>>() {
            });


            return roles;
        } catch (IOException e) {
            List<Role> roles = new ArrayList<Role>();
            return roles;
        }

    }


    public void save(Role role) throws IOException {


        List<Role> roles = toJavaObject();

        for (Role role1 : roles) {

            if (role1.getId() == role.getId()) {
                break;
            }

        }
        roles.add(role);
        toJSON(roles);


    }

    public List<Role> findAll() {


        return toJavaObject();
    }

    public Role findById(long id) {


        List<Role> roles = toJavaObject();

        for (Role role : roles) {

            if (role.getId() == id) {
                return role;
            }

        }
        return null;
    }

    public Role findByName(String name) {
        List<Role> roles = toJavaObject();

        for (Role role : roles) {

            if (role.getRoleName().equals(name)) {

                     return role;
            }

        }
        return null;
    }

    public void delteById(long id) throws IOException {
        List<Role> roles = toJavaObject();

        for (int i = 0; i < roles.size(); i++) {

            if (roles.get(i).getId() == id) {
                roles.remove(i);
            }
        }

        toJSON(roles);
    }
}
