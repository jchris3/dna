package com.pooltpv.mjc.dna.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class AssureDna {
    @Id
    @Column(name = "U_CLE_P")
    private String id;

    @Column(name = "code_assure")
    private String codeClient;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "profession")
    private String profession;
    @Column(name = "DATE_NAISSANCE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateNaissance;
    @Column(name = "ville")
    private String ville;
    @Column(name = "rue")
    private String rue;
    @Column(name = "code_postal")
    private String codePostal;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "email")
    private String email;

    @Column(name = "NUMERO_PERMIS")
    private String numeroPermis;

    @Column(name = "CATEGORIE_PERMIS")
    private String categorisPermis;

    @Column(name = "DATE_DELIVRANCE")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateDelivrance;

    @Column(name = "DATE_EXPIRATION")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateExpiration;
    @Column(name = "permis_delivre_par")
    private String permisDelivrePar;
}
