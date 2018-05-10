package com.sio2017.vinote;

import java.io.Serializable;

/**
 * Une instance de cette classe contient toutes les informations concernant un commentaire écrit sur un vin exposé au salon.
 * @author Alexandre GOBY et Jérôme LOPES
 */

public class Note implements Serializable {

    private int id;
    private long timestamp;
    private String commentaire;
    private int note;
    private Invite invite;
    private Vin vin ;

    public Note() {
    }

    public Note(int id, long timestamp, String commentaire, int note, Invite invite, Vin vin) {
        this.id = id;
        this.timestamp = timestamp;
        this.commentaire = commentaire;
        this.note = note;
        this.invite = invite;
        this.vin = vin;
    }

    public int getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public int getNote() {
        return note;
    }

    public Invite getInvite() {
        return invite;
    }

    public Vin getVin() {
        return vin;
    }

    public String getTypeObjet () { return "note" ; }
}
