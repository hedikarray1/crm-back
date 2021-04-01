package com.societe2icom.crmbackend.Entities.WoocommerceEntities;

public class Embalage {

    private long ID;
    private String title;
    private double volume;
    private double height;
    private double ammount;
    private double length;
    private double price;
    private double min_ammount;
    private double width;
    private String description;
    private String type;
    private String reference;

    public Embalage() {
    }

    public Embalage(String title, double volume, double height, double ammount, double length, double price, double min_ammount, double width, String description, String type, String reference) {
        this.title = title;
        this.volume = volume;
        this.height = height;
        this.ammount = ammount;
        this.length = length;
        this.price = price;
        this.min_ammount = min_ammount;
        this.width = width;
        this.description = description;
        this.type = type;
        this.reference = reference;
    }

    public Embalage(long ID, String title, double volume, double height, double ammount, double length, double price, double min_ammount, double width, String description, String type, String reference) {
        this.ID = ID;
        this.title = title;
        this.volume = volume;
        this.height = height;
        this.ammount = ammount;
        this.length = length;
        this.price = price;
        this.min_ammount = min_ammount;
        this.width = width;
        this.description = description;
        this.type = type;
        this.reference = reference;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMin_ammount() {
        return min_ammount;
    }

    public void setMin_ammount(double min_ammount) {
        this.min_ammount = min_ammount;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Embalage{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", volume=" + volume +
                ", height=" + height +
                ", ammount=" + ammount +
                ", length=" + length +
                ", price=" + price +
                ", min_ammount=" + min_ammount +
                ", width=" + width +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
