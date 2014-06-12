package com.lab.hocindyjeromeroy187lab1.app;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.graphics.Typeface;
import android.widget.TextView;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity implements TabListener,OnItemSelectedListener {
    private Spinner spinner1;
    private ArrayAdapter<String> dataAdapter;
    private List<String> listFonction;
    private ArrayList<Membre> listeMembre=new ArrayList<Membre>();
    public static final String  CLE_LISTE_MEMEBRE="com.lab.hocindyjeromeroy187lab1.app.LISTEMEMBRE";
    public static final String CLE_NOM="com.lab.com.lab.hocindyjeromeroy187lab1.app.NOM";
    public static final String CLE_PRENOM="com.lab.com.lab.hocindyjeromeroy187lab1.app.PRENOM";
    public static final String CLE_SEXE="com.lab.com.lab.hocindyjeromeroy187lab1.app.SEXE";
    private EditText etNom;
    private EditText etPrenom;
    private RadioGroup rgSexe;
    private RadioButton rbSexe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); etNom=(EditText)findViewById(R.id.etNom);


        etPrenom=(EditText)findViewById(R.id.etPrenom);
        addItemsOnSpinner1();

        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/Roboto-BoldCondensed.ttf");
        TextView tv=(TextView)findViewById(R.id.tvEnregis);
        tv.setTypeface(tf,Typeface.NORMAL);


        rgSexe = (RadioGroup)findViewById(R.id.rgSexe);

        Button envoyer=(Button)findViewById(R.id.btnEnvoyer);
        envoyer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                ajouterMembre();
            }
        });


        ActionBar bar = getActionBar();
        bar.setTitle("Des Menbres");
        bar.setSubtitle("Laboratoire 1");
        getWindow();

        //Creer Tabs

        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayShowTitleEnabled(true);
        bar.show();

        bar.addTab(bar.newTab().setIcon(R.drawable.icon_a_member).setTabListener(this));
        bar.addTab(bar.newTab().setIcon(R.drawable.icon_b_regis).setTabListener(this));
        bar.addTab(bar.newTab().setIcon(R.drawable.icon_c_lister).setTabListener(this));
        bar.addTab(bar.newTab().setIcon(R.drawable.icon_d_search).setTabListener(this));
        bar.addTab(bar.newTab().setIcon(R.drawable.icon_e_listchoice).setTabListener(this));
        bar.addTab(bar.newTab().setIcon(R.drawable.icon_f_listfemin).setTabListener(this));
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        int position=tab.getPosition();


        switch(position){
            case 0:
                break;

            case 1:
                Intent intent=new Intent(getApplicationContext(),com.lab.hocindyjeromeroy187lab1.app.EnregistrerActivity.class);
                intent.putExtra(CLE_LISTE_MEMEBRE,listeMembre);
                startActivity(intent);
                break;

            case 2:
                Intent intentListerActivity=new Intent(getApplicationContext(),com.lab.hocindyjeromeroy187lab1.app.ListerActivity.class);

                startActivity(intentListerActivity);

                break;

            case 3:
                String nom = etNom.getText().toString();
                String prenom = etPrenom.getText().toString();
                Intent intentRechercheActivity = new Intent(getApplicationContext(),com.lab.hocindyjeromeroy187lab1.app.RechercheActivity.class);
                intentRechercheActivity.putExtra(CLE_NOM, nom);
                intentRechercheActivity.putExtra(CLE_PRENOM, prenom);


                startActivity(intentRechercheActivity);
                break;
            case 4:

               int sexeChoisis = rgSexe.getCheckedRadioButtonId();

                Intent intentChoisirSexe = new Intent(getApplicationContext(),com.lab.hocindyjeromeroy187lab1.app.ListerSexe.class);
                switch (sexeChoisis){
                    case R.id.radioBtnHomme:
                        intentChoisirSexe.putExtra(CLE_SEXE,"Homme");
                        break;
                    case R.id.radioBtnFemme:
                        intentChoisirSexe.putExtra(CLE_SEXE,"Femme");
                    break;
                }
                startActivity(intentChoisirSexe);
                break;
            case 5:
                Intent intentListerSexeFemininActivity=new Intent(getApplicationContext(),com.lab.hocindyjeromeroy187lab1.app.ListerSexeFeminin.class);

                startActivity(intentListerSexeFemininActivity);

                break;






        }

    }
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {


    }
    public void addItemsOnSpinner1() {
        spinner1   = (Spinner) findViewById(R.id.spinner1);
        listFonction = new ArrayList<String>();
        listFonction.add("Enseignant");
        listFonction.add("Etudiant");
        listFonction.add("Ingénieur");
        listFonction.add("Retraité");
        listFonction.add("Autre");

        dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listFonction);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int choix,
                               long arg3) {
        switch (choix) {
            case 0:
                spinner1.setAdapter(dataAdapter);
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
   //Extra notes from Cindy:following methodes to create popup- we can insert the popup inside one of the tab navigation bar
   public void listerActivity() {
       Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("List Femme");
       builder.setMessage("Voulez vous continuez?");
       builder.setPositiveButton("ok", new OkOnClickListener());
       builder.setNegativeButton("cancel", new CancelOnClickListener());
       builder.show();
       Toast.makeText(this, "Option selectionnÈ", Toast.LENGTH_SHORT).show();
   }

    public void ajouterMembre()
    {
        Membre membre=new Membre();

        String nom=etNom.getText().toString();
        membre.setNom(nom);


        String prenom=etPrenom.getText().toString();
        membre.setPrenom(prenom);

        RadioGroup rgSexe=(RadioGroup)findViewById(R.id.rgSexe);
        int idSexe=rgSexe.getCheckedRadioButtonId();
        RadioButton rbSexe=(RadioButton)findViewById(idSexe);
        membre.setSexe(rbSexe.getText().toString());
        Spinner spinner=(Spinner)findViewById(R.id.spinner1);
        String fonction= String.valueOf(spinner.getSelectedItem());
        membre.setFonction(fonction);
        EditText etCommentaire=(EditText)findViewById(R.id.etCommentaires);
        String commentaire=etCommentaire.getText().toString();
        membre.setCommentaire(commentaire);
        listeMembre.add(membre);

    }

    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {

        }
    }

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            MainActivity.this.finish();
        }
    }



}
