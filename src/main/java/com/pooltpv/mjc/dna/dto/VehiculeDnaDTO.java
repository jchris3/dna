package com.pooltpv.mjc.dna.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDnaDTO {
    private String mark;
    private String modele;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date datePremiereMiseCirculation;
    private String immatriculation;
    private String chassis;
    private String categorie;
    private String capaciteCharge;
    private int puissanceFiscale;
    private int remorque;
    private String nombrePortes;
    private String immatriculationRemorque;
    private String sourceEnergie;
    private String nombreDePlaces;
    private String cylindre;
    private int doubleCommande;
    private int reponsabiliteCivile;
    private int utilitaire;
    private int typeEngin;
    private String poidsTotalAutoriseEnCharge;
}
