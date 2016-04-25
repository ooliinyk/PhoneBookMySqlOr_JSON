package com.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.validator.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "phone_book_item")
public class PhoneBookItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "phone_book_item_id")
    private long id;


    @Size(min = 4)
    @NotEmpty
    @JsonProperty("surname")
    @Column(name = "surname")
    private String surname;

    @Size(min = 4)
    @NotEmpty
    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @Size(min = 4)
    @NotEmpty
    @NotNull
    @Column(name = "patronymic")
    @JsonProperty("patronymic")
    private String patronymic;


    @Phone
    @JsonProperty("mob_phone")
    @Column(name = "mob_phone")
    private String mobPhone;

    @JsonProperty("home_phone")
    @Column(name = "home_phone")
    private String homePhone;

    @JsonProperty("address")
    @Column(name = "address")
    private String address;

    @Email
    @JsonProperty("email")
    @Column(name = "email")
    private String email;

    @JsonProperty("users")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_to_phone_book_item",
            joinColumns = {@JoinColumn(name = "phone_book_item_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users = new ArrayList<User>();


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PhoneBookItem{");
        sb.append("address='").append(address).append('\'');
        sb.append(", id=").append(id);
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", mobPhone='").append(mobPhone).append('\'');
        sb.append(", homePhone='").append(homePhone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", users=").append(users);
        sb.append('}');
        return sb.toString();
    }
}
