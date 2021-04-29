/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

/**
 *
 * @author monia
 */
public class Demande {
    
    private int id;
    private String nom;
    private String prenom;
    private int num;
    private String CV;
    private String comment;
    private int IDO;

    public int getIDO() {
        return IDO;
    }

    public void setIDO(int IDO) {
        this.IDO = IDO;
    }

    public Demande() {
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", num=" + num + ", CV=" + CV + ", comment=" + comment + ", IDO=" + IDO + '}';
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCV() {
        return CV;
    }

    public void setCV(String CV) {
        this.CV = CV;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
