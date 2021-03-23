package com.societe2icom.crmbackend.Entities;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "licence_keys")
public class Licence {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "licence_key")
    private String Key;

    @Column(name="licence_expiration_date")
    private Date ExpirationDate;

    @Column(name = "licence_creation_date")
    private Date CreationDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company",referencedColumnName = "id")
    private Company Company;


    public Licence() {
    }

    public Licence(String key, Date expirationDate, Date creationDate) {
        Key = key;
        ExpirationDate = expirationDate;
        CreationDate = creationDate;
    }

    public Licence(String key, Date expirationDate, Date creationDate, com.societe2icom.crmbackend.Entities.Company company) {
        Key = key;
        ExpirationDate = expirationDate;
        CreationDate = creationDate;
        Company = company;
    }

    public Licence(Long id, String key, Date expirationDate, Date creationDate, com.societe2icom.crmbackend.Entities.Company company) {
        Id = id;
        Key = key;
        ExpirationDate = expirationDate;
        CreationDate = creationDate;
        Company = company;
    }

    public com.societe2icom.crmbackend.Entities.Company getCompany() {
        return Company;
    }

    public void setCompany(com.societe2icom.crmbackend.Entities.Company company) {
        Company = company;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Licence{" +
                "Id=" + Id +
                ", Key='" + Key + '\'' +
                ", ExpirationDate=" + ExpirationDate +
                ", CreationDate=" + CreationDate +
                ", Company=" + Company +
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
