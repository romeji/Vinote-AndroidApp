package com.sio2017.vinote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Une instance de cette classe contient toutes les informations concernant un visiteur du salon.
 * @author Alexandre GOBY et Jérôme LOPES
 */

public class Connexion {


    /**
     * Récupère tous les objets du type donné depuis le serveur REST
     * @param typeObjet
     * @return
     */
    static public String getFromRest (int typeObjet) {

        StringBuilder sb = new StringBuilder() ;
        //String URL = "http://192.168.2.130/vinote/web/app_dev.php/" ;
        String URL = "http://192.168.1.36/vinote/web/app_dev.php/" ;

        switch (typeObjet) {
            case 1 :
                URL += "exposants" ;
                break ;
            case 2 :
                URL += "invites" ;
                break ;
            case 3 :
                URL += "notes" ;
                break ;
            case 4 :
                URL += "vins" ;
                break ;

        }

        try{
            java.net.URL url = new java.net.URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString() ;
    }

    /**
     * Récupère un objet du type et avec l'id donnés depuis le serveur REST
     * @param typeObjet
     * @param id
     * @return
     */
    static public String getFromRest (String typeObjet, String id) {

        StringBuilder sb = new StringBuilder() ;
        //String URL = "http://192.168.2.130/vinote/web/app_dev.php/" ;
        String URL = "http://192.168.1.36/vinote/web/app_dev.php/" ;

        switch (typeObjet) {
            case "exposant" :
                URL += "exposant/" ;
                break ;
            case "invite" :
                URL += "invite/" ;
                break ;
            case "note" :
                URL += "note/" ;
                break ;
            case "note_vin" :
                URL += "notes/vin/" ;
                break ;
            case "vin" :
                URL += "vin/" ;
                break ;
        }
        URL += id ;

        try{
            java.net.URL url = new java.net.URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString() ;
    }

    /**
     * Convertit les objets JSON récupérés depuis le serveur REST en objets Exposant.
     * @param json
     * @return
     */
    static public ArrayList getJsonExposants (String json){

        ArrayList<Exposant> exposants = new ArrayList<Exposant>();

        try {
            JSONArray exposantsArray = new JSONArray(json);

            for (int i = 0; i < exposantsArray.length(); i++) {
                JSONObject exposantJson = exposantsArray.getJSONObject(i);
                final Exposant exposant = new Exposant(
                        exposantJson.getInt("id"),
                        exposantJson.getString("emplacement"),
                        exposantJson.getString("nom"),
                        exposantJson.getString("prenom"),
                        exposantJson.getString("email"),
                        exposantJson.getString("domaine"),
                        exposantJson.getString("description"),
                        exposantJson.getString("adresse"),
                        exposantJson.getInt("cp"),
                        exposantJson.getString("ville"),
                        exposantJson.getString("region"),
                        exposantJson.getString("siteweb"),
                        exposantJson.getString("photo")
                );

                exposants.add(exposant);

            }
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }

        return exposants ;
    }

    /**
     * Convertit les objets JSON récupérés depuis le serveur REST en objets Invite.
     * @param json
     * @return
     */
    static public ArrayList getJsonInvites (String json){

        ArrayList<Invite> invites = new ArrayList<Invite>();

        try {
            JSONArray invitesArray = new JSONArray(json);

            for (int i = 0; i < invitesArray.length(); i++) {
                JSONObject inviteJson = invitesArray.getJSONObject(i);
                final Invite invite = new Invite(
                        inviteJson.getInt("id"),
                        inviteJson.getString("nom"),
                        inviteJson.getString("prenom"),
                        inviteJson.getString("email"),
                        inviteJson.getString("photo")
                );

                invites.add(invite);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return invites ;
    }

    /**
     * Convertit les objets JSON récupérés depuis le serveur REST en objets Note.
     * @param json
     * @return
     */
    static public ArrayList getJsonNotes (String json){

        ArrayList<Note> notes = new ArrayList<Note>();

        try {
            JSONArray notesArray = new JSONArray(json);

            for (int i = 0; i < notesArray.length(); i++) {
                JSONObject noteJson = notesArray.getJSONObject(i);
                final Note note = new Note(
                        noteJson.getInt("id"),
                        noteJson.getInt("timestamp"),
                        noteJson.getString("commentaire"),
                        noteJson.getInt("note"),
                        new Invite(
                                noteJson.getJSONObject("invite").getInt("id"),
                                noteJson.getJSONObject("invite").getString("nom"),
                                noteJson.getJSONObject("invite").getString("prenom"),
                                noteJson.getJSONObject("invite").getString("email"),
                                noteJson.getJSONObject("invite").getString("photo")
                        ),
                        new Vin(
                                noteJson.getJSONObject("vin").getInt("id"),
                                new Exposant(
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getInt("id"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("emplacement"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("nom"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("prenom"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("email"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("domaine"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("description"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("adresse"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getInt("cp"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("ville"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("region"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("siteweb"),
                                        noteJson.getJSONObject("vin").getJSONObject("exposant").getString("photo")
                                ),
                                noteJson.getJSONObject("vin").getString("appellation"),
                                noteJson.getJSONObject("vin").getInt("annee"),
                                noteJson.getJSONObject("vin").getString("type"),
                                noteJson.getJSONObject("vin").getString("cepage"),
                                noteJson.getJSONObject("vin").getInt("alcool"),
                                noteJson.getJSONObject("vin").getInt("prix"),
                                noteJson.getJSONObject("vin").getInt("score"),
                                noteJson.getJSONObject("vin").getString("description"),
                                noteJson.getJSONObject("vin").getString("avis"),
                                noteJson.getJSONObject("vin").getString("photo")
                        )
                );

                notes.add(note);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return notes ;
    }

    /**
     * Convertit les objets JSON récupérés depuis le serveur REST en objets Vin.
     * @param json
     * @return
     */
    static public ArrayList getJsonVins (String json){

        ArrayList<Vin> vins = new ArrayList<Vin>();

        try {
            JSONArray vinsArray = new JSONArray(json);

            for (int i = 0; i < vinsArray.length(); i++) {
                JSONObject vinJson = vinsArray.getJSONObject(i);
                JSONObject exposantJson = vinJson.getJSONObject("exposant");

                Exposant exposant = new Exposant(
                        exposantJson.getInt("id"),
                        exposantJson.getString("emplacement"),
                        exposantJson.getString("nom"),
                        exposantJson.getString("prenom"),
                        exposantJson.getString("email"),
                        exposantJson.getString("domaine"),
                        exposantJson.getString("description"),
                        exposantJson.getString("adresse"),
                        exposantJson.getInt("cp"),
                        exposantJson.getString("ville"),
                        exposantJson.getString("region"),
                        exposantJson.getString("siteweb"),
                        exposantJson.getString("photo")
                );

                Vin vin = new Vin(
                        vinJson.getInt("id"),
                        exposant,
                        vinJson.getString("appellation"),
                        vinJson.getInt("annee"),
                        vinJson.getString("type"),
                        vinJson.getString("cepage"),
                        vinJson.getInt("alcool"),
                        vinJson.getInt("prix"),
                        vinJson.getInt("score"),
                        vinJson.getString("description"),
                        vinJson.getString("avis"),
                        vinJson.getString("photo")
                );

                vins.add(vin);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vins ;
    }

}
