package com.societe2icom.crmbackend.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "feature")
public class Feature {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String Name;

    @Column(name = "group_name")
    private String GroupName;

    @Column(name = "description")
    private String Description;

    @Column(name = "price")
    private float Price;

    public Feature() {
    }

    public Feature(String name, String groupName, String description, float price) {
        Name = name;
        GroupName = groupName;
        Description = description;
        Price = price;
    }

    public Feature(Long id, String name, String groupName, String description, float price) {
        Id = id;
        Name = name;
        GroupName = groupName;
        Description = description;
        Price = price;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", GroupName='" + GroupName + '\'' +
                ", Description='" + Description + '\'' +
                ", Price=" + Price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feature)) return false;
        Feature feature = (Feature) o;
        return Float.compare(feature.Price, Price) == 0 && Id.equals(feature.Id) && Name.equals(feature.Name) && GroupName.equals(feature.GroupName) && Description.equals(feature.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name, GroupName, Description, Price);
    }
}
