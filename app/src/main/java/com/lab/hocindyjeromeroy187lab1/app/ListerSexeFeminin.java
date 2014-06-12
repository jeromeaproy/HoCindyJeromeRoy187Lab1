package com.lab.hocindyjeromeroy187lab1.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class ListerSexeFeminin extends ActionBarActivity {
Spinner spnSexeFeminin;

    Button btnRevenir;
    ArrayList<String> listeMembresTrouves=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister_sexe_feminin);
        spnSexeFeminin=(Spinner)findViewById(R.id.spnListerSexeFeminin);

        btnRevenir=(Button)findViewById(R.id.btnRevenir);


        btnRevenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListerSexeFeminin.this,MainActivity.class);
                startActivity(intent);
            }
        });


        Toast.makeText(this,"sexe",Toast.LENGTH_LONG).show();
        trouverMembre();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listeMembresTrouves);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSexeFeminin.setAdapter(adapter);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lister_sexe_feminin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void trouverMembre(){
      //  String sexeCherche= getIntent().getStringExtra(MainActivity.CLE_SEXE);
        String sexeCherche= "Femme";
        String ligne = null;
        try {
            BufferedReader brRecherche = new BufferedReader(new InputStreamReader(openFileInput("membres2.txt")));
            try {
                while((ligne = brRecherche.readLine())!=null){


                    Toast.makeText(this, ligne, Toast.LENGTH_LONG).show();

                    StringTokenizer stk = new StringTokenizer(ligne,";");

                    stk.nextToken();stk.nextToken();
                    String sexe=stk.nextToken();

                    if (sexe.equals(sexeCherche)){
                        listeMembresTrouves.add(ligne);
                        Toast.makeText(this, "Trouve"+ligne, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }



}
