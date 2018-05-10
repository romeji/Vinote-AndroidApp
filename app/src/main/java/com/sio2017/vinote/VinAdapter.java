package com.sio2017.vinote;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class VinAdapter extends ArrayAdapter<Vin> {

    Vin vin;
    Bitmap bitmap;

    public VinAdapter (Context context, ArrayList<Vin> vins){super(context, 0, vins);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_vins, parent, false) ;
        }

        VinViewHolder viewHolder = (VinViewHolder) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new VinViewHolder();
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);
            viewHolder.appellation = (TextView) convertView.findViewById(R.id.nom);
            viewHolder.domaine = (TextView) convertView.findViewById(R.id.time);
            viewHolder.rating = (RatingBar) convertView.findViewById(R.id.rating);
        }

        vin = getItem(position);
        bitmap = null;

        Thread t = new Thread(new Runnable() {
            public void run() {
                String url = vin.getPhoto();
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


        viewHolder.photo.setImageBitmap(bitmap);
        viewHolder.appellation.setText(vin.getAppellation() + " - " + vin.getAnnee());
        viewHolder.domaine.setText(vin.getExposant().getDomaine());
        viewHolder.rating.setRating((float)(vin.getScore()-50)/10);

        return convertView;

    }

    private class VinViewHolder{
        public ImageView photo;
        public TextView appellation;
        public TextView domaine;
        public RatingBar rating;
    }

}
