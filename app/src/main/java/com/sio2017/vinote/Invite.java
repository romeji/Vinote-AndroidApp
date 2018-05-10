package com.sio2017.vinote;

import java.io.Serializable;

/**
 * Une instance de cette classe contient toutes les informations concernant un visiteur du salon.
 * @author Alexandre GOBY et Jérôme LOPES
 */

public class Invite implements Serializable {

    public int id;
    public String nom;
    public String prenom;
    public String email;
    public String photo;

    public Invite() {
    }

    public Invite(int id, String nom, String prenom, String email, String photo) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.photo = photo;
    }

    public int getId() {
        return id;
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

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Invite{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public String getTypeObjet () { return "invite" ; }
}
