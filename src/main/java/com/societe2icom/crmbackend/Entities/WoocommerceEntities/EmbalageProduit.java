package com.societe2icom.crmbackend.Entities.WoocommerceEntities;

public class EmbalageProduit {
    private long ID;
    private long id_produit;
    private long id_embalage;

    public EmbalageProduit() {
    }

    public EmbalageProduit(long id_produit, long id_embalage) {
        this.id_produit = id_produit;
        this.id_embalage = id_embalage;
    }

    public EmbalageProduit(long ID, long id_produit, long id_embalage) {
        this.ID = ID;
        this.id_produit = id_produit;
        this.id_embalage = id_embalage;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getId_produit() {
        return id_produit;
    }

    public void setId_produit(long id_produit) {
        this.id_produit = id_produit;
    }

    public long getId_embalage() {
        return id_embalage;
    }

    public void setId_embalage(long id_embalage) {
        this.id_embalage = id_embalage;
    }

    @Override
    public String toString() {
        return "EmbalageProduit{" +
                "ID=" + ID +
                ", id_produit=" + id_produit +
                ", id_embalage=" + id_embalage +
                '}';
    }
}
