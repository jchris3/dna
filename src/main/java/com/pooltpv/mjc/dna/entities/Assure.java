package com.pooltpv.mjc.dna.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class Assure {
    @Id
    @Column(name = "U_CLE_P")
    private String id;

    @Column(name = "code_assure")
    private String codeAssure;

    @Column(name = "code_assureur")
    private  String codeAssureur;

    @Column(name = "qualite")
    private String qualite;

    @Column(name = "nom")
    private String  nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "profession")
    private String profession;

    @Column(name = "DATE_NAISSANCE")
    @JsonFormat(pattern="dd-MM-yyyy")
    private String dateNaissance;
    @Column(name = "code_type_piece")
    private String codeTypePiece;
    @Column(name = "num_contribuable")
    private String numContribuable;

    @Column(name = "ville")
    private String ville;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "NUMERO_PERMIS")
    private String numeroPermis;

    @Column(name = "CATEGORIE_PERMIS")
    private String categoriePermis;

    @Column(name = "DATE_DELIVRANCE")
    @JsonFormat(pattern="dd-MM-yyyy")
    private String dateDelivrance;

    @Column(name = "nom_prenom_conducteur")
    private String nomPrenomConducteur;

    @Column(name = "date_naissance_conducteur")
    @JsonFormat(pattern="dd-MM-yyyy")
    private String dateNaissanceConducteur;
}
