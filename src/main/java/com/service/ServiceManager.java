package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Service("ServiceManager")

public class ServiceManager {



    @Autowired
    @Qualifier("userService")
    private AbstractUserService abstractUserService;
    @Autowired
    @Qualifier("RoleService")
    private AbstractRoleService abstractRoleService;
    @Autowired
    @Qualifier("PhoneBookItemService")
    private AbstractPhoneBookItemService abstractPhoneBookItemService;


    @Autowired
    @Qualifier("UserServiceJSON")
    private AbstractUserService abstractUserServiceJSON;
    @Autowired
    @Qualifier("RoleServiceJSON")
    private AbstractRoleService abstractRoleServiceJSON;
    @Autowired
    @Qualifier("PhoneBookItemDAO_JSON")
    private AbstractPhoneBookItemService abstractPhoneBookItemServiceJSON;

    @Value("${my.switchDB}")
    private int SWITCHER ;


    public AbstractPhoneBookItemService getPhoneBookItemService() {
        switch (SWITCHER) {
            case 1:
                return abstractPhoneBookItemService;
            case 2:
                return abstractPhoneBookItemServiceJSON;
            default:
                throw new IllegalStateException();
        }

    }

    public AbstractRoleService getRoleService() {
        switch (SWITCHER) {
            case 1:
                return abstractRoleService;
            case 2:
                return abstractRoleServiceJSON;
            default:
                throw new IllegalStateException();
        }

    }

    public AbstractUserService getUserService() {

        switch (SWITCHER) {
            case 1:
                return abstractUserService;
            case 2:
//
                return abstractUserServiceJSON;
            default:
                throw new IllegalStateException();
        }

    }
}
