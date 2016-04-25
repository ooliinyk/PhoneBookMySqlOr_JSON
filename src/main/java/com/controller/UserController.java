package com.controller;

import com.entity.Role;
import com.entity.User;
import com.service.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by user on 16.04.2016.
 */
@Controller
public class UserController{

    @Autowired
    ServiceManager serviceManager;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String savedRegistration(@Valid User user,
                                    BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "registration";
        }

        Set<Role> set = new HashSet<Role>();

        set.add(serviceManager.getRoleService().findByName("USER"));
        user.setRoles(set);

        if (!serviceManager.getUserService().isUserLoginUnique(user.getId(), user.getLogin())) {
            FieldError ssnError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
                    new String[]{user.getLogin()}, Locale.getDefault()));
            result.addError(ssnError);
            return "registration";
        }

        serviceManager.getUserService().save(user);

        model.addAttribute("success", "User " + user.getName() + " has been registered successfully");
        return "welcome";
    }

}
