package com.pooltpv.mjc.dna.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"code_client","nom","prenom","profession","DATE_NAISSANCE","ville",
        "rue","boite_postal","telephone","email","NUMERO_PERMIS","CATEGORIE_PERMIS","DATE_DELIVRANCE","DATE_EXPIRATION",
        "PERMIS_DELIVRE_PAR"})
public class AssureDnaDTO {
    private String codeClient;
    private String  nom;
    private String prenom;
    private String profession;
    private Date dateNaissance;
    private String ville;
    private String rue;
    private String boitePostal;
    private String telephone;
    private String email;
    private String numeroPermis;
    private String categoriePermis;
    private Date dateDelivrance;
    private Date dateExpiration;
    private String permisDelivrePar;

}
