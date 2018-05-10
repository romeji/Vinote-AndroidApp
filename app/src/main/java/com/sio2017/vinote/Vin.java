package com.sio2017.vinote;

import java.io.Serializable;

/**
 * Une instance de cette classe contient toutes les informations concernant un vin exposé au salon.
 * @author Alexandre GOBY et Jérôme LOPES
 */

public class Vin implements Serializable{

    private int id;
    private Exposant exposant;
    private String appellation;
    private int annee;
    private String type;
    private String cepage;
    private int alcool;
    private int prix;
    private int score;
    private String description;
    private String avis;
    private String photo;


    public Vin() {
    }

    public Vin(int id, Exposant exposant, String appellation, int annee, String type, String cepage, int alcool, int prix, int score, String description, String avis, String photo) {
        this.id = id;
        this.exposant = exposant;
        this.appellation = appellation;
        this.annee = annee;
        this.type = type;
        this.cepage = cepage;
        this.alcool = alcool;
        this.prix = prix;
        this.score = score;
        this.description = description;
        this.avis = avis;
        this.photo = photo;
    }


    /**
     * Renvoie l'identifiant du vin.
     * @return
     */
    public int getId() {
        return id;
    }

    public Exposant getExposant() {
        return exposant;
    }

    public String getAppellation() {
        return appellation;
    }

    public int getAnnee() {
        return annee;
    }

    public String getType() {
        return type;
    }

    public String getCepage() {
        return cepage;
    }

    public int getAlcool() {
        return alcool;
    }

    public int getPrix() {
        return prix;
    }

    public int getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }

    public String getAvis() {
        return avis;
    }

    public String getPhoto() {
        return photo;
    }


    @Override
    public String toString() {
        return "Vin{" +
                "id=" + id +
                ", exposant=" + exposant +
                ", appellation='" + appellation + '\'' +
                ", annee=" + annee +
                ", type='" + type + '\'' +
                ", cepage='" + cepage + '\'' +
                ", alcool=" + alcool +
                ", prix=" + prix +
                ", score=" + score +
                ", description='" + description + '\'' +
                ", commentaire='" + avis + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public String getTypeObjet () { return "vin" ; }
}
