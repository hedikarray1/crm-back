package com.societe2icom.crmbackend.Entities.WoocommerceEntities;

public class Etiquette {

    private long ID;
    private String reference;
    private double ammount;

    public Etiquette() {
    }

    public Etiquette(String reference, double ammount) {
        this.reference = reference;
        this.ammount = ammount;
    }

    public Etiquette(long ID, String reference, double ammount) {
        this.ID = ID;
        this.reference = reference;
        this.ammount = ammount;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    @Override
    public String toString() {
        return "Etiquette{" +
                "ID=" + ID +
                ", reference='" + reference + '\'' +
                ", ammount=" + ammount +
                '}';
    }
}
