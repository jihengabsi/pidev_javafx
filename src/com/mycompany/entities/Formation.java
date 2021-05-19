/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Formation {
    private int id,Prix;
    private String Titre,category,Descritpion,Lieu,Date,dateFin;


    public Formation() {
    }

    public Formation( String Titre, String category, String Descritpion, String Lieu,int Prix, String Date, String dateFin) {
       
        this.Titre = Titre;
        this.category = category;
        this.Descritpion = Descritpion;
        this.Lieu = Lieu;
        this.Date = Date;
        this.dateFin = dateFin;
         this.Prix = Prix;
    }

       
    public Formation(int id, int Prix, String Titre, String category, String Descritpion, String Lieu, String Date, String dateFin) {
        this.id = id;
        this.Prix = Prix;
        this.Titre = Titre;
        this.category = category;
        this.Descritpion = Descritpion;
        this.Lieu = Lieu;
        this.Date = Date;
        this.dateFin = dateFin;
    }

    public Formation(String Titre, String Descritpion, String Date) {
        this.Titre = Titre;
        this.Descritpion = Descritpion;
        this.Date = Date;
    }

    public Formation(String Titre, String Descritpion) {
        this.Titre = Titre;
        this.Descritpion = Descritpion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescritpion() {
        return Descritpion;
    }

    public void setDescritpion(String Descritpion) {
        this.Descritpion = Descritpion;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

           
    
}
