package com.lab.hocindyjeromeroy187lab1.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ListerActivity extends Activity {
    GridView gv;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_lister);
        Button button=(Button)findViewById(R.id.btnRevenir);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListerActivity.this,MainActivity.class));
            }
        });

        gv= (GridView) findViewById(R.id.gvListeMembres);
        listerMembre();
    }


    public void listerMembre()

    {String temp=null;
        StringBuffer sb=new StringBuffer();
        try

        {
            InputStream is = openFileInput("membres2.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buf=new BufferedReader(isr);
            while ((temp=buf.readLine())!=null)
            {
                sb.append(temp);

            }

            String temp2=sb.toString();
            ArrayList<String> enTete=new ArrayList<String>();
            enTete.add("Nom");
            enTete.add("Prénom");
            enTete.add("Sexe");
            enTete.add("Fonction");
            enTete.add("Commentaire");
            String[] informations=temp2.split(";");
            enTete.addAll(Arrays.asList(informations));
            // String[]   informations={"bob","rgoer"};
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,enTete);

            gv.setAdapter(adapter);

        } catch (Throwable throwable)

        {
            Log.i("ListerActivité", throwable.getMessage());
        }
// Intent intent=new Intent(getApplicationContext(),com.example.myapplication.app.EnregistrerActivty.class);
        // intent.putExtra(CLE_LISTE_MEMEBRE,listeMembre);
        // startActivity(intent);
    }
}