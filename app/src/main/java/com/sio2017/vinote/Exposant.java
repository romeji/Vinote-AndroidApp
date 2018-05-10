package com.sio2017.vinote;

import java.io.Serializable;

/**
 * Une instance de cette classe contient toutes les informations concernant un exposant du salon.
 * @author Alexandre GOBY et Jérôme LOPES
 */

public class Exposant implements Serializable {

    private int id;
    private String emplacement;
    private String nom;
    private String prenom;
    private String email;
    private String domaine;
    private String description ;
    private String adresse;
    private int cp;
    private String ville;
    private String region;
    private String siteweb;
    private String photo;

    public Exposant() {
    }

    public Exposant(int id, String emplacement, String nom, String prenom, String email, String domaine, String description, String adresse, int cp, String ville, String region, String siteweb, String photo) {
        this.id = id;
        this.emplacement = emplacement;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.domaine = domaine;
        this.description = description;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.region = region;
        this.siteweb = siteweb;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getDescription() {
        return description;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCp() {
        return cp;
    }

    public String getVille() {
        return ville;
    }

    public String getRegion() {
        return region;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Exposant{" +
                "id=" + id +
                ", emplacement='" + emplacement + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", domaine='" + domaine + '\'' +
                ", description='" + description + '\'' +
                ", adresse='" + adresse + '\'' +
                ", cp=" + cp +
                ", ville='" + ville + '\'' +
                ", region='" + region + '\'' +
                ", siteweb='" + siteweb + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public String getTypeObjet () { return "exposant" ; }
}
