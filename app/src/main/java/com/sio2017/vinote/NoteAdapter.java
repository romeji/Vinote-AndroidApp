package com.sio2017.vinote;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class NoteAdapter extends ArrayAdapter<Note> {

    Note note;
    Bitmap bitmap;

    public NoteAdapter(Context context, ArrayList<Note> notes){super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_notes, parent, false) ;
        }

        NoteViewHolder viewHolder = (NoteViewHolder) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new NoteViewHolder();
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);
            viewHolder.nom = (TextView) convertView.findViewById(R.id.nom);
            viewHolder.commentaire = (TextView) convertView.findViewById(R.id.commentaire);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.note = (RatingBar) convertView.findViewById(R.id.note);
        }

        note = getItem(position);
        bitmap = null;

        Thread t = new Thread(new Runnable() {
            public void run() {
                String url = note.getInvite().getPhoto();
                try (InputStream is = new URL(url).openStream() ) {
                    bitmap = BitmapFactory.decodeStream( is );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        Date date = new java.sql.Date(note.getTimestamp());

        viewHolder.photo.setImageBitmap(bitmap);
        viewHolder.nom.setText(note.getInvite().getPrenom() + " " + note.getInvite().getNom());
        viewHolder.commentaire.setText(note.getCommentaire());
        viewHolder.time.setText(date.toString());
        viewHolder.note.setRating((float)(note.getNote()));

        return convertView;

    }

    private class NoteViewHolder{
        public ImageView photo;
        public TextView nom;
        public TextView commentaire;
        public TextView time;
        public RatingBar note;
    }

}
