package com.societe2icom.crmbackend.Entities;


import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "user")
    private User User;


    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "company")
    private Company Company;

    @Column(name = "role")
    private String Role;

    public Employee() {
    }

    public Employee(long id, String role) {
        Id = id;
        Role = role;
    }

    public Employee(com.societe2icom.crmbackend.Entities.User user, com.societe2icom.crmbackend.Entities.Company company, String role) {
        User = user;
        Company = company;
        Role = role;
    }

    public Employee(long id, com.societe2icom.crmbackend.Entities.User user, com.societe2icom.crmbackend.Entities.Company company, String role) {
        Id = id;
        User = user;
        Company = company;
        Role = role;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public com.societe2icom.crmbackend.Entities.User getUser() {
        return User;
    }

    public void setUser(com.societe2icom.crmbackend.Entities.User user) {
        User = user;
    }

    public com.societe2icom.crmbackend.Entities.Company getCompany() {
        return Company;
    }

    public void setCompany(com.societe2icom.crmbackend.Entities.Company company) {
        Company = company;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "Id=" + Id +
                ", User=" + User +
                ", Company=" + Company +
                ", Role='" + Role + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
