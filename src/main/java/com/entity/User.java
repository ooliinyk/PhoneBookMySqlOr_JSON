package com.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("user_id")
    @Column(name = "user_id")
    private long id;

    @Size(min = 3)
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z\\d\\s]*$", message = "{only English characters allowed, without special characters}")
    @JsonProperty("login")
    @Column(name = "login")
    private String login;

    @Size(min = 5)
    @NotEmpty
    @JsonProperty("password")
    @Column(name = "password")
    private String password;

    @Size(min = 5)
    @NotEmpty
    @JsonProperty("surname")
    @Column(name = "surname")
    private String surname;

    @Size(min = 5)
    @NotEmpty
    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @Size(min = 5)
    @NotEmpty
    @JsonProperty("patronymic")
    @Column(name = "patronymic")
    private String patronymic;

    @JsonProperty("roles")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<Role>();


    @JsonProperty("phoneBookItems")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_to_phone_book_item",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "phone_book_item_id")})
    private List<PhoneBookItem> phoneBookItems = new ArrayList<PhoneBookItem>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public List<PhoneBookItem> getPhoneBookItems() {
        return phoneBookItems;
    }

    public void setPhoneBookItems(List<PhoneBookItem> phoneBookItems) {
        this.phoneBookItems = phoneBookItems;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("User{");
//        sb.append("id=").append(id);
//        sb.append(", login='").append(login).append('\'');
//        sb.append(", password='").append(password).append('\'');
//        sb.append(", surname='").append(surname).append('\'');
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", patronymic='").append(patronymic).append('\'');
//        sb.append(", roles=").append(roles);
//        sb.append(", phoneBookItems=").append(phoneBookItems);
//        sb.append('}');
//        return sb.toString();
//    }
}
