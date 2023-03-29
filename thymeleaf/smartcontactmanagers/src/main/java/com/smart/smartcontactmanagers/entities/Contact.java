package com.smart.smartcontactmanagers.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idC;
    private String nomC;
    private String petitNom;
    private String phone;
    private String work;
    @Column(length = 500000)
    private String description;
    private String emailC;
    private String imageC;
    
    @ManyToOne
    private User user;

    public Contact() {
        super();
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getPetitNom() {
        return petitNom;
    }

    public void setPetitNom(String petitNom) {
        this.petitNom = petitNom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailC() {
        return emailC;
    }

    public void setEmailC(String emailC) {
        this.emailC = emailC;
    }

    public String getImageC() {
        return imageC;
    }

    public void setImageC(String imageC) {
        this.imageC = imageC;
    }

    @Override
    public String toString() {
        return "Contact [idC=" + idC + ", nomC=" + nomC + ", petitNom=" + petitNom + ", phone=" + phone + ", work="
                + work + ", description=" + description + ", emailC=" + emailC + ", imageC=" + imageC + "]";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
