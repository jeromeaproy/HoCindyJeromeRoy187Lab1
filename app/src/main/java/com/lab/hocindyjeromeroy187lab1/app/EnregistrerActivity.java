package com.lab.hocindyjeromeroy187lab1.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by jeromeroy on 2014-05-30.
 */
public class EnregistrerActivity extends Activity {
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
setContentView(R.layout.activity_enregistrer);

        Button bouton=(Button)findViewById(R.id.btnRevenir);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EnregistrerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
       String eol= System.getProperty("line.separator");
        String temp=null;


        try


        {






OutputStreamWriter out=new OutputStreamWriter(openFileOutput("membres2.txt", Context.MODE_APPEND));


            ArrayList<Membre> membres=getIntent().getParcelableArrayListExtra(MainActivity.CLE_LISTE_MEMEBRE);

            for(Parcelable p:membres)

            {

                   Membre membre = (Membre) p;
                   out.append(membre.getNom() + ";");
                out.append(membre.getPrenom() + ";");
                out.append(membre.getSexe() + ";");

                out.append(membre.getFonction() + ";");
                out.append(membre.getCommentaire()+eol);



                   Toast.makeText(getApplicationContext(), membre.getNom(), Toast.LENGTH_LONG).show();
               }


            out.close();

        }
catch(FileNotFoundException fexc)

    {
        Log.v("probeme","Fichier introuvable");

    }
        catch (IOException iox)
        {
            Log.v("probleme", "ssasa");
            iox.printStackTrace();

        }



    }
}
