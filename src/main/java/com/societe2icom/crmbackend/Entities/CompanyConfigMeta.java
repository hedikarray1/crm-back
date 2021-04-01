package com.societe2icom.crmbackend.Entities;

import javax.persistence.*;

@Entity
@Table(name = "company_config_meta")
public class CompanyConfigMeta {


    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company", referencedColumnName = "id")
    private Company Company;

    @Column(name = "key")
    private String Key;
    @Column(name = "value")
    private String Value;


    public CompanyConfigMeta() {
    }

    public CompanyConfigMeta(Long id) {
        Id = id;
    }

    public CompanyConfigMeta(Long id, com.societe2icom.crmbackend.Entities.Company company, String key, String value) {
        Id = id;
        Company = company;
        Key = key;
        Value = value;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public com.societe2icom.crmbackend.Entities.Company getCompany() {
        return Company;
    }

    public void setCompany(com.societe2icom.crmbackend.Entities.Company company) {
        Company = company;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "CompanyConfigMeta{" +
                "Id=" + Id +
                ", Company=" + Company +
                ", Key='" + Key + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
