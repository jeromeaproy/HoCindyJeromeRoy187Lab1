package com.lab.hocindyjeromeroy187lab1.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by 201495688 on 2014-06-10.
 */
public class RechercheActivity extends Activity{
    private Button btnRecherche;
    private ListView lvRecherche;
    private ArrayList<String> listMembresTrouves;
    private ArrayList<String> listInfoMembresTrouves;

    protected void onCreate(Bundle bundle)

    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_rechercher);


        listInfoMembresTrouves=new ArrayList<String>();
        listMembresTrouves = new ArrayList<String>();



        btnRecherche = (Button) findViewById(R.id.btnRetourRecherche);
        lvRecherche =(ListView) findViewById(R.id.listViewRecherche);
        trouverMembre();

        construireListe();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listInfoMembresTrouves);
       lvRecherche.setAdapter(arrayAdapter);


        btnRecherche.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
              Intent i = new Intent(RechercheActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void trouverMembre(){
        String nom = getIntent().getStringExtra(MainActivity.CLE_NOM);
        String prenom = getIntent().getStringExtra(MainActivity.CLE_PRENOM);
        String ligne = null;
        try {
            BufferedReader brRecherche = new BufferedReader(new InputStreamReader(openFileInput("membres2.txt")));
            try {
                while((ligne = brRecherche.readLine())!=null){
                    StringTokenizer stk = new StringTokenizer(ligne,";");

                    String nomChercher=stk.nextToken();
                    String prenomChercher=stk.nextToken();


                    if (nomChercher.equals(nom)&&prenomChercher.equals(prenom)){
                        listMembresTrouves.add(ligne);


                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void construireListe() {
        for (String membresTrouves : listMembresTrouves) {
           StringTokenizer stk = new StringTokenizer(membresTrouves, ";");
            listInfoMembresTrouves.add("nom :" + stk.nextToken());
            listInfoMembresTrouves.add("prenom :" + stk.nextToken());
            listInfoMembresTrouves.add("sexe :" + stk.nextToken());
            listInfoMembresTrouves.add("fonction :" + stk.nextToken());
            listInfoMembresTrouves.add("commentaire :" + stk.nextToken());
            listInfoMembresTrouves.add("***************************");

        }
    }

}
