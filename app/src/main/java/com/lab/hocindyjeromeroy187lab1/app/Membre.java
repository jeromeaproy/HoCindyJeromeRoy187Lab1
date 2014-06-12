package com.lab.hocindyjeromeroy187lab1.app;

import android.os.Parcelable;

import android.os.Parcel;

/**
 * Created by jeromeroy on 2014-05-30.     
 B
 */
public class Membre implements Parcelable{

    private String nom;
    private String prenom;

    private enum Sexe{Homme,Femme};
    private Sexe sexe;
    private enum Fonction {
        Enseignant,Etudiant,Ingénieur,Retraité,Autre



    };
    private Fonction fonction;
    private String commentaire;

    public Membre(Parcel in)
    {
        nom=in.readString();
        prenom=in.readString();
        sexe=Sexe.valueOf(in.readString());
        fonction=Fonction.valueOf(in.readString());
        commentaire=in.readString();

    }
    public Membre()
    {
        super();
    }

    public void setFonction(String fonction)
    {
        this.fonction=Fonction.valueOf(fonction);
    }


    public String getSexe()
    {
        return sexe.name();

    }

    public String getFonction()
    {
        return fonction.name();
    }
    public void setSexe(String sexe)
    {
        this.sexe=  Sexe.valueOf(sexe);

    }



    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    public int describeContents()

    {
        return 0;

    }
    public void writeToParcel(Parcel parcel,int flag)

    {
        parcel.writeString(nom);

        parcel.writeString(prenom);
        parcel.writeString(sexe.name());
        parcel.writeString(fonction.name());
        parcel.writeString(commentaire);





    }

    public  static final Parcelable.Creator<Membre> CREATOR=new Parcelable.Creator<Membre>() {

        public Membre createFromParcel(Parcel in)
        {
            return new Membre(in);

        }

        @Override
        public Membre[] newArray(int i) {
            return new Membre[i];
        }
    };


}
