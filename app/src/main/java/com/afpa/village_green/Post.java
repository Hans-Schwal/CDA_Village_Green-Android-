package com.afpa.village_green;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
/*
* Notre API va nous générer un json à l'adresse "http://10.0.2.2:8080/index.php/api/liste/".
* Nous créons donc en conséquence une classe d'objets Post.
* */

public class Post {
    /*
    private int id_client;
    private String coefficient_client;
    private String nom_client;
    private String prenom_client;
    private String adresse_client;
    private String comp_adresse_client;
    private String code_postal_client;
    private String ville_client;
    private int statut_client;
    private String tel_client;
    private String mail_client;
    private String mdp_client;
    private double reduction_client;
    private String adresse_livraison_client;
    private String adresse_facturation_client;
    private int id_commercial;

    @SerializedName("numero_client")
    private String text;

    public int getId_client() {
        return id_client;
    }

    public String getCoefficient_client() {
        return coefficient_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public String getAdresse_client() {
        return adresse_client;
    }

    public String getComp_adresse_client() {
        return comp_adresse_client;
    }

    public String getCode_postal_client() {
        return code_postal_client;
    }

    public String getVille_client() {
        return ville_client;
    }

    public int getStatut_client() {
        return statut_client;
    }

    public String getTel_client() {
        return tel_client;
    }

    public String getMail_client() {
        return mail_client;
    }

    public String getMdp_client() {
        return mdp_client;
    }

    public double getReduction_client() {
        return reduction_client;
    }

    public String getAdresse_livraison_client() {
        return adresse_livraison_client;
    }

    public String getAdresse_facturation_client() {
        return adresse_facturation_client;
    }

    public int getId_commercial() {
        return id_commercial;
    }

    public String getText() {
        return text;
    }
    */
    private int id_produit;
    private String ref_produit;
    private String ref_fournisseur;
    private String lien_photo_produit;
    private String description_produit;
    private double prix_achat_produit;
    private double prix_vente_produit;
    private int id_sous_rubrique;
    private int id_fournisseur;

    @SerializedName("designation_produit")
    private String text;

    public int getId_produit() {
        return id_produit;
    }

    public String getRef_produit() {
        return ref_produit;
    }

    public String getRef_fournisseur() {
        return ref_fournisseur;
    }

    public String getLien_photo_produit() {
        return lien_photo_produit;
    }

    public String getDescription_produit() {
        return description_produit;
    }

    public double getPrix_achat_produit() {
        return prix_achat_produit;
    }

    public double getPrix_vente_produit() {
        return prix_vente_produit;
    }

    public int getId_sous_rubrique() {
        return id_sous_rubrique;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public String getText() {
        return text;
    }

    @NonNull
    @Override
    public String toString() {
        return text;
    }
}
