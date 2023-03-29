package com.main.thymejquery.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String departement;
    private String updateBy;
    private String updateOn;

    public Student(Long id, String nom, String departement, String updateBy, String updateOn) {
        this.id = id;
        this.nom = nom;
        this.departement = departement;
        this.updateBy = updateBy;
        this.updateOn = updateOn;
    }

    public Student() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(String updateOn) {
        this.updateOn = updateOn;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", nom=" + nom + ", departement=" + departement + ", updateBy=" + updateBy
                + ", updateOn=" + updateOn + "]";
    }

}
