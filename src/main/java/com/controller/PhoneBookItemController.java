package com.controller;

import com.entity.PhoneBookItem;
import com.entity.User;
import com.service.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16.04.2016.
 */

@Controller
public class PhoneBookItemController {


    @Autowired
    ServiceManager serviceManager;


    @RequestMapping(value = {"/listAdmin"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        PhoneBookItem phoneBookItem = new PhoneBookItem();


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();

        List<PhoneBookItem> phoneBookItems = serviceManager.getPhoneBookItemService().findALlforUser(login);


//        User user = serviceManager.getUserService().findByLogin(login);
//        Set<PhoneBookItem> phoneBookItems = user.getPhoneBookItems();

        model.addAttribute("phoneBookItems", phoneBookItems);
        model.addAttribute("phoneBookItem", phoneBookItem);
        return "main";
    }


    @RequestMapping(value = "/addPhoneBookItem", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        PhoneBookItem phoneBookItem = new PhoneBookItem();
        model.addAttribute("phoneBookItem", phoneBookItem);
        return "addItem";
    }


    @RequestMapping(value = "/addPhoneBookItem", method = RequestMethod.POST)
    public String savedRegistration(@Valid PhoneBookItem phoneBookItem,
                                    BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "addItem";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        List<User> users = new ArrayList<>();
        users.add(serviceManager.getUserService().findByLogin(name));
        phoneBookItem.setUsers(users);
        serviceManager.getPhoneBookItemService().saveDocument(phoneBookItem);

        return "welcome";
    }


    @RequestMapping(value = {"/edit-phoneBook-{phoneBookItemId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable Long phoneBookItemId, ModelMap model) {
        PhoneBookItem phoneBookItem = serviceManager.getPhoneBookItemService().findById(phoneBookItemId);

        model.addAttribute("phoneBookItem", phoneBookItem);
        model.addAttribute("edit", true);
        return "addItem";
    }

    @RequestMapping(value = {"/edit-phoneBook-{phoneBookItemId}"}, method = RequestMethod.POST)
    public String updateBook(@Valid PhoneBookItem phoneBookItem, BindingResult result,
                             ModelMap model, @PathVariable Integer phoneBookItemId) {

        if (result.hasErrors()) {
            return "addItem";
        }
        serviceManager.getPhoneBookItemService().updatePhoneBookItem(phoneBookItem);
        return "redirect:/listAdmin";
    }


    @RequestMapping(value = {"/delete-phoneBook-{phoneBookItemId}"}, method = RequestMethod.GET)
    public String deleteBook(@PathVariable Long phoneBookItemId) {
        serviceManager.getPhoneBookItemService().deleteById(phoneBookItemId);
        return "redirect:/listAdmin";
    }


    @RequestMapping(value = {"/finPhoneBookItemByName"}, method = RequestMethod.POST)
    public String finPhoneBookItemByName(@ModelAttribute PhoneBookItem phoneBookItem, @RequestParam String name, ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        List<PhoneBookItem> phoneBookItems = serviceManager.getPhoneBookItemService().findByName(name, login);
        if (phoneBookItems.isEmpty()) {
            model.addAttribute("fail", "No phoneBookItem with name  " + name + " in DB ");
            return "main";
        }
        model.addAttribute("phoneBookItems", phoneBookItems);
        PhoneBookItem phoneBookItem1 = new PhoneBookItem();
        model.addAttribute("phoneBookItem", phoneBookItem1);
        return "main";
    }

    @RequestMapping(value = {"/finPhoneBookItemBySurname"}, method = RequestMethod.POST)
    public String finPhoneBookItemBySurname(@ModelAttribute PhoneBookItem phoneBookItem, @RequestParam String surname, ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        List<PhoneBookItem> phoneBookItems = serviceManager.getPhoneBookItemService().findBySurname(surname, login);
        if (phoneBookItems.isEmpty()) {
            model.addAttribute("fail", "No phoneBookItem with name  " + surname + " in DB ");
            return "main";
        }
        model.addAttribute("phoneBookItems", phoneBookItems);
        PhoneBookItem phoneBookItem1 = new PhoneBookItem();
        model.addAttribute("phoneBookItem", phoneBookItem1);
        return "main";
    }

    @RequestMapping(value = {"/finPhoneBookItemByMobPhone"}, method = RequestMethod.POST)
    public String finPhoneBookItemByMobPhone(@ModelAttribute PhoneBookItem phoneBookItem, @RequestParam String mobPhone, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();

        List<PhoneBookItem> phoneBookItems = serviceManager.getPhoneBookItemService().findByMobPhone(mobPhone, login);

        if (phoneBookItems.isEmpty()) {
            model.addAttribute("fail", "No phoneBookItem with name  " + mobPhone + " in DB ");
            return "main";
        }

        model.addAttribute("phoneBookItems", phoneBookItems);
        PhoneBookItem phoneBookItem1 = new PhoneBookItem();
        model.addAttribute("phoneBookItem", phoneBookItem1);

        return "main";
    }


}
