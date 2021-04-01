package com.societe2icom.crmbackend.Entities;


import com.fasterxml.jackson.annotation.JsonView;
import com.societe2icom.crmbackend.Configuration.Views;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "companies")
public class Company {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "title")
    private  String Title;




    @Column(name = "logo")

    private String Logo;

    @Column(name = "cover")
    @JsonView(Views.Public.class)

    private String Cover;

    @Column(name="description")
    private String Description;

    @Column(name="type")
    private String Type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "licence",referencedColumnName = "id")
    private Licence Licence;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Payment> Payments;

    public Company() {
    }

    public Company(Long id, String title, String logo, String cover, String description, String type, com.societe2icom.crmbackend.Entities.Licence licence, List<Payment> payments) {
        Id = id;
        Title = title;

        Logo = logo;
        Cover = cover;
        Description = description;
        Type = type;
        Licence = licence;
        Payments = payments;
    }

    public Company(String title, String logo, String cover, String description, String type, com.societe2icom.crmbackend.Entities.Licence licence, List<Payment> payments) {
        Title = title;

        Logo = logo;
        Cover = cover;
        Description = description;
        Type = type;
        Licence = licence;
        Payments = payments;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String cover) {
        Cover = cover;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public com.societe2icom.crmbackend.Entities.Licence getLicence() {
        return Licence;
    }

    public void setLicence(com.societe2icom.crmbackend.Entities.Licence licence) {
        Licence = licence;
    }

    public List<Payment> getPayments() {
        return Payments;
    }

    public void setPayments(List<Payment> payments) {
        Payments = payments;
    }


}
