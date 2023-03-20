package com.pooltpv.mjc.dna.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"code_assure", "code_assureur", "qualite"
        ,"nom","prenom","profession","DATE_NAISSANCE","num_contribuable","ville",
        "rue","boite_postal","telephone","email","NUMERO_PERMIS","CATEGORIE_PERMIS","DATE_DELIVRANCE","DATE_EXPIRATION",
        "PERMIS_DELIVRE_PAR","nom_prenom_conducteur","date_naissance_conducteur"})
public class AssureDTO {
    private String codeAssure;
    private  int codeAssureur;
    private String qualite;
    private String  nom;
    private String prenom;
    private String profession;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateNaissance;
    private String codeTypePiece;
    private String numContribuable;
    private String ville;
    private String rue;
    private String boitePostal;
    private String telephone;
    private String email;
    private String numeroPermis;
    private String categoriePermis;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateDelivrance;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateExpiration;
    private String permisDelivrePar;
    private String nomPrenomConducteur;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateNaissanceConducteur;
}
