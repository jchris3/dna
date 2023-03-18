package com.pooltpv.mjc.dna.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class Vehicule {
    @Id
    @Column(name = "U_CLE_P")
    private String id;

    @Column(name = "code_assure")
    private String codeAssure;

    @Column(name = "code_assureur")
    private int codeAssureur;

    private String marque;

    private String modele;

    @Column(name = "date_premiere_mise_circulation")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date datePremiereMiseCirculation;

    private String immatriculation;

    private String chassis;

    private String usage;

    @Column(name = "charge_utile")
    private String chargeUtile;

    @Column(name = "puissance_fiscale")
    private String puissanceFiscale;

    private int remorque;

    @Column(name = "nombre_portes")
    private String nombrePortes;

    @Column(name = "immatriculation_remorque")
    private String immatriculationRemorque;

    @Column(name = "source_energie")
    private String sourceEnergie;

    @Column(name = "nombre_de_places")
    private String nombreDePlaces;

    private String cylindree;

    @Column(name = "double_commande")
    private int doubleCommande;

    @Column(name = "reponsabilite_civile")
    private int reponsabiliteCivile;

    private int utilitaire;

    @Column(name = "type_engin")
    private int typeEngin;

    @Column(name = "poids_total_autorise_en_charge")
    private String poidsTotalAutoriseEnCharge;
}
