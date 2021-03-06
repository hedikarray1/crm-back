package com.societe2icom.crmbackend.Entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name ="firstname")
    private String Firstname;

    @Column(name = "lastname")
    private String Lastname;


    @Column(name = "email", unique = true)
    private String Email;

    @Column(name = "password")
    private String Password;

    @Column(name = "birthdate")
    private Date Birthdate;

    @Column(name = "phone")
    private String Phone;

    @Column(name = "role")
    private String Role;

    @Column(name = "picture")
    private String Picture;

    @Column(name = "token")
    private String Token;



    public User(){

    }

    public User(String email, String password) {
        Email = email;
        Password = password;
    }

    public User(String firstname, String lastname, String email, String password, Date birthdate, String phone, String role, String picture, String token) {
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        Password = password;
        Birthdate = birthdate;
        Phone = phone;
        Role = role;
        Picture = picture;
        Token = token;
    }

    public User(String firstname, String lastname, String email, String password, Date birthdate, String phone, String role, String picture, String token, Company company) {
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        Password = password;
        Birthdate = birthdate;
        Phone = phone;
        Role = role;
        Picture = picture;
        Token = token;

    }

    public User(Long id, String firstname, String lastname, String email, String password, Date birthdate, String phone, String role, String picture, String token) {
        Id = id;
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        Password = password;
        Birthdate = birthdate;
        Phone = phone;
        Role = role;
        Picture = picture;
        Token = token;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Date getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(Date birthdate) {
        Birthdate = birthdate;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
