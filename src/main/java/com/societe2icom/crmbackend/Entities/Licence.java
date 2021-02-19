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

    public Licence(Long id, String key, Date ExpirationDate, Date creationDate) {
        Id = id;
        Key = key;
        this.ExpirationDate = ExpirationDate;
        CreationDate = creationDate;
    }

    public Licence(String key, Date ExpirationDate, Date creationDate) {
        Key = key;
        this.ExpirationDate = ExpirationDate;
        CreationDate = creationDate;
    }

    public Licence() {
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Licence licence = (Licence) o;
        return Id == licence.Id && Objects.equals(Key, licence.Key) && Objects.equals(ExpirationDate, licence.ExpirationDate) && Objects.equals(CreationDate, licence.CreationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Key, ExpirationDate, CreationDate);
    }
}
