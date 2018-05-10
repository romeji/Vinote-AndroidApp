package com.sio2017.vinote;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView mListView ;
    ArrayList<Exposant> exposants =  new ArrayList<Exposant>();
    ArrayList<Invite> invites = new ArrayList<Invite>();
    ArrayList<Note> notes = new ArrayList<Note>();
    ArrayList<Vin> vins = new ArrayList<Vin>();
    VinAdapter vinAdapter ;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chargement);


        Thread t = new Thread(new Runnable() {
            public void run() {
                //exposants = Connexion.getJsonExposants(Connexion.getFromRest(1)) ;
                //invites = Connexion.getJsonInvites(Connexion.getFromRest(2)) ;
                notes = Connexion.getJsonNotes(Connexion.getFromRest(3)) ;
                vins = Connexion.getJsonVins(Connexion.getFromRest(4)) ;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setContentView(R.layout.activity_main);
                        mListView = (ListView) findViewById(R.id.listView);
                        vinAdapter = new VinAdapter(MainActivity.this, vins);
                        mListView.setAdapter(vinAdapter);
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            public void onItemClick(AdapterView parentView, View childView, int position, long id){
                                Vin vin = vinAdapter.getItem(position);
                                Intent intent = new Intent(MainActivity.this, VinActivity.class);
                                intent.putExtra("vin", vin);
                                startActivity(intent);
                            }
                            public void onNothingSelected(AdapterView parentView){

                            }
                        }

                        );
                    }
                });

            }
        });
        t.start();
    }

}
