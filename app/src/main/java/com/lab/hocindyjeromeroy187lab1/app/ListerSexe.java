package com.lab.hocindyjeromeroy187lab1.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ListerSexe extends ActionBarActivity {
    ArrayList<String> listeMembresTrouves=new ArrayList<String>();
    Spinner spListerSexe;

    Button btnRevenir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister_sexe);

        spListerSexe=(Spinner)findViewById(R.id.spListerSexe);
        btnRevenir=(Button)findViewById(R.id.btnRevenir);

        btnRevenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListerSexe.this,MainActivity.class);
                startActivity(intent);
            }
        });


        Toast.makeText(this,"sexe",Toast.LENGTH_LONG).show();
        trouverMembre();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listeMembresTrouves);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spListerSexe.setAdapter(adapter);


    }


    public void trouverMembre(){
        String sexeCherche= getIntent().getStringExtra(MainActivity.CLE_SEXE);

        String ligne = null;
        try {
            BufferedReader brRecherche = new BufferedReader(new InputStreamReader(openFileInput("membres2.txt")));
            try {
                while((ligne = brRecherche.readLine())!=null){




                      StringTokenizer stk = new StringTokenizer(ligne,";");

                    stk.nextToken();stk.nextToken();
                    String sexe=stk.nextToken();

                   if (sexe.equals(sexeCherche)){
                      listeMembresTrouves.add(ligne);

                   }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lister_sexe, menu);
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
}
