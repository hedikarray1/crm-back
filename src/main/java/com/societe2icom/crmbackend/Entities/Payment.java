package com.societe2icom.crmbackend.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "payments")
public class Payment {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "date")
    private Date Date;

    @Column(name = "currency")
    private String Currency;

    @Column(name = "amount")
    private float Amount;

    @Column(name = "payment_method")
    private String PaymentMethod;

    @ManyToOne
    @JoinColumn(name="company", nullable=false)
    private Company company;


    public Payment() {
    }

    public Payment(java.util.Date date, String currency, float amount, String paymentMethod) {
        Date = date;
        Currency = currency;
        Amount = amount;
        PaymentMethod = paymentMethod;
    }

    public Payment(java.util.Date date, String currency, float amount, String paymentMethod, Company company) {
        Date = date;
        Currency = currency;
        Amount = amount;
        PaymentMethod = paymentMethod;
        this.company = company;
    }

    public Payment(int id, java.util.Date date, String currency, float amount, String paymentMethod, Company company) {
        Id = id;
        Date = date;
        Currency = currency;
        Amount = amount;
        PaymentMethod = paymentMethod;
        this.company = company;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
