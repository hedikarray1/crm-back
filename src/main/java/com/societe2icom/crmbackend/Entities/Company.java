package com.societe2icom.crmbackend.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//defying entities
@Entity
@Table(name = "companies")
public class Company {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "title")
    private  String Title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin", referencedColumnName = "id")
    private User Admin;

    @OneToMany(cascade =CascadeType.ALL ,mappedBy = "company")
    private List<User> Employees;

    @Column(name = "logo")
    private String Logo;

    @Column(name="description")
    private String Description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "licence",referencedColumnName = "id")
    private Licence Licence;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Payment> Payments;

    public Company() {
    }

    public Company(Long id, String title, String logo, String description) {
        Id = id;
        Title = title;
        Logo = logo;
        Description = description;
    }

    public Company(String title, User admin, String logo, String description, com.societe2icom.crmbackend.Entities.Licence licence) {
        Title = title;
        Admin = admin;
        Logo = logo;
        Description = description;
        Licence = licence;
    }

    public Company(Long id, String title, User admin, String logo, String description, com.societe2icom.crmbackend.Entities.Licence licence) {
        Id = id;
        Title = title;
        Admin = admin;
        Logo = logo;
        Description = description;
        Licence = licence;
    }

    public Company(Long id, String title, User admin, List<User> employees, String logo, String description, com.societe2icom.crmbackend.Entities.Licence licence, List<Payment> payments) {
        Id = id;
        Title = title;
        Admin = admin;
        Employees = employees;
        Logo = logo;
        Description = description;
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

    public User getAdmin() {
        return Admin;
    }

    public void setAdmin(User admin) {
        Admin = admin;
    }

    public List<User> getEmployees() {
        return Employees;
    }

    public void setEmployees(List<User> employees) {
        Employees = employees;
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

    @Override
    public String toString() {
        return "Company{" +
                "Id=" + Id +
                ", Title='" + Title + '\'' +
                ", Admin=" + Admin +
                ", Employees=" + Employees +
                ", Logo='" + Logo + '\'' +
                ", Description='" + Description + '\'' +
                ", Licence=" + Licence +
                ", Payments=" + Payments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Id == company.Id && Objects.equals(Title, company.Title) && Objects.equals(Admin, company.Admin) && Objects.equals(Employees, company.Employees) && Objects.equals(Logo, company.Logo) && Objects.equals(Description, company.Description) && Objects.equals(Licence, company.Licence) && Objects.equals(Payments, company.Payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Title, Admin, Employees, Logo, Description, Licence, Payments);
    }
}
